package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class AquaticRejuvenationEnchantment extends FFEnchantment {
	public AquaticRejuvenationEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(2, rarity, type, slots, 
				() -> FFEConfig.isAquaticRejuvenationDiscoverable, 
				() -> FFEConfig.isAquaticRejuvenationTradeable, 
				() -> FFEConfig.isAquaticRejuvenationTreasure);
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
