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

public class PoisonAspectEnchantment extends FFEnchantment {
	
	public PoisonAspectEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canPoisonAspectBeAppliedToItems, 
				() -> FFEConfig.canPoisonAspectBeAppliedToBooks, 
				() -> FFEConfig.canPoisonAspectGenerateInLoot, 
				() -> FFEConfig.canPoisonAspectAppearInTrades);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 10 + 20 * (enchantmentLevel - 1);
	}	
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return super.getMinCost(enchantmentLevel) + 50;
	}
	
	@Override
	public int getMaxLevel() {
	      return 2;
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != Enchantments.FIRE_ASPECT && ench != FFE.WITHER_ASPECT;
	}
	
	@Override
	public void doPostAttack(LivingEntity user, Entity target, int level) {		
		if(user instanceof FakePlayer || !(target instanceof LivingEntity)) return;
		LivingEntity livingTarget = (LivingEntity) target;		
		livingTarget.addEffect(new EffectInstance(Effects.POISON, 100, level - 1, false, true));
	}

}
