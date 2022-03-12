package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class WeightedBladeEnchantment extends FFEnchantment {
	
	public WeightedBladeEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canWeightedBeAppliedToItems, 
				() -> FFEConfig.canWeightedBeAppliedToBooks, 
				() -> FFEConfig.canWeightedGenerateInLoot, 
				() -> FFEConfig.canWeightedAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}
	
	@Override
	public int getMinCost(int level) {
		return 5 + (level - 1) * 9;
	}
	
	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 15;
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) {
		if(this.canBeAppliedToItems.getAsBoolean()) {
			return stack.getItem() instanceof AxeItem ? true : super.canEnchant(stack);
		}
		return false;
	}

}
