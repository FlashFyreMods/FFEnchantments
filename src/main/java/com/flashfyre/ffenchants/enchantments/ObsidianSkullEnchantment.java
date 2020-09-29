package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class ObsidianSkullEnchantment extends Enchantment {

	public ObsidianSkullEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
		super(rarityIn, typeIn, slots);
		setRegistryName(FFE.MOD_ID, "obsidian_skull");
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
	public boolean isTreasureEnchantment() {
		return true;
	}
	
	@Override
	public boolean canApplyTogether(Enchantment ench) {
		if (ench instanceof ProtectionEnchantment) {
			return false;
		} else {
			return super.canApplyTogether(ench);
		}
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
