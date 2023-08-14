package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFECore;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFECore.MOD_ID)
public class BuoyancyHorseEnchantment extends FFEnchantment {

	public BuoyancyHorseEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(1, rarity, type, slots, 
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
