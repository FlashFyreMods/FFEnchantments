package com.flashfyre.ffenchantments.loot_modifiers;

import java.util.List;

import javax.annotation.Nonnull;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantItemInChestLootModifier extends LootModifier {
	
	public static final Codec<EnchantItemInChestLootModifier> CODEC = RecordCodecBuilder.create(
		inst -> LootModifier.codecStart(inst).and(
			inst.group(
					ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(m -> m.item),
					Codec.FLOAT.fieldOf("chance").forGetter(m -> m.chance),
					Codec.list(ForgeRegistries.ENCHANTMENTS.getCodec()).fieldOf("enchantments").forGetter(m -> m.enchantments)
				)			
			).apply(inst, EnchantItemInChestLootModifier::new)
		);
	
	private final Item item;
	private final float chance;
	private final List<Enchantment> enchantments;

	public EnchantItemInChestLootModifier(LootItemCondition[] conditions, Item item, float chance, List<Enchantment> enchantmentsList) {
		super(conditions);
		this.item = item;
		this.chance = chance;
		this.enchantments = enchantmentsList;
	}
	
	@Nonnull
	@Override
	public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext ctx) {
		//if(!ctx.hasParam(LootContextParams.BLOCK_ENTITY)) return generatedLoot;
		RandomSource r = ctx.getRandom();
		for(ItemStack stack : generatedLoot) {
			if(stack.isEmpty()) continue;
			if(stack.getItem() == item) {
				if(r.nextDouble() < this.chance) {
					Enchantment enchantment = enchantments.get(r.nextInt(enchantments.size()));
					stack.enchant(enchantment, 1 + ctx.getRandom().nextInt(enchantment.getMaxLevel()));
				}					
			}
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}
}
