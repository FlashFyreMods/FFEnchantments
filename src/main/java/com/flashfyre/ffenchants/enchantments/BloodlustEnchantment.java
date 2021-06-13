package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class BloodlustEnchantment extends FFEnchantment {

	public BloodlustEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 5 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMaxEnchantability(enchantmentLevel) + 50;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canBloodlustBeAppliedToItems && stack.getItem() instanceof AxeItem) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canBloodlustBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canBloodlustGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canBloodlustAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !(FFEConfig.canBloodlustBeAppliedToBooks || FFEConfig.canBloodlustBeAppliedToItems);
	}
	
	@SubscribeEvent
	public static void applyStrengthOnKill(LivingDeathEvent event) {
		DamageSource source = event.getSource();
		if(source == null || source.getTrueSource() == null) return;
		if(source.getTrueSource() instanceof LivingEntity) {
			LivingEntity user = (LivingEntity) source.getTrueSource();
			if(user instanceof FakePlayer) return;
			int level = FFE.getEnchantmentLevel(user.getItemStackFromSlot(EquipmentSlotType.MAINHAND), FFE.BLOODLUST);
			if(level > 0) {
				int strength = 0;
				if (user.isPotionActive(Effects.STRENGTH)) {
					strength = user.getActivePotionEffect(Effects.STRENGTH).getAmplifier() + 1; //Add one level to their current strength level
				}				
				strength = Math.min(strength, 1 + level);	//Bloodlust 1 caps at strength 3, bloodlust 2 caps at strength 4
				user.addPotionEffect(new EffectInstance(Effects.STRENGTH, 80 + (40 * (level - 1)), strength, false, true));
			}					
		}
	}
}
