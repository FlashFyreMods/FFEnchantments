package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFECore;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFECore.MOD_ID)
public class VampiricEnchantment extends FFEnchantment {

	public VampiricEnchantment(Rarity rarity) {
		super(rarity, 1, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND, 
				() -> FFEConfig.isVampiricDiscoverable, 
				() -> FFEConfig.isVampiricTradeable,
				() -> FFEConfig.isVampiricTreasure);
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
		if(attacker instanceof Player) return;
		heal(attacker, event.getEntity());
	}
	
	/*
	 * Fired when players hit entities. Takes the attack cooldown into consideration.
	 */
	@SubscribeEvent
	public static void playerHealOnHit(AttackEntityEvent event) {
		Player wielder = event.getEntity();
		if(!wielder.isHurt()) return;
		if(wielder.getAttackStrengthScale(0) < 1.0F) return;
		heal(wielder, event.getTarget());
	}
	
	private static void heal(Entity attacker, Entity target) {		
		if(target instanceof LivingEntity && attacker instanceof LivingEntity) {
			LivingEntity livingTarget = (LivingEntity) target;
			LivingEntity livingAttacker = (LivingEntity) attacker;
			if(livingTarget.isInvertedHealAndHarm()) return;
			int level = livingAttacker.getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(FFECore.Enchantments.VAMPIRIC.get());
			if(level > 0) {
				Level world = livingAttacker.level;
				if(!world.isClientSide() && world instanceof ServerLevel) {
					RandomSource r = livingAttacker.getRandom();
					if(r.nextInt(6 - level) == 0) {
						livingAttacker.heal(2);
						if(livingAttacker instanceof Player) {
							world.playSound(null, livingAttacker.blockPosition(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1.0F, 1.0F);
						}
						else {
							world.playSound(null, livingAttacker.blockPosition(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.HOSTILE, 1.0F, 1.0F);
						}
						ServerLevel serverWorld = (ServerLevel) livingAttacker.level;
						serverWorld.sendParticles(ParticleTypes.HEART, (double) livingAttacker.blockPosition().getX() + 0.5D, (double) livingAttacker.blockPosition().getY() + 1.5D, (double) livingAttacker.blockPosition().getZ() + 0.5D, 1, 0, 0, 0, 0);
					}
				}			
			}
		}
	}

}
