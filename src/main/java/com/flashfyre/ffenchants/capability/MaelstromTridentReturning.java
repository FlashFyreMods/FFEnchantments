package com.flashfyre.ffenchants.capability;

import net.minecraft.nbt.CompoundTag;

public class MaelstromTridentReturning {
	
	private boolean isTridentReturning;
	
	public boolean isTridentReturning() {
		return this.isTridentReturning;
	}
	
	public void setTridentReturning() {
		this.isTridentReturning = true;
	}
	
	public void saveNBTData(CompoundTag nbt) {
		nbt.putBoolean("MaelstromTridentReturning", isTridentReturning);
	}
	
	public void loadNBTData(CompoundTag nbt) {
		this.isTridentReturning = nbt.getBoolean("MaelstromTridentReturning");
	}

}
