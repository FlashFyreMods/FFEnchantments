package com.flashfyre.ffenchantments.enchantments;

import java.util.Map.Entry;

import com.flashfyre.ffenchantments.FFECore;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

public class SearingEnchantment extends FFEnchantment {
	
	public SearingEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(2, rarity, type, slots,
				() -> FFEConfig.isSearingDiscoverable,
				() -> FFEConfig.isSearingTradeable,
				() -> FFEConfig.isSearingTreasure);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 10 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxCost(int p_223551_1_) {
		return super.getMinCost(p_223551_1_) + 50;
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) {
		return stack.getItem() instanceof ArmorItem ? true : super.canEnchant(stack);
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != Enchantments.FROST_WALKER && ench != Enchantments.DEPTH_STRIDER;
	}
	
	@Override
	public void doPostAttack(LivingEntity p_44686_, Entity p_44687_, int p_44688_) {
		super.doPostAttack(p_44686_, p_44687_, p_44688_);
	}
	
	public static int calculateBurnDuration(LivingEntity wearer) {
		Iterable<ItemStack> armour = wearer.getArmorSlots();
		int burnDuration = 0;
		for(ItemStack stack : armour) {
			int level = stack.getEnchantmentLevel(FFECore.Enchantments.SEARING.get());
			if (level > 0 && wearer.getRandom().nextFloat() < level * 0.225F) {
				burnDuration += 2;				
			}
		}
		
		if(burnDuration > 0) {
			Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.getRandomItemWith(Enchantments.THORNS, wearer);
			if(entry != null) {
				entry.getValue().hurtAndBreak(2, wearer, (livingEntity) -> {
					livingEntity.broadcastBreakEvent(entry.getKey());
		        });
			}
		}
		
		return burnDuration;
	}
}
