package com.flashfyre.ffenchantments.enchantments;

import java.util.List;

import com.flashfyre.ffenchantments.FFEConfig;
import com.flashfyre.ffenchantments.FFECore;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class MaelstromEnchantment extends FFEnchantment {
	
	public MaelstromEnchantment(Rarity rarity) {
		super(rarity, 3, EnchantmentCategory.TRIDENT, EquipmentSlot.MAINHAND, 
				() -> FFEConfig.isMaelstromDiscoverable, 
				() -> FFEConfig.isMaelstromTradeable, 
				() -> FFEConfig.isMaelstromTreasure);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 6;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 6;
	}
	
	public static void spawnParticles(Level world, AbstractArrow trident) {
		if(!world.isClientSide()) {
			ServerLevel serverWorld = (ServerLevel) world;
			double x = trident.position().x() + 0.5*Math.cos(trident.tickCount)*Math.cos(trident.getYRot() * Math.PI/180) + Math.sin(trident.getYRot() * Math.PI/180);
			double y = trident.position().y() + 0.5*Math.sin(trident.tickCount);
			//double y = trident.position().y() - 0.5*Math.sin(trident.ticksExisted)*Math.sin(trident.rotationPitch * Math.PI/180) + Math.cos(trident.rotationPitch * Math.PI/180);
			double z = trident.position().z() - 0.5*Math.cos(trident.tickCount)*Math.sin(trident.getYRot() * Math.PI/180) + Math.cos(trident.getYRot() * Math.PI/180);
			serverWorld.sendParticles(ParticleTypes.BUBBLE, x, y, z, 1, 0, 0, 0, 0);				
		}
	}
	
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != Enchantments.RIPTIDE;
	}
	
	public static void apply(ServerLevel sWorld, ThrownTrident trident, int level) {
		double radius = 2.5D;
		List<LivingEntity> entitiesInAoE = sWorld.getEntitiesOfClass(LivingEntity.class, new AABB(trident.position().add(radius, radius, radius), trident.position().add(-radius, -radius, -radius)));
		entitiesInAoE.forEach((e) -> {
			if(e == trident.getOwner()) return; // Skip if entity is the trident's shooter
			if(e instanceof TamableAnimal pet && pet.getOwner() == trident.getOwner()) return; // Skip if entity is a pet of the trident's shooter
			float moveStrength = 0.10F;
			Vec3 entityPos = e.getBoundingBox().getCenter();
			Vec3 tridentPos = trident.position();
			e.push((tridentPos.x()-entityPos.x())*moveStrength, (tridentPos.y()-entityPos.y())*moveStrength, (tridentPos.z()-entityPos.z())*moveStrength);
			//e.push(trident.getDeltaMovement().x() / 15, trident.getDeltaMovement().y() / 15, trident.getDeltaMovement().z() / 15);
			//e.hurt(new DamageSource(e.level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(FFEDamageTypes.MAELSTROM), trident.getOwner(), null), 2.0F * level);
			e.hurt(FFECore.maelstromDamage(trident, trident.getOwner()), 2.0F*level);
			e.hurtMarked = true;
		});
	}
	
	
}
