package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFE;
import com.flashfyre.ffenchantments.FFEConfig;
import com.flashfyre.ffenchantments.capability.ShooterEnchantmentsProvider;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class OutrushEnchantment extends FFEnchantment 
{
	public OutrushEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
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
			int level = FFE.getEnchantmentLevel(attacker.getItemInHand(InteractionHand.MAIN_HAND), FFE.OUTRUSH);
			if(level > 0) 
			{
				if(target.fireImmune()) {
					event.setAmount(((float)level * 2.5F) + event.getAmount());
					doExtraEffects(attacker, target);
					if(target.isOnFire()) {
						target.clearFire();
					}
				}
				else {
					if(target.isOnFire()) {
						target.clearFire();
						doExtraEffects(attacker, target);
					}
				}				
			}
		}
		else if(event.getSource().getDirectEntity() instanceof ThrownTrident) // When a thrown trident hits a living entity
		{
			ThrownTrident trident = (ThrownTrident) event.getSource().getDirectEntity();
			
			if(event.getSource().getEntity() instanceof LivingEntity) //If the trident has a thrower
			{
				LivingEntity thrower = (LivingEntity) event.getSource().getEntity();
				trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_ENCHANTMENTS).ifPresent(data -> 
				{		
					if(data.hasEnchantment(FFE.OUTRUSH))
					{						
						int level = data.getEnchantments().get(FFE.OUTRUSH);
						if(level > 0) {
							if(target.fireImmune()) {
								event.setAmount(((float)level * 2.5F) + event.getAmount());								
								doExtraEffects(thrower, target);
								if(target.isOnFire()) {
									target.clearFire();
								}
							}
							else {
								if(target.isOnFire()) {
									target.clearFire();
									doExtraEffects(thrower, target);
								}
							}							
						}						
					}				
				});					
			}									
		}
	}
	
	public static void doExtraEffects(LivingEntity attacker, LivingEntity target) 
	{		
		Level world = attacker.level;
		if(!world.isClientSide()) 
		{
			if(attacker instanceof Player)
			{
				world.playSound(null, target.blockPosition(), SoundEvents.FIRE_EXTINGUISH, SoundSource.MASTER, 1.0F, 1.0F);
			}
			ServerLevel serverWorld = (ServerLevel) world;
			serverWorld.sendParticles(ParticleTypes.SPLASH, (double) target.blockPosition().getX() + 0.5D, (double) target.blockPosition().getY() + 0.75D, (double) target.blockPosition().getZ() + 0.5D, 24, 0, 0, 0, 0);
		}		
	}
}
