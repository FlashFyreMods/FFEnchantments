package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.FFEConfig;
import com.flashfyre.ffenchants.capability.ArrowOriginalMotionMagnitudeCapability;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsProvider;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class SharpshooterEnchantment extends FFEnchantment 
{
	public SharpshooterEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) 
	{
		super(rarity, type, slots);
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 3;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 12 + (enchantmentLevel - 1) * 20;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) 
	{
		return 50;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if(FFEConfig.canSharpshooterBeAppliedToItems) {
			return super.canApplyAtEnchantingTable(stack);
		}
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return FFEConfig.canSharpshooterBeAppliedToBooks;
	}
	
	@Override
	public boolean canGenerateInLoot() {
		return FFEConfig.canSharpshooterGenerateInLoot;
	}
	
	@Override
	public boolean canVillagerTrade() {
		return FFEConfig.canSharpshooterAppearInTrades;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return !FFEConfig.canSharpshooterBeAppliedToBooks && !FFEConfig.canSharpshooterBeAppliedToItems;
	}
	
	public static void setNewVelocity(LivingEntity shooter, AbstractArrowEntity arrow, int level) {
		if(!arrow.getIsCritical()) return;
		arrow.setMotion(0, 0, 0);		
		float originalVelocity = getOriginalVelocity(arrow);
		float originalAccuracy = getOriginalAccuracy(arrow);
		arrow.func_234612_a_(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, originalVelocity + calcVelocityIncrease(level), originalAccuracy - calcInaccuracyDecrease(level));
	}
	
	public static float getOriginalVelocity(AbstractArrowEntity arrow) {
		return arrow.getPersistentData().contains("originalVelocity") ? arrow.getPersistentData().getFloat("originalVelocity") : 3.0F;
	}
	
	public static float getOriginalAccuracy(AbstractArrowEntity arrow) {
		return arrow.getPersistentData().contains("originalAccuracy") ? arrow.getPersistentData().getFloat("originalAccuracy") : 1.0F;
	}
	
	@SubscribeEvent
	public static void reduceSharpshooterDamage(LivingHurtEvent event) {
		if(event.getSource().getImmediateSource() instanceof AbstractArrowEntity) {
			
			AbstractArrowEntity projectile = (AbstractArrowEntity) event.getSource().getImmediateSource();
			if(projectile.func_234616_v_() instanceof LivingEntity)	{
				
				projectile.getCapability(ShooterEnchantmentsProvider.SHOOTER_INFO_CAPABILITY).ifPresent(data -> {

					if(data.hasEnchantment(FFE.SHARPSHOOTER)) {
						int level = data.getEnchantments().get(FFE.SHARPSHOOTER);
						if(level > 0)
						{	
							projectile.getCapability(ArrowOriginalMotionMagnitudeCapability.ARROW_MOTION_CAP).ifPresent(cap -> {
								int i = MathHelper.ceil(MathHelper.clamp(cap.getMagnitude() * projectile.getDamage(), 0.0D, 2.147483647E9D));
								long j = (long)projectile.world.rand.nextInt(i / 2 + 2);
						        i = (int)Math.min(j + (long)i, 2147483647L);
						        event.setAmount(i);
							});							
						}
					}					
				});
			}
		}
	}
	
	public static float calcVelocityIncrease(int level) {
		return 0.4F * level;
	}
	
	public static float calcInaccuracyDecrease(int level) {
		return Math.min(0.2F * level, 1.0F);
	}
}
