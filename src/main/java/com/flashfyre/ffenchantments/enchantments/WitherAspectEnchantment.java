package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;
import com.flashfyre.ffenchantments.FFECore;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.util.FakePlayer;

public class WitherAspectEnchantment extends FFEnchantment {

	public WitherAspectEnchantment(Rarity rarity) {
		super(rarity, 2, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND,
				() -> FFEConfig.isWitherAspectDiscoverable,
				() -> FFEConfig.isWitherAspectTradeable,
				() -> FFEConfig.isWitherAspectTreasure);
	}

	public int getMinCost(int enchantmentLevel) {
		return 10 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxCost(int p_223551_1_) {
		return super.getMinCost(p_223551_1_) + 50;
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != Enchantments.FIRE_ASPECT && ench != FFECore.Enchantments.POISON_ASPECT.get();
	}
	
	@Override
	public void doPostAttack(LivingEntity user, Entity target, int level) {
		
		if(user instanceof FakePlayer || !(target instanceof LivingEntity)) return;
		
		LivingEntity livingTarget = (LivingEntity) target;		
		livingTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, level - 1, false, true));
	
	}
}
