package com.flashfyre.ffenchants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.flashfyre.ffenchants.capability.MaelstromTridentReturningCapability;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsProvider;
import com.flashfyre.ffenchants.enchantments.InfernoEnchantment;
import com.flashfyre.ffenchants.enchantments.MaelstromEnchantment;
import com.flashfyre.ffenchants.packets.BuoyancyPacket;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class EventHandler {
	
	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		
		if(event.getObject() instanceof AbstractArrowEntity) {
			event.addCapability(new ResourceLocation(FFE.MOD_ID, "shooter_enchantments"), new ShooterEnchantmentsProvider());
		}
		if(event.getObject() instanceof TridentEntity) {
			event.addCapability(new ResourceLocation(FFE.MOD_ID, "maelstrom_applied"), new MaelstromTridentReturningCapability());
		}
	}
	
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {	
		if(event.getWorld().isClientSide()) return;
		
		if(event.getEntity() instanceof AbstractArrowEntity) {			
			AbstractArrowEntity projectile = (AbstractArrowEntity) event.getEntity();			
			if(projectile.getOwner() instanceof LivingEntity) {				
				LivingEntity shooter = (LivingEntity)projectile.getOwner();
				ItemStack item = shooter.getItemInHand(shooter.getUsedItemHand());
				
				projectile.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(cap -> { // If the capability is present, which it should always be
					if(!(event.getWorld() instanceof ServerWorld)) return;
					FFEWorldSavedData savedData = FFEWorldSavedData.get((ServerWorld) event.getWorld());
					
					
					
					int pillagingLevel = EnchantmentHelper.getItemEnchantmentLevel(FFE.PILLAGING, item);
					if(pillagingLevel > 0 && !cap.hasEnchantment(FFE.PILLAGING)) {
						cap.addEnchantment(FFE.PILLAGING, pillagingLevel);
					}
					
					int outrushLevel = EnchantmentHelper.getItemEnchantmentLevel(FFE.OUTRUSH, item);
					if(outrushLevel > 0 && !cap.hasEnchantment(FFE.OUTRUSH)) {
						cap.addEnchantment(FFE.OUTRUSH, outrushLevel);		
					}

					int infernoLevel = EnchantmentHelper.getItemEnchantmentLevel(FFE.INFERNO, item);
					if(infernoLevel > 0 && !cap.hasEnchantment(FFE.INFERNO)) {
						cap.addEnchantment(FFE.INFERNO, infernoLevel);
						projectile.setSecondsOnFire(100);
						savedData.addInfernoArrowUUID(projectile);
					}
					
					int maelstromLevel = EnchantmentHelper.getItemEnchantmentLevel(FFE.MAELSTROM, item);
					if(maelstromLevel > 0 && !cap.hasEnchantment(FFE.MAELSTROM) && projectile instanceof TridentEntity) {
						cap.addEnchantment(FFE.MAELSTROM, maelstromLevel);
						savedData.addMaelstromTridentUUID((TridentEntity) projectile);
					}
				});	
				
				int pointedLevel = EnchantmentHelper.getItemEnchantmentLevel(FFE.POINTED, item);
				if(pointedLevel > 0) {
					projectile.setBaseDamage(projectile.getBaseDamage() + (double)pointedLevel * 0.5D + 0.5D);
				}
				
			}						
		}
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if(FFE.getEnchantmentLevel(entity.getItemBySlot(EquipmentSlotType.FEET), FFE.ANCHORING_CURSE) > 0) {
			if(entity.isInWater()) {
				if(entity instanceof PlayerEntity) {
					PlayerEntity player = (PlayerEntity) entity;
					if(player.abilities.flying) {
						return;
					}
				}
				entity.onInsideBubbleColumn(true);
			}
		}
		if(entity.isInWaterRainOrBubble()) {
			ItemStack stack = entity.getItemInHand(Hand.MAIN_HAND);
			if(!stack.isDamaged()) return;
			int level = FFE.getEnchantmentLevel(stack, FFE.AQUATIC_REJUVENATION);
			if(level > 0) {
				if(entity.level.getGameTime() % (140 - (level * 40)) == 0) {
					stack.setDamageValue(stack.getDamageValue() - 1);
				}	
			}				
		}
		if(entity instanceof AbstractHorseEntity) {
			AbstractHorseEntity horse = (AbstractHorseEntity) entity;
			if(!horse.isInWater()) return;
			if(horse.isVehicle()) {
				ItemStack saddle = ItemStack.EMPTY;
				if(!horse.level.isClientSide) { //We can only get the saddle server side
					saddle = horse.inventory.getItem(0);
					if(!saddle.isEmpty()) {
						int level = FFE.getEnchantmentLevel(saddle, FFE.BUOYANCY_HORSE);
						if(level > 0) {
							Entity rider = horse.getControllingPassenger();
							if(rider instanceof PlayerEntity) {
								int id = horse.getId();
								FFE.PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> horse.getCommandSenderWorld().getChunkAt(horse.blockPosition())), new BuoyancyPacket(id));
							}							
						}						
					}
				}			
			}
		}
	}
	
	@SubscribeEvent
	public static void applyStrengthOnKill(LivingDeathEvent event) {
		DamageSource source = event.getSource();
		if(source == null || source.getEntity() == null) return;
		if(source.getEntity() instanceof LivingEntity) {
			LivingEntity user = (LivingEntity) source.getEntity();
			if(user instanceof FakePlayer) return;
			int level = FFE.getEnchantmentLevel(user.getItemBySlot(EquipmentSlotType.MAINHAND), FFE.BLOODLUST);
			if(level > 0) {
				int strength = 0;
				if (user.hasEffect(Effects.DAMAGE_BOOST)) {
					strength = user.getEffect(Effects.DAMAGE_BOOST).getAmplifier() + 1; //Add one level to their current strength level
				}				
				strength = Math.min(strength, 1 + level);	//Bloodlust 1 caps at strength 3, bloodlust 2 caps at strength 4
				user.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 80 + (40 * (level - 1)), strength, false, true));
			}					
		}
	}
	
	@SubscribeEvent
	public static void onProjectileImpact(ProjectileImpactEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof AbstractArrowEntity) {
			if(entity instanceof TridentEntity) {
				TridentEntity trident = (TridentEntity) entity;
				if(trident.getOwner() instanceof LivingEntity) {				
					trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(enchantmentData -> {
						if(enchantmentData.hasEnchantment(FFE.MAELSTROM)) {
							trident.getCapability(MaelstromTridentReturningCapability.MAELSTROM_APPLIED_CAPABILITY).ifPresent(maelstromData -> {								
								World world = event.getEntity().getCommandSenderWorld();
								if(world instanceof ServerWorld) {
									if(trident.isInWaterOrBubble()) {
										MaelstromEnchantment.apply((ServerWorld) world, trident, enchantmentData.getEnchantments().get(FFE.MAELSTROM));
									}									
								}
								maelstromData.setMaelstromApplied(true);								
							});
						}
					});
				}
			} else {
				if(entity.isUnderWater()) return;
				AbstractArrowEntity arrow = (AbstractArrowEntity) entity;
				if(arrow.getOwner() instanceof LivingEntity) {				
					arrow.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data ->
					{
						if(data.hasEnchantment(FFE.INFERNO)) {
							int level = data.getEnchantments().get(FFE.INFERNO);
							if(level > 0) {
								List<LivingEntity> entitiesInAoE = FFE.getEntitiesInAABB(arrow.level, level*1.5, arrow.position());
								for(LivingEntity e : entitiesInAoE) {
									if(!InfernoEnchantment.isEntityValidForIgnition(e, arrow)) continue;						
									e.setSecondsOnFire(10);
									InfernoEnchantment.spawnParticles(arrow.level, e.position(), 3);
								}
							}
						}
					});
				}
			}			
		}
	}
	
	@SubscribeEvent
	public static void worldTick(WorldTickEvent event) {
		
		if (event.phase == TickEvent.Phase.END || !(event.world instanceof ServerWorld)) return;
		
		ServerWorld sWorld = (ServerWorld) event.world;		
		FFEWorldSavedData savedData = FFEWorldSavedData.get(sWorld);		
		List<UUID> maelstromUUIDs = savedData.getMaelstromUUIDs();
		List<UUID> tridentsToRemove = new ArrayList<UUID>();
		
		for(UUID id : maelstromUUIDs) {
			Entity retrievedEntity = sWorld.getEntity(id);
			if (retrievedEntity instanceof TridentEntity) {
				TridentEntity trident = (TridentEntity) retrievedEntity;
				if(!trident.isInWaterOrBubble()) continue;
				if(!trident.isAlive() || trident.inGround || trident == null) {
					tridentsToRemove.add(id);
				} else {
					trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> {
						if(data.hasEnchantment(FFE.MAELSTROM)) {
							trident.getCapability(MaelstromTridentReturningCapability.MAELSTROM_APPLIED_CAPABILITY).ifPresent(maelstromData -> {
								if(!maelstromData.isMaelstromApplied()) { // return if the trident is on its way back to the player
									
									int level = data.getEnchantments().get(FFE.MAELSTROM);
									if(level > 0) {
										MaelstromEnchantment.apply(sWorld, trident, level);
									}
								}
							});
						}
					});
				}
			} else {
				tridentsToRemove.add(id);
			}
		}
		savedData.removeMaelstromTridentUUIDS(tridentsToRemove);
		
		List<UUID> infernoUUIDs = savedData.getInfernoUUIDs();
		List<UUID> arrowsToRemove = new ArrayList<UUID>();
		
		for(UUID id : infernoUUIDs) {
			Entity retrievedEntity = sWorld.getEntity(id);
			if (retrievedEntity instanceof AbstractArrowEntity) {
				AbstractArrowEntity arrow = (AbstractArrowEntity) retrievedEntity;
				if(arrow.isUnderWater()) continue;
				if(!arrow.isAlive() || arrow.inGround || arrow == null) {
					arrowsToRemove.add(id);
				} else {
					arrow.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> {
						if(data.hasEnchantment(FFE.INFERNO)) {
							int level = data.getEnchantments().get(FFE.INFERNO);
							if(level > 0) {
								List<LivingEntity> entitiesInAoE = FFE.getEntitiesInAABB(sWorld, level*0.75, arrow.position());
								for(LivingEntity e : entitiesInAoE) {
									if(!InfernoEnchantment.isEntityValidForIgnition(e, arrow)) continue;
									e.setSecondsOnFire(5);
								}
								InfernoEnchantment.spawnParticles(sWorld, arrow.position(), 1);
							}						
						}
					});
				}
			} else {
				arrowsToRemove.add(id);
			}
		}
		savedData.removeInfernoArrowUUIDS(arrowsToRemove);
	} 

}
