package com.flashfyre.ffenchants.enchantments;

import java.util.UUID;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class SteadfastEnchantment extends Enchantment {
	
	public static final String steadfast_modifier_uuid = "c9b42190-12b8-4015-96b3-d0df6c89812c";
	
	public SteadfastEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
		super(rarityIn, typeIn, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public int getMinEnchantability(int level) {
		return 10 + 20 * (level - 1);
	}
	
	@Override
	public int getMaxEnchantability(int level) {
		return super.getMinEnchantability(level) + 50;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canSteadfastBeAppliedToItems) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canSteadfastBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canSteadfastGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canSteadfastAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !FFEConfig.canSteadfastBeAppliedToBooks && !FFEConfig.canSteadfastBeAppliedToItems;
	}
	
	@SubscribeEvent
	public static void applyKnockbackResistance(LivingEquipmentChangeEvent event) {
		LivingEntity wearer = event.getEntityLiving();
		if(event.getSlot() == EquipmentSlotType.CHEST) { // If chestplate is put in armour slot
			int levelTo = FFE.getEnchantmentLevel(event.getTo(), FFE.STEADFAST);
			int levelFrom = FFE.getEnchantmentLevel(event.getFrom(), FFE.STEADFAST);
			if(levelTo == levelFrom) return; //If the levels are the same we don't need to adjust anything
			if(levelFrom > 0) { //If the item taken out was enchanted with steadfast, remove the modifier
				wearer.getAttribute(Attributes.KNOCKBACK_RESISTANCE).removeModifier(UUID.fromString(steadfast_modifier_uuid));
			}
			if(levelTo > 0) { //If the item put in is enchanted with steadfast
				if(wearer.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getModifier(UUID.fromString(steadfast_modifier_uuid)) == null) {
					AttributeModifier modifier = new AttributeModifier(UUID.fromString(steadfast_modifier_uuid), "steadfast_enchantment", 0.2F * levelTo, AttributeModifier.Operation.ADDITION);
					wearer.getAttribute(Attributes.KNOCKBACK_RESISTANCE).applyPersistentModifier(modifier);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void knockBackAttacker(LivingAttackEvent event) {
		LivingEntity target = event.getEntityLiving();
		if(target instanceof FakePlayer) return;
		int level = FFE.getEnchantmentLevel(target.getItemStackFromSlot(EquipmentSlotType.CHEST), FFE.STEADFAST);
		if(level > 0) {
			Entity source = event.getSource().getImmediateSource();
			if(source == null) return;
			if(source instanceof LivingEntity) {
				LivingEntity attacker = (LivingEntity) source;				
				attacker.applyKnockback(0.15F * level, target.getPosX() - attacker.getPosX(), target.getPosZ() - attacker.getPosZ());
			}
		}
	}
}
