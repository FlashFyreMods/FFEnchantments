package com.flashfyre.ffenchants.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class MaelstromAppliedStorage implements IStorage<IMaelstromApplied> {
	
	@Override
	public INBT writeNBT(Capability<IMaelstromApplied> capability, IMaelstromApplied instance, Direction side) 
	{
		CompoundNBT tag = new CompoundNBT();
		tag.putBoolean("maelstromApplied", instance.maelstromApplied());
		return tag;
	}

	@Override
	public void readNBT(Capability<IMaelstromApplied> capability, IMaelstromApplied instance, Direction side, INBT nbt)
	{
		CompoundNBT tag = (CompoundNBT) nbt;
		instance.setMaelstromApplied(tag.getBoolean("maelstromApplied"));
	}

}
