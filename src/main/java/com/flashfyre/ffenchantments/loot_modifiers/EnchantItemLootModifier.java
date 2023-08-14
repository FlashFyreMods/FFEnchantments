package com.flashfyre.ffenchantments.loot_modifiers;

import java.util.List;

import javax.annotation.Nonnull;

import com.flashfyre.ffenchantments.FFEConfig;
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

public class EnchantItemLootModifier extends LootModifier {
	
	public static final Codec<EnchantItemLootModifier> CODEC = RecordCodecBuilder.create(
		inst -> LootModifier.codecStart(inst).and(
			inst.group(
					ForgeRegistries.ITEMS.getCodec().fieldOf("item_to_enchant").forGetter(m -> m.itemToEnchant),
					Codec.FLOAT.fieldOf("chance").forGetter(m -> m.chance),
					Codec.list(ForgeRegistries.ENCHANTMENTS.getCodec()).fieldOf("possible_enchantments").forGetter(m -> m.possibleEnchantments)
				)			
			).apply(inst, EnchantItemLootModifier::new)
		);
	
	private final Item itemToEnchant;
	private final float chance;
	private final List<Enchantment> possibleEnchantments;

	public EnchantItemLootModifier(LootItemCondition[] conditions, Item itemToEnchant, float chance, List<Enchantment> enchantmentsList) {
		super(conditions);
		this.itemToEnchant = itemToEnchant;
		this.chance = chance;
		this.possibleEnchantments = enchantmentsList;
	}
	
	@Nonnull
	@Override
	public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext ctx) {
		RandomSource r = ctx.getRandom();
		
		for(ItemStack stack : generatedLoot) {
			if(stack.isEmpty()) continue;
			if(stack.getItem() == itemToEnchant) {
				if(r.nextDouble() < this.chance) {
					Enchantment enchantment = possibleEnchantments.get(r.nextInt(possibleEnchantments.size()));
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
