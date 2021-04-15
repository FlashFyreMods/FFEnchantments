package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.FFEConfig;
import com.flashfyre.ffenchants.packets.LeapingToServerPacket;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SaddleItem;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class LeapingHorseEnchantment extends Enchantment {

	public LeapingHorseEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
		super(rarityIn, typeIn, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}
	
	@Override
	public int getMinEnchantability(int level) {
		return 5 + (level - 1) * 9;
	}
	
	@Override
	public int getMaxEnchantability(int level) {
		return this.getMinEnchantability(level) + 15;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(stack.getItem() instanceof SaddleItem) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canLeapingBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canLeapingGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canLeapingAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !FFEConfig.canLeapingBeAppliedToBooks;
	}
	
	//This event is client side only in the case of horses jumping while being controlled by the player, hence we need to send a packet to the server
	@SubscribeEvent
	public static void onLivingJump(LivingJumpEvent event) {
		
		LivingEntity entity = event.getEntityLiving();
		
		if(!entity.world.isRemote) return;
		
		if(entity instanceof AbstractHorseEntity) {
			FFE.PacketHandler.INSTANCE.sendToServer(new LeapingToServerPacket());
		}
	}
	
	public static void increaseJumpHeight() {
		
	}
	
	public static double getYVelocity(int level) {
		return 0.12 * level;
	}
}
