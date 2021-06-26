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
import net.minecraft.item.SaddleItem;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class QuicknessHorseEnchantment extends FFEnchantment {
	
	public static final String quickness_modifier_uuid = "340345c6-41e4-4e9f-ada2-b6f7161871fb";

	public QuicknessHorseEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 6;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return this.getMinEnchantability(enchantmentLevel) + 6;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(stack.getItem() instanceof SaddleItem) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canQuicknessBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canQuicknessGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canQuicknessAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !FFEConfig.canQuicknessBeAppliedToBooks;
	}
	
	@SubscribeEvent
	public static void increaseSpeed(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if(entity.world.isRemote) return;
		if(entity instanceof AbstractHorseEntity) {
			AbstractHorseEntity horse = (AbstractHorseEntity) entity;
			ItemStack saddle = horse.horseChest.getStackInSlot(0);
			int level = FFE.getEnchantmentLevel(saddle, FFE.QUICKNESS_HORSE);
			if(level > 0) {
				if(entity.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(UUID.fromString(quickness_modifier_uuid)) == null) {
					AttributeModifier modifier = new AttributeModifier(UUID.fromString(quickness_modifier_uuid), "quickness_horse_enchantment", 0.045F * level, AttributeModifier.Operation.ADDITION);
					entity.getAttribute(Attributes.MOVEMENT_SPEED).applyPersistentModifier(modifier);
				}
			}
			else {
				entity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(UUID.fromString(quickness_modifier_uuid));
			}
		}	
	}
}
