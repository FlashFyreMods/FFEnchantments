package com.flashfyre.ffenchants.enchantments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.capability.MaelstromTridentReturningCapability;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsProvider;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
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
		for(AbstractArrowEntity trident : loadedTridentEntities) {			
			if(!trident.isAlive() || trident.inGround || trident == null) {
				tridentsToRemove.add(trident);
			}
			else {
				if(!trident.isInWaterOrBubbleColumn()) return;
				trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> {
					if(data.hasEnchantment(FFE.MAELSTROM)) {						
						trident.getCapability(MaelstromTridentReturningCapability.MAELSTROM_APPLIED_CAPABILITY).ifPresent(maelstromData ->
						{
							if(maelstromData.isMaelstromApplied() == true) {
								return;
							}
							else {
								List<LivingEntity> entitiesInAoE = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(trident.getPosition().add(-2, -2, -2), trident.getPosition().add(2, 2, 2)));
								int level = data.getEnchantments().get(FFE.MAELSTROM);
								if(level > 0) {
									entitiesInAoE.forEach((e) -> {
										if(e == trident.func_234616_v_()) return; // Return if entity is the trident's shooter
										if(e instanceof TameableEntity) {
											TameableEntity pet = (TameableEntity) e;
											if(pet.getOwner() == trident.func_234616_v_()) return; // Return if entity is a pet of the trident's shooter
										}
										e.attackEntityFrom(FFE.causeMaelstromDamage(trident, trident.func_234616_v_()), 2.0F * level);
										e.addVelocity((trident.getPosX()-e.getPosX()) / 15, (trident.getPosY()-e.getPosY()) / 15, (trident.getPosZ()-e.getPosZ()) / 15);
										e.addVelocity(trident.getMotion().getX() / 15, trident.getMotion().getY() / 15, trident.getMotion().getZ() / 15);
										e.velocityChanged = true;
									});
									spawnParticles(world, trident);
								}								
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
	public static void maelstromImpactEvent(ProjectileImpactEvent event) { // Sets maelstrom applied capability to true, so that mobs aren't moved or damaged when a trident with loyalty comes back
		Entity entity = event.getEntity();
		if(entity instanceof TridentEntity) {
			TridentEntity trident = (TridentEntity) entity;
			if(trident.func_234616_v_() instanceof LivingEntity) {				
				trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(enchantmentData ->
				{
					if(enchantmentData.hasEnchantment(FFE.MAELSTROM)) {
						trident.getCapability(MaelstromTridentReturningCapability.MAELSTROM_APPLIED_CAPABILITY).ifPresent(maelstromData ->
						{
							maelstromData.setMaelstromApplied(true);
						});
					}
				});
			}
		}
	}
	
	public static void spawnParticles(World world, AbstractArrowEntity trident) {
		if(!world.isRemote()) {
			ServerWorld serverWorld = (ServerWorld) world;
			double x = trident.getPositionVec().getX() + 0.5*Math.cos(trident.ticksExisted)*Math.cos(trident.rotationYaw * Math.PI/180) + Math.sin(trident.rotationYaw * Math.PI/180);
			double y = trident.getPositionVec().getY() + 0.5*Math.sin(trident.ticksExisted);
			//double y = trident.getPositionVec().getY() - 0.5*Math.sin(trident.ticksExisted)*Math.sin(trident.rotationPitch * Math.PI/180) + Math.cos(trident.rotationPitch * Math.PI/180);
			double z = trident.getPositionVec().getZ() - 0.5*Math.cos(trident.ticksExisted)*Math.sin(trident.rotationYaw * Math.PI/180) + Math.cos(trident.rotationYaw * Math.PI/180);
			serverWorld.spawnParticle(ParticleTypes.BUBBLE, x, y, z, 1, 0, 0, 0, 0);				
		}
	}
	
	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.RIPTIDE;
	}
}
