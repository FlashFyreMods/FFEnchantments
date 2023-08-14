package com.flashfyre.ffenchantments.enchantments;

import java.util.function.BooleanSupplier;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FFEnchantment extends Enchantment {
	
	private final int maxLevel;
	private final BooleanSupplier isDiscoverable;
	private final BooleanSupplier isTradeable;
	private final BooleanSupplier isTreasureOnly;

	protected FFEnchantment(int maxLevel, Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots, BooleanSupplier isDiscoverable, BooleanSupplier isTradeable, BooleanSupplier isTreasureOnly) {
		super(rarity, category, slots);
		this.maxLevel = maxLevel;
		this.isDiscoverable = isDiscoverable;
		this.isTradeable = isTradeable;
		this.isTreasureOnly = isTreasureOnly;
	}
	
	@Override
	public int getMaxLevel() {
		return this.maxLevel;
	}
	
	@Override
	public boolean isDiscoverable() {
		return this.isDiscoverable.getAsBoolean();
	}
	
	@Override
	public boolean isTradeable() {
		return this.isTradeable.getAsBoolean();
	}
	
	@Override
	public boolean isTreasureOnly() {
		return this.isTreasureOnly.getAsBoolean();
	}

}
