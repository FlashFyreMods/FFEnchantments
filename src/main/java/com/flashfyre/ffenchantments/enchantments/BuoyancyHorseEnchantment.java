package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;
import com.flashfyre.ffenchantments.FFECore;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFECore.MOD_ID)
public class BuoyancyHorseEnchantment extends FFEnchantment {

	public BuoyancyHorseEnchantment(Rarity rarity) {
		super(rarity, 1, Category.SADDLE, EMPTY_SLOTS, 
				() -> FFEConfig.isBuoyancyDiscoverable, 
				() -> FFEConfig.isBuoyancyTradeable,
				() -> FFEConfig.isBuoyancyTreasure);
	}
	
	public int getMinEnchantability(int level) {
		return 20;
	}

	public int getMaxEnchantability(int level) {
		return 50;
	}
}
