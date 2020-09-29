package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class RejuvenationEnchantment extends Enchantment {
	public RejuvenationEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots);
		setRegistryName(FFE.MOD_ID, "rejuvenation");
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 2;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 5 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return true;
	}
	
	@Override
	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.MENDING;
	}
	
	@SubscribeEvent
	public static void healTick(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if(entity.isInWaterRainOrBubbleColumn()) {
			ItemStack stack = entity.getHeldItem(Hand.MAIN_HAND);
			if(!stack.isDamaged()) return;
			int level = FFE.getEnchantmentLevel(stack, FFE.REJUVENATION);
			if(level > 0) {
				if(entity.world.getGameTime() % (140 - (level * 40)) == 0) {
					stack.setDamage(stack.getDamage() - 1);
				}	
			}				
		}		
	}	
}
