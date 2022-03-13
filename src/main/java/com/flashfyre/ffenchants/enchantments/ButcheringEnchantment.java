package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class ButcheringEnchantment extends FFEnchantment {
	
	public ButcheringEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canButcheringBeAppliedToItems, 
				() -> FFEConfig.canButcheringBeAppliedToBooks, 
				() -> FFEConfig.canButcheringGenerateInLoot, 
				() -> FFEConfig.canButcheringAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
	      return 5 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return super.getMinCost(enchantmentLevel) + 50;
	}
	
	@SubscribeEvent
	public static void applyExtraDamage(LivingHurtEvent event) {
		if(event.getSource().getDirectEntity() instanceof LivingEntity) {
			LivingEntity attacker = (LivingEntity) event.getSource().getDirectEntity();
			int level = FFE.getEnchantmentLevel(attacker.getItemBySlot(EquipmentSlotType.MAINHAND), FFE.BUTCHERING);
			if(level > 0) {
				if(event.getEntityLiving() instanceof AnimalEntity) {
					event.setAmount(event.getAmount() + level);
				}
			}
		}		
	}
}
