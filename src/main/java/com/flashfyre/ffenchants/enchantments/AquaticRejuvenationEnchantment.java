package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class AquaticRejuvenationEnchantment extends FFEnchantment {
	public AquaticRejuvenationEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots);
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 2;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 5 + 20 * (enchantmentLevel - 1);
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}
	
	/*
	 * Returns false on enchantments that should be incompatible.
	 * Used in:
	 *  Anvils
	 * 	Enchantment tables
	 *  EnchantmentHelper#addRandomEnchantment
	 *  Enchant command
	 */
	@Override
	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.MENDING;
	}
	
	/*
	 * Checks if itemstack is the right type to recieve the enchantment
	 * Used in:
	 * 	Enchantment tables
	 * 	EnchantmentHelper#addRandomEnchantment
	 * 	Enchantment#canApply
	 */
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canAquaticRejuvenationBeAppliedToItems) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canAquaticRejuvenationBeAppliedToBooks;
	}
	
	/*
	 * Whether or not this enchantment can generate in loot AND whether or not it can appear in the enchantment table.
	 * False will prevent it from appearing in the enchantment table as well.
	 */
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canAquaticRejuvenationGenerateInLoot;
	}
	
	/*
	 * Whether or not a villager can trade an enchanted book with this enchantment
	 */
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canAquaticRejuvenationAppearInTrades;
	}
	/*
	 * If true:
	 * - Prevents enchantment from appearing in enchantment table on both books and items
	 * - Prevents villagers from selling items enchanted with this enchantment
	 * - Prevents all other instances of items being enchanted with this item aside from the EnchantWithLevels loot function IF treasure is enabled, which is set in the loot table.
	 * - Increases price of enchanted book in villager trades
	 */	
	@Override
	public boolean isTreasureEnchantment() {
		return !(FFEConfig.canAquaticRejuvenationBeAppliedToBooks || FFEConfig.canAquaticRejuvenationBeAppliedToItems);
	}
	
	@SubscribeEvent
	public static void repairTick(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if(entity.isInWaterRainOrBubbleColumn()) {
			ItemStack stack = entity.getHeldItem(Hand.MAIN_HAND);
			if(!stack.isDamaged()) return;
			int level = FFE.getEnchantmentLevel(stack, FFE.AQUATIC_REJUVENATION);
			if(level > 0) {
				if(entity.world.getGameTime() % (140 - (level * 40)) == 0) {
					stack.setDamage(stack.getDamage() - 1);
				}	
			}				
		}		
	}	
}
