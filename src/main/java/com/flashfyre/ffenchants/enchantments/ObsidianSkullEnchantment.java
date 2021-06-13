package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class ObsidianSkullEnchantment extends FFEnchantment {

	public ObsidianSkullEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
		super(rarityIn, typeIn, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 4;
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
	public boolean canApplyTogether(Enchantment ench) {
		if (ench instanceof ProtectionEnchantment) {
			return false;
		} else {
			return super.canApplyTogether(ench);
		}
    }
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canObsidianSkullBeAppliedToItems) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canObsidianSkullBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canObsidianSkullGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canObsidianSkullAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !(FFEConfig.canObsidianSkullBeAppliedToBooks || FFEConfig.canObsidianSkullBeAppliedToItems);
	}
	
	@SubscribeEvent
	public static void reduceImpactDamage(LivingHurtEvent event) {	
		int level = FFE.getEnchantmentLevel(event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD), FFE.OBSIDIAN_SKULL);
		if(level > 0) {
			if(event.getSource() == DamageSource.FLY_INTO_WALL) {
				event.setAmount(event.getAmount() - Math.max(0,(level * 2)));
			}
		}
	}
}
