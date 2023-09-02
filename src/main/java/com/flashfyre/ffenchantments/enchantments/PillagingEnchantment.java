package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFECore;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PillagingEnchantment extends FFEnchantment {
	
	public PillagingEnchantment(Rarity rarity) {
		super(rarity, 5, EnchantmentCategory.CROSSBOW, EquipmentSlot.MAINHAND, 
				() -> FFEConfig.isPillagingDiscoverable, 
				() -> FFEConfig.isPillagingTradeable, 
				() -> FFEConfig.isPillagingTreasure);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 10;
	}
	
	@Override
	public int getMaxCost(int p_223551_1_) {
		return 50;
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != FFECore.Enchantments.POINTED.get();
	}
}
