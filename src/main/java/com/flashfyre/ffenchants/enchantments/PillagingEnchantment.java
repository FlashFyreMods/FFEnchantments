package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsProvider;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class PillagingEnchantment extends FFEnchantment 
{
	public PillagingEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
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
		if(event.getSource().getDirectEntity() instanceof AbstractArrowEntity) 
		{			
			AbstractArrowEntity arrow = (AbstractArrowEntity) event.getSource().getDirectEntity();
			arrow.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> 
			{				
				if(data.hasEnchantment(FFE.PILLAGING))
				{
					if(event.getEntityLiving().getMobType() == CreatureAttribute.ILLAGER)
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
