package com.flashfyre.ffenchants.enchantments;

import java.util.Random;

import com.flashfyre.ffenchants.FFE;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class WeightedEnchantment extends Enchantment {
	
	public WeightedEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots);
		setRegistryName(FFE.MOD_ID, "weighted");
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}
	
	@Override
	public int getMinEnchantability(int level) {
		return 5 + (level - 1) * 9;
	}
	
	@Override
	public int getMaxEnchantability(int level) {
		return this.getMinEnchantability(level) + 15;
	}
	
	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() instanceof AxeItem ? true : super.canApply(stack);
	}
	
	@SubscribeEvent
	public static void applyWeaknessOnCrit(CriticalHitEvent event) {
		if(event.isVanillaCritical()) {
			int level = FFE.getEnchantmentLevel(event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.MAINHAND), FFE.WEIGHTED);
			if(level > 0) {
				if(event.getTarget() instanceof LivingEntity) {
					LivingEntity target = (LivingEntity) event.getTarget();
					Random r = event.getPlayer().getRNG();
					if(r.nextInt(5 - level) == 0) {
						target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 80 * level, level - 1, false, true));
					}					
				}
			}
		}				
	}

}
