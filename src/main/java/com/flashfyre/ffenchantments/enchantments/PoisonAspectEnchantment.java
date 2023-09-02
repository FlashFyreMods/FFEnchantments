package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFECore;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.util.FakePlayer;

public class PoisonAspectEnchantment extends FFEnchantment {
	
	public PoisonAspectEnchantment(Rarity rarity) {
		super(rarity, 2, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND, 
				() -> FFEConfig.isPoisonAspectDiscoverable, 
				() -> FFEConfig.isPoisonAspectTradeable, 
				() -> FFEConfig.isPoisonAspectTreasure);
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
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != Enchantments.FIRE_ASPECT && ench != FFECore.Enchantments.WITHER_ASPECT.get();
	}
	
	@Override
	public void doPostAttack(LivingEntity user, Entity target, int level) {		
		if(user instanceof FakePlayer || !(target instanceof LivingEntity)) return;
		LivingEntity livingTarget = (LivingEntity) target;
		livingTarget.addEffect(new MobEffectInstance(MobEffects.POISON, 100, level - 1, false, true));
	}

}
