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
		
		public final ForgeConfigSpec.BooleanValue enableEndCityLootModifications;
		public final ForgeConfigSpec.BooleanValue enableJungleTempleLootModifications;
		public final ForgeConfigSpec.BooleanValue enableNetherFortressLootModifications;
		public final ForgeConfigSpec.BooleanValue enablePillagerOutpostLootModifications;
		public final ForgeConfigSpec.BooleanValue enableSmallOceanRuinLootModifications;
		public final ForgeConfigSpec.BooleanValue enableLargeOceanRuinLootModifications;
		public final ForgeConfigSpec.BooleanValue enableWoodlandMansionLootModifications;
		public final ForgeConfigSpec.BooleanValue enableIglooLootModifications;
		
		public final ForgeConfigSpec.BooleanValue enableAllLootModifications;
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Enable/disable Enchantments")
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
			
			builder.comment("Enable/disable Loot Table Modifications")
			.push("loot_tables");

			enableAllLootModifications = builder
					.comment("Enable/disable all chest loot modifications")
					.worldRestart()
					.define("enableLootModifications", true);
			
			enableEndCityLootModifications = builder
					.comment("Enable/disable additions to end city chest loot. Disable this if you disable Obsidian Skull.")
					.worldRestart()
					.define("enableEndCityLootModifications", true);
			
			enableJungleTempleLootModifications = builder
					.comment("Enable/disable additions to jungle temple chest loot. Disable this if you disable Poison Aspect.")
					.worldRestart()
					.define("enableJungleTempleLootModifications", true);
			
			enableNetherFortressLootModifications = builder
					.comment("Enable/disable additions to nether fortress chest loot. Disable this if you disable Searing or Wither Aspect.")
					.worldRestart()
					.define("enableNetherFortressLootModifications", true);
			
			enablePillagerOutpostLootModifications = builder
					.comment("Enable/disable additions to pillager outpost chest loot. Disable this if you disable Pillaging.")
					.worldRestart()
					.define("enablePillagerOutpostLootModifications", true);
			
			enableSmallOceanRuinLootModifications = builder
					.comment("Enable/disable additions to small ocean ruin chest loot. Disable this if you disable Curse of Anchoring, Torrent or Outrush.")
					.worldRestart()
					.define("enableSmallOceanRuinLootModifications", true);
			
			enableLargeOceanRuinLootModifications = builder
					.comment("Enable/disable additions to large ocean ruin chest loot.Disable this if you disable Curse of Anchoring, Neptune's Rejuvenation, Torrent or Outrush.")
					.worldRestart()
					.define("enableLargeOceanRuinLootModifications", true);
			
			enableWoodlandMansionLootModifications = builder
					.comment("Enable/disable additions to woodland mansion chest loot. Disable this if you disable Bloodlust, Sharpshooter or Pillaging.")
					.worldRestart()
					.define("enableWoodlandMansionLootModifications", true);
			
			enableIglooLootModifications = builder
					.comment("Enable/disable additions to igloo chest loot")
					.worldRestart()
					.define("enableIglooLootModifications", true);
			
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
