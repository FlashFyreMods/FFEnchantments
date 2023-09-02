package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;

public class BloodlustEnchantment extends FFEnchantment {

	public BloodlustEnchantment(Rarity rarity) {
		super(rarity, 2, Category.SWORD_AND_AXE, EquipmentSlot.MAINHAND, 
				() -> FFEConfig.isBloodlustDiscoverable, 
				() -> FFEConfig.isBloodlustTradeable, 
				() -> FFEConfig.isBloodlustTreasure);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return super.getMaxCost(enchantmentLevel) + 50;
	}
}
