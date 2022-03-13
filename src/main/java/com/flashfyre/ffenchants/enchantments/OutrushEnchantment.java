package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsProvider;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class OutrushEnchantment extends FFEnchantment 
{
	public OutrushEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canOutrushBeAppliedToItems, 
				() -> FFEConfig.canOutrushBeAppliedToBooks, 
				() -> FFEConfig.canOutrushGenerateInLoot, 
				() -> FFEConfig.canOutrushAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 5;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 1 + (enchantmentLevel - 1) * 8;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return this.getMinCost(enchantmentLevel) + 20;
	}
	
	@SubscribeEvent
	public static void damageFireEntities(LivingHurtEvent event) 
	{
		LivingEntity target = event.getEntityLiving();
		if(event.getSource().getDirectEntity() instanceof LivingEntity) //When a livingentity hits another living entity
		{			
			LivingEntity attacker = (LivingEntity) event.getSource().getDirectEntity();
			int level = FFE.getEnchantmentLevel(attacker.getItemInHand(Hand.MAIN_HAND), FFE.OUTRUSH);
			if(level > 0) 
			{
				if(target.fireImmune()) {
					event.setAmount(((float)level * 2.5F) + event.getAmount());
					doOtherEffects(attacker, target);
					if(target.isOnFire()) {
						target.clearFire();
					}
				}
				else {
					if(target.isOnFire()) {
						target.clearFire();
						doOtherEffects(attacker, target);
					}
				}				
			}
		}
		else if(event.getSource().getDirectEntity() instanceof TridentEntity) //When a trident hits a living entity
		{
			TridentEntity trident = (TridentEntity) event.getSource().getDirectEntity();
			
			if(event.getSource().getEntity() instanceof LivingEntity) //If the trident has a thrower
			{
				LivingEntity thrower = (LivingEntity) event.getSource().getEntity();
				trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> 
				{		
					if(data.hasEnchantment(FFE.OUTRUSH)) 
					{						
						int level = data.getEnchantments().get(FFE.OUTRUSH);
						if(level > 0) {
							if(target.fireImmune()) {
								event.setAmount(((float)level * 2.5F) + event.getAmount());								
								doOtherEffects(thrower, target);
								if(target.isOnFire()) {
									target.clearFire();
								}
							}
							else {
								if(target.isOnFire()) {
									target.clearFire();
									doOtherEffects(thrower, target);
								}
							}							
						}						
					}				
				});					
			}									
		}
	}
	
	public static void doOtherEffects(LivingEntity attacker, LivingEntity target) 
	{		
		World world = attacker.level;
		if(!world.isClientSide()) 
		{
			if(attacker instanceof PlayerEntity)
			{
				world.playSound(null, target.blockPosition(), SoundEvents.FIRE_EXTINGUISH, SoundCategory.MASTER, 1.0F, 1.0F);
			}
			ServerWorld serverWorld = (ServerWorld) world;
			serverWorld.sendParticles(ParticleTypes.SPLASH, (double) target.blockPosition().getX() + 0.5D, (double) target.blockPosition().getY() + 0.75D, (double) target.blockPosition().getZ() + 0.5D, 24, 0, 0, 0, 0);
		}		
	}
}
