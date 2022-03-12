package com.flashfyre.ffenchantments.capability;

import javax.annotation.Nonnull;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ShooterEnchantmentsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
	
	public static Capability<ShooterEnchantments> SHOOTER_ENCHANTMENTS = CapabilityManager.get(new CapabilityToken<>(){});
	
	private ShooterEnchantments shooterEnchantments = null;
	private final LazyOptional<ShooterEnchantments> opt = LazyOptional.of(this::createShooterEnchantments);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == SHOOTER_ENCHANTMENTS ? opt.cast() : LazyOptional.empty();
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		createShooterEnchantments().saveNBTData(nbt);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		createShooterEnchantments().loadNBTData(nbt);
	}
	
	@Nonnull
	private ShooterEnchantments createShooterEnchantments() {
		return this.shooterEnchantments == null ? new ShooterEnchantments() : this.shooterEnchantments;
	}
}