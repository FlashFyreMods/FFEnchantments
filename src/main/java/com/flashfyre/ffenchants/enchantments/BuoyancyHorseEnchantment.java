package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class BuoyancyHorseEnchantment extends FFEnchantment {

	public BuoyancyHorseEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> true, 
				() -> FFEConfig.canBuoyancyBeAppliedToBooks, 
				() -> FFEConfig.canBuoyancyGenerateInLoot, 
				() -> FFEConfig.canBuoyancyAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	public int getMinEnchantability(int level) {
		return 20;
	}

	public int getMaxEnchantability(int level) {
		return 50;
	}
}
