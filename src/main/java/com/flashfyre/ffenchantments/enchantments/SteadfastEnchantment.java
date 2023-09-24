package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SteadfastEnchantment extends FFEnchantment {	
	public SteadfastEnchantment(Rarity rarity) {
		super(rarity, 3, EnchantmentCategory.ARMOR_CHEST, EquipmentSlot.CHEST, 
				() -> FFEConfig.isSteadfastDiscoverable,
				() -> FFEConfig.isSteadfastTradeable, 
				() -> FFEConfig.isSteadfastTreasure);
	}
	
	@Override
	public int getMinCost(int level) {
		return 10 + 20 * (level - 1);
	}
	
	@Override
	public int getMaxCost(int level) {
		return super.getMinCost(level) + 50;
	}
}
