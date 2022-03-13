package com.flashfyre.ffenchants.enchantments;

import java.util.Random;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

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
public class WeightedBladeEnchantment extends FFEnchantment {
	
	public WeightedBladeEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canWeightedBeAppliedToItems, 
				() -> FFEConfig.canWeightedBeAppliedToBooks, 
				() -> FFEConfig.canWeightedGenerateInLoot, 
				() -> FFEConfig.canWeightedAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}
	
	@Override
	public int getMinCost(int level) {
		return 5 + (level - 1) * 9;
	}
	
	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 15;
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) {
		if(this.canBeAppliedToItems.getAsBoolean()) {
			return stack.getItem() instanceof AxeItem ? true : super.canEnchant(stack);
		}
		return false;
	}
	
	@SubscribeEvent
	public static void applyWeaknessOnCrit(CriticalHitEvent event) {
		if(event.isVanillaCritical()) {
			int level = FFE.getEnchantmentLevel(event.getEntityLiving().getItemBySlot(EquipmentSlotType.MAINHAND), FFE.WEIGHTED_BLADE);
			if(level > 0) {
				if(event.getTarget() instanceof LivingEntity) {
					LivingEntity target = (LivingEntity) event.getTarget();
					Random r = event.getPlayer().getRandom();
					if(r.nextInt(5 - level) == 0) {
						target.addEffect(new EffectInstance(Effects.WEAKNESS, 80 * level, level - 1, false, true));
					}					
				}
			}
		}				
	}

}
