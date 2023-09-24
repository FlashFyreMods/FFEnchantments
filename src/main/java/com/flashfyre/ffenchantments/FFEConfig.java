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
	
	public static boolean isAquaticRestorationDiscoverable, isAquaticRestorationTradeable, isAquaticRestorationTreasure;
	public static boolean isBuoyancyDiscoverable, isBuoyancyTradeable, isBuoyancyTreasure;	
	public static boolean isButcheringDiscoverable, isButcheringTradeable, isButcheringTreasure;
	public static boolean isEnderShroudDiscoverable, isEnderShroudTradeable, isEnderShroudTreasure;
	public static boolean isInfernoDiscoverable, isInfernoTradeable, isInfernoTreasure;	
	public static boolean isLeapingDiscoverable, isLeapingTradeable, isLeapingTreasure;	
	public static boolean isMaelstromDiscoverable, isMaelstromTradeable, isMaelstromTreasure;	
	public static boolean isObsidianSkullDiscoverable, isObsidianSkullTradeable, isObsidianSkullTreasure;	
	public static boolean isOutpourDiscoverable, isOutpourTradeable, isOutpourTreasure;
	public static boolean isRetributionDiscoverable, isRetributionTradeable, isRetributionTreasure;	
	public static boolean isKeenPointDiscoverable, isKeenPointTradeable, isKeenPointTreasure;
	public static boolean isPoisonAspectDiscoverable, isPoisonAspectTradeable, isPoisonAspectTreasure;	
	public static boolean isQuicknessDiscoverable, isQuicknessTradeable, isQuicknessTreasure;	
	public static boolean isSearingTouchDiscoverable, isSearingTouchTradeable, isSearingTouchTreasure;	
	public static boolean isSteadfastDiscoverable, isSteadfastTradeable, isSteadfastTreasure;	
	public static boolean isTorrentDiscoverable, isTorrentTradeable, isTorrentTreasure;
	public static boolean isVampiricDiscoverable, isVampiricTradeable, isVampiricTreasure;	
	public static boolean isWeightedBladeDiscoverable, isWeightedBladeTradeable, isWeightedBladeTreasure;	
	public static boolean isWitherAspectDiscoverable, isWitherAspectTradeable, isWitherAspectTreasure;
	
	
	public static void bakeConfig() {
		
		isAquaticRestorationDiscoverable = COMMON.isAquaticRejuvenationDiscoverable.get();
		isAquaticRestorationTradeable = COMMON.isAquaticRejuvenationTradeable.get();
		isAquaticRestorationTreasure = COMMON.isAquaticRejuvenationTreasure.get();
		
		isBuoyancyDiscoverable = COMMON.isBuoyancyDiscoverable.get();
		isBuoyancyTradeable = COMMON.isBuoyancyTradeable.get();
		isBuoyancyTreasure = COMMON.isBuoyancyTreasure.get();
		
		isButcheringDiscoverable = COMMON.isButcheringDiscoverable.get();
		isButcheringTradeable = COMMON.isButcheringTradeable.get();
		isButcheringTreasure = COMMON.isButcheringTreasure.get();
		
		isEnderShroudDiscoverable = COMMON.isEnderShroudDiscoverable.get();
		isEnderShroudTradeable = COMMON.isEnderShroudTradeable.get();
		isEnderShroudTreasure = COMMON.isEnderShroudTreasure.get();
		
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
		
		isOutpourDiscoverable = COMMON.isOutpourDiscoverable.get();
		isOutpourTradeable = COMMON.isOutpourTradeable.get();
		isOutpourTreasure = COMMON.isOutpourTreasure.get();
		
		isRetributionDiscoverable = COMMON.isRetributionDiscoverable.get();
		isRetributionTradeable = COMMON.isRetributionTradeable.get();
		isRetributionTreasure = COMMON.isRetributionTreasure.get();
		
		isKeenPointDiscoverable = COMMON.isKeenPointDiscoverable.get();
		isKeenPointTradeable = COMMON.isKeenPointTradeable.get();
		isKeenPointTreasure = COMMON.isKeenPointTreasure.get();
		
		isPoisonAspectDiscoverable = COMMON.isPoisonAspectDiscoverable.get();
		isPoisonAspectTradeable = COMMON.isPoisonAspectTradeable.get();
		isPoisonAspectTreasure = COMMON.isPoisonAspectTreasure.get();
		
		isQuicknessDiscoverable = COMMON.isQuicknessDiscoverable.get();
		isQuicknessTradeable = COMMON.isQuicknessTradeable.get();
		isQuicknessTreasure = COMMON.isQuicknessTreasure.get();
		
		isSearingTouchDiscoverable = COMMON.isSearingTouchDiscoverable.get();
		isSearingTouchTradeable = COMMON.isSearingTouchTradeable.get();
		isSearingTouchTreasure = COMMON.isSearingTouchTreasure.get();
		
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
		
		public final ForgeConfigSpec.BooleanValue isAquaticRejuvenationDiscoverable, isAquaticRejuvenationTradeable, isAquaticRejuvenationTreasure;
		public final ForgeConfigSpec.BooleanValue isBuoyancyDiscoverable, isBuoyancyTradeable, isBuoyancyTreasure;
		public final ForgeConfigSpec.BooleanValue isButcheringDiscoverable, isButcheringTradeable, isButcheringTreasure;
		public final ForgeConfigSpec.BooleanValue isEnderShroudDiscoverable, isEnderShroudTradeable, isEnderShroudTreasure;
		public final ForgeConfigSpec.BooleanValue isInfernoDiscoverable, isInfernoTradeable, isInfernoTreasure;
		public final ForgeConfigSpec.BooleanValue isLeapingDiscoverable, isLeapingTradeable, isLeapingTreasure;
		public final ForgeConfigSpec.BooleanValue isMaelstromDiscoverable, isMaelstromTradeable, isMaelstromTreasure;
		public final ForgeConfigSpec.BooleanValue isObsidianSkullDiscoverable, isObsidianSkullTradeable, isObsidianSkullTreasure;
		public final ForgeConfigSpec.BooleanValue isOutpourDiscoverable, isOutpourTradeable, isOutpourTreasure;
		public final ForgeConfigSpec.BooleanValue isRetributionDiscoverable, isRetributionTradeable, isRetributionTreasure;
		public final ForgeConfigSpec.BooleanValue isKeenPointDiscoverable, isKeenPointTradeable, isKeenPointTreasure;
		public final ForgeConfigSpec.BooleanValue isPoisonAspectDiscoverable, isPoisonAspectTradeable, isPoisonAspectTreasure;
		public final ForgeConfigSpec.BooleanValue isQuicknessDiscoverable, isQuicknessTradeable, isQuicknessTreasure;
		public final ForgeConfigSpec.BooleanValue isSearingTouchDiscoverable, isSearingTouchTradeable, isSearingTouchTreasure;
		public final ForgeConfigSpec.BooleanValue isSteadfastDiscoverable, isSteadfastTradeable, isSteadfastTreasure;
		public final ForgeConfigSpec.BooleanValue isTorrentDiscoverable, isTorrentTradeable, isTorrentTreasure;
		public final ForgeConfigSpec.BooleanValue isVampiricDiscoverable, isVampiricTradeable, isVampiricTreasure;
		public final ForgeConfigSpec.BooleanValue isWeightedBladeDiscoverable, isWeightedBladeTradeable, isWeightedBladeTreasure;
		public final ForgeConfigSpec.BooleanValue isWitherAspectDiscoverable, isWitherAspectTradeable, isWitherAspectTreasure;
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Enchantment Configuration")
			.push("enchantments");
			
			builder.push("aquatic_restoration");
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
			
			builder.push("ender_shroud");
			isEnderShroudDiscoverable = builder
					.worldRestart()
					.define("isEnderShroudDiscoverable", true);			
			isEnderShroudTradeable = builder
					.worldRestart()
					.define("isEnderShroudTradeable", true);
			isEnderShroudTreasure = builder
					.worldRestart()
					.define("isEnderShroudTreasure", false);
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
			
			builder.push("outpour");
			isOutpourDiscoverable = builder
					.worldRestart()
					.define("isOutpourDiscoverable", true);			
			isOutpourTradeable = builder
					.worldRestart()
					.define("isOutpourTradeable", true);
			isOutpourTreasure = builder
					.worldRestart()
					.define("isOutpourTreasure", false);
			builder.pop();
			
			builder.push("retribution");
			isRetributionDiscoverable = builder
					.worldRestart()
					.define("isRetributionDiscoverable", true);			
			isRetributionTradeable = builder
					.worldRestart()
					.define("isRetributionTradeable", true);
			isRetributionTreasure = builder
					.worldRestart()
					.define("isRetributionTreasure", false);
			builder.pop();
			
			builder.push("keen_point");
			isKeenPointDiscoverable = builder
					.worldRestart()
					.define("isKeenPointDiscoverable", true);			
			isKeenPointTradeable = builder
					.worldRestart()
					.define("isKeenPointTradeable", true);
			isKeenPointTreasure = builder
					.worldRestart()
					.define("isKeenPointTreasure", false);
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
			
			builder.push("searing_touch");
			isSearingTouchDiscoverable = builder
					.worldRestart()
					.define("isSearingTouchDiscoverable", true);			
			isSearingTouchTradeable = builder
					.worldRestart()
					.define("isSearingTouchTradeable", true);
			isSearingTouchTreasure = builder
					.worldRestart()
					.define("isSearingTouchTreasure", false);
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
			
			builder.push("weighted_blade");
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