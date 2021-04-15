package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class AnchoringCurseEnchantment extends Enchantment {

	public AnchoringCurseEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public int getMinEnchantability(int enchantmentLevel) {
		return 25;
	}

	public int getMaxEnchantability(int enchantmentLevel) {
		return 50;
	}
	
	public int getMaxLevel() {
		return 1;
	}
	
	public boolean isTreasureEnchantment() {
		return true;
	}
	
	public boolean isCurse() {
		return true;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canAnchoringCurseGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canAnchoringAppearInTrades;
	}
	
	@SubscribeEvent
	public static void anchoringTick(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if(FFE.getEnchantmentLevel(entity.getItemStackFromSlot(EquipmentSlotType.FEET), FFE.ANCHORING_CURSE) > 0) {
			if(entity.isInWater()) {
				if(entity instanceof PlayerEntity) {
					PlayerEntity player = (PlayerEntity) entity;
					if(player.abilities.isFlying) {
						return;
					}
				}
				entity.onEnterBubbleColumn(true);
			}
		}
	}

}
