package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

public class AquaticRejuvenationEnchantment extends FFEnchantment {
	public AquaticRejuvenationEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canAquaticRejuvenationBeAppliedToItems, 
				() -> FFEConfig.canAquaticRejuvenationBeAppliedToBooks, 
				() -> FFEConfig.canAquaticRejuvenationGenerateInLoot, 
				() -> FFEConfig.canAquaticRejuvenationAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 2;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return super.getMinCost(enchantmentLevel) + 50;
	}
	
	/**
	 * Returns false on enchantments that should be incompatible.
	 * Used in:
	 *  Anvils
	 * 	Enchantment tables
	 *  EnchantmentHelper#addRandomEnchantment
	 *  Enchant command
	 */
	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != Enchantments.MENDING;
	}
}
