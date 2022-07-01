package com.flashfyre.ffenchantments.loot_modifiers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.flashfyre.ffenchantments.FFEConfig;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantSaddlesLootModifier extends LootModifier {
	
	private final Item itemToCheck;
	private final List<Enchantment> enchantments;

	public EnchantSaddlesLootModifier(LootItemCondition[] conditionsIn, Item itemCheck, List<Enchantment> enchantmentsList) {
		super(conditionsIn);
		itemToCheck = itemCheck;
		enchantments = enchantmentsList;
	}
	
	@Nonnull
	@Override
	public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext ctx) {
		if(FFEConfig.enableSaddlesRandomlyEnchanted) {
			RandomSource r = ctx.getRandom();
			
			for(ItemStack stack : generatedLoot) {
				if(stack.isEmpty()) continue;
				if(stack.getItem() == itemToCheck) {
					if(r.nextDouble() < FFEConfig.enchantSaddleChance) {
						Enchantment enchantment = enchantments.get(r.nextInt(enchantments.size()));
						stack.enchant(enchantment, 1 + ctx.getRandom().nextInt(enchantment.getMaxLevel()));
					}					
				}
			}
		}
		return generatedLoot;
	}
	
	public static class Serializer extends GlobalLootModifierSerializer<EnchantSaddlesLootModifier> {

        @Override
        public EnchantSaddlesLootModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
            Item saddleItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "saddleItem"))));
            JsonArray enchantmentsArray = GsonHelper.getAsJsonArray(object, "enchantments");          
            List<Enchantment> enchantments = new ArrayList<Enchantment>();
            for(int i=0; i<enchantmentsArray.size(); i++) {
            	enchantments.add(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantmentsArray.get(i).getAsString())));
            }
            return new EnchantSaddlesLootModifier(conditionsIn, saddleItem, enchantments);
        }

		@Override
		public JsonObject write(EnchantSaddlesLootModifier instance) {
			throw new UnsupportedOperationException("datagen not supported omegalul");
		}
    }
}
