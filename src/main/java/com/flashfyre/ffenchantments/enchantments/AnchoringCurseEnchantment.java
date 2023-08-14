package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AnchoringCurseEnchantment extends FFEnchantment {

	public AnchoringCurseEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(1, rarity, type, slots, 
				() -> FFEConfig.isAnchoringCurseDiscoverable, 
				() -> FFEConfig.isAnchoringCurseTradeable, 
				() -> true);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 25;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 50;
	}
	
	@Override
	public boolean isCurse() {
		return true;
	}

}
