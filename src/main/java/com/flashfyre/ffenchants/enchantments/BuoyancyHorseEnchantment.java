package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;
import com.flashfyre.ffenchants.packets.BuoyancyPacket;

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
public class BuoyancyHorseEnchantment extends FFEnchantment {

	public BuoyancyHorseEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
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
	public boolean canApplyAtEnchantingTable(ItemStack stack) { //Doesn't actually work as saddle items aren't enchantable and have an enchantability of 0
		if(stack.getItem() instanceof SaddleItem) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !FFEConfig.canBuoyancyBeAppliedToBooks;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canBuoyancyBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canBuoyancyGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canBuoyancyAppearInTrades;
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
