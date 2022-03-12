package com.flashfyre.ffenchantments.enchantments;

import java.util.Random;

import com.flashfyre.ffenchantments.FFE;
import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
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
	
	@SubscribeEvent
	public static void burnAttackers(LivingHurtEvent event) {
		LivingEntity wearer = event.getEntityLiving();
		DamageSource source = event.getSource();
		if(!(source.getDirectEntity() instanceof LivingEntity)) return; //If immediate source isn't living, skip. e.g if skeleton shoots player.
		Entity attacker = source.getEntity();
		if(wearer.isInWater() || !(attacker instanceof LivingEntity)) return;
		attacker = (LivingEntity) attacker;
		if(attacker.fireImmune()) return;
		Iterable<ItemStack> armour = wearer.getArmorSlots();
		int dur = 0;
		for(ItemStack stack : armour) {
			int level = EnchantmentHelper.getItemEnchantmentLevel(FFE.SEARING, stack);
			if(level < 1) continue; //Reset loop iteration and check next piece of armour			
			Random r = wearer.getRandom();
			if (r.nextInt(5 - level) == 0) {
				dur += (2 * level);
			}
		}
		
		if(dur > 0) {
			attacker.setSecondsOnFire(dur);
		}
	}
}
