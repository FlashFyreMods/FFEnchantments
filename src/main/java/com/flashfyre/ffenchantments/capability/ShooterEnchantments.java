package com.flashfyre.ffenchantments.capability;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;

public class ShooterEnchantments {
	
	private Map<Enchantment, Integer> enchantments;

	public ShooterEnchantments() {
		this.enchantments = new HashMap<Enchantment, Integer>();
	}
	
	public Map<Enchantment, Integer> getEnchantments() {
		return this.enchantments;
	}
	
	public void setEnchantments(Map<Enchantment, Integer> enchantments) {
		this.enchantments = enchantments;
	}

	public void addEnchantment(Enchantment ench, int level) {
		this.enchantments.put(ench, level);	
	}

	public boolean hasEnchantment(Enchantment ench) {
		return this.enchantments.containsKey(ench);
	}
	
	public void saveNBTData(CompoundTag nbt) {
		ListTag list = new ListTag();
		this.enchantments.forEach((enchantment, level) -> {
			CompoundTag innerTag = new CompoundTag();
			innerTag.putString("Name", enchantment.getRegistryName().toString());
			innerTag.putInt("Level", level);
			list.add(innerTag);
		});
		nbt.put("Enchantments", list);
	}
	
	public void loadNBTData(CompoundTag nbt) {
		ListTag list = nbt.getList("Enchantments", Tag.TAG_COMPOUND);
		for(int i = 0; i < list.size(); ++i) {
			CompoundTag compound = list.getCompound(i);
			ResourceLocation enchLocation = new ResourceLocation(compound.getString("Name"));
			if(ForgeRegistries.ENCHANTMENTS.containsKey(enchLocation)) {
				Enchantment ench = ForgeRegistries.ENCHANTMENTS.getValue(enchLocation);
				int level = compound.getInt("Level");
				this.enchantments.put(ench, level);
			}
			
		}
	}
}
