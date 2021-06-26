package com.flashfyre.ffenchants.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class MaelstromTridentReturningCapability implements IMaelstromApplied, IStorage<IMaelstromApplied>, ICapabilitySerializable<INBT> {
	
	@CapabilityInject(IMaelstromApplied.class)
	public static final Capability<IMaelstromApplied> MAELSTROM_APPLIED_CAPABILITY = null;
	
	private LazyOptional<IMaelstromApplied> instance = LazyOptional.of(() -> this);
	
	private boolean maelstromApplied = false;

	@Override
	public boolean isMaelstromApplied() {
		return this.maelstromApplied;
	}

	@Override
	public void setMaelstromApplied(boolean maelstromApplied) {
		this.maelstromApplied = maelstromApplied;
	}
	
	@Override
	public INBT writeNBT(Capability<IMaelstromApplied> capability, IMaelstromApplied instance, Direction side) 
	{
		CompoundNBT tag = new CompoundNBT();
		tag.putBoolean("maelstromApplied", instance.isMaelstromApplied());
		return tag;
	}

	@Override
	public void readNBT(Capability<IMaelstromApplied> capability, IMaelstromApplied instance, Direction side, INBT nbt)
	{
		CompoundNBT tag = (CompoundNBT) nbt;
		instance.setMaelstromApplied(tag.getBoolean("maelstromApplied"));
	}
	
	@Override
	public INBT serializeNBT() {
		return MAELSTROM_APPLIED_CAPABILITY.getStorage().writeNBT(MAELSTROM_APPLIED_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		MAELSTROM_APPLIED_CAPABILITY.getStorage().readNBT(MAELSTROM_APPLIED_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == MAELSTROM_APPLIED_CAPABILITY ? instance.cast() : LazyOptional.empty();
	}
}
