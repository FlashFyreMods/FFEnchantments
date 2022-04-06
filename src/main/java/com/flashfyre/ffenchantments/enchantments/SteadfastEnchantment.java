package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SteadfastEnchantment extends FFEnchantment {
	
	public static final String STEADFAST_MODIFIER_ID = "c9b42190-12b8-4015-96b3-d0df6c89812c";
	
	public SteadfastEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canSteadfastBeAppliedToItems, 
				() -> FFEConfig.canSteadfastBeAppliedToBooks, 
				() -> FFEConfig.canSteadfastGenerateInLoot, 
				() -> FFEConfig.canSteadfastAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public int getMinCost(int level) {
		return 10 + 20 * (level - 1);
	}
	
	@Override
	public int getMaxCost(int level) {
		return super.getMinCost(level) + 50;
	}
}
