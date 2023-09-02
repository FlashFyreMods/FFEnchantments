package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;
import com.flashfyre.ffenchantments.FFECore;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WeightedBladeEnchantment extends FFEnchantment {
	
	public WeightedBladeEnchantment(Rarity rarity) {
		super(rarity, 2, Category.SWORD_AND_AXE, EquipmentSlot.MAINHAND,
				() -> FFEConfig.isWeightedBladeDiscoverable,
				() -> FFEConfig.isWeightedBladeTradeable, 
				() -> FFEConfig.isWeightedBladeTreasure);
	}
	
	@Override
	public int getMinCost(int level) {
		return 5 + (level - 1) * 9;
	}
	
	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 15;
	}
	
	@SubscribeEvent
	public static void onCrit(CriticalHitEvent event) {
		if(event.isVanillaCritical()) {
			int level = event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(FFECore.Enchantments.WEIGHTED_BLADE.get());
			if(level > 0) {
				if(event.getTarget() instanceof LivingEntity livingTarget) {
					livingTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60 * level, 0, false, true));
				}
			}
		}				
	}
}
