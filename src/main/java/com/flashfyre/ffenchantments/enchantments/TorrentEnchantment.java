package com.flashfyre.ffenchantments.enchantments;

import java.util.UUID;

import com.flashfyre.ffenchantments.FFECore;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.common.ForgeMod;

public class TorrentEnchantment extends FFEnchantment {
	
	public static final String TORRENT_MODIFIER_UUID = "6ec63b9e-3854-4d1a-9b4f-1b9a568a8905";
	
	public TorrentEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(3, rarity, type, slots, 
				() -> FFEConfig.isTorrentDiscoverable,
				() -> FFEConfig.isTorrentTradeable,
				() -> FFEConfig.isTorrentTreasure);
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
		int level = entity.getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(FFECore.Enchantments.TORRENT.get());
		if(!entity.isInWater()) {
			if(entity.getAttribute(ForgeMod.SWIM_SPEED.get()).getModifier(UUID.fromString(TORRENT_MODIFIER_UUID)) != null) {
				entity.getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(UUID.fromString(TORRENT_MODIFIER_UUID));
			}
			return;
		}
		if(level > 0) {
			if(entity.getAttribute(ForgeMod.SWIM_SPEED.get()).getModifier(UUID.fromString(TORRENT_MODIFIER_UUID)) == null) {
				AttributeModifier modifier = new AttributeModifier(UUID.fromString(TORRENT_MODIFIER_UUID), "torrent_enchantment", (level * 0.3333333333333F), AttributeModifier.Operation.ADDITION);
				entity.getAttribute(ForgeMod.SWIM_SPEED.get()).addPermanentModifier(modifier);
			}
			
		}
		else {
			if(entity.getAttribute(ForgeMod.SWIM_SPEED.get()).getModifier(UUID.fromString(TORRENT_MODIFIER_UUID)) != null) {
				entity.getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(UUID.fromString(TORRENT_MODIFIER_UUID));
			}		
		}	
	}
}
