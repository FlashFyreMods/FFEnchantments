package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsProvider;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
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
		if(event.getSource().getDirectEntity() instanceof AbstractArrow) 
		{			
			AbstractArrow arrow = (AbstractArrow) event.getSource().getDirectEntity();
			arrow.getCapability(ShooterEnchantmentsProvider.SHOOTER_ENCHANTMENTS).ifPresent(data -> 
			{				
				if(data.hasEnchantment(FFE.PILLAGING))
				{
					if(event.getEntityLiving().getMobType() == MobType.ILLAGER)
					{
						int level = data.getEnchantments().get(FFE.PILLAGING);
						event.setAmount(event.getAmount() + (2.5F * level));
					}					
				}				
			});			
		}
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != FFE.POINTED;
	}
}
