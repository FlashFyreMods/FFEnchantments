package com.flashfyre.ffenchants.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class MaelstromAppliedProvider implements ICapabilitySerializable<INBT> {
	
	@CapabilityInject(IMaelstromApplied.class)
	public static final Capability<IMaelstromApplied> MAELSTROM_APPLIED_CAPABILITY = null;
	
	private LazyOptional<IMaelstromApplied> instance = LazyOptional.of(MAELSTROM_APPLIED_CAPABILITY::getDefaultInstance);
	
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
