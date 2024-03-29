package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFECore;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class KeenPointEnchantment extends FFEnchantment {
	
	public KeenPointEnchantment(Rarity rarity) {
		super(rarity, 3, EnchantmentCategory.CROSSBOW, EquipmentSlot.MAINHAND, 
				() -> FFEConfig.isKeenPointDiscoverable, 
				() -> FFEConfig.isKeenPointTradeable, 
				() -> FFEConfig.isKeenPointTreasure);
	}
	
	public int getMinCost(int level) {
		return level * 10;
	}

	public int getMaxCost(int level) {
		return this.getMinCost(level) + 15;
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != FFECore.Enchantments.RETRIBUTION.get();
	}
}
