package com.flashfyre.ffenchants.loot_modifiers;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.flashfyre.ffenchants.FFE;
import com.google.gson.JsonObject;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class RemoveDisabledEnchantmentsLootModifier extends LootModifier {

	protected RemoveDisabledEnchantmentsLootModifier(ILootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		//FFE.LOGGER.info(FFE.validEnchantmentsForChestLoot);
		for(ItemStack stack : generatedLoot) {
			//FFE.LOGGER.info(EnchantmentHelper.getEnchantments(stack));
			Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack)
					.entrySet()
					.stream()
					.filter(entry -> !entry.getKey().getRegistryName().getNamespace().equals(FFE.MOD_ID) || FFE.validEnchantmentsForChestLoot.contains(entry.getKey().getRegistryName()))
					.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		    EnchantmentHelper.setEnchantments(map, stack);
		    if(map.size() == 0) {
		    	if(stack.getItem() instanceof EnchantedBookItem) {
		    		stack.shrink(stack.getCount());
		    	}
		    }	  
			
		}
		return generatedLoot;
	}
	
	public static class Serializer extends GlobalLootModifierSerializer<RemoveDisabledEnchantmentsLootModifier> {

        @Override
        public RemoveDisabledEnchantmentsLootModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            return new RemoveDisabledEnchantmentsLootModifier(conditionsIn);
        }
    }
}
