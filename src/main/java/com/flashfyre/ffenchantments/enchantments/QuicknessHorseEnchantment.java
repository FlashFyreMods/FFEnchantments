package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

public class QuicknessHorseEnchantment extends FFEnchantment {
	
	public static final String QUICKNESS_MODIFIER_UUID = "340345c6-41e4-4e9f-ada2-b6f7161871fb";

	public QuicknessHorseEnchantment(Rarity rarity) {
		super(rarity, 3, Category.SADDLE, EMPTY_SLOTS, 
				() -> FFEConfig.isQuicknessDiscoverable,
				() -> FFEConfig.isQuicknessTradeable,
				() -> FFEConfig.isQuicknessTreasure);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 6;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 6;
	}
}
