package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;
import com.flashfyre.ffenchants.packets.LeapingToServerPacket;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class LeapingHorseEnchantment extends FFEnchantment {

	public LeapingHorseEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
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
		
		if(entity instanceof AbstractHorse) {
			FFE.PacketHandler.INSTANCE.sendToServer(new LeapingToServerPacket());
		}
	}
	
	public static double getYVelocity(int level) {
		return 0.12 * level;
	}
}
