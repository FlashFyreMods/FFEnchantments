package com.flashfyre.ffenchants.loot_modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

// If you are reading this class, check out https://github.com/Tslat/Advent-Of-Ascension/blob/1.16.5/source/loottable/modifier/InjectOrReplaceLootModifier.java instead
public class InjectOrReplaceLootModifier extends LootModifier {
	
	private final ResourceLocation injectTableLocation;
	private final boolean shouldReplace;

	protected InjectOrReplaceLootModifier(ILootCondition[] conditionsIn, ResourceLocation injectTableLocation, boolean replace) {
		super(conditionsIn);
		this.injectTableLocation = injectTableLocation;
		this.shouldReplace=replace;
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		List<ItemStack> newLoot = new ArrayList<ItemStack>();
		LootTable lootTable = context.getLootTable(this.injectTableLocation);
		
		if (lootTable != LootTable.EMPTY) {
			lootTable.getRandomItemsRaw(context, newLoot::add);
		}
		return shouldReplace ? (newLoot.isEmpty() ? generatedLoot : newLoot) : Stream.concat(generatedLoot.stream(), newLoot.stream()).collect(Collectors.toList());	
	}
	
	public static class Serializer extends GlobalLootModifierSerializer<InjectOrReplaceLootModifier> {
		
		@Override
        public InjectOrReplaceLootModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            ResourceLocation injectTableLocation  = new ResourceLocation(JSONUtils.getAsString(object, "table"));
            boolean shouldReplace = JSONUtils.getAsBoolean(object, "replace");
            return new InjectOrReplaceLootModifier(conditionsIn, injectTableLocation, shouldReplace);
        }
		
		@Override
		public JsonObject write(InjectOrReplaceLootModifier instance) {
			throw new UnsupportedOperationException("datagen not supported omegalul");
		}
	}
	
	
}
