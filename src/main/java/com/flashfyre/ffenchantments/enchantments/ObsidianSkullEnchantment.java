package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFE;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class ObsidianSkullEnchantment extends FFEnchantment {

	public ObsidianSkullEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canObsidianSkullBeAppliedToItems, 
				() -> FFEConfig.canObsidianSkullBeAppliedToBooks, 
				() -> FFEConfig.canObsidianSkullGenerateInLoot, 
				() -> FFEConfig.canObsidianSkullAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 4;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 6;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 6;
	}
	
	@Override
	public int getDamageProtection(int level, DamageSource source) {
		if(source == DamageSource.FLY_INTO_WALL) {
			return level * 4;
		}
		return 0;
	}
}
