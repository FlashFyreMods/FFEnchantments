package com.flashfyre.ffenchants.enchantments;

import java.util.Random;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class VampiricEnchantment extends FFEnchantment {

	public VampiricEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canVampiricBeAppliedToItems, 
				() -> FFEConfig.canVampiricBeAppliedToBooks, 
				() -> FFEConfig.canVampiricGenerateInLoot, 
				() -> FFEConfig.canVampiricAppearInTrades);
	}
	
	@Override
	public int getMaxLevel()
	{
		return 2;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 1 + enchantmentLevel * 5;
	}
	
	/*
	 * Fired when mobs hit other entities
	 */
	@SubscribeEvent
	public static void mobHealOnHit(LivingDamageEvent event) {		
		Entity attacker = event.getSource().getDirectEntity();		
		if(attacker instanceof PlayerEntity) return;
		heal(attacker, event.getEntityLiving());
	}
	
	/*
	 * Fired when players hit entities. Takes the attack cooldown into consideration.
	 */
	@SubscribeEvent
	public static void playerHealOnHit(AttackEntityEvent event) {
		PlayerEntity wielder = event.getPlayer();
		if(!wielder.isHurt()) return;
		if(wielder.getAttackStrengthScale(0) < 1.0F) return;
		heal(wielder, event.getTarget());
	}
	
	private static void heal(Entity attacker, Entity target) {		
		if(target instanceof LivingEntity && attacker instanceof LivingEntity) {
			LivingEntity livingTarget = (LivingEntity) target;
			LivingEntity livingAttacker = (LivingEntity) attacker;
			if(livingTarget.isInvertedHealAndHarm()) return;
			int level = FFE.getEnchantmentLevel(livingAttacker.getItemBySlot(EquipmentSlotType.MAINHAND), FFE.VAMPIRIC);
			if(level > 0) {
				World world = livingAttacker.level;
				if(!world.isClientSide() && world instanceof ServerWorld) {
					Random r = livingAttacker.getRandom();
					if(r.nextInt(6 - level) == 0) {
						livingAttacker.heal(2);
						if(livingAttacker instanceof PlayerEntity) {
							world.playSound(null, livingAttacker.blockPosition(), SoundEvents.EVOKER_CAST_SPELL, SoundCategory.PLAYERS, 1.0F, 1.0F);
						}
						else {
							world.playSound(null, livingAttacker.blockPosition(), SoundEvents.EVOKER_CAST_SPELL, SoundCategory.HOSTILE, 1.0F, 1.0F);
						}
						ServerWorld serverWorld = (ServerWorld) livingAttacker.level;
						serverWorld.sendParticles(ParticleTypes.HEART, (double) livingAttacker.blockPosition().getX() + 0.5D, (double) livingAttacker.blockPosition().getY() + 1.5D, (double) livingAttacker.blockPosition().getZ() + 0.5D, 1, 0, 0, 0, 0);
					}
				}			
			}
		}
	}

}
