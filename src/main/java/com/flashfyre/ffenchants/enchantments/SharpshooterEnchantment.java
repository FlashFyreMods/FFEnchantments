package com.flashfyre.ffenchants.enchantments;

import com.flashfyre.ffenchants.FFE;
import com.flashfyre.ffenchants.misc.FFEConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class SharpshooterEnchantment extends Enchantment 
{
	public SharpshooterEnchantment(Enchantment.Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) 
	{
		super(rarity, type, slots);
		setRegistryName(FFE.MOD_ID, "sharpshooter");
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
	public boolean isTreasureEnchantment() {
		return !FFEConfig.canSharpshooterBeAppliedToBooks && !FFEConfig.canSharpshooterBeAppliedToItems;
	}
	
	public static void setNewVelocity(LivingEntity shooter, AbstractArrowEntity arrow, int level) 
	{
		arrow.shoot(arrow.getMotion().x, arrow.getMotion().y, arrow.getMotion().z, 3.15F + (0.3F * level), 1.0F - (0.2F * level)); //We get the arrow's existing motion and set it to the new velocity based on the enchantment
	}
}
