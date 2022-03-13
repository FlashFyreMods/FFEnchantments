package com.flashfyre.ffenchants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

public class FFEWorldSavedData extends WorldSavedData {
	
	private static final String DATA_NAME = FFE.MOD_ID + "_specialprojectiles";
	
	private List<UUID> infernoArrows = new ArrayList<UUID>(); // A list of all arrow entity uuids that are enchanted with inferno.
	private List<UUID> maelstromTridents = new ArrayList<UUID>(); // A list of all trident entity uuids that are enchanted with maelstrom.

	public FFEWorldSavedData() {
		super(DATA_NAME);
	}

	@Override
	public void load(CompoundNBT nbtCompound) {
		ListNBT infernoNbtList = nbtCompound.getList("InfernoArrows", Constants.NBT.TAG_INT_ARRAY);
		infernoArrows.clear();
		for (int i = 0; i < infernoNbtList.size(); ++i) {
           infernoArrows.add(NBTUtil.loadUUID(infernoNbtList.get(i)));
        }
		
		ListNBT maelstromNbtList = nbtCompound.getList("MaelstromTridents", Constants.NBT.TAG_INT_ARRAY);
		maelstromTridents.clear();
		for (int i = 0; i < maelstromNbtList.size(); ++i) {
			maelstromTridents.add(NBTUtil.loadUUID(maelstromNbtList.get(i)));
        }
	}

	@Override
	public CompoundNBT save(CompoundNBT nbtCompound) {
		ListNBT infernoNbtList = new ListNBT();
		for(UUID id : infernoArrows) {
			infernoNbtList.add(NBTUtil.createUUID(id));
		}
		nbtCompound.put("InfernoArrows", infernoNbtList);
		
		ListNBT maelstromNbtList = new ListNBT();
		for(UUID id : maelstromTridents) {
			maelstromNbtList.add(NBTUtil.createUUID(id));
		}
		nbtCompound.put("MaelstromTridents", infernoNbtList);
		
		return nbtCompound;
	}
	
	public List<UUID> getInfernoUUIDs() {
		return Collections.unmodifiableList(this.infernoArrows);
	}
	
	public void addInfernoArrowUUID(AbstractArrowEntity arrow) {
		this.infernoArrows.add(arrow.getUUID());
		this.setDirty();
	}
	
	public void removeInfernoArrowUUIDS(List<UUID> arrowsToRemove) {
		infernoArrows.removeAll(arrowsToRemove);
		arrowsToRemove.clear();
		this.setDirty();
	}
	
	public List<UUID> getMaelstromUUIDs() {
		return Collections.unmodifiableList(this.maelstromTridents);
	}
	
	public void addMaelstromTridentUUID(TridentEntity trident) {
		this.maelstromTridents.add(trident.getUUID());
		this.setDirty();
	}
	
	public void removeMaelstromTridentUUIDS(List<UUID> tridentsToRemove) {
		maelstromTridents.removeAll(tridentsToRemove);
		tridentsToRemove.clear();
		this.setDirty();
	}
	
	public static FFEWorldSavedData get(ServerWorld level) {
        return level.getDataStorage().computeIfAbsent(FFEWorldSavedData::new, DATA_NAME);
    }
}
