package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class ButcheringEnchantment extends FFEnchantment {
	
	public ButcheringEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
		super(rarityIn, typeIn, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
	      return 5 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canButcheringBeAppliedToItems && stack.getItem() instanceof AxeItem) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canButcheringBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canButcheringGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canButcheringAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !(FFEConfig.canButcheringBeAppliedToBooks || FFEConfig.canButcheringBeAppliedToItems);
	}
	
	@SubscribeEvent
	public static void applyExtraDamage(LivingHurtEvent event) {
		if(event.getSource().getImmediateSource() instanceof LivingEntity) {
			LivingEntity attacker = (LivingEntity) event.getSource().getImmediateSource();
			int level = FFE.getEnchantmentLevel(attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND), FFE.BUTCHERING);
			if(level > 0) {
				if(event.getEntityLiving() instanceof AnimalEntity) {
					event.setAmount(event.getAmount() + level);
				}
			}
		}		
	}
}
