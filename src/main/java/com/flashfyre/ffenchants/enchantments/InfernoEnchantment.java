package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;	

public class InfernoEnchantment extends FFEnchantment {
	
	public InfernoEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canInfernoBeAppliedToItems, 
				() -> FFEConfig.canInfernoBeAppliedToBooks, 
				() -> FFEConfig.canInfernoGenerateInLoot, 
				() -> FFEConfig.canInfernoAppearInTrades);
	}
	
	public static boolean isEntityValidForIgnition(LivingEntity livingEntity, AbstractArrowEntity arrow) {
		if(arrow.level.isRainingAt(arrow.blockPosition())) return false;
		if(livingEntity == arrow.getOwner()) return false;
		if(livingEntity.isInWater()) return false;
		if(livingEntity instanceof TameableEntity) {
			TameableEntity pet = (TameableEntity) livingEntity;
			if(pet.getOwner() == arrow.getOwner()) return false;
		}
		return true;		
	}
	
	public static void spawnParticles(World world, Vector3d pos, int count) {
		if(!world.isClientSide()) {
			ServerWorld serverWorld = (ServerWorld) world;			
			serverWorld.sendParticles(ParticleTypes.LAVA, pos.x(), pos.y(), pos.z(), count, 0, 0, 0, 0);				
		}
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
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
