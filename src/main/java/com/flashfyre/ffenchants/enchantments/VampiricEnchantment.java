package com.flashfyre.ffenchants.enchantments;

import java.util.Random;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
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

	public VampiricEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots);
	}
	
	@Override
	public int getMaxLevel()
	{
		return 2;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 1 + enchantmentLevel * 5;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canVampiricBeAppliedToItems) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canVampiricBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canVampiricGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canVampiricAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !(FFEConfig.canVampiricBeAppliedToBooks || FFEConfig.canVampiricBeAppliedToItems);
	}
	
	/*
	 * Fired when mobs hit other entities
	 */
	@SubscribeEvent
	public static void mobHealOnHit(LivingDamageEvent event) {		
		Entity attacker = event.getSource().getImmediateSource();		
		if(attacker instanceof PlayerEntity) return;
		heal(attacker, event.getEntityLiving());
	}
	
	/*
	 * Fired when players hit entities. Takes the attack cooldown into consideration.
	 */
	@SubscribeEvent
	public static void playerHealOnHit(AttackEntityEvent event) {
		PlayerEntity wielder = event.getPlayer();
		if(!wielder.shouldHeal()) return;
		if(wielder.getCooledAttackStrength(0) < 1.0F) return;
		heal(wielder, event.getTarget());
	}
	
	public static void heal(Entity attacker, Entity target) {		
		if(target instanceof LivingEntity && attacker instanceof LivingEntity) {
			LivingEntity livingTarget = (LivingEntity) target;
			LivingEntity livingAttacker = (LivingEntity) attacker;
			if(livingTarget.isEntityUndead()) return;
			int level = FFE.getEnchantmentLevel(livingAttacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND), FFE.VAMPIRIC);
			if(level > 0) {
				World world = livingAttacker.world;
				if(!world.isRemote() && world instanceof ServerWorld) {
					Random r = livingAttacker.getRNG();
					if(r.nextInt(6 - level) == 0) {
						livingAttacker.heal(2);
						world.playSound(null, livingAttacker.getPosition(), SoundEvents.ENTITY_EVOKER_CAST_SPELL, SoundCategory.MASTER, 1.0F, 1.0F);
						ServerWorld serverWorld = (ServerWorld) livingAttacker.world;
						serverWorld.spawnParticle(ParticleTypes.HEART, (double) livingAttacker.getPosition().getX() + 0.5D, (double) livingAttacker.getPosition().getY() + 1.5D, (double) livingAttacker.getPosition().getZ() + 0.5D, 1, 0, 0, 0, 0);
					}
				}			
			}
		}
	}

}
