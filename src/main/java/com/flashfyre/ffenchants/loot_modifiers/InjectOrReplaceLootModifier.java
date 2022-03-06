package com.flashfyre.ffenchants.loot_modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class InjectOrReplaceLootModifier extends LootModifier {
	
	private final ResourceLocation injectTableLocation;
	private final boolean shouldReplace;

	protected InjectOrReplaceLootModifier(LootItemCondition[] conditionsIn, ResourceLocation injectTableLocation, boolean replace) {
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
        public InjectOrReplaceLootModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
            ResourceLocation injectTableLocation  = new ResourceLocation(GsonHelper.getAsString(object, "table"));
            boolean shouldReplace = GsonHelper.getAsBoolean(object, "replace");
            return new InjectOrReplaceLootModifier(conditionsIn, injectTableLocation, shouldReplace);
        }
		
		@Override
		public JsonObject write(InjectOrReplaceLootModifier instance) {
			throw new UnsupportedOperationException("datagen not supported omegalul");
		}
	}
	
	
}
