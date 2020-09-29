package com.flashfyre.ffenchants.capability;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;

public interface IShooterEnchantments 
{	
	Map<Enchantment, Integer> getEnchantments();
	void setEnchantments(Map<Enchantment, Integer> enchantments);
	void addEnchantment(Enchantment ench, int level);
	boolean hasEnchantment(Enchantment ench);
}
