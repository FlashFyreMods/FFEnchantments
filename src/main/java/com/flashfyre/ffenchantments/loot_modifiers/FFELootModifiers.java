package com.flashfyre.ffenchantments.loot_modifiers;

import com.flashfyre.ffenchantments.FFECore;
import com.mojang.serialization.Codec;

import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FFELootModifiers {
	
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM_CODECS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, FFECore.MOD_ID);
	
	public static final RegistryObject<Codec<EnchantItemLootModifier>> ENCHANT_ITEM = GLM_CODECS.register("enchant_item", () -> EnchantItemLootModifier.CODEC);
	public static final RegistryObject<Codec<ReplaceWithTableModifier>> REPLACE_WITH_TABLE = GLM_CODECS.register("replace_with_table", () -> ReplaceWithTableModifier.CODEC);
	public static final RegistryObject<Codec<AddFromExtraTableModifier>> ADD_FROM_EXTRA_TABLE = GLM_CODECS.register("add_from_extra_table", () -> AddFromExtraTableModifier.CODEC);
}
