package com.flashfyre.ffenchants.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ShooterEnchantmentsProvider implements ICapabilitySerializable<INBT> {
	
	@CapabilityInject(IShooterEnchantments.class)
	public static final Capability<IShooterEnchantments> SHOOTER_INFO_CAPABILITY = null;
	
	private LazyOptional<IShooterEnchantments> instance = LazyOptional.of(SHOOTER_INFO_CAPABILITY::getDefaultInstance);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == SHOOTER_INFO_CAPABILITY ? instance.cast() : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		return SHOOTER_INFO_CAPABILITY.getStorage().writeNBT(SHOOTER_INFO_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
	}


	@Override
	public void deserializeNBT(INBT nbt) {
		SHOOTER_INFO_CAPABILITY.getStorage().readNBT(SHOOTER_INFO_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
	}
}