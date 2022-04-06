package com.flashfyre.ffenchantments.enchantments;

import java.util.Random;

import com.flashfyre.ffenchantments.FFE;
import com.flashfyre.ffenchantments.FFEConfig;

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
		super(rarity, type, slots, 
				() -> FFEConfig.canSearingBeAppliedToItems, 
				() -> FFEConfig.canSearingBeAppliedToBooks, 
				() -> FFEConfig.canSearingGenerateInLoot, 
				() -> FFEConfig.canSearingAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
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
		if(FFEConfig.canSearingBeAppliedToItems) {
			return stack.getItem() instanceof ArmorItem ? true : super.canEnchant(stack);
		}		
		return false;
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		return super.checkCompatibility(ench) && ench != Enchantments.FROST_WALKER && ench != Enchantments.DEPTH_STRIDER;
	}
	
	public static int calculateBurnDuration(LivingEntity wearer) {
		Iterable<ItemStack> armour = wearer.getArmorSlots();
		int burnDuration = 0;
		for(ItemStack stack : armour) {
			int level = EnchantmentHelper.getItemEnchantmentLevel(FFE.Enchantments.SEARING.get(), stack);
			if(level < 1) continue; //Reset loop iteration and check next piece of armour			
			Random r = wearer.getRandom();
			if (r.nextInt(5 - level) == 0) {
				burnDuration += (2 * level);
			}
		}
		
		return burnDuration;
	}
}
