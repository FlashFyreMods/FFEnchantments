package com.flashfyre.ffenchants.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ArrowOriginalMotionMagnitudeCapability implements IArrowOriginalMotionMagnitude, IStorage<IArrowOriginalMotionMagnitude>, ICapabilitySerializable<INBT> {
	
	@CapabilityInject(IArrowOriginalMotionMagnitude.class)
	public static final Capability<IArrowOriginalMotionMagnitude> ARROW_MOTION_CAP = null;
	
	private LazyOptional<IArrowOriginalMotionMagnitude> instance = LazyOptional.of(() -> this);
	
	private double magnitude = 0.0F;

	@Override
	public double getMagnitude() {
		return this.magnitude;
	}

	@Override
	public void setMagnitude(double motion) {
		this.magnitude = motion;
	}
	
	@Override
	public INBT writeNBT(Capability<IArrowOriginalMotionMagnitude> capability, IArrowOriginalMotionMagnitude instance, Direction side) 
	{
		CompoundNBT tag = new CompoundNBT();
		tag.putDouble("originalArrowMotion", instance.getMagnitude());
		return tag;
	}

	@Override
	public void readNBT(Capability<IArrowOriginalMotionMagnitude> capability, IArrowOriginalMotionMagnitude instance, Direction side, INBT nbt)
	{
		CompoundNBT tag = (CompoundNBT) nbt;
		instance.setMagnitude(tag.getDouble("originalArrowMotion"));
	}
	
	@Override
	public INBT serializeNBT() {
		return ARROW_MOTION_CAP.getStorage().writeNBT(ARROW_MOTION_CAP, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		ARROW_MOTION_CAP.getStorage().readNBT(ARROW_MOTION_CAP, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == ARROW_MOTION_CAP ? instance.cast() : LazyOptional.empty();
	}

}
