package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;	

public class InfernoEnchantment extends FFEnchantment {
	
	public InfernoEnchantment(Rarity rarity) {
		super(rarity, 2, EnchantmentCategory.CROSSBOW, EquipmentSlot.MAINHAND, 
				() -> FFEConfig.isInfernoDiscoverable, 
				() -> FFEConfig.isInfernoTradeable, 
				() -> FFEConfig.isInfernoTreasure);
	}
	
	public static boolean isEntityValidForIgnition(LivingEntity livingEntity, AbstractArrow arrow) {
		if(arrow.level.isRainingAt(arrow.blockPosition())) return false;
		if(livingEntity == arrow.getOwner()) return false;
		if(livingEntity.isInWater()) return false;
		if(livingEntity instanceof TamableAnimal) {
			TamableAnimal pet = (TamableAnimal) livingEntity;
			if(pet.getOwner() == arrow.getOwner()) return false;
		}
		return true;		
	}
	
	public static void spawnParticles(Level world, Vec3 pos, int count) {
		if(!world.isClientSide()) {
			ServerLevel serverWorld = (ServerLevel) world;			
			serverWorld.sendParticles(ParticleTypes.LAVA, pos.x(), pos.y(), pos.z(), count, 0, 0, 0, 0);				
		}
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return super.getMaxCost(enchantmentLevel) + 50;
	}
}
