package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class AquaticRestoration extends FFEnchantment {
	public AquaticRestoration(Rarity rarity) {
		super(rarity, 2, EnchantmentCategory.TRIDENT, EquipmentSlot.MAINHAND, 
				() -> FFEConfig.isAquaticRestorationDiscoverable, 
				() -> FFEConfig.isAquaticRestorationTradeable, 
				() -> FFEConfig.isAquaticRestorationTreasure);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return super.getMinCost(enchantmentLevel) + 50;
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != Enchantments.MENDING;
	}
}
