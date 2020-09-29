package com.flashfyre.ffenchants.capability;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.enchantment.Enchantment;

public class ShooterEnchantments implements IShooterEnchantments 
{	
	private Map<Enchantment, Integer> enchantments;

	public ShooterEnchantments()
	{
		this.enchantments = new HashMap<Enchantment, Integer>();
	}
	
	@Override
	public Map<Enchantment, Integer> getEnchantments() 
	{
		return this.enchantments;
	}
	
	@Override
	public void setEnchantments(Map<Enchantment, Integer> enchantments) 
	{
		this.enchantments = enchantments;
	}

	@Override
	public void addEnchantment(Enchantment ench, int level) 
	{
		this.enchantments.put(ench, level);	
	}

	@Override
	public boolean hasEnchantment(Enchantment ench) 
	{
		return this.enchantments.containsKey(ench);
	}
}
