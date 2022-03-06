package com.flashfyre.ffenchants.enchantments;

import java.util.UUID;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class QuicknessHorseEnchantment extends FFEnchantment {
	
	public static final String QUICKNESS_MODIFIER_UUID = "340345c6-41e4-4e9f-ada2-b6f7161871fb";

	public QuicknessHorseEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(rarity, type, slots, 
				() -> true, 
				() -> FFEConfig.canQuicknessBeAppliedToBooks, 
				() -> FFEConfig.canQuicknessGenerateInLoot, 
				() -> FFEConfig.canQuicknessAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 6;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 6;
	}
	
	@SubscribeEvent
	public static void increaseSpeed(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if(entity.level.isClientSide) return;
		if(entity instanceof AbstractHorse) {
			AbstractHorse horse = (AbstractHorse) entity;
			ItemStack saddle = horse.inventory.getItem(0);
			int level = FFE.getEnchantmentLevel(saddle, FFE.QUICKNESS_HORSE);
			if(level > 0) {
				if(entity.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(UUID.fromString(QUICKNESS_MODIFIER_UUID)) == null) {
					AttributeModifier modifier = new AttributeModifier(UUID.fromString(QUICKNESS_MODIFIER_UUID), "quickness_horse_enchantment", 0.045F * level, AttributeModifier.Operation.ADDITION);
					entity.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(modifier);
				}
			}
			else {
				entity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(UUID.fromString(QUICKNESS_MODIFIER_UUID));
			}
		}	
	}
}
