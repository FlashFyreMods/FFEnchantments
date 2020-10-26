package com.flashfyre.ffenchants.misc;

import java.util.ArrayList;
import java.util.List;

import com.flashfyre.ffenchants.FFE;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FFE.MOD_ID)
public class FFELootTables {
	
	public static final List<String> CHESTS = new ArrayList<>();
	
	@SubscribeEvent
	public static void injectLootPools(LootTableLoadEvent event) {
		
		if(!FFEConfig.enableAllLootAdditions) return;
		
		String prefix = "minecraft:chests/";
		String name = event.getName().toString();
		
		if (name.startsWith(prefix)) {
			String file = name.substring(name.indexOf(prefix) + prefix.length());
			
			if(CHESTS.contains(file)) {				
				event.getTable().addPool(getInjectPool(file));			
			}
		}
	}
	
	public static LootPool getInjectPool(String entryName) {
		return LootPool.builder()
				.addEntry(getInjectEntry(entryName, 1))
				.bonusRolls(0, 0)
				.name("ffe_inject")
				.build();
	}

	private static LootEntry.Builder<?> getInjectEntry(String name, int weight) {
		ResourceLocation table = new ResourceLocation(FFE.MOD_ID, "inject/" + name);
		return TableLootEntry.builder(table)
				.weight(weight);
	}
}
