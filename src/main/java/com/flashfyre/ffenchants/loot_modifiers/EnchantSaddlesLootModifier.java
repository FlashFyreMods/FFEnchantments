package com.flashfyre.ffenchants.loot_modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.flashfyre.ffenchants.misc.FFEConfig;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantSaddlesLootModifier extends LootModifier {
	
	private final Item itemToCheck;
	private final List<Enchantment> enchantments;

	public EnchantSaddlesLootModifier(ILootCondition[] conditionsIn, Item itemCheck, List<Enchantment> enchantmentsList) {
		super(conditionsIn);
		itemToCheck = itemCheck;
		enchantments = enchantmentsList;
	}
	
	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext ctx) {
		if(FFEConfig.enableSaddlesRandomlyEnchanted) {
			Random r = ctx.getRandom();
			
			for(ItemStack stack : generatedLoot) {
				if(stack.isEmpty()) continue;
				if(stack.getItem() == itemToCheck) {
					if(r.nextDouble() < FFEConfig.enchantSaddleChance) {
						Enchantment enchantment = enchantments.get(r.nextInt(enchantments.size()));
						stack.addEnchantment(enchantment, 1 + ctx.getRandom().nextInt(enchantment.getMaxLevel()));
					}					
				}
			}
		}
		return generatedLoot;
	}
	
	public static class Serializer extends GlobalLootModifierSerializer<EnchantSaddlesLootModifier> {

        @Override
        public EnchantSaddlesLootModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            Item saddleItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation((JSONUtils.getString(object, "saddleItem"))));
            JsonArray enchantmentsArray = JSONUtils.getJsonArray(object, "enchantments");          
            List<Enchantment> enchantments = new ArrayList<Enchantment>();
            for(int i=0; i<enchantmentsArray.size(); i++) {
            	enchantments.add(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantmentsArray.get(i).getAsString())));
            }
            return new EnchantSaddlesLootModifier(conditionsIn, saddleItem, enchantments);
        }
    }

}
