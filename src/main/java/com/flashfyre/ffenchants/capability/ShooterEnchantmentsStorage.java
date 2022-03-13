package com.flashfyre.ffenchants.capability;

import java.util.Set;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.registries.ForgeRegistries;

public class ShooterEnchantmentsStorage implements IStorage<IShooterEnchantments> 
{

	@Override
	public INBT writeNBT(Capability<IShooterEnchantments> capability, IShooterEnchantments instance, Direction side) 
	{
		CompoundNBT tag = new CompoundNBT();
		instance.getEnchantments().forEach((enchantment, level) -> 
		{
			tag.putInt(enchantment.getRegistryName().toString(), level);
		});
		return tag;
	}

	@Override
	public void readNBT(Capability<IShooterEnchantments> capability, IShooterEnchantments instance, Direction side, INBT nbt)
	{
		CompoundNBT tag = (CompoundNBT) nbt;
		Set<String> set = (tag).getAllKeys();
		set.forEach(key ->
		{
			Enchantment ench = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(key));
			instance.addEnchantment(ench, tag.getInt(key));
		});
	}
}
