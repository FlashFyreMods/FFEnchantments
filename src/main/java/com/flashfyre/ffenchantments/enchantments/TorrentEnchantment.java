package com.flashfyre.ffenchantments.enchantments;

import java.util.UUID;

import com.flashfyre.ffenchantments.FFE;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class TorrentEnchantment extends FFEnchantment {
	
	public static final String torrent_modifier_uuid = "6ec63b9e-3854-4d1a-9b4f-1b9a568a8905";
	
	public TorrentEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canTorrentBeAppliedToItems, 
				() -> FFEConfig.canTorrentBeAppliedToBooks, 
				() -> FFEConfig.canTorrentGenerateInLoot, 
				() -> FFEConfig.canTorrentAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 3;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 6;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 6;
	}
	
	public static void increaseSpeed(LivingEntity entity) {
		int level = entity.getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel( FFE.Enchantments.TORRENT.get());
		if(!entity.isInWater()) {
			if(entity.getAttribute(ForgeMod.SWIM_SPEED.get()).getModifier(UUID.fromString(torrent_modifier_uuid)) != null) {
				entity.getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(UUID.fromString(torrent_modifier_uuid));
			}
			return;
		}
		if(level > 0) {
			if(entity.getAttribute(ForgeMod.SWIM_SPEED.get()).getModifier(UUID.fromString(torrent_modifier_uuid)) == null) {
				AttributeModifier modifier = new AttributeModifier(UUID.fromString(torrent_modifier_uuid), "torrent_enchantment", (level * 0.3333333333333F), AttributeModifier.Operation.ADDITION);
				entity.getAttribute(ForgeMod.SWIM_SPEED.get()).addPermanentModifier(modifier);
			}
			
		}
		else {
			if(entity.getAttribute(ForgeMod.SWIM_SPEED.get()).getModifier(UUID.fromString(torrent_modifier_uuid)) != null) {
				entity.getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(UUID.fromString(torrent_modifier_uuid));
			}		
		}	
	}
}
