package com.flashfyre.ffenchants.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class FFEnchantment extends Enchantment {

	protected FFEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	/*
	 * Calls Enchantment#canApplyAtEnchantingTable for vanilla enchantments
	 * Used in:
	 *  Anvils
	 *  "Enchant randomly" loot function
	 * 	Enchant command
	 * 
	 * In this mod we want to ignore whether it can be applied to items in the config use normal behaviour
	 */
	@Override
	public boolean canApply(ItemStack stack) {
		return stack.canApplyAtEnchantingTable(this);
	}

}
