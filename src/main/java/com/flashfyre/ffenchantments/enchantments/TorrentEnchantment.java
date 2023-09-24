package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class TorrentEnchantment extends FFEnchantment {
	
	public static final String TORRENT_MODIFIER_UUID = "6ec63b9e-3854-4d1a-9b4f-1b9a568a8905";
	
	public TorrentEnchantment(Rarity rarity) {
		super(rarity, 3, EnchantmentCategory.TRIDENT, EquipmentSlot.MAINHAND, 
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
}
