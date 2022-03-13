package com.flashfyre.ffenchants.enchantments;

import java.util.UUID;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class SteadfastEnchantment extends FFEnchantment {
	
	public static final String STEADFAST_MODIFIER_UUID = "c9b42190-12b8-4015-96b3-d0df6c89812c";
	
	public SteadfastEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canSteadfastBeAppliedToItems, 
				() -> FFEConfig.canSteadfastBeAppliedToBooks, 
				() -> FFEConfig.canSteadfastGenerateInLoot, 
				() -> FFEConfig.canSteadfastAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public int getMinCost(int level) {
		return 10 + 20 * (level - 1);
	}
	
	@Override
	public int getMaxCost(int level) {
		return super.getMinCost(level) + 50;
	}
	
	@SubscribeEvent
	public static void applyKnockbackResistance(LivingEquipmentChangeEvent event) {
		LivingEntity wearer = event.getEntityLiving();
		if(event.getSlot() == EquipmentSlotType.CHEST) { // If chestplate is put in armour slot
			int levelTo = FFE.getEnchantmentLevel(event.getTo(), FFE.STEADFAST);
			int levelFrom = FFE.getEnchantmentLevel(event.getFrom(), FFE.STEADFAST);
			if(levelTo == levelFrom) return; //If the levels are the same we don't need to adjust anything
			if(levelFrom > 0) { //If the item taken out was enchanted with steadfast, remove the modifier
				wearer.getAttribute(Attributes.KNOCKBACK_RESISTANCE).removeModifier(UUID.fromString(STEADFAST_MODIFIER_UUID));
			}
			if(levelTo > 0) { // If the item put in is enchanted with steadfast
				if(wearer.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getModifier(UUID.fromString(STEADFAST_MODIFIER_UUID)) == null) {
					AttributeModifier modifier = new AttributeModifier(UUID.fromString(STEADFAST_MODIFIER_UUID), "steadfast_enchantment", 0.2F * levelTo, AttributeModifier.Operation.ADDITION);
					wearer.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(modifier);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void knockBackAttacker(LivingAttackEvent event) {
		LivingEntity target = event.getEntityLiving();
		if(target instanceof FakePlayer) return;
		int level = FFE.getEnchantmentLevel(target.getItemBySlot(EquipmentSlotType.CHEST), FFE.STEADFAST);
		if(level > 0) {
			Entity source = event.getSource().getDirectEntity();
			if(source == null) return;
			if(source instanceof LivingEntity) {
				LivingEntity attacker = (LivingEntity) source;				
				attacker.knockback(0.15F * level, target.getX() - attacker.getX(), target.getZ() - attacker.getZ());
			}
		}
	}
}
