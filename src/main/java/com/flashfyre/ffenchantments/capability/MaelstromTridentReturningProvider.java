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

public class MaelstromTridentReturningProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
	
	public static Capability<MaelstromTridentReturning> MAELSTROM_TRIDENT_RETURNING = CapabilityManager.get(new CapabilityToken<>(){});
	
	private MaelstromTridentReturning maelstromTridentReturning = null;
	private final LazyOptional<MaelstromTridentReturning> opt = LazyOptional.of(this::createMaelstromTridentReturning);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == MAELSTROM_TRIDENT_RETURNING ? opt.cast() : LazyOptional.empty();
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		createMaelstromTridentReturning().saveNBTData(nbt);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		createMaelstromTridentReturning().loadNBTData(nbt);
	}
	
	@Nonnull
	private MaelstromTridentReturning createMaelstromTridentReturning() {
		return this.maelstromTridentReturning == null ? new MaelstromTridentReturning() : this.maelstromTridentReturning;
	}
}
