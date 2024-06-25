package com.flashfyre.ffenchantments.loot_modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class AddFromExtraTableModifier extends LootModifier {
	
	private final ResourceLocation table;

	protected AddFromExtraTableModifier(LootItemCondition[] conditionsIn, ResourceLocation table) {
		super(conditionsIn);
		this.table = table;
	}

	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext ctx) {
		LootTable tableToAdd = ctx.getLootTable(this.table);
		tableToAdd.getRandomItemsRaw(ctx, LootTable.createStackSplitter(generatedLoot::add));
		return generatedLoot;	
	}
	
	public static final Codec<AddFromExtraTableModifier> CODEC = RecordCodecBuilder.create(instance -> codecStart(instance).and(
			ResourceLocation.CODEC.fieldOf("table").forGetter(lootModifier -> lootModifier.table)
			).apply(instance, AddFromExtraTableModifier::new));

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}
	
	
}
