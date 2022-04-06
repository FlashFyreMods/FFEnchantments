package com.flashfyre.ffenchantments.enchantments;

import com.flashfyre.ffenchantments.FFEConfig;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

public class OutrushEnchantment extends FFEnchantment 
{
	public OutrushEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(rarity, type, slots, 
				() -> FFEConfig.canOutrushBeAppliedToItems, 
				() -> FFEConfig.canOutrushBeAppliedToBooks, 
				() -> FFEConfig.canOutrushGenerateInLoot, 
				() -> FFEConfig.canOutrushAppearInTrades);
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 5;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 1 + (enchantmentLevel - 1) * 8;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return this.getMinCost(enchantmentLevel) + 20;
	}
	
	public static void doExtraEffects(LivingEntity attacker, LivingEntity target) 
	{		
		Level world = attacker.level;
		if(!world.isClientSide()) 
		{
			if(attacker instanceof Player)
			{
				world.playSound(null, target.blockPosition(), SoundEvents.FIRE_EXTINGUISH, SoundSource.MASTER, 1.0F, 1.0F);
			}
			ServerLevel serverWorld = (ServerLevel) world;
			serverWorld.sendParticles(ParticleTypes.SPLASH, (double) target.blockPosition().getX() + 0.5D, (double) target.blockPosition().getY() + 0.75D, (double) target.blockPosition().getZ() + 0.5D, 24, 0, 0, 0, 0);
		}		
	}
}
