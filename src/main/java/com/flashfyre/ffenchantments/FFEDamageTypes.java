package com.flashfyre.ffenchantments;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class FFEDamageTypes {
	
	public static final ResourceKey<DamageType> MAELSTROM = key("maelstrom");
	
	private static ResourceKey<DamageType> key(String id) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(FFECore.MOD_ID, id));
	}

}
