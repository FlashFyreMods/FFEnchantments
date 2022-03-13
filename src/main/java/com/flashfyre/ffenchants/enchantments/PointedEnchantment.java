package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class PointedEnchantment extends FFEnchantment {
	
	public PointedEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canPointedBeAppliedToItems, 
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
