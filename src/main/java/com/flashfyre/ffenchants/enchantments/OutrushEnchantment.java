package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsProvider;
import com.flashfyre.ffenchants.misc.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class OutrushEnchantment extends Enchantment 
{
	public OutrushEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
		super(rarity, type, slots);
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 5;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 1 + (enchantmentLevel - 1) * 8;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) 
	{
		return this.getMinEnchantability(enchantmentLevel) + 20;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canOutrushBeAppliedToItems) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canOutrushBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canOutrushGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canOutrushAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !(FFEConfig.canOutrushBeAppliedToBooks || FFEConfig.canOutrushBeAppliedToItems);
	}
	
	@SubscribeEvent
	public static void damageFireEntities(LivingHurtEvent event) 
	{
		LivingEntity target = event.getEntityLiving();
		if(target.isImmuneToFire()) 
		{
			if(event.getSource().getImmediateSource() instanceof LivingEntity) //When a livingentity hits another living entity
			{			
				LivingEntity attacker = (LivingEntity) event.getSource().getImmediateSource();
				int level = FFE.getEnchantmentLevel(attacker.getHeldItem(Hand.MAIN_HAND), FFE.OUTRUSH);
				if(level > 0) 
				{
					event.setAmount(((float)level * 2.5F) + event.getAmount());
					doOtherEffects(attacker, target);										
				}			
			}
			else if(event.getSource().getImmediateSource() instanceof TridentEntity) //When a trident hits a living entity
			{
				TridentEntity trident = (TridentEntity) event.getSource().getImmediateSource();
				
				if(event.getSource().getTrueSource() instanceof LivingEntity) //If the trident has a thrower
				{
					LivingEntity thrower = (LivingEntity) event.getSource().getTrueSource();
					trident.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> 
					{		
						if(data.hasEnchantment(FFE.OUTRUSH)) 
						{
							
							int level = data.getEnchantments().get(FFE.OUTRUSH);
							event.setAmount(((float)level * 2.5F) + event.getAmount());
							if(target.isBurning()) {
								target.extinguish();
							}
							doOtherEffects(thrower, target);
						}				
					});					
				}									
			}
		}				
	}
	
	public static void doOtherEffects(LivingEntity attacker, LivingEntity target) 
	{		
		World world = attacker.world;
		if(!world.isRemote()) 
		{
			if(attacker instanceof PlayerEntity)
			{
				world.playSound(null, target.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.MASTER, 1.0F, 1.0F);
			}
			ServerWorld serverWorld = (ServerWorld) world;
			serverWorld.spawnParticle(ParticleTypes.SPLASH, (double) target.getPosition().getX() + 0.5D, (double) target.getPosition().getY() + 0.75D, (double) target.getPosition().getZ() + 0.5D, 24, 0, 0, 0, 0);
		}		
	}
}
