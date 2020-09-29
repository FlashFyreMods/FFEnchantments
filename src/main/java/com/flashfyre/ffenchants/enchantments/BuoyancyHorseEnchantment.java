package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.BuoyancyPacket;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SaddleItem;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class BuoyancyHorseEnchantment extends Enchantment {

	public BuoyancyHorseEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
		setRegistryName(FFE.MOD_ID, "buoyancy_horse");
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	public int getMinEnchantability(int level) {
		return 20;
	}

	public int getMaxEnchantability(int level) {
		return 50;
	}
	
	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() instanceof SaddleItem;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack.getItem() instanceof SaddleItem;
	}
	
	@SubscribeEvent
	public static void buoyancyTick(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if(entity instanceof AbstractHorseEntity) {
			AbstractHorseEntity horse = (AbstractHorseEntity) entity;
			if(!horse.isInWater()) return;
			if(horse.isBeingRidden()) {
				ItemStack saddle = ItemStack.EMPTY;
				if(!horse.world.isRemote) { //We can only get the saddle server side
					saddle = horse.horseChest.getStackInSlot(0);
					if(!saddle.isEmpty()) {
						int level = FFE.getEnchantmentLevel(saddle, FFE.BUOYANCY_HORSE);
						if(level > 0) {
							Entity rider = horse.getControllingPassenger();
							if(rider instanceof PlayerEntity) {
								int id = horse.getEntityId();
								FFE.PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> horse.getEntityWorld().getChunkAt(horse.getPosition())), new BuoyancyPacket(id));
							}							
						}						
					}
				}			
			}
		}
	}
}
