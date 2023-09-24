package com.flashfyre.ffenchantments.loot_modifiers;

import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import com.flashfyre.ffenchantments.FFEUtil;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class AddEnchantedBookLootModifier extends LootModifier {
	
	public static final Codec<AddEnchantedBookLootModifier> CODEC = RecordCodecBuilder.create(instance -> codecStart(instance).and(
			FFEUtil.listOrElementCodec(ForgeRegistries.ENCHANTMENTS.getCodec()).fieldOf("enchantments").forGetter(lootModifier -> lootModifier.enchantments)).apply(instance, AddEnchantedBookLootModifier::new));
	
	private final List<Enchantment> enchantments;
	
	protected AddEnchantedBookLootModifier(LootItemCondition[] conditions, List<Enchantment> enchantments) {
		super(conditions);
		this.enchantments = enchantments;
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot,
			LootContext ctx) {		
		List<Enchantment> enchantments = this.enchantments.stream().filter(e -> e.isDiscoverable() && e.isAllowedOnBooks()).collect(Collectors.toList());
		if(!enchantments.isEmpty()) {
			RandomSource r = ctx.getRandom();
			Enchantment ench = enchantments.get(r.nextInt(this.enchantments.size()));
			ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
			EnchantedBookItem.addEnchantment(book, new EnchantmentInstance(ench, Mth.nextInt(r, ench.getMinLevel(), ench.getMaxLevel())));
			generatedLoot.add(book);
		}		
		return generatedLoot;
	}
	
	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}
}
