package com.flashfyre.ffenchants.enchantments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsProvider;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;	

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class InfernoEnchantment extends FFEnchantment{
	
	public static List<AbstractArrowEntity> loadedArrowEntities = new ArrayList<>(); //A list of all arrow entities enchanted with maelstrom. They are removed from this list when they are in the ground.

	public InfernoEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
		super(rarityIn, typeIn, slots);
	}
	
	@SubscribeEvent
	public static void infernoWorldTick(WorldTickEvent event) {
		World world = event.world;		
		Set<AbstractArrowEntity> arrowsToRemove = new HashSet<AbstractArrowEntity>();		
		for(AbstractArrowEntity arrow : loadedArrowEntities) {			
			if(!arrow.isAlive() || arrow.inGround || arrow == null) {
				arrowsToRemove.add(arrow);
			}
			else {
				arrow.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> {
					if(data.hasEnchantment(FFE.INFERNO)) {
						int level = data.getEnchantments().get(FFE.INFERNO);
						if(level > 0) {
							List<LivingEntity> entitiesInAoE = FFE.getEntitiesInAABB(world, level*0.75, arrow.getPositionVec());
							entitiesInAoE.forEach((e) -> {
								if(e == arrow.func_234616_v_()) return; // Return if entity in aabb is arrow's shooter
								if(e instanceof TameableEntity) {
									TameableEntity pet = (TameableEntity) e;
									if(pet.getOwner() == arrow.func_234616_v_()) return; // Return if entity is a pet of the trident's shooter
								}
								e.setFire(5);
							});
							spawnParticles(world, arrow.getPositionVec(), 1);
						}						
					}
				});
			}
		}
	}
	
	@SubscribeEvent
	public static void infernoImpactEvent(ProjectileImpactEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof AbstractArrowEntity) {
			AbstractArrowEntity arrow = (AbstractArrowEntity) entity;
			if(arrow.func_234616_v_() instanceof LivingEntity) {				
				arrow.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data ->
				{
					if(data.hasEnchantment(FFE.INFERNO)) {
						int level = data.getEnchantments().get(FFE.INFERNO);
						if(level > 0) {
							List<LivingEntity> entitiesInAoE = FFE.getEntitiesInAABB(arrow.world, level*1.5, arrow.getPositionVec());
							boolean applied = false;
							for(LivingEntity e : entitiesInAoE) {
								if(e == arrow.func_234616_v_()) continue; // If entity is arrow's shooter
								if(e instanceof TameableEntity) {
									TameableEntity pet = (TameableEntity) e;
									if(pet.getOwner() == arrow.func_234616_v_()) continue; // If entity is pet of arrow's shooter
								}
								e.setFire(10);
								spawnParticles(arrow.world, e.getPositionVec(), 3);
								applied = true;
							}
							if(applied) {
								arrow.world.playSound(null, arrow.getPosition(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.5F, 1.0F);	
							}												
							//arrow.world.createExplosion(arrow.func_234616_v_(), arrow.getPosX(), arrow.getPosY(), arrow.getPosZ(), 1, true, Explosion.Mode.NONE);
						}
					}
				});
			}
		}
	}
	
	public static boolean isValid(LivingEntity livingEntity, AbstractArrowEntity arrow) {
		if(livingEntity == arrow.func_234616_v_()) return false; // If entity is arrow's shooter
		if(livingEntity instanceof TameableEntity) {
			TameableEntity pet = (TameableEntity) livingEntity;
			if(pet.getOwner() == arrow.func_234616_v_()) return false; // If entity is pet of arrow's shooter
		}
		return true;		
	}
	
	public static void spawnParticles(World world, Vector3d pos, int count) {
		if(!world.isRemote()) {
			ServerWorld serverWorld = (ServerWorld) world;			
			serverWorld.spawnParticle(ParticleTypes.LAVA, pos.getX(), pos.getY(), pos.getZ(), count, 0, 0, 0, 0);				
		}
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}

}
