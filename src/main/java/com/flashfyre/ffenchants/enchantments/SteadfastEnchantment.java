package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.capability.SteadfastHandlerProvider;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class SteadfastEnchantment extends Enchantment {
	
	public SteadfastEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
		super(rarityIn, typeIn, slots);
		setRegistryName(FFE.MOD_ID, "steadfast");
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
	
	@SubscribeEvent
	public static void reduceKnockback(LivingKnockBackEvent event) {
		LivingEntity target = event.getEntityLiving();
		if(target instanceof FakePlayer) return;
		target.getCapability(SteadfastHandlerProvider.STEADFAST_HANDLER_CAPABILITY).ifPresent(targetData ->
		{			
			boolean steadfastHandled = targetData.getSteadfastHandled();
			if(steadfastHandled == true) { //Occurrs if an attacker was just knocked back by steadfast and is now the target
				targetData.setSteadfastHandled(false);
			}
			
			int level = FFE.getEnchantmentLevel(target.getItemStackFromSlot(EquipmentSlotType.CHEST), FFE.STEADFAST);
			if(level > 0) {
				float strength = event.getStrength();
				float newStrength =  strength - level / 10.0F;			
				event.setStrength(newStrength);
				if(steadfastHandled == true) { //Occurrs if an attacker was just knocked back by steadfast and is now the target
					return;
				}
				if(event.getAttacker() instanceof LivingEntity) {
					LivingEntity attacker = (LivingEntity) event.getAttacker();
					attacker.getCapability(SteadfastHandlerProvider.STEADFAST_HANDLER_CAPABILITY).ifPresent(attackerData ->
					{	
						attackerData.setSteadfastHandled(true); //This entity is about to be knocked back by steadfast
						attacker.velocityChanged = true;
						attacker.knockBack(target, 0.4F, target.getPosX() - attacker.getPosX(), target.getPosZ() - attacker.getPosZ());
					});
				}
			}
		});			
	}
}
