package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BloodlustEnchantment extends FFEnchantment {

	public BloodlustEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(2, rarity, type, slots, 
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
