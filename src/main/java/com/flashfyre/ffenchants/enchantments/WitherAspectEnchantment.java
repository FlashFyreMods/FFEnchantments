package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.util.FakePlayer;

public class WitherAspectEnchantment extends FFEnchantment {

	public WitherAspectEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canWitherAspectBeAppliedToItems, 
				() -> FFEConfig.canWitherAspectBeAppliedToBooks, 
				() -> FFEConfig.canWitherAspectGenerateInLoot, 
				() -> FFEConfig.canWitherAspectAppearInTrades);
	}

	public int getMinCost(int enchantmentLevel) {
		return 10 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxCost(int p_223551_1_) {
		return super.getMinCost(p_223551_1_) + 50;
	}
	
	@Override
	public int getMaxLevel() {
	      return 2;
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != Enchantments.FIRE_ASPECT && ench != FFE.POISON_ASPECT;
	}
	
	@Override
	public void doPostAttack(LivingEntity user, Entity target, int level) {
		
		if(user instanceof FakePlayer || !(target instanceof LivingEntity)) return;
		
		LivingEntity livingTarget = (LivingEntity) target;		
		livingTarget.addEffect(new EffectInstance(Effects.WITHER, 200, level - 1, false, true));
	
	}
}
