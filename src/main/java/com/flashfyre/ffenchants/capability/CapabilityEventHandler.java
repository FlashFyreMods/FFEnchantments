package com.flashfyre.ffenchants.capability;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.enchantments.MaelstromEnchantment;
import com.flashfyre.ffenchants.enchantments.SharpshooterEnchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class CapabilityEventHandler {
	
	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		
		if(event.getObject() instanceof AbstractArrowEntity) {
			event.addCapability(new ResourceLocation(FFE.MOD_ID, "shooter_enchantments"), new ShooterEnchantmentsProvider());
		}
		if(event.getObject() instanceof TridentEntity) {
			event.addCapability(new ResourceLocation(FFE.MOD_ID, "maelstrom_applied"), new MaelstromAppliedProvider());
		}
	}
	
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
	
		if(event.getWorld().isRemote()) return;
		
		if(event.getEntity() instanceof AbstractArrowEntity)
		{			
			AbstractArrowEntity projectile = (AbstractArrowEntity) event.getEntity();			
			if(projectile.func_234616_v_() instanceof LivingEntity)
			{				
				projectile.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> //If the capability is present, which it should always be
				{	
					LivingEntity shooter = (LivingEntity)projectile.func_234616_v_();
					ItemStack item = shooter.getHeldItem(Hand.MAIN_HAND);
					
					int pillagingLevel = EnchantmentHelper.getEnchantmentLevel(FFE.PILLAGING, item);
					if(pillagingLevel > 0 && !data.hasEnchantment(FFE.PILLAGING)) 
					{
						data.addEnchantment(FFE.PILLAGING, pillagingLevel);						
					}
					
					int outrushLevel = EnchantmentHelper.getEnchantmentLevel(FFE.OUTRUSH, item);
					if(outrushLevel > 0 && !data.hasEnchantment(FFE.OUTRUSH)) 
					{
						data.addEnchantment(FFE.OUTRUSH, outrushLevel);		
					}
					
					int sharpshooterLevel = EnchantmentHelper.getEnchantmentLevel(FFE.SHARPSHOOTER, item);
					if(sharpshooterLevel > 0 && !data.hasEnchantment(FFE.SHARPSHOOTER)) 
					{
						data.addEnchantment(FFE.SHARPSHOOTER, sharpshooterLevel);
						SharpshooterEnchantment.setNewVelocity(shooter, projectile, sharpshooterLevel);
							
					}
					
					int maelstromLevel = EnchantmentHelper.getEnchantmentLevel(FFE.MAELSTROM, item);
					if(maelstromLevel > 0 && !data.hasEnchantment(FFE.MAELSTROM)) {
						data.addEnchantment(FFE.MAELSTROM, maelstromLevel);
						MaelstromEnchantment.loadedTridentEntities.add(projectile);
					}
				});	
			}						
		}
	}
}
