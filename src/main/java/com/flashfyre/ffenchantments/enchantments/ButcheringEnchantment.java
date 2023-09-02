package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;

public class ButcheringEnchantment extends FFEnchantment {
	
	public ButcheringEnchantment(Rarity rarity) {
		super(rarity, 3, Category.AXE, EquipmentSlot.MAINHAND, 
				() -> FFEConfig.isButcheringDiscoverable, 
				() -> FFEConfig.isButcheringTradeable, 
				() -> FFEConfig.isButcheringTreasure);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
	      return 5 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return super.getMinCost(enchantmentLevel) + 50;
	}
}
