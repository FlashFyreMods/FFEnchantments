package com.flashfyre.ffenchantments.loot_modifiers;

import com.flashfyre.ffenchantments.FFE;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FFELootModifierSerializers {
	
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, FFE.MOD_ID);
	
	public static final RegistryObject<GlobalLootModifierSerializer<?>> ENCHANT_SADDLES = LOOT_MODIFIER_SERIALIZERS.register("enchant_saddles", () -> new EnchantSaddlesLootModifier.Serializer());
	public static final RegistryObject<GlobalLootModifierSerializer<?>> LOOT_TABLE_INJECT = LOOT_MODIFIER_SERIALIZERS.register("loot_table_inject", () -> new TableInjectLootModifier.Serializer());
}
