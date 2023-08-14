package com.flashfyre.ffenchantments;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = FFECore.MOD_ID, bus = Bus.MOD)
public class FFEConfig
{
	
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;
	
	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
	
	public static boolean isAnchoringCurseDiscoverable;
	public static boolean isAnchoringCurseTradeable;
	
	public static boolean isAquaticRejuvenationDiscoverable;
	public static boolean isAquaticRejuvenationTradeable;
	public static boolean isAquaticRejuvenationTreasure;

	public static boolean isBloodlustDiscoverable;
	public static boolean isBloodlustTradeable;
	public static boolean isBloodlustTreasure;
	
	public static boolean isBuoyancyDiscoverable;
	public static boolean isBuoyancyTradeable;
	public static boolean isBuoyancyTreasure;
	
	public static boolean isButcheringDiscoverable;
	public static boolean isButcheringTradeable;
	public static boolean isButcheringTreasure;
	
	public static boolean isInfernoDiscoverable;
	public static boolean isInfernoTradeable;
	public static boolean isInfernoTreasure;
	
	public static boolean isLeapingDiscoverable;
	public static boolean isLeapingTradeable;
	public static boolean isLeapingTreasure;
	
	public static boolean isMaelstromDiscoverable;
	public static boolean isMaelstromTradeable;
	public static boolean isMaelstromTreasure;
	
	public static boolean isObsidianSkullDiscoverable;
	public static boolean isObsidianSkullTradeable;
	public static boolean isObsidianSkullTreasure;
	
	public static boolean isOutrushDiscoverable;
	public static boolean isOutrushTradeable;
	public static boolean isOutrushTreasure;

	public static boolean isPillagingDiscoverable;
	public static boolean isPillagingTradeable;
	public static boolean isPillagingTreasure;
	
	public static boolean isPointedDiscoverable;
	public static boolean isPointedTradeable;
	public static boolean isPointedTreasure;

	public static boolean isPoisonAspectDiscoverable;
	public static boolean isPoisonAspectTradeable;
	public static boolean isPoisonAspectTreasure;
	
	public static boolean isQuicknessDiscoverable;
	public static boolean isQuicknessTradeable;
	public static boolean isQuicknessTreasure;
	
	public static boolean isSearingDiscoverable;
	public static boolean isSearingTradeable;
	public static boolean isSearingTreasure;
	
	public static boolean isSteadfastDiscoverable;
	public static boolean isSteadfastTradeable;
	public static boolean isSteadfastTreasure;
	
	public static boolean isTorrentDiscoverable;
	public static boolean isTorrentTradeable;
	public static boolean isTorrentTreasure;

	public static boolean isVampiricDiscoverable;
	public static boolean isVampiricTradeable;
	public static boolean isVampiricTreasure;
	
	public static boolean isWeightedBladeDiscoverable;
	public static boolean isWeightedBladeTradeable;
	public static boolean isWeightedBladeTreasure;
	
	public static boolean isWitherAspectDiscoverable;
	public static boolean isWitherAspectTradeable;
	public static boolean isWitherAspectTreasure;
	
	
	public static void bakeConfig() {
		
		isAnchoringCurseDiscoverable = COMMON.isAnchoringCurseDiscoverable.get();
		isAnchoringCurseTradeable = COMMON.isAnchoringCurseTradeable.get();
		
		isAquaticRejuvenationDiscoverable = COMMON.isAquaticRejuvenationDiscoverable.get();
		isAquaticRejuvenationTradeable = COMMON.isAquaticRejuvenationTradeable.get();
		isAquaticRejuvenationTreasure = COMMON.isAquaticRejuvenationTreasure.get();
		
		isBloodlustDiscoverable = COMMON.isBloodlustDiscoverable.get();
		isBloodlustTradeable = COMMON.isBloodlustTradeable.get();
		isBloodlustTreasure = COMMON.isBloodlustTreasure.get();
		
		isBuoyancyDiscoverable = COMMON.isBuoyancyDiscoverable.get();
		isBuoyancyTradeable = COMMON.isBuoyancyTradeable.get();
		isBuoyancyTreasure = COMMON.isBuoyancyTreasure.get();
		
		isButcheringDiscoverable = COMMON.isButcheringDiscoverable.get();
		isButcheringTradeable = COMMON.isButcheringTradeable.get();
		isButcheringTreasure = COMMON.isButcheringTreasure.get();
		
		isInfernoDiscoverable = COMMON.isInfernoDiscoverable.get();
		isInfernoTradeable = COMMON.isInfernoTradeable.get();
		isInfernoTreasure = COMMON.isInfernoTreasure.get();
		
		isLeapingDiscoverable = COMMON.isLeapingDiscoverable.get();
		isLeapingTradeable = COMMON.isLeapingTradeable.get();
		isLeapingTreasure = COMMON.isLeapingTreasure.get();
		
		isMaelstromDiscoverable = COMMON.isMaelstromDiscoverable.get();
		isMaelstromTradeable = COMMON.isMaelstromTradeable.get();
		isMaelstromTreasure = COMMON.isMaelstromTreasure.get();
		
		isObsidianSkullDiscoverable = COMMON.isObsidianSkullDiscoverable.get();
		isObsidianSkullTradeable = COMMON.isObsidianSkullTradeable.get();
		isObsidianSkullTreasure = COMMON.isObsidianSkullTreasure.get();
		
		isOutrushDiscoverable = COMMON.isOutrushDiscoverable.get();
		isOutrushTradeable = COMMON.isOutrushTradeable.get();
		isOutrushTreasure = COMMON.isOutrushTreasure.get();
		
		isPillagingDiscoverable = COMMON.isPillagingDiscoverable.get();
		isPillagingTradeable = COMMON.isPillagingTradeable.get();
		isPillagingTreasure = COMMON.isPillagingTreasure.get();
		
		isPointedDiscoverable = COMMON.isPointedDiscoverable.get();
		isPointedTradeable = COMMON.isPointedTradeable.get();
		isPointedTreasure = COMMON.isPointedTreasure.get();
		
		isPoisonAspectDiscoverable = COMMON.isPoisonAspectDiscoverable.get();
		isPoisonAspectTradeable = COMMON.isPoisonAspectTradeable.get();
		isPoisonAspectTreasure = COMMON.isPoisonAspectTreasure.get();
		
		isQuicknessDiscoverable = COMMON.isQuicknessDiscoverable.get();
		isQuicknessTradeable = COMMON.isQuicknessTradeable.get();
		isQuicknessTreasure = COMMON.isQuicknessTreasure.get();
		
		isSearingDiscoverable = COMMON.isSearingDiscoverable.get();
		isSearingTradeable = COMMON.isSearingTradeable.get();
		isSearingTreasure = COMMON.isSearingTreasure.get();
		
		isSteadfastDiscoverable = COMMON.isSteadfastDiscoverable.get();
		isSteadfastTradeable = COMMON.isSteadfastTradeable.get();
		isSteadfastTreasure = COMMON.isSteadfastTreasure.get();
		
		isTorrentDiscoverable = COMMON.isTorrentDiscoverable.get();
		isTorrentTradeable = COMMON.isTorrentTradeable.get();
		isTorrentTreasure = COMMON.isTorrentTreasure.get();
		
		isVampiricDiscoverable = COMMON.isVampiricDiscoverable.get();
		isVampiricTradeable = COMMON.isVampiricTradeable.get();
		isVampiricTreasure = COMMON.isVampiricTreasure.get();
		
		isWeightedBladeDiscoverable = COMMON.isWeightedBladeDiscoverable.get();
		isWeightedBladeTradeable = COMMON.isWeightedBladeTradeable.get();
		isWeightedBladeTreasure = COMMON.isWeightedBladeTreasure.get();
		
		isWitherAspectDiscoverable = COMMON.isWitherAspectDiscoverable.get();
		isWitherAspectTradeable = COMMON.isWitherAspectTradeable.get();
		isWitherAspectTreasure = COMMON.isWitherAspectTreasure.get();
		
		FFECore.LOGGER.info("Baked FFE Config.");
	}
	
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfigEvent configEvent) {
		if (configEvent.getConfig().getSpec() == FFEConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}
	
	@SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
		FFECore.LOGGER.debug("Loaded FFE config file", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
    	FFECore.LOGGER.debug("FFEnchantments config was reloaded");
    }
	
	public static class Common {
		
		public final ForgeConfigSpec.BooleanValue isAnchoringCurseDiscoverable;
		public final ForgeConfigSpec.BooleanValue isAnchoringCurseTradeable;
		
		public final ForgeConfigSpec.BooleanValue isAquaticRejuvenationDiscoverable;
		public final ForgeConfigSpec.BooleanValue isAquaticRejuvenationTradeable;
		public final ForgeConfigSpec.BooleanValue isAquaticRejuvenationTreasure;
		
		public final ForgeConfigSpec.BooleanValue isBloodlustDiscoverable;
		public final ForgeConfigSpec.BooleanValue isBloodlustTradeable;
		public final ForgeConfigSpec.BooleanValue isBloodlustTreasure;
		
		public final ForgeConfigSpec.BooleanValue isBuoyancyDiscoverable;
		public final ForgeConfigSpec.BooleanValue isBuoyancyTradeable;
		public final ForgeConfigSpec.BooleanValue isBuoyancyTreasure;
		
		public final ForgeConfigSpec.BooleanValue isButcheringDiscoverable;
		public final ForgeConfigSpec.BooleanValue isButcheringTradeable;
		public final ForgeConfigSpec.BooleanValue isButcheringTreasure;
		
		public final ForgeConfigSpec.BooleanValue isInfernoDiscoverable;
		public final ForgeConfigSpec.BooleanValue isInfernoTradeable;
		public final ForgeConfigSpec.BooleanValue isInfernoTreasure;
		
		public final ForgeConfigSpec.BooleanValue isLeapingDiscoverable;
		public final ForgeConfigSpec.BooleanValue isLeapingTradeable;
		public final ForgeConfigSpec.BooleanValue isLeapingTreasure;
		
		public final ForgeConfigSpec.BooleanValue isMaelstromDiscoverable;
		public final ForgeConfigSpec.BooleanValue isMaelstromTradeable;
		public final ForgeConfigSpec.BooleanValue isMaelstromTreasure;
		
		public final ForgeConfigSpec.BooleanValue isObsidianSkullDiscoverable;
		public final ForgeConfigSpec.BooleanValue isObsidianSkullTradeable;
		public final ForgeConfigSpec.BooleanValue isObsidianSkullTreasure;
		
		public final ForgeConfigSpec.BooleanValue isOutrushDiscoverable;
		public final ForgeConfigSpec.BooleanValue isOutrushTradeable;
		public final ForgeConfigSpec.BooleanValue isOutrushTreasure;
		
		public final ForgeConfigSpec.BooleanValue isPillagingDiscoverable;
		public final ForgeConfigSpec.BooleanValue isPillagingTradeable;
		public final ForgeConfigSpec.BooleanValue isPillagingTreasure;
		
		public final ForgeConfigSpec.BooleanValue isPointedDiscoverable;
		public final ForgeConfigSpec.BooleanValue isPointedTradeable;
		public final ForgeConfigSpec.BooleanValue isPointedTreasure;
		
		public final ForgeConfigSpec.BooleanValue isPoisonAspectDiscoverable;
		public final ForgeConfigSpec.BooleanValue isPoisonAspectTradeable;
		public final ForgeConfigSpec.BooleanValue isPoisonAspectTreasure;
		
		public final ForgeConfigSpec.BooleanValue isQuicknessDiscoverable;
		public final ForgeConfigSpec.BooleanValue isQuicknessTradeable;
		public final ForgeConfigSpec.BooleanValue isQuicknessTreasure;
		
		public final ForgeConfigSpec.BooleanValue isSearingDiscoverable;
		public final ForgeConfigSpec.BooleanValue isSearingTradeable;
		public final ForgeConfigSpec.BooleanValue isSearingTreasure;
		
		public final ForgeConfigSpec.BooleanValue isSteadfastDiscoverable;
		public final ForgeConfigSpec.BooleanValue isSteadfastTradeable;
		public final ForgeConfigSpec.BooleanValue isSteadfastTreasure;
		
		public final ForgeConfigSpec.BooleanValue isTorrentDiscoverable;
		public final ForgeConfigSpec.BooleanValue isTorrentTradeable;
		public final ForgeConfigSpec.BooleanValue isTorrentTreasure;
		
		public final ForgeConfigSpec.BooleanValue isVampiricDiscoverable;
		public final ForgeConfigSpec.BooleanValue isVampiricTradeable;
		public final ForgeConfigSpec.BooleanValue isVampiricTreasure;
		
		public final ForgeConfigSpec.BooleanValue isWeightedBladeDiscoverable;
		public final ForgeConfigSpec.BooleanValue isWeightedBladeTradeable;
		public final ForgeConfigSpec.BooleanValue isWeightedBladeTreasure;
		
		public final ForgeConfigSpec.BooleanValue isWitherAspectDiscoverable;
		public final ForgeConfigSpec.BooleanValue isWitherAspectTradeable;
		public final ForgeConfigSpec.BooleanValue isWitherAspectTreasure;
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Enchantment Configuration")
			.push("enchantments");
			
			builder.push("anchoring_curse");
			isAnchoringCurseDiscoverable = builder
					.worldRestart()
					.define("isAnchoringCurseDiscoverable", true);			
			isAnchoringCurseTradeable = builder
					.worldRestart()
					.define("isAnchoringCurseTradeable", true);
			builder.pop();
			
			builder.push("aquatic_rejuvenation");
			isAquaticRejuvenationDiscoverable = builder
					.worldRestart()
					.define("canAquaticRejuvenationGenerateInLoot", true);			
			isAquaticRejuvenationTradeable = builder
					.worldRestart()
					.define("isAquaticRejuvenationTradeable", true);
			isAquaticRejuvenationTreasure = builder
					.worldRestart()
					.define("isAquaticRejuvenationTreasure", true);
			builder.pop();
			
			builder.push("bloodlust");
			isBloodlustDiscoverable = builder
					.worldRestart()
					.define("isBloodlustDiscoverable", true);			
			isBloodlustTradeable = builder
					.worldRestart()
					.define("isBloodlustTradeable", true);
			isBloodlustTreasure = builder
					.worldRestart()
					.define("isBloodlustTreasure", false);
			builder.pop();
			
			builder.push("buoyancy");
			isBuoyancyDiscoverable = builder
					.worldRestart()
					.define("isBuoyancyDiscoverable", true);			
			isBuoyancyTradeable = builder
					.worldRestart()
					.define("isBuoyancyTradeable", true);
			isBuoyancyTreasure = builder
					.worldRestart()
					.define("isBuoyancyTreasure", false);
			builder.pop();
			
			builder.push("butchering");
			isButcheringDiscoverable = builder
					.worldRestart()
					.define("isButcheringDiscoverable", true);			
			isButcheringTradeable = builder
					.worldRestart()
					.define("isButcheringTradeable", true);
			isButcheringTreasure = builder
					.worldRestart()
					.define("isButcheringTreasure", false);
			builder.pop();
			
			builder.push("inferno");
			isInfernoDiscoverable = builder
					.worldRestart()
					.define("isInfernoDiscoverable", true);			
			isInfernoTradeable = builder
					.worldRestart()
					.define("isInfernoTradeable", true);
			isInfernoTreasure = builder
					.worldRestart()
					.define("isInfernoTreasure", false);
			builder.pop();
			
			builder.push("leaping");
			isLeapingDiscoverable = builder
					.worldRestart()
					.define("isLeapingDiscoverable", true);			
			isLeapingTradeable = builder
					.worldRestart()
					.define("isLeapingTradeable", true);
			isLeapingTreasure = builder
					.worldRestart()
					.define("isLeapingTreasure", false);
			builder.pop();
			
			builder.push("maelstrom");
			isMaelstromDiscoverable = builder
					.worldRestart()
					.define("isMaelstromDiscoverable", true);			
			isMaelstromTradeable = builder
					.worldRestart()
					.define("isMaelstromTradeable", true);
			isMaelstromTreasure = builder
					.worldRestart()
					.define("isMaelstromTreasure", false);
			builder.pop();
			
			builder.push("obsidian_skull");
			isObsidianSkullDiscoverable = builder
					.worldRestart()
					.define("isObsidianSkullDiscoverable", true);			
			isObsidianSkullTradeable = builder
					.worldRestart()
					.define("isObsidianSkullTradeable", true);
			isObsidianSkullTreasure = builder
					.worldRestart()
					.define("isObsidianSkullTreasure", true);
			builder.pop();
			
			builder.push("outrush");
			isOutrushDiscoverable = builder
					.worldRestart()
					.define("isOutrushDiscoverable", true);			
			isOutrushTradeable = builder
					.worldRestart()
					.define("isOutrushTradeable", true);
			isOutrushTreasure = builder
					.worldRestart()
					.define("isOutrushTreasure", false);
			builder.pop();
			
			builder.push("pillaging");
			isPillagingDiscoverable = builder
					.worldRestart()
					.define("isPillagingDiscoverable", true);			
			isPillagingTradeable = builder
					.worldRestart()
					.define("isPillagingTradeable", true);
			isPillagingTreasure = builder
					.worldRestart()
					.define("isPillagingTreasure", false);
			builder.pop();
			
			builder.push("pointed");
			isPointedDiscoverable = builder
					.worldRestart()
					.define("isPointedDiscoverable", true);			
			isPointedTradeable = builder
					.worldRestart()
					.define("isPointedTradeable", true);
			isPointedTreasure = builder
					.worldRestart()
					.define("isPointedTreasure", false);
			builder.pop();
			
			builder.push("poison_aspect");
			isPoisonAspectDiscoverable = builder
					.worldRestart()
					.define("isPoisonAspectDiscoverable", true);			
			isPoisonAspectTradeable = builder
					.worldRestart()
					.define("isPoisonAspectTradeable", true);
			isPoisonAspectTreasure = builder
					.worldRestart()
					.define("isPoisonAspectTreasure", false);
			builder.pop();
			
			builder.push("quickness");
			isQuicknessDiscoverable = builder
					.worldRestart()
					.define("isQuicknessDiscoverable", true);			
			isQuicknessTradeable = builder
					.worldRestart()
					.define("isQuicknessTradeable", true);
			isQuicknessTreasure = builder
					.worldRestart()
					.define("isQuicknessTreasure", false);
			builder.pop();
			
			builder.push("searing");
			isSearingDiscoverable = builder
					.worldRestart()
					.define("isSearingDiscoverable", true);			
			isSearingTradeable = builder
					.worldRestart()
					.define("isSearingTradeable", true);
			isSearingTreasure = builder
					.worldRestart()
					.define("isSearingTreasure", false);
			builder.pop();
			
			builder.push("steadfast");
			isSteadfastDiscoverable = builder
					.worldRestart()
					.define("isSteadfastDiscoverable", true);			
			isSteadfastTradeable = builder
					.worldRestart()
					.define("isSteadfastTradeable", true);
			isSteadfastTreasure = builder
					.worldRestart()
					.define("isSteadfastTreasure", false);
			builder.pop();
			
			builder.push("torrent");
			isTorrentDiscoverable = builder
					.worldRestart()
					.define("isTorrentDiscoverable", true);			
			isTorrentTradeable = builder
					.worldRestart()
					.define("isTorrentTradeable", true);
			isTorrentTreasure = builder
					.worldRestart()
					.define("isTorrentTreasure", false);
			builder.pop();
			
			builder.push("vampiric");
			isVampiricDiscoverable = builder
					.worldRestart()
					.define("isVampiricDiscoverable", true);			
			isVampiricTradeable = builder
					.worldRestart()
					.define("isVampiricTradeable", true);
			isVampiricTreasure = builder
					.worldRestart()
					.define("isVampiricTreasure", true);
			builder.pop();
			
			builder.push("weighted");
			isWeightedBladeDiscoverable = builder
					.worldRestart()
					.define("isWeightedBladeDiscoverable", true);			
			isWeightedBladeTradeable = builder
					.worldRestart()
					.define("isWeightedBladeTradeable", true);
			isWeightedBladeTreasure = builder
					.worldRestart()
					.define("isWeightedBladeTreasure", false);
			builder.pop();				
			
			builder.push("wither_aspect");
			isWitherAspectDiscoverable = builder
					.worldRestart()
					.define("isWitherAspectDiscoverable", true);			
			isWitherAspectTradeable = builder
					.worldRestart()
					.define("isWitherAspectTradeable", true);
			isWitherAspectTreasure = builder
					.worldRestart()
					.define("isWitherAspectTreasure", true);
			builder.pop();
			
			builder.pop();
		}
	}
}