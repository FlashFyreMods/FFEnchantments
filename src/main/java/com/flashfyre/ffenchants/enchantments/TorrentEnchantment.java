package com.flashfyre.ffenchants.enchantments;

import java.util.UUID;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class TorrentEnchantment extends Enchantment {
	
	public static final String torrent_modifier_uuid = "6ec63b9e-3854-4d1a-9b4f-1b9a568a8905";
	
	public TorrentEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots);
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
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canTorrentBeAppliedToItems) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canTorrentBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canTorrentGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canTorrentAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !FFEConfig.canTorrentBeAppliedToBooks && !FFEConfig.canTorrentBeAppliedToItems;
	}
	
	@SubscribeEvent
	public static void increaseSpeed(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if(entity.world.isRemote) return;
		ItemStack heldItem = entity.getHeldItem(Hand.MAIN_HAND);
		int level = FFE.getEnchantmentLevel(heldItem, FFE.TORRENT);
		if(!entity.isInWater()) {
			if(entity.getAttribute(ForgeMod.SWIM_SPEED.get()).getModifier(UUID.fromString(torrent_modifier_uuid)) != null) {
				entity.getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(UUID.fromString(torrent_modifier_uuid));
			}
			return;
		}
		if(level > 0) {
			if(entity.getAttribute(ForgeMod.SWIM_SPEED.get()).getModifier(UUID.fromString(torrent_modifier_uuid)) == null) {
				AttributeModifier modifier = new AttributeModifier(UUID.fromString(torrent_modifier_uuid), "torrent_enchantment", 0.2F + (level * 0.3), AttributeModifier.Operation.ADDITION);
				entity.getAttribute(ForgeMod.SWIM_SPEED.get()).applyPersistentModifier(modifier);
			}
			
		}
		else {
			if(entity.getAttribute(ForgeMod.SWIM_SPEED.get()).getModifier(UUID.fromString(torrent_modifier_uuid)) != null) {
				entity.getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(UUID.fromString(torrent_modifier_uuid));
			}		
		}	
	}
}
