package com.flashfyre.ffenchants.misc;

import org.apache.commons.lang3.tuple.Pair;

import com.flashfyre.ffenchants.FFE;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = FFE.MOD_ID, bus = Bus.MOD)
public class FFEConfig
{
	
	public static class Common {
		
		public final ForgeConfigSpec.BooleanValue enableBloodlust;
		public final ForgeConfigSpec.BooleanValue enableVampiric;
		public final ForgeConfigSpec.BooleanValue enablePillaging;
		public final ForgeConfigSpec.BooleanValue enableWeighted;
		public final ForgeConfigSpec.BooleanValue enablePoisonAspect;
		public final ForgeConfigSpec.BooleanValue enableWitherAspect;
		public final ForgeConfigSpec.BooleanValue enableSearing;
		public final ForgeConfigSpec.BooleanValue enableSharpshooter;
		public final ForgeConfigSpec.BooleanValue enableSteadfast;
		public final ForgeConfigSpec.BooleanValue enableOutrush;
		public final ForgeConfigSpec.BooleanValue enableTorrent;
		public final ForgeConfigSpec.BooleanValue enableRejuvenation;
		public final ForgeConfigSpec.BooleanValue enableObsidianSkull;
		public final ForgeConfigSpec.BooleanValue enableButchering;
		public final ForgeConfigSpec.BooleanValue enableLeaping;
		public final ForgeConfigSpec.BooleanValue enableBuoyancy;
		public final ForgeConfigSpec.BooleanValue enableQuickness;
		public final ForgeConfigSpec.BooleanValue enableAnchoringCurse;
		
		public final ForgeConfigSpec.BooleanValue enableEndCityLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableJungleTempleLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableNetherFortressLootAdditions;
		public final ForgeConfigSpec.BooleanValue enablePillagerOutpostLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableSmallOceanRuinLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableLargeOceanRuinLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableWoodlandMansionLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableIglooLootAdditions;		
		
		public final ForgeConfigSpec.BooleanValue enableAllLootAdditions;
		
		public final ForgeConfigSpec.BooleanValue enableGlobalLootModifiers;
		public final ForgeConfigSpec.DoubleValue enchantSaddleChance;
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Enchantment Configuration")
			.push("enchantments");
			
			enableBloodlust = builder
					.comment("Enable Bloodlust")
					.worldRestart()
					.define("enableBloodlust", true);
			
			enableVampiric = builder
					.comment("Enable Vampiric")
					.worldRestart()
					.define("enableVampiric", true);
			
			enablePillaging = builder
					.comment("Enable Pillaging")
					.worldRestart()
					.define("enablePillaging", true);
			
			enableWeighted = builder
					.comment("Enable Weighted")
					.worldRestart()
					.define("enableWeighted", true);
			
			enablePoisonAspect = builder
					.comment("Enable Poison Aspect")
					.worldRestart()
					.define("enablePoisonAspect", true);
			
			enableWitherAspect = builder
					.comment("Enable Wither Aspect")
					.worldRestart()
					.define("enableWitherAspect", true);
			
			enableSearing = builder
					.comment("Enable Searing")
					.worldRestart()
					.define("enableSearing", true);
			
			enableSharpshooter = builder
					.comment("Enable Sharpshooter")
					.worldRestart()
					.define("enableSharpshooter", true);
			
			enableSteadfast = builder
					.comment("Enable Steadfast")
					.worldRestart()
					.define("enableSteadfast", true);
			
			enableOutrush = builder
					.comment("Enable Outrush")
					.worldRestart()
					.define("enableOutrush", true);
			
			enableTorrent = builder
					.comment("Enable Torrent")
					.worldRestart()
					.define("enableTorrent", true);
			
			enableRejuvenation = builder
					.comment("Enable Neptune's Rejuvenation")
					.worldRestart()
					.define("enableRejuvenation", true);
			
			enableObsidianSkull = builder
					.comment("Enable Obsidian Skull")
					.worldRestart()
					.define("enableObsidianSkull", true);
			
			enableButchering = builder
					.comment("Enable Butchering")
					.worldRestart()
					.define("enableButchering", true);
			
			enableLeaping = builder
					.comment("Enable Leaping")
					.worldRestart()
					.define("enableLeaping", true);
			
			enableBuoyancy = builder
					.comment("Enable Buoyancy")
					.worldRestart()
					.define("enableBuoyancy", true);
			
			enableQuickness = builder
					.comment("Enable Quickness")
					.worldRestart()
					.define("enableQuickness", true);
			
			enableAnchoringCurse = builder
					.comment("Enable Curse of Anchoring")
					.worldRestart()
					.define("enableAnchoringCurse", true);
			
			builder.pop();
			
			builder.comment("Loot Table Additions Configuration")
			.push("loot_tables");

			enableAllLootAdditions = builder
					.comment("Enable/disable all chest loot Additions")
					.worldRestart()
					.define("enableLootAdditions", true);
			
			enableEndCityLootAdditions = builder
					.comment("Enable/disable additions to end city chest loot. Disable this if you disable Obsidian Skull.")
					.worldRestart()
					.define("enableEndCityLootAdditions", true);
			
			enableJungleTempleLootAdditions = builder
					.comment("Enable/disable additions to jungle temple chest loot. Disable this if you disable Poison Aspect.")
					.worldRestart()
					.define("enableJungleTempleLootAdditions", true);
			
			enableNetherFortressLootAdditions = builder
					.comment("Enable/disable additions to nether fortress chest loot. Disable this if you disable Searing or Wither Aspect.")
					.worldRestart()
					.define("enableNetherFortressLootAdditions", true);
			
			enablePillagerOutpostLootAdditions = builder
					.comment("Enable/disable additions to pillager outpost chest loot. Disable this if you disable Pillaging.")
					.worldRestart()
					.define("enablePillagerOutpostLootAdditions", true);
			
			enableSmallOceanRuinLootAdditions = builder
					.comment("Enable/disable additions to small ocean ruin chest loot. Disable this if you disable Curse of Anchoring, Torrent or Outrush.")
					.worldRestart()
					.define("enableSmallOceanRuinLootAdditions", true);
			
			enableLargeOceanRuinLootAdditions = builder
					.comment("Enable/disable additions to large ocean ruin chest loot. Disable this if you disable Curse of Anchoring, Neptune's Rejuvenation, Torrent or Outrush.")
					.worldRestart()
					.define("enableLargeOceanRuinLootAdditions", true);
			
			enableWoodlandMansionLootAdditions = builder
					.comment("Enable/disable additions to woodland mansion chest loot. Disable this if you disable Bloodlust, Sharpshooter or Pillaging.")
					.worldRestart()
					.define("enableWoodlandMansionLootAdditions", true);
			
			enableIglooLootAdditions = builder
					.comment("Enable/disable additions to igloo chest loot")
					.worldRestart()
					.define("enableIglooLootAdditions", true);
			
			builder.pop();
			
			builder.comment("Loot Modifier Configuration")
			.push("loot_modifiers");
			
			enableGlobalLootModifiers = builder
					.comment("Enable/disable global loot modifiers. This adds enchantments to some saddles in chest loot")
					.worldRestart()
					.define("enableGlobalLootModifiers", true);
			
			enchantSaddleChance = builder
					.comment("The chance for a saddle to be enchanted with a random saddle enchantment. 1.0F means it will be guaranteed to be enchanted.")
					.worldRestart()
					.defineInRange("enchantSaddleChance", 0.5F, 0.0F, 1.0F);
			
			builder.pop();
		}
	}
	
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;
	
	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
	
	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading event) {
		
	}
	
	@SubscribeEvent
	public static void  onFileChange(final ModConfig.Reloading event) {
		
	}
}
