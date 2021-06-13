package com.flashfyre.ffenchants.enchantments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.capability.MaelstromAppliedProvider;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsProvider;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class MaelstromEnchantment extends FFEnchantment {
	
	public static List<AbstractArrowEntity> loadedTridentEntities = new ArrayList<>(); //A list of all trident entities enchanted with maelstrom. They are removed from this list when they are in the ground.

	public MaelstromEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
		super(rarityIn, typeIn, slots);
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 3;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 6;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return this.getMinEnchantability(enchantmentLevel) + 6;
	}
	
	@SubscribeEvent
	public static void maelstromWorldTick(WorldTickEvent event) {
		World world = event.world;		
		Set<AbstractArrowEntity> tridentsToRemove = new HashSet<AbstractArrowEntity>();		
		for(AbstractArrowEntity t : loadedTridentEntities) {			
			if(!t.isAlive() || t.inGround || t == null) {
				tridentsToRemove.add(t);
			}
			else {
				t.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> {
					if(data.hasEnchantment(FFE.MAELSTROM)) {
						
						t.getCapability(MaelstromAppliedProvider.MAELSTROM_APPLIED_CAPABILITY).ifPresent(maelstromData ->
						{
							if(maelstromData.maelstromApplied() == true) {
								return;
							}
							else {
								List<LivingEntity> entitiesInAoE = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(t.getPosition().add(-2, -2, -2), t.getPosition().add(2, 2, 2)));
								int level = data.getEnchantments().get(FFE.MAELSTROM);
								entitiesInAoE.forEach((e) -> {
									if(e == t.func_234616_v_()) return;
									//if(e == world.getEntityByID(t.field_234610_c_)) return;
									e.attackEntityFrom(DamageSource.GENERIC, 2.0F * level);
									e.addVelocity((t.getPosX()-e.getPosX()) / 15, (t.getPosY()-e.getPosY()) / 15, (t.getPosZ()-e.getPosZ()) / 15);
									e.addVelocity(t.getMotion().getX() / 15, t.getMotion().getY() / 15, t.getMotion().getZ() / 15);
									e.velocityChanged = true;
								});
								doOtherEffects(world, t);
							}
						});						
					}
				});		
			}					
		}		
		loadedTridentEntities.removeAll(tridentsToRemove);
		tridentsToRemove.clear();
	}
	
	@SubscribeEvent
	public static void maelstromImpactEvent(ProjectileImpactEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof TridentEntity) {
			TridentEntity trident = (TridentEntity) entity;
			if(trident.func_234616_v_() instanceof LivingEntity)
			{				
				trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(enchantmentData ->
				{
					if(enchantmentData.hasEnchantment(FFE.MAELSTROM)) {
						trident.getCapability(MaelstromAppliedProvider.MAELSTROM_APPLIED_CAPABILITY).ifPresent(maelstromData ->
						{
							maelstromData.setMaelstromApplied(true);
						});
					}
				});
			}
		}
	}
	
	public static void doOtherEffects(World world, AbstractArrowEntity trident) {
		if(!world.isRemote()) {
			ServerWorld serverWorld = (ServerWorld) world;
			System.out.println(trident.rotationYaw * Math.PI/180);
			double x = trident.getPositionVec().getX() + 0.5*Math.cos(trident.ticksExisted)*Math.cos(trident.rotationYaw * Math.PI/180) + Math.sin(trident.rotationYaw * Math.PI/180);
			//double y = trident.getPositionVec().getY() + Math.sin(trident.ticksExisted);
			double y = trident.getPositionVec().getY() + 0.5*Math.sin(trident.ticksExisted);
			double z = trident.getPositionVec().getZ() - 0.5*Math.cos(trident.ticksExisted)*Math.sin(trident.rotationYaw * Math.PI/180) + Math.cos(trident.rotationYaw * Math.PI/180);
			if(trident.isInWaterOrBubbleColumn()) {
				serverWorld.spawnParticle(ParticleTypes.BUBBLE, x, y, z, 1, 0, 0, 0, 0);	
			}
			else {
				serverWorld.spawnParticle(ParticleTypes.SPLASH, x, y, z, 8, 0, 0, 0, 0);	
			}
				
		}
	}
}
