package com.flashfyre.ffenchantments.loot_modifiers;

import org.jetbrains.annotations.NotNull;

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

public class ReplaceWithTableModifier extends LootModifier {
	
	private final ResourceLocation table;

	protected ReplaceWithTableModifier(LootItemCondition[] conditions, ResourceLocation table) {
		super(conditions);
		this.table = table;
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot,
			LootContext ctx) {		
		LootTable replacementTable = ctx.getLootTable(this.table);
		ObjectArrayList<ItemStack> replacementLoot = new ObjectArrayList<ItemStack>();
		replacementTable.getRandomItemsRaw(ctx, generatedLoot::add);
		return replacementLoot;
	}
	
	public static final Codec<ReplaceWithTableModifier> CODEC = RecordCodecBuilder.create(instance -> codecStart(instance).and(
			ResourceLocation.CODEC.fieldOf("table").forGetter(lootModifier -> lootModifier.table)
			).apply(instance, ReplaceWithTableModifier::new));
	
	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}

}
