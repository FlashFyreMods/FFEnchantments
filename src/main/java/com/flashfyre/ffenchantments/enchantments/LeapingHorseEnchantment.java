package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LeapingHorseEnchantment extends FFEnchantment {
	
	public LeapingHorseEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(2, rarity, type, slots, 
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
