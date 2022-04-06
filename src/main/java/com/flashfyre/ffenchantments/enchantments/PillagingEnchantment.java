package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFE;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PillagingEnchantment extends FFEnchantment 
{
	public PillagingEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canPillagingBeAppliedToItems, 
				() -> FFEConfig.canPillagingBeAppliedToBooks, 
				() -> FFEConfig.canPillagingGenerateInLoot, 
				() -> FFEConfig.canPillagingAppearInTrades);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 5 + (enchantmentLevel - 1) * 10;
	}
	
	@Override
	public int getMaxCost(int p_223551_1_) 
	{
		return 50;
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 5;
	}
	
	@SubscribeEvent
	public static void extraDamageToPillagers(LivingHurtEvent event) 
	{	
		
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != FFE.Enchantments.POINTED.get();
	}
}
