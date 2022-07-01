package com.flashfyre.ffenchantments.loot_modifiers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class TableInjectLootModifier extends LootModifier {
	
	private final ResourceLocation injectTableLocation;
	private final boolean shouldReplace;

	protected TableInjectLootModifier(LootItemCondition[] conditionsIn, ResourceLocation injectTableLocation, boolean replace) {
		super(conditionsIn);
		this.injectTableLocation = injectTableLocation;
		this.shouldReplace=replace;
	}

	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<ItemStack>();
		LootTable lootTable = context.getLootTable(this.injectTableLocation);
		
		if (lootTable != LootTable.EMPTY) {
			lootTable.getRandomItemsRaw(context, newLoot::add);
		}
		return shouldReplace ? (newLoot.isEmpty() ? generatedLoot : newLoot) : Stream.concat(generatedLoot.stream(), newLoot.stream()).collect(Collectors.toCollection(ObjectArrayList::new));	
	}
	
	public static class Serializer extends GlobalLootModifierSerializer<TableInjectLootModifier> {
		
		@Override
        public TableInjectLootModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
            ResourceLocation injectTableLocation  = new ResourceLocation(GsonHelper.getAsString(object, "table"));
            boolean shouldReplace = GsonHelper.getAsBoolean(object, "replace");
            return new TableInjectLootModifier(conditionsIn, injectTableLocation, shouldReplace);
        }
		
		@Override
		public JsonObject write(TableInjectLootModifier instance) {
			throw new UnsupportedOperationException("datagen not supported omegalul");
		}
	}
	
	
}
