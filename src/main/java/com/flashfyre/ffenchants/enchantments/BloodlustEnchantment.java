package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class BloodlustEnchantment extends FFEnchantment {

	public BloodlustEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canBloodlustBeAppliedToItems, 
				() -> FFEConfig.canBloodlustBeAppliedToBooks, 
				() -> FFEConfig.canBloodlustGenerateInLoot, 
				() -> FFEConfig.canBloodlustAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
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
