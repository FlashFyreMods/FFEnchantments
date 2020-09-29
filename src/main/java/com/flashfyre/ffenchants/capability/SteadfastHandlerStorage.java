package com.flashfyre.ffenchants.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class SteadfastHandlerStorage implements IStorage<ISteadfastHandler> 
{

	@Override
	public INBT writeNBT(Capability<ISteadfastHandler> capability, ISteadfastHandler instance, Direction side) 
	{
		CompoundNBT tag = new CompoundNBT();
		tag.putBoolean("steadfast_handled", instance.getSteadfastHandled());
		return tag;
	}

	@Override
	public void readNBT(Capability<ISteadfastHandler> capability, ISteadfastHandler instance, Direction side, INBT nbt)
	{
		CompoundNBT tag = (CompoundNBT) nbt;
		instance.setSteadfastHandled(tag.getBoolean("steadfast_handled"));
		
	}
}