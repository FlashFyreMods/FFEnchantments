package com.flashfyre.ffenchants.enchantments;

import java.util.UUID;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class QuicknessHorseEnchantment extends FFEnchantment {
	
	public static final String quickness_modifier_uuid = "340345c6-41e4-4e9f-ada2-b6f7161871fb";

	public QuicknessHorseEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
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
		if(entity instanceof AbstractHorseEntity) {
			AbstractHorseEntity horse = (AbstractHorseEntity) entity;
			ItemStack saddle = horse.inventory.getItem(0);
			int level = FFE.getEnchantmentLevel(saddle, FFE.QUICKNESS_HORSE);
			if(level > 0) {
				if(entity.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(UUID.fromString(quickness_modifier_uuid)) == null) {
					AttributeModifier modifier = new AttributeModifier(UUID.fromString(quickness_modifier_uuid), "quickness_horse_enchantment", 0.045F * level, AttributeModifier.Operation.ADDITION);
					entity.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(modifier);
				}
			}
			else {
				entity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(UUID.fromString(quickness_modifier_uuid));
			}
		}	
	}
}
