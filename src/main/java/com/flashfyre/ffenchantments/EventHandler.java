package com.flashfyre.ffenchantments;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.flashfyre.ffenchantments.capability.MaelstromTridentReturningProvider;
import com.flashfyre.ffenchantments.capability.ShooterEnchantments;
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
import net.minecraft.world.InteractionHand;
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
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid=FFECore.MOD_ID)
public class EventHandler {
	
	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		
		if(event.getObject() instanceof AbstractArrow) {
			event.addCapability(new ResourceLocation(FFECore.MOD_ID, "shooter_enchantments"), new ShooterEnchantmentsProvider());
		}
		if(event.getObject() instanceof ThrownTrident) {
			event.addCapability(new ResourceLocation(FFECore.MOD_ID, "maelstrom_trident_returning"), new MaelstromTridentReturningProvider());
		}
	}
	
	/**
	 * This event is used to add custom enchantments to a projectile's capability, based on the enchantments on the shooter's held item.
	 * This is so that we can make the enchantment still do things after the shooter has changed weapons.
	 * @param event
	 */
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinLevelEvent event) {	
		if(event.getLevel().isClientSide()) return;
		
		if(event.getEntity() instanceof AbstractArrow projectile) {			
			if(projectile.getOwner() instanceof LivingEntity shooter) {				
				ItemStack stack = shooter.getItemInHand(shooter.getUsedItemHand());
				
				projectile.getCapability(ShooterEnchantmentsProvider.SHOOTER_ENCHANTMENTS).ifPresent(cap -> {
					if(!(event.getLevel() instanceof ServerLevel)) return;
					
					FFESavedData savedData = FFESavedData.getOrCreate((ServerLevel) event.getLevel());
					
					checkAndAddEnchantment(cap, stack, FFECore.Enchantments.PILLAGING.get());
					checkAndAddEnchantment(cap, stack, FFECore.Enchantments.OUTRUSH.get());

					int infernoLevel = stack.getEnchantmentLevel(FFECore.Enchantments.INFERNO.get());
					if(infernoLevel > 0 && !cap.hasEnchantment(FFECore.Enchantments.INFERNO.get())) {
						cap.addEnchantment(FFECore.Enchantments.INFERNO.get(), infernoLevel);
						projectile.setSecondsOnFire(100);
						savedData.addInfernoArrowUUID(projectile);
					}
					
					int maelstromLevel = stack.getEnchantmentLevel(FFECore.Enchantments.MAELSTROM.get());
					if(maelstromLevel > 0 && !cap.hasEnchantment(FFECore.Enchantments.MAELSTROM.get()) && projectile instanceof ThrownTrident) {
						cap.addEnchantment(FFECore.Enchantments.MAELSTROM.get(), maelstromLevel);
						savedData.addMaelstromTridentUUID((ThrownTrident) projectile);
					}
					
					int pointedLevel = stack.getEnchantmentLevel(FFECore.Enchantments.POINTED.get());
					if(pointedLevel > 0) {
						projectile.setBaseDamage(projectile.getBaseDamage() + (double)pointedLevel * 0.5D + 0.5D);
					}
				});					
			}						
		}
	}
	
	private static void checkAndAddEnchantment(ShooterEnchantments cap, ItemStack stack, Enchantment ench) {
		int level = stack.getEnchantmentLevel(ench);
		if(level > 0 && !cap.hasEnchantment(ench)) {
			cap.addEnchantment(ench, level);
		}
	}
	
	@SubscribeEvent
	public static void onLivingTick(LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		TorrentEnchantment.increaseSpeed(entity);
		if(entity.getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(FFECore.Enchantments.ANCHORING_CURSE.get()) > 0) {
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
				int level = stack.getEnchantmentLevel(FFECore.Enchantments.AQUATIC_REJUVENATION.get());
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
				int buoyancyLevel = stack.getEnchantmentLevel(FFECore.Enchantments.BUOYANCY_HORSE.get());
				if(buoyancyLevel > 0) {
					if(horse.isInWater() && horse.isVehicle() && !stack.isEmpty()) {
						Entity rider = horse.getControllingPassenger();
						if(rider instanceof Player) {
							int id = horse.getId();
							FFECore.PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> horse.getCommandSenderWorld().getChunkAt(horse.blockPosition())), new BuoyancyPacket(id));
						}
					}
				}
				int quicknessLevel = stack.getEnchantmentLevel(FFECore.Enchantments.QUICKNESS_HORSE.get());
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
						if(enchantmentData.hasEnchantment(FFECore.Enchantments.MAELSTROM.get())) {
							trident.getCapability(MaelstromTridentReturningProvider.MAELSTROM_TRIDENT_RETURNING).ifPresent(maelstromData -> {								
								Level world = event.getEntity().getCommandSenderWorld();
								if(world instanceof ServerLevel) {
									if(trident.isInWaterOrBubble()) {
										MaelstromEnchantment.apply((ServerLevel) world, trident, enchantmentData.getEnchantments().get(FFECore.Enchantments.MAELSTROM.get()));
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
							if(data.hasEnchantment(FFECore.Enchantments.INFERNO.get())) {
								int level = data.getEnchantments().get(FFECore.Enchantments.INFERNO.get());
								if(level > 0) {
									List<LivingEntity> entitiesInAoE = FFECore.getEntitiesInAABB(arrow.level, level*1.5, arrow.position());
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
	public static void worldTick(LevelTickEvent event) {
		
		if (event.phase == TickEvent.Phase.END || !(event.level instanceof ServerLevel)) return;
		
		ServerLevel sWorld = (ServerLevel) event.level;
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
						if(data.hasEnchantment(FFECore.Enchantments.MAELSTROM.get())) {
							trident.getCapability(MaelstromTridentReturningProvider.MAELSTROM_TRIDENT_RETURNING).ifPresent(maelstromData -> {
								if(!maelstromData.isTridentReturning()) { // return if the trident is not on its way back to the player
									
									int level = data.getEnchantments().get(FFECore.Enchantments.MAELSTROM.get());
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
						if(data.hasEnchantment(FFECore.Enchantments.INFERNO.get())) {
							int level = data.getEnchantments().get(FFECore.Enchantments.INFERNO.get());
							if(level > 0) {
								List<LivingEntity> entitiesInAoE = FFECore.getEntitiesInAABB(sWorld, level*0.75, arrow.position());
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
			int level = event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(FFECore.Enchantments.WEIGHTED_BLADE.get());
			if(level > 0) {
				if(event.getTarget() instanceof LivingEntity livingTarget) {
					livingTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60 * level, 0, false, true));
				}
			}
		}				
	}
	
	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event) {
		LivingEntity livingEntity = event.getEntity();
		int teleportAttempts = 0;
		Iterable<ItemStack> armorItems = livingEntity.getArmorSlots();
    	if (armorItems != null && Iterables.size(armorItems) > 0) {
    		for(ItemStack stack : armorItems) {
    			if(stack.getEnchantmentLevel(FFECore.Enchantments.END_CURSE.get()) > 0) {
    				teleportAttempts += 1;
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
			LivingEntity target = event.getEntity();
			int butcheringLevel = attacker.getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(FFECore.Enchantments.BUTCHERING.get());
			if(butcheringLevel > 0) {
				if(event.getEntity() instanceof Animal) {
					event.setAmount(event.getAmount() + butcheringLevel);
				}
			}
			int outrushLevel = attacker.getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(FFECore.Enchantments.OUTRUSH.get());
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
					if(data.hasEnchantment(FFECore.Enchantments.OUTRUSH.get()))
					{						
						int level = data.getEnchantments().get(FFECore.Enchantments.OUTRUSH.get());
						if(level > 0) {
							LivingEntity target = event.getEntity();
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
				if(data.hasEnchantment(FFECore.Enchantments.PILLAGING.get())) {
					if(event.getEntity().getMobType() == MobType.ILLAGER)	{
						int level = data.getEnchantments().get(FFECore.Enchantments.PILLAGING.get());
						event.setAmount(event.getAmount() + (2.5F * level));
					}					
				}				
			});			
		}
	}
	
	@SubscribeEvent
	public static void applyKnockbackResistance(LivingEquipmentChangeEvent event) {
		LivingEntity wearer = event.getEntity();
		if(event.getSlot() == EquipmentSlot.CHEST) { // If chestplate is put in armour slot
			int levelTo = event.getTo().getEnchantmentLevel(FFECore.Enchantments.STEADFAST.get());
			int levelFrom = event.getFrom().getEnchantmentLevel(FFECore.Enchantments.STEADFAST.get());
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
		LivingEntity target = event.getEntity();
		if(target instanceof FakePlayer) return;
		int level = target.getItemBySlot(EquipmentSlot.CHEST).getEnchantmentLevel(FFECore.Enchantments.STEADFAST.get());
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
		
		LivingEntity entity = event.getEntity();
		
		if(!entity.level.isClientSide) return;
		
		if(entity instanceof AbstractHorse) {
			FFECore.PacketHandler.INSTANCE.sendToServer(new LeapingToServerPacket());
		}
	}
}
