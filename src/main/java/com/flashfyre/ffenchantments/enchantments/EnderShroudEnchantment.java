package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFECore;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnderShroudEnchantment extends FFEnchantment {

	public EnderShroudEnchantment(Rarity rarity) {
		super(rarity, 1, EnchantmentCategory.ARMOR_HEAD, EquipmentSlot.HEAD, 
				() -> true, 
				() -> true, 
				() -> false);
	}
	
	@SubscribeEvent
	public static void onEndermanAnger(EnderManAngerEvent event) {
		if(event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(FFECore.Enchantments.END_CURSE.get()) > 0) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	private void onLivingEntityTick(LivingEvent.LivingTickEvent event) {
		
	}
}
