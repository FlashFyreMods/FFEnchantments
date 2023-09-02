package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

public class LeapingHorseEnchantment extends FFEnchantment {
	
	public LeapingHorseEnchantment(Rarity rarity) {
		super(rarity, 2, Category.SADDLE, EMPTY_SLOTS, 
				() -> FFEConfig.isLeapingDiscoverable, 
				() -> FFEConfig.isLeapingTradeable, 
				() -> FFEConfig.isLeapingTreasure);
	}
	
	@Override
	public int getMinCost(int level) {
		return 5 + (level - 1) * 9;
	}
	
	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 15;
	}
	
	public static double getYVelocity(int level) {
		return 0.12 * level;
	}
}
