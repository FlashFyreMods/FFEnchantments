package com.flashfyre.ffenchants.enchantments;

import java.util.List;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class MaelstromEnchantment extends FFEnchantment {
	
	public MaelstromEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canMaelstromBeAppliedToItems, 
				() -> FFEConfig.canMaelstromBeAppliedToBooks, 
				() -> FFEConfig.canMaelstromGenerateInLoot, 
				() -> FFEConfig.canMaelstromAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 3;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 6;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 6;
	}
	
	public static void spawnParticles(World world, AbstractArrowEntity trident) {
		if(!world.isClientSide()) {
			ServerWorld serverWorld = (ServerWorld) world;
			double x = trident.position().x() + 0.5*Math.cos(trident.tickCount)*Math.cos(trident.yRot * Math.PI/180) + Math.sin(trident.yRot * Math.PI/180);
			double y = trident.position().y() + 0.5*Math.sin(trident.tickCount);
			//double y = trident.position().y() - 0.5*Math.sin(trident.ticksExisted)*Math.sin(trident.rotationPitch * Math.PI/180) + Math.cos(trident.rotationPitch * Math.PI/180);
			double z = trident.position().z() - 0.5*Math.cos(trident.tickCount)*Math.sin(trident.yRot * Math.PI/180) + Math.cos(trident.yRot * Math.PI/180);
			serverWorld.sendParticles(ParticleTypes.BUBBLE, x, y, z, 1, 0, 0, 0, 0);				
		}
	}
	
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != Enchantments.RIPTIDE;
	}
	
	public static void apply(ServerWorld sWorld, TridentEntity trident, int level) {
		double radius = 2.5D;
		List<LivingEntity> entitiesInAoE = sWorld.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(trident.position().add(radius, radius, radius), trident.position().add(-radius, -radius, -radius)));
		entitiesInAoE.forEach((e) -> {
			if(e == trident.getOwner()) return; // Skip if entity is the trident's shooter
			if(e instanceof TameableEntity) {
				TameableEntity pet = (TameableEntity) e;
				if(pet.getOwner() == trident.getOwner()) return; // Skip if entity is a pet of the trident's shooter
			}
			float moveStrength = 0.10F;
			Vector3d entityPos = e.getBoundingBox().getCenter();
			Vector3d tridentPos = trident.position();
			e.push((tridentPos.x()-entityPos.x())*moveStrength, (tridentPos.y()-entityPos.y())*moveStrength, (tridentPos.z()-entityPos.z())*moveStrength);
			//e.push(trident.getDeltaMovement().x() / 15, trident.getDeltaMovement().y() / 15, trident.getDeltaMovement().z() / 15);
			e.hurt(FFE.causeMaelstromDamage(trident, trident.getOwner()), 2.0F * level);
			e.hurtMarked = true;
		});
	}
	
	
}
