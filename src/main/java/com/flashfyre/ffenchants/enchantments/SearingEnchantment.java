package com.flashfyre.ffenchants.enchantments;

import java.util.Random;

import com.flashfyre.ffenchants.FFE;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class SearingEnchantment extends Enchantment {
	
	public SearingEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots);
		setRegistryName(FFE.MOD_ID, "searing");
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 10 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxEnchantability(int p_223551_1_) {
		return super.getMinEnchantability(p_223551_1_) + 50;
	}
	
	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() instanceof ArmorItem ? true : super.canApply(stack);
	}
	
	@Override
	protected boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.FROST_WALKER && ench != Enchantments.DEPTH_STRIDER;
	}
	
	@SubscribeEvent
	public static void burnAttackers(LivingHurtEvent event) {
		LivingEntity wearer = event.getEntityLiving();
		DamageSource source = event.getSource();
		if(!(source.getImmediateSource() instanceof LivingEntity)) return; //If immediate source isn't living, skip. e.g if skeleton shoots player.
		Entity attacker = source.getTrueSource();
		if(wearer.isInWater() || !(attacker instanceof LivingEntity)) return;
		attacker = (LivingEntity) attacker;
		if(attacker.isImmuneToFire()) return;
		Iterable<ItemStack> armour = wearer.getArmorInventoryList();
		int dur = 0;
		for(ItemStack stack : armour) {
			int level = EnchantmentHelper.getEnchantmentLevel(FFE.SEARING, stack);
			if(level < 1) continue; //Reset loop iteration and check next piece of armour			
			Random r = wearer.getRNG();
			if (r.nextInt(5 - level) == 0) {
				dur += (2 * level);
			}
		}
		
		if(dur > 0) {
			attacker.setFire(dur);
		}
	}
}
