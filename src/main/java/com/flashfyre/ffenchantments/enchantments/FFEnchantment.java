package com.flashfyre.ffenchantments.enchantments;

import java.util.function.BooleanSupplier;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FFEnchantment extends Enchantment {
	
	protected BooleanSupplier canBeAppliedToBooks;
	protected BooleanSupplier canBeAppliedToItems;
	protected BooleanSupplier canGenerateInLoot;
	protected BooleanSupplier canAppearInTrades;

	protected FFEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot[] slots, BooleanSupplier canBeAppliedToItems, 
			BooleanSupplier canBeAppliedToBooks, BooleanSupplier canGenerateInLoot, BooleanSupplier canAppearInTrades) {
		super(rarity, type, slots);
		this.canBeAppliedToItems = canBeAppliedToItems;
		this.canBeAppliedToBooks = canBeAppliedToBooks;
		this.canGenerateInLoot = canGenerateInLoot;
		this.canAppearInTrades = canAppearInTrades;
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
	public boolean canEnchant(ItemStack stack) {
		return stack.canApplyAtEnchantingTable(this);
	}
	
	/**
	 * Checks if itemstack is the right type to recieve the enchantment
	 * Used in:
	 * 	Enchantment tables
	 * 	EnchantmentHelper#addRandomEnchantment
	 * 	Enchantment#canApply
	 */
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(this.canBeAppliedToItems.getAsBoolean()) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return this.canBeAppliedToBooks.getAsBoolean();
	}
	
	/**
	 * Whether or not this enchantment can generate in loot.
	 * False will prevent it from appearing in the enchantment table as well.
	 */
	@Override
	public boolean isDiscoverable() {
		return this.canGenerateInLoot.getAsBoolean();
	}
	
	/**
	 * Whether or not a villager can trade an enchanted book with this enchantment
	 */
	@Override
	public boolean isTradeable() {
		return this.canAppearInTrades.getAsBoolean();
	}
	
	/**
	 * If true:
	 * - Prevents enchantment from appearing in enchantment table on both books and items
	 * - Prevents villagers from selling items enchanted with this enchantment
	 * - Prevents all other instances of items being enchanted with this item aside from the EnchantWithLevels loot function IF treasure is enabled, which is set in the loot table.
	 * - Increases price of enchanted book in villager trades
	 */	
	@Override
	public boolean isTreasureOnly() {
		return !(this.canBeAppliedToBooks.getAsBoolean() || this.canBeAppliedToItems.getAsBoolean());
	}

}
