package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.util.FakePlayer;

public class PoisonAspectEnchantment extends FFEnchantment {
	
	public PoisonAspectEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots);
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 10 + 20 * (enchantmentLevel - 1);
	}	
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}
	
	@Override
	public int getMaxLevel() {
	      return 2;
	}
	
	@Override
	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.FIRE_ASPECT && ench != FFE.WITHER_ASPECT;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canPoisonAspectBeAppliedToItems) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canPoisonAspectBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canPoisonAspectGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canPoisonAspectAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !FFEConfig.canPoisonAspectBeAppliedToBooks && !FFEConfig.canPoisonAspectBeAppliedToItems;
	}
	
	@Override
	public void onEntityDamaged(LivingEntity user, Entity target, int level) {		
		if(user instanceof FakePlayer || !(target instanceof LivingEntity)) return;
		LivingEntity livingTarget = (LivingEntity) target;		
		livingTarget.addPotionEffect(new EffectInstance(Effects.POISON, 100, level - 1, false, true));
	}

}
