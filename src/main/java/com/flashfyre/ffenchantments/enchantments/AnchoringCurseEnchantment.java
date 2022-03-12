package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AnchoringCurseEnchantment extends FFEnchantment {

	public AnchoringCurseEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(rarity, type, slots, 
				() -> false, 
				() -> false, 
				() -> FFEConfig.canAnchoringCurseGenerateInLoot, 
				() -> FFEConfig.canAnchoringCurseAppearInTrades);
	}
	
	public int getMinEnchantability(int enchantmentLevel) {
		return 25;
	}

	public int getMaxEnchantability(int enchantmentLevel) {
		return 50;
	}
	
	public int getMaxLevel() {
		return 1;
	}
	
	public boolean isTreasureEnchantment() {
		return true;
	}
	
	public boolean isCurse() {
		return true;
	}

}
