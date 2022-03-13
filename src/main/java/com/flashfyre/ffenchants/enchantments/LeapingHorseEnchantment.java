package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;
import com.flashfyre.ffenchants.packets.LeapingToServerPacket;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class LeapingHorseEnchantment extends FFEnchantment {

	public LeapingHorseEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> true, 
				() -> FFEConfig.canLeapingBeAppliedToBooks, 
				() -> FFEConfig.canLeapingGenerateInLoot, 
				() -> FFEConfig.canLeapingAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}
	
	@Override
	public int getMinCost(int level) {
		return 5 + (level - 1) * 9;
	}
	
	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 15;
	}
	
	/**
	 *  This event is client side only in the case of horses jumping while being controlled by the player.
	 *  This is important because the horse's inventory (where we need to check for enchantments) is only stored server side.
	 */
	@SubscribeEvent
	public static void onLivingJump(LivingJumpEvent event) {
		
		LivingEntity entity = event.getEntityLiving();
		
		if(!entity.level.isClientSide) return;
		
		if(entity instanceof AbstractHorseEntity) {
			FFE.PacketHandler.INSTANCE.sendToServer(new LeapingToServerPacket());
		}
	}
	
	public static double getYVelocity(int level) {
		return 0.12 * level;
	}
}
