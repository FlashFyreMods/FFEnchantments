package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ObsidianSkullEnchantment extends FFEnchantment {
	
	public ObsidianSkullEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
		super(4, rarity, category, slots, 
				() -> FFEConfig.isObsidianSkullDiscoverable, 
				() -> FFEConfig.isObsidianSkullTradeable, 
				() -> FFEConfig.isObsidianSkullTreasure);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 6;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 6;
	}
	
	@Override
	public int getDamageProtection(int level, DamageSource source) {
		if(source.is(DamageTypes.FLY_INTO_WALL)) {
			return level * 4;
		}
		return 0;
	}
}
