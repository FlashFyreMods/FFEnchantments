package com.flashfyre.ffenchantments;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.flashfyre.ffenchantments.capability.MaelstromTridentReturningProvider;
import com.flashfyre.ffenchantments.capability.ShooterEnchantmentsProvider;
import com.flashfyre.ffenchantments.enchantments.EndCurseEnchantment;
import com.flashfyre.ffenchantments.enchantments.InfernoEnchantment;
import com.flashfyre.ffenchantments.enchantments.MaelstromEnchantment;
import com.flashfyre.ffenchantments.enchantments.OutrushEnchantment;
import com.flashfyre.ffenchantments.enchantments.QuicknessHorseEnchantment;
import com.flashfyre.ffenchantments.enchantments.SearingEnchantment;
import com.flashfyre.ffenchantments.enchantments.SteadfastEnchantment;
import com.flashfyre.ffenchantments.enchantments.TorrentEnchantment;
import com.flashfyre.ffenchantments.packets.BuoyancyPacket;
import com.flashfyre.ffenchantments.packets.LeapingToServerPacket;
import com.google.common.collect.Iterables;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class EventHandler {
	
	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		
		if(event.getObject() instanceof AbstractArrow) {
			event.addCapability(new ResourceLocation(FFE.MOD_ID, "shooter_enchantments"), new ShooterEnchantmentsProvider());
		}
		if(event.getObject() instanceof ThrownTrident) {
			event.addCapability(new ResourceLocation(FFE.MOD_ID, "maelstrom_trident_returning"), new MaelstromTridentReturningProvider());
		}
	}
	
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {	
		if(event.getWorld().isClientSide()) return;
		
		if(event.getEntity() instanceof AbstractArrow) {			
			AbstractArrow projectile = (AbstractArrow) event.getEntity();
			if(projectile.getOwner() instanceof LivingEntity) {				
				LivingEntity shooter = (LivingEntity)projectile.getOwner();
				ItemStack stack = shooter.getItemInHand(shooter.getUsedItemHand());
				
				projectile.getCapability(ShooterEnchantmentsProvider.SHOOTER_ENCHANTMENTS).ifPresent(cap -> { // If the capability is present, which it should always be
					if(!(event.getWorld() instanceof ServerLevel)) return;
					
					FFESavedData savedData = FFESavedData.getOrCreate((ServerLevel) event.getWorld());
					
					int pillagingLevel = stack.getEnchantmentLevel(FFE.Enchantments.PILLAGING.get());
					if(pillagingLevel > 0 && !cap.hasEnchantment(FFE.Enchantments.PILLAGING.get())) {
						cap.addEnchantment(FFE.Enchantments.PILLAGING.get(), pillagingLevel);
					}
					
					int outrushLevel = stack.getEnchantmentLevel(FFE.Enchantments.OUTRUSH.get());
					if(outrushLevel > 0 && !cap.hasEnchantment(FFE.Enchantments.OUTRUSH.get())) {
						cap.addEnchantment(FFE.Enchantments.OUTRUSH.get(), outrushLevel);		
					}

					int infernoLevel = stack.getEnchantmentLevel(FFE.Enchantments.INFERNO.get());
					if(infernoLevel > 0 && !cap.hasEnchantment(FFE.Enchantments.INFERNO.get())) {
						cap.addEnchantment(FFE.Enchantments.INFERNO.get(), infernoLevel);
						projectile.setSecondsOnFire(100);
						savedData.addInfernoArrowUUID(projectile);
					}
					
					int maelstromLevel = stack.getEnchantmentLevel(FFE.Enchantments.MAELSTROM.get());
					if(maelstromLevel > 0 && !cap.hasEnchantment(FFE.Enchantments.MAELSTROM.get()) && projectile instanceof ThrownTrident) {
						cap.addEnchantment(FFE.Enchantments.MAELSTROM.get(), maelstromLevel);
						savedData.addMaelstromTridentUUID((ThrownTrident) projectile);
					}
					
					int pointedLevel = stack.getEnchantmentLevel(FFE.Enchantments.POINTED.get());
					if(pointedLevel > 0) {
						projectile.setBaseDamage(projectile.getBaseDamage() + (double)pointedLevel * 0.5D + 0.5D);
					}
				});					
			}						
		}
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		TorrentEnchantment.increaseSpeed(entity);
		if(entity.getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(FFE.Enchantments.ANCHORING_CURSE.get()) > 0) {
			if(entity.isInWater()) {
				if(entity instanceof Player) {
					Player player = (Player) entity;
					if(!player.getAbilities().flying) {
						entity.onInsideBubbleColumn(true);
					}
				} else {
					entity.onInsideBubbleColumn(true);
				}				
			}
		}
		if(entity.isInWaterRainOrBubble()) {
			ItemStack stack = entity.getItemInHand(InteractionHand.MAIN_HAND);
			if(stack.isDamaged()) {
				int level = stack.getEnchantmentLevel(FFE.Enchantments.AQUATIC_REJUVENATION.get());
				if(level > 0) {
					if(entity.level.getGameTime() % (140 - (level * 40)) == 0) {
						stack.setDamageValue(stack.getDamageValue() - 1);
					}	
				}	
			}						
		}
		if(entity instanceof AbstractHorse) {
			AbstractHorse horse = (AbstractHorse) entity;
			if(!horse.level.isClientSide) {
				ItemStack stack = horse.inventory.getItem(0);
				int buoyancyLevel = stack.getEnchantmentLevel(FFE.Enchantments.BUOYANCY_HORSE.get());
				if(buoyancyLevel > 0) {
					if(horse.isInWater() && horse.isVehicle() && !stack.isEmpty()) {
						Entity rider = horse.getControllingPassenger();
						if(rider instanceof Player) {
							int id = horse.getId();
							FFE.PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> horse.getCommandSenderWorld().getChunkAt(horse.blockPosition())), new BuoyancyPacket(id));
						}
					}
				}
				int quicknessLevel = stack.getEnchantmentLevel(FFE.Enchantments.QUICKNESS_HORSE.get());
				AttributeInstance moveSpeed = horse.getAttribute(Attributes.MOVEMENT_SPEED);
				UUID uuid = UUID.fromString(QuicknessHorseEnchantment.QUICKNESS_MODIFIER_UUID);
				AttributeModifier modifier = new AttributeModifier(uuid, "quickness_horse_enchantment", 0.045F * quicknessLevel, AttributeModifier.Operation.ADDITION);
				
				if(quicknessLevel > 0) {
					if(!moveSpeed.hasModifier(modifier)) { // if the horse doesn't have the modifier, add it			
						moveSpeed.addPermanentModifier(modifier);
					}
				}
				else {
					if(moveSpeed.hasModifier(modifier)) { // if the horse still has the modifier, remove it
						moveSpeed.removeModifier(UUID.fromString(QuicknessHorseEnchantment.QUICKNESS_MODIFIER_UUID));
					}
				}
			}			
		}
	}
	
	/*@SubscribeEvent
	public static void applyStrengthOnKill(LivingDeathEvent event) {
		DamageSource source = event.getSource();
		if(source == null || source.getEntity() == null) return;
		if(source.getEntity() instanceof LivingEntity) {
			LivingEntity user = (LivingEntity) source.getEntity();
			if(user instanceof FakePlayer) return;
			int level = user.getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(FFE.Enchantments.BLOODLUST.get());
			if(level > 0) {
				int strength = 0;
				if (user.hasEffect(MobEffects.DAMAGE_BOOST)) {
					strength = user.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() + 1; //Add one level to their current strength level
				}				
				strength = Math.min(strength, 1 + level);	//Bloodlust 1 caps at strength 3, bloodlust 2 caps at strength 4
				user.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 80 + (40 * (level - 1)), strength, false, true));
			}					
		}
	}*/
	
	@SubscribeEvent
	public static void onProjectileImpact(ProjectileImpactEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof AbstractArrow) {
			if(entity instanceof ThrownTrident) {
				ThrownTrident trident = (ThrownTrident) entity;
				if(trident.getOwner() instanceof LivingEntity) {				
					trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_ENCHANTMENTS).ifPresent(enchantmentData -> {
						if(enchantmentData.hasEnchantment(FFE.Enchantments.MAELSTROM.get())) {
							trident.getCapability(MaelstromTridentReturningProvider.MAELSTROM_TRIDENT_RETURNING).ifPresent(maelstromData -> {								
								Level world = event.getEntity().getCommandSenderWorld();
								if(world instanceof ServerLevel) {
									if(trident.isInWaterOrBubble()) {
										MaelstromEnchantment.apply((ServerLevel) world, trident, enchantmentData.getEnchantments().get(FFE.Enchantments.MAELSTROM.get()));
									}									
								}
								//aelstromData.setMaelstromApplied(true);			
								maelstromData.setTridentReturning();
							});
						}
					});
				}
			} else {
				if(!entity.isUnderWater()) {
					AbstractArrow arrow = (AbstractArrow) entity;
					if(arrow.getOwner() instanceof LivingEntity) {				
						arrow.getCapability(ShooterEnchantmentsProvider.SHOOTER_ENCHANTMENTS).ifPresent(data ->
						{
							if(data.hasEnchantment(FFE.Enchantments.INFERNO.get())) {
								int level = data.getEnchantments().get(FFE.Enchantments.INFERNO.get());
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
	}
	
	@SubscribeEvent
	public static void worldTick(WorldTickEvent event) {
		
		if (event.phase == TickEvent.Phase.END || !(event.world instanceof ServerLevel)) return;
		
		ServerLevel sWorld = (ServerLevel) event.world;
		FFESavedData savedData = FFESavedData.getOrCreate(sWorld);		
		List<UUID> maelstromUUIDs = savedData.getMaelstromUUIDs();
		List<UUID> tridentsToRemove = new ArrayList<UUID>();
		
		for(UUID id : maelstromUUIDs) {
			Entity retrievedEntity = sWorld.getEntity(id);
			if (retrievedEntity instanceof ThrownTrident) {
				ThrownTrident trident = (ThrownTrident) retrievedEntity;
				if(!trident.isInWaterOrBubble()) continue;
				if(!trident.isAlive() || trident.inGround || trident == null) {
					tridentsToRemove.add(id);
				} else {
					trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_ENCHANTMENTS).ifPresent(data -> {
						if(data.hasEnchantment(FFE.Enchantments.MAELSTROM.get())) {
							trident.getCapability(MaelstromTridentReturningProvider.MAELSTROM_TRIDENT_RETURNING).ifPresent(maelstromData -> {
								if(!maelstromData.isTridentReturning()) { // return if the trident is not on its way back to the player
									
									int level = data.getEnchantments().get(FFE.Enchantments.MAELSTROM.get());
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
			if (retrievedEntity instanceof AbstractArrow) {
				AbstractArrow arrow = (AbstractArrow) retrievedEntity;
				if(arrow.isUnderWater()) continue;
				if(!arrow.isAlive() || arrow.inGround || arrow == null) {
					arrowsToRemove.add(id);
				} else {
					arrow.getCapability(ShooterEnchantmentsProvider.SHOOTER_ENCHANTMENTS).ifPresent(data -> {
						if(data.hasEnchantment(FFE.Enchantments.INFERNO.get())) {
							int level = data.getEnchantments().get(FFE.Enchantments.INFERNO.get());
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
	
	@SubscribeEvent
	public static void onCrit(CriticalHitEvent event) {
		if(event.isVanillaCritical()) {
			int level = event.getEntityLiving().getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(FFE.Enchantments.WEIGHTED_BLADE.get());
			if(level > 0) {
				if(event.getTarget() instanceof LivingEntity) {
					LivingEntity target = (LivingEntity) event.getTarget();
					RandomSource r = event.getPlayer().getRandom();
					if(r.nextInt(5 - level) == 0) {
						target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 80 * level, level - 1, false, true));
					}					
				}
			}
		}				
	}
	
	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event) {
		LivingEntity livingEntity = event.getEntityLiving();
		int teleportAttempts = 0;
		Iterable<ItemStack> armorItems = livingEntity.getArmorSlots();
    	if (armorItems != null && Iterables.size(armorItems) > 0) {
    		for(ItemStack stack : armorItems) {
    			if(stack.getEnchantmentLevel(FFE.Enchantments.END_CURSE.get()) > 0) {
    				teleportAttempts += 1;
    				System.out.println();
    			}
    		}
        }
    	if(teleportAttempts > 0) {
    		for(int attempt = 1; attempt <= teleportAttempts; attempt++) {
    			boolean success = EndCurseEnchantment.attemptTeleport(livingEntity);
    			if (success == true) break;
    		}
    	}    	
	}
	
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		if(event.getSource().getDirectEntity() instanceof LivingEntity) { // if direct attacker is living
			LivingEntity attacker = (LivingEntity) event.getSource().getDirectEntity();
			LivingEntity target = event.getEntityLiving();
			int butcheringLevel = attacker.getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(FFE.Enchantments.BUTCHERING.get());
			if(butcheringLevel > 0) {
				if(event.getEntityLiving() instanceof Animal) {
					event.setAmount(event.getAmount() + butcheringLevel);
				}
			}
			int outrushLevel = attacker.getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(FFE.Enchantments.OUTRUSH.get());
			if(outrushLevel > 0) {				
				if(target.fireImmune()) {
					event.setAmount(((float)outrushLevel * 2.5F) + event.getAmount());
					OutrushEnchantment.doExtraEffects(attacker, target);
					if(target.isOnFire()) {
						target.clearFire();
					}
				}
				else {
					if(target.isOnFire()) {
						target.clearFire();
						OutrushEnchantment.doExtraEffects(attacker, target);
					}
				}				
			}
			
			if(!(attacker.fireImmune() || target.isInWaterOrBubble())) {
				int burnDuration = SearingEnchantment.calculateBurnDuration(target);
				if (burnDuration > 0) {
					attacker.setSecondsOnFire(burnDuration);
				}				
			}
		}
		else if(event.getSource().getDirectEntity() instanceof ThrownTrident) // When a thrown trident hits a living entity
		{
			ThrownTrident trident = (ThrownTrident) event.getSource().getDirectEntity();
			
			if(event.getSource().getEntity() instanceof LivingEntity) //If the trident has a thrower
			{
				LivingEntity thrower = (LivingEntity) event.getSource().getEntity();
				trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_ENCHANTMENTS).ifPresent(data -> 
				{		
					if(data.hasEnchantment(FFE.Enchantments.OUTRUSH.get()))
					{						
						int level = data.getEnchantments().get(FFE.Enchantments.OUTRUSH.get());
						if(level > 0) {
							LivingEntity target = event.getEntityLiving();
							if(target.fireImmune()) {
								event.setAmount(((float)level * 2.5F) + event.getAmount());								
								OutrushEnchantment.doExtraEffects(thrower, target);
								if(target.isOnFire()) {
									target.clearFire();
								}
							}
							else {
								if(target.isOnFire()) {
									target.clearFire();
									OutrushEnchantment.doExtraEffects(thrower, target);
								}
							}							
						}						
					}				
				});					
			}									
		} else if(event.getSource().getDirectEntity() instanceof AbstractArrow) {			
			AbstractArrow arrow = (AbstractArrow) event.getSource().getDirectEntity();
			arrow.getCapability(ShooterEnchantmentsProvider.SHOOTER_ENCHANTMENTS).ifPresent(data -> {				
				if(data.hasEnchantment(FFE.Enchantments.PILLAGING.get())) {
					if(event.getEntityLiving().getMobType() == MobType.ILLAGER)	{
						int level = data.getEnchantments().get(FFE.Enchantments.PILLAGING.get());
						event.setAmount(event.getAmount() + (2.5F * level));
					}					
				}				
			});			
		}
	}
	
	@SubscribeEvent
	public static void applyKnockbackResistance(LivingEquipmentChangeEvent event) {
		LivingEntity wearer = event.getEntityLiving();
		if(event.getSlot() == EquipmentSlot.CHEST) { // If chestplate is put in armour slot
			int levelTo = event.getTo().getEnchantmentLevel(FFE.Enchantments.STEADFAST.get());
			int levelFrom = event.getFrom().getEnchantmentLevel(FFE.Enchantments.STEADFAST.get());
			if(levelTo == levelFrom) return; //If the levels are the same we don't need to adjust anything
			UUID uuid = UUID.fromString(SteadfastEnchantment.STEADFAST_MODIFIER_ID);
			AttributeInstance knockbackResistance = wearer.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
			if(levelFrom > 0) { // If the item taken out was enchanted with steadfast, remove the modifier
				knockbackResistance.removeModifier(uuid);
			}
			if(levelTo > 0) { // If the item put in is enchanted with steadfast, add the modifier
				AttributeModifier modifier = new AttributeModifier(uuid, "steadfast_enchantment", 0.2F * levelTo, AttributeModifier.Operation.ADDITION);
				if(!knockbackResistance.hasModifier(modifier)) {					
					knockbackResistance.addPermanentModifier(modifier);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void knockBackAttacker(LivingAttackEvent event) {
		LivingEntity target = event.getEntityLiving();
		if(target instanceof FakePlayer) return;
		int level = target.getItemBySlot(EquipmentSlot.CHEST).getEnchantmentLevel(FFE.Enchantments.STEADFAST.get());
		if(level > 0) {
			Entity source = event.getSource().getDirectEntity();
			if(source instanceof LivingEntity) {
				LivingEntity attacker = (LivingEntity) source;				
				attacker.knockback(0.15F * level, target.getX() - attacker.getX(), target.getZ() - attacker.getZ());
			}
		}
	}
	
	/**
	 *  This event is client side only in the case of horses jumping while being controlled by the player.
	 *  This is important because the horse's inventory (where we need to check for enchantments) is only stored server side.
	 */
	@SubscribeEvent
	public static void onLivingJump(LivingJumpEvent event) {
		
		LivingEntity entity = event.getEntityLiving();
		
		if(!entity.level.isClientSide) return;
		
		if(entity instanceof AbstractHorse) {
			FFE.PacketHandler.INSTANCE.sendToServer(new LeapingToServerPacket());
		}
	}
}
