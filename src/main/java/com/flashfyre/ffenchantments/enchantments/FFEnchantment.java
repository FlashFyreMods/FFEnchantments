package com.flashfyre.ffenchantments.enchantments;

import java.util.function.BooleanSupplier;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.SaddleItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FFEnchantment extends Enchantment {
	
	protected static final EquipmentSlot[] EMPTY_SLOTS = {};
	protected static final EquipmentSlot[] ARMOUR_SLOTS = new EquipmentSlot[] {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
	
	private final int maxLevel;
	private final BooleanSupplier isDiscoverable;
	private final BooleanSupplier isTradeable;
	private final BooleanSupplier isTreasureOnly;

	protected FFEnchantment(Rarity rarity, int maxLevel, EnchantmentCategory category, EquipmentSlot[] slots, BooleanSupplier isDiscoverable, BooleanSupplier isTradeable, BooleanSupplier isTreasureOnly) {
		super(rarity, category, slots);
		this.maxLevel = maxLevel;
		this.isDiscoverable = isDiscoverable;
		this.isTradeable = isTradeable;
		this.isTreasureOnly = isTreasureOnly;
	}
	
	protected FFEnchantment(Rarity rarity, int maxLevel, EnchantmentCategory category, EquipmentSlot slot, BooleanSupplier isDiscoverable, BooleanSupplier isTradeable, BooleanSupplier isTreasureOnly) {
		this(rarity, maxLevel, category, new EquipmentSlot[] {slot}, isTreasureOnly, isTreasureOnly, isTreasureOnly);
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
	
	protected static class Category {	
		protected static final EnchantmentCategory AXE = EnchantmentCategory.create("AXE",  item -> item instanceof AxeItem);
		protected static final EnchantmentCategory SWORD_AND_AXE = EnchantmentCategory.create("SWORD_AND_AXE",  item -> item instanceof AxeItem || item instanceof SwordItem);
		protected static final EnchantmentCategory SADDLE = EnchantmentCategory.create("SADDLE", item -> item instanceof SaddleItem);
		protected static final EnchantmentCategory SHIELD = EnchantmentCategory.create("SHIELD", item -> item instanceof ShieldItem);
	}

}
