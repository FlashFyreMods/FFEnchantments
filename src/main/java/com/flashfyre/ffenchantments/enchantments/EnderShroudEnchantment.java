package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;
import com.flashfyre.ffenchantments.FFECore;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnderShroudEnchantment extends FFEnchantment {

	public EnderShroudEnchantment(Rarity rarity) {
		super(rarity, 1, EnchantmentCategory.ARMOR_HEAD, EquipmentSlot.HEAD, 
				() -> FFEConfig.isEnderShroudDiscoverable, 
				() -> FFEConfig.isEnderShroudTradeable, 
				() -> FFEConfig.isEnderShroudTreasure);
	}
	
	public int getMinCost(int p_45223_) {
		return 25;
	}

	public int getMaxCost(int p_45227_) {
		return 50;
	}
	
	@SubscribeEvent
	public static void onEndermanAnger(EnderManAngerEvent event) {
		if(event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(FFECore.Enchantments.ENDER_SHROUD.get()) > 0) {
			event.setCanceled(true);
		}
	}
}
