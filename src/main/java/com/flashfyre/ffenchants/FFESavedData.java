package com.flashfyre.ffenchants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.saveddata.SavedData;

public class FFESavedData extends SavedData {
	
	private static final String DATA_NAME = FFE.MOD_ID + "_specialprojectiles";
	
	private List<UUID> infernoArrows = new ArrayList<UUID>(); // A list of all arrow entity uuids that are enchanted with inferno.
	private List<UUID> maelstromTridents = new ArrayList<UUID>(); // A list of all trident entity uuids that are enchanted with maelstrom.

	public FFESavedData() {
	}
	
	public FFESavedData(CompoundTag compoundTag) {
		ListTag infernoNbtList = compoundTag.getList("InfernoArrows", Tag.TAG_INT_ARRAY);
		
		infernoArrows.clear();
		for (int i = 0; i < infernoNbtList.size(); ++i) {
           infernoArrows.add(NbtUtils.loadUUID(infernoNbtList.get(i)));
        }
		
		ListTag maelstromNbtList = compoundTag.getList("MaelstromTridents", Tag.TAG_INT_ARRAY);
		maelstromTridents.clear();
		for (int i = 0; i < maelstromNbtList.size(); ++i) {
			maelstromTridents.add(NbtUtils.loadUUID(maelstromNbtList.get(i)));
        }
	}
	
	public static FFESavedData getOrCreate(ServerLevel level) {
		return level.getDataStorage().computeIfAbsent(FFESavedData::new, FFESavedData::new, DATA_NAME);
	}
	
	public static FFESavedData create() {
		return new FFESavedData();
	}

	@Override
	public CompoundTag save(CompoundTag nbtCompound) {
		ListTag infernoNbtList = new ListTag();
		for(UUID id : infernoArrows) {
			infernoNbtList.add(NbtUtils.createUUID(id));
		}
		nbtCompound.put("InfernoArrows", infernoNbtList);
		
		ListTag maelstromNbtList = new ListTag();
		for(UUID id : maelstromTridents) {
			maelstromNbtList.add(NbtUtils.createUUID(id));
		}
		nbtCompound.put("MaelstromTridents", infernoNbtList);
		
		return nbtCompound;
	}
	
	public List<UUID> getInfernoUUIDs() {
		return Collections.unmodifiableList(this.infernoArrows);
	}
	
	public void addInfernoArrowUUID(AbstractArrow arrow) {
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
	
	public void addMaelstromTridentUUID(ThrownTrident trident) {
		this.maelstromTridents.add(trident.getUUID());
		this.setDirty();
	}
	
	public void removeMaelstromTridentUUIDS(List<UUID> tridentsToRemove) {
		maelstromTridents.removeAll(tridentsToRemove);
		tridentsToRemove.clear();
		this.setDirty();
	}
}
