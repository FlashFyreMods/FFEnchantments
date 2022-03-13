package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class AnchoringCurseEnchantment extends FFEnchantment {

	public AnchoringCurseEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
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
