package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BloodlustEnchantment extends FFEnchantment {

	public BloodlustEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
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
