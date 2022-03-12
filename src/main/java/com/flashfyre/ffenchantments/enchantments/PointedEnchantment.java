package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFE;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PointedEnchantment extends FFEnchantment {
	
	public PointedEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(rarity, type, slots, 
				() -> true, 
				() -> true, 
				() -> true, 
				() -> true);
	}
	
	public int getMinCost(int level) {
		return level * 10;
	}

	public int getMaxCost(int level) {
		return this.getMinCost(level) + 15;
	}

	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != FFE.PILLAGING;
	}
}
