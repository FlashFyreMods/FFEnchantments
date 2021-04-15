package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsProvider;
import com.flashfyre.ffenchants.misc.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class PillagingEnchantment extends Enchantment 
{
	public PillagingEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) 
	{
		super(rarityIn, typeIn, slots);
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 5 + (enchantmentLevel - 1) * 10;
	}
	
	@Override
	public int getMaxEnchantability(int p_223551_1_) 
	{
		return 50;
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 5;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canPillagingBeAppliedToItems) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canPillagingBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canPillagingGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canPillagingAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !(FFEConfig.canPillagingBeAppliedToBooks || FFEConfig.canPillagingBeAppliedToItems);
	}
	
	@SubscribeEvent
	public static void extraDamageToPillagers(LivingHurtEvent event) 
	{	
		if(event.getSource().getImmediateSource() instanceof AbstractArrowEntity) 
		{			
			AbstractArrowEntity arrow = (AbstractArrowEntity) event.getSource().getImmediateSource();
			arrow.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> 
			{				
				if(data.hasEnchantment(FFE.PILLAGING))
				{
					if(event.getEntityLiving().getCreatureAttribute() == CreatureAttribute.ILLAGER)
					{
						int level = data.getEnchantments().get(FFE.PILLAGING);
						event.setAmount(event.getAmount() + (2.5F * level));
					}					
				}				
			});			
		}
	}
}
