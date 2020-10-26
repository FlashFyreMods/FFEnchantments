package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.FFEConfig;

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

public class WitherAspectEnchantment extends Enchantment {

	public WitherAspectEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots);
		setRegistryName(FFE.MOD_ID, "wither_aspect");
	}
	
	public int getMinEnchantability(int enchantmentLevel) {
		return 10 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxEnchantability(int p_223551_1_) {
		return super.getMinEnchantability(p_223551_1_) + 50;
	}
	
	@Override
	public int getMaxLevel() {
	      return 2;
	}
	
	@Override
	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.FIRE_ASPECT && ench != FFE.POISON_ASPECT;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canWitherAspectBeAppliedToItems) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canWitherAspectBeAppliedToBooks;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !FFEConfig.canWitherAspectBeAppliedToBooks && !FFEConfig.canWitherAspectBeAppliedToItems;
	}
	
	@Override
	public void onEntityDamaged(LivingEntity user, Entity target, int level) {
		
		if(user instanceof FakePlayer || !(target instanceof LivingEntity)) return;
		
		LivingEntity livingTarget = (LivingEntity) target;		
		livingTarget.addPotionEffect(new EffectInstance(Effects.WITHER, 200, level - 1, false, true));
	
	}
	
	
	
}
