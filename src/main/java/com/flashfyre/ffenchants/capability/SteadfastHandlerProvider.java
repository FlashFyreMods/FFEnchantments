package com.flashfyre.ffenchants.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class SteadfastHandlerProvider implements ICapabilitySerializable<INBT> {
		
	@CapabilityInject(ISteadfastHandler.class)
	public static final Capability<ISteadfastHandler> STEADFAST_HANDLER_CAPABILITY = null;
	
	private LazyOptional<ISteadfastHandler> instance = LazyOptional.of(STEADFAST_HANDLER_CAPABILITY::getDefaultInstance);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == STEADFAST_HANDLER_CAPABILITY ? instance.cast() : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		return STEADFAST_HANDLER_CAPABILITY.getStorage().writeNBT(STEADFAST_HANDLER_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
	}


	@Override
	public void deserializeNBT(INBT nbt) {
		STEADFAST_HANDLER_CAPABILITY.getStorage().readNBT(STEADFAST_HANDLER_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
	}
}
