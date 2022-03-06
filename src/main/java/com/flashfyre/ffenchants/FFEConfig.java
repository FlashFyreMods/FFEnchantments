package com.flashfyre.ffenchants;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = FFE.MOD_ID, bus = Bus.MOD)
public class FFEConfig
{
	
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;
	
	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
	
	public static boolean canAnchoringCurseGenerateInLoot;
	public static boolean canAnchoringCurseAppearInTrades;
	
	public static boolean canAquaticRejuvenationBeAppliedToBooks;
	public static boolean canAquaticRejuvenationBeAppliedToItems;
	public static boolean canAquaticRejuvenationGenerateInLoot;
	public static boolean canAquaticRejuvenationAppearInTrades;

	public static boolean canBloodlustBeAppliedToBooks;
	public static boolean canBloodlustBeAppliedToItems;
	public static boolean canBloodlustGenerateInLoot;
	public static boolean canBloodlustAppearInTrades;
	
	public static boolean canBuoyancyBeAppliedToBooks;
	public static boolean canBuoyancyGenerateInLoot;
	public static boolean canBuoyancyAppearInTrades;
	
	public static boolean canButcheringBeAppliedToBooks;
	public static boolean canButcheringBeAppliedToItems;
	public static boolean canButcheringGenerateInLoot;
	public static boolean canButcheringAppearInTrades;
	
	public static boolean canInfernoBeAppliedToBooks;
	public static boolean canInfernoBeAppliedToItems;
	public static boolean canInfernoGenerateInLoot;
	public static boolean canInfernoAppearInTrades;
	
	public static boolean canLeapingBeAppliedToBooks;
	public static boolean canLeapingGenerateInLoot;
	public static boolean canLeapingAppearInTrades;
	
	public static boolean canMaelstromBeAppliedToBooks;
	public static boolean canMaelstromBeAppliedToItems;
	public static boolean canMaelstromGenerateInLoot;
	public static boolean canMaelstromAppearInTrades;
	
	public static boolean canObsidianSkullBeAppliedToBooks;
	public static boolean canObsidianSkullBeAppliedToItems;
	public static boolean canObsidianSkullGenerateInLoot;
	public static boolean canObsidianSkullAppearInTrades;
	
	public static boolean canOutrushBeAppliedToBooks;
	public static boolean canOutrushBeAppliedToItems;
	public static boolean canOutrushGenerateInLoot;
	public static boolean canOutrushAppearInTrades;

	public static boolean canPillagingBeAppliedToBooks;
	public static boolean canPillagingBeAppliedToItems;
	public static boolean canPillagingGenerateInLoot;
	public static boolean canPillagingAppearInTrades;
	
	public static boolean canPointedBeAppliedToBooks;
	public static boolean canPointedBeAppliedToItems;
	public static boolean canPointedGenerateInLoot;
	public static boolean canPointedAppearInTrades;

	public static boolean canPoisonAspectBeAppliedToBooks;
	public static boolean canPoisonAspectBeAppliedToItems;
	public static boolean canPoisonAspectGenerateInLoot;
	public static boolean canPoisonAspectAppearInTrades;
	
	public static boolean canQuicknessBeAppliedToBooks;
	public static boolean canQuicknessGenerateInLoot;
	public static boolean canQuicknessAppearInTrades;
	
	public static boolean canSearingBeAppliedToBooks;
	public static boolean canSearingBeAppliedToItems;
	//public static boolean isSearingApplicableOnOtherArmourAnvil;
	public static boolean canSearingGenerateInLoot;
	public static boolean canSearingAppearInTrades;
	
	public static boolean canSteadfastBeAppliedToBooks;
	public static boolean canSteadfastBeAppliedToItems;
	public static boolean canSteadfastGenerateInLoot;
	public static boolean canSteadfastAppearInTrades;
	
	public static boolean canTorrentBeAppliedToBooks;
	public static boolean canTorrentBeAppliedToItems;
	public static boolean canTorrentGenerateInLoot;
	public static boolean canTorrentAppearInTrades;

	public static boolean canVampiricBeAppliedToBooks;
	public static boolean canVampiricBeAppliedToItems;
	public static boolean canVampiricGenerateInLoot;
	public static boolean canVampiricAppearInTrades;
	
	public static boolean canWeightedBeAppliedToBooks;
	public static boolean canWeightedBeAppliedToItems;
	public static boolean canWeightedGenerateInLoot;
	public static boolean canWeightedAppearInTrades;
	
	public static boolean canWitherAspectBeAppliedToBooks;
	public static boolean canWitherAspectBeAppliedToItems;
	public static boolean canWitherAspectGenerateInLoot;
	public static boolean canWitherAspectAppearInTrades;
	
	
	public static boolean enableEndCityLootAdditions;
	public static boolean enableJungleTempleLootAdditions;
	public static boolean enableNetherFortressLootAdditions;
	public static boolean enablePillagerOutpostLootAdditions;
	public static boolean enableSmallOceanRuinLootAdditions;
	public static boolean enableLargeOceanRuinLootAdditions;
	public static boolean enableWoodlandMansionLootAdditions;
	public static boolean enableIglooLootAdditions;			
	public static boolean enableAllLootAdditions;
	
	
	public static boolean enableSaddlesRandomlyEnchanted;
	public static double enchantSaddleChance;
	
	public static void bakeConfig() {
		
		canAnchoringCurseGenerateInLoot = COMMON.canAnchoringCurseGenerateInLoot.get();
		canAnchoringCurseAppearInTrades = COMMON.canAnchoringCurseAppearInTrades.get();
		
		canAquaticRejuvenationBeAppliedToBooks = COMMON.canAquaticRejuvenationBeAppliedToBooks.get();
		canAquaticRejuvenationBeAppliedToItems = COMMON.canAquaticRejuvenationBeAppliedToItems.get();
		canAquaticRejuvenationGenerateInLoot = COMMON.canAquaticRejuvenationGenerateInLoot.get();
		canAquaticRejuvenationAppearInTrades = COMMON.canAquaticRejuvenationAppearInTrades.get();
		
		canBloodlustBeAppliedToBooks = COMMON.canBloodlustBeAppliedToBooks.get();
		canBloodlustBeAppliedToItems = COMMON.canBloodlustBeAppliedToItems.get();
		canBloodlustGenerateInLoot = COMMON.canBloodlustGenerateInLoot.get();
		canBloodlustAppearInTrades = COMMON.canBloodlustAppearInTrades.get();
		
		canButcheringBeAppliedToBooks = COMMON.canButcheringBeAppliedToBooks.get();
		canButcheringBeAppliedToItems = COMMON.canButcheringBeAppliedToItems.get();
		canButcheringGenerateInLoot = COMMON.canButcheringGenerateInLoot.get();
		canButcheringAppearInTrades = COMMON.canButcheringAppearInTrades.get();
		
		canBuoyancyBeAppliedToBooks = COMMON.canBuoyancyBeAppliedToBooks.get();
		canBuoyancyGenerateInLoot = COMMON.canBuoyancyGenerateInLoot.get();
		canBuoyancyAppearInTrades = COMMON.canBuoyancyAppearInTrades.get();
		
		canInfernoBeAppliedToBooks = COMMON.canInfernoBeAppliedToBooks.get();
		canInfernoBeAppliedToItems = COMMON.canInfernoBeAppliedToItems.get();
		canInfernoGenerateInLoot = COMMON.canInfernoGenerateInLoot.get();
		canInfernoAppearInTrades = COMMON.canInfernoAppearInTrades.get();
		
		canLeapingBeAppliedToBooks = COMMON.canLeapingBeAppliedToBooks.get();
		canLeapingGenerateInLoot = COMMON.canLeapingGenerateInLoot.get();
		canLeapingAppearInTrades = COMMON.canLeapingAppearInTrades.get();
		
		canMaelstromBeAppliedToBooks = COMMON.canMaelstromBeAppliedToBooks.get();
		canMaelstromBeAppliedToItems = COMMON.canMaelstromBeAppliedToItems.get();
		canMaelstromGenerateInLoot = COMMON.canMaelstromGenerateInLoot.get();
		canMaelstromAppearInTrades = COMMON.canMaelstromAppearInTrades.get();
		
		canObsidianSkullBeAppliedToBooks = COMMON.canObsidianSkullBeAppliedToBooks.get();
		canObsidianSkullBeAppliedToItems = COMMON.canObsidianSkullBeAppliedToItems.get();
		canObsidianSkullGenerateInLoot = COMMON.canObsidianSkullGenerateInLoot.get();
		canObsidianSkullAppearInTrades = COMMON.canObsidianSkullAppearInTrades.get();
		
		canOutrushBeAppliedToBooks = COMMON.canOutrushBeAppliedToBooks.get();
		canOutrushBeAppliedToItems = COMMON.canOutrushBeAppliedToItems.get();
		canOutrushGenerateInLoot = COMMON.canOutrushGenerateInLoot.get();
		canOutrushAppearInTrades = COMMON.canOutrushAppearInTrades.get();
		
		canPillagingBeAppliedToBooks = COMMON.canPillagingBeAppliedToBooks.get();
		canPillagingBeAppliedToItems = COMMON.canPillagingBeAppliedToItems.get();
		canPillagingGenerateInLoot = COMMON.canPillagingGenerateInLoot.get();
		canPillagingAppearInTrades = COMMON.canPillagingAppearInTrades.get();
		
		canPointedBeAppliedToBooks = COMMON.canPointedBeAppliedToBooks.get();
		canPointedBeAppliedToItems = COMMON.canPointedBeAppliedToItems.get();
		canPointedGenerateInLoot = COMMON.canPointedGenerateInLoot.get();
		canPointedAppearInTrades = COMMON.canPointedAppearInTrades.get();
		
		canPoisonAspectBeAppliedToBooks = COMMON.canPoisonAspectBeAppliedToBooks.get();
		canPoisonAspectBeAppliedToItems = COMMON.canPoisonAspectBeAppliedToItems.get();
		canPoisonAspectGenerateInLoot = COMMON.canPoisonAspectGenerateInLoot.get();
		canPoisonAspectAppearInTrades = COMMON.canPoisonAspectAppearInTrades.get();
		
		canQuicknessBeAppliedToBooks = COMMON.canQuicknessBeAppliedToBooks.get();
		canQuicknessGenerateInLoot = COMMON.canQuicknessGenerateInLoot.get();
		canQuicknessAppearInTrades = COMMON.canQuicknessAppearInTrades.get();
		
		canSearingBeAppliedToBooks = COMMON.canSearingBeAppliedToBooks.get();
		canSearingBeAppliedToItems = COMMON.canSearingBeAppliedToItems.get();
		canSearingGenerateInLoot = COMMON.canSearingGenerateInLoot.get();
		canSearingAppearInTrades = COMMON.canSearingAppearInTrades.get();
		
		canSteadfastBeAppliedToBooks = COMMON.canSteadfastBeAppliedToBooks.get();
		canSteadfastBeAppliedToItems = COMMON.canSteadfastBeAppliedToItems.get();
		canSteadfastGenerateInLoot = COMMON.canSteadfastGenerateInLoot.get();
		canSteadfastAppearInTrades = COMMON.canSteadfastAppearInTrades.get();
		
		canTorrentBeAppliedToBooks = COMMON.canTorrentBeAppliedToBooks.get();
		canTorrentBeAppliedToItems = COMMON.canTorrentBeAppliedToItems.get();
		canTorrentGenerateInLoot = COMMON.canTorrentGenerateInLoot.get();
		canTorrentAppearInTrades = COMMON.canTorrentAppearInTrades.get();
		
		canVampiricBeAppliedToBooks = COMMON.canVampiricBeAppliedToBooks.get();
		canVampiricBeAppliedToItems = COMMON.canVampiricBeAppliedToItems.get();
		canVampiricGenerateInLoot = COMMON.canVampiricGenerateInLoot.get();
		canVampiricAppearInTrades = COMMON.canVampiricAppearInTrades.get();
		
		canWeightedBeAppliedToBooks = COMMON.canWeightedBeAppliedToBooks.get();
		canWeightedBeAppliedToItems = COMMON.canWeightedBeAppliedToItems.get();
		canWeightedGenerateInLoot = COMMON.canWeightedGenerateInLoot.get();
		canWeightedAppearInTrades = COMMON.canWeightedAppearInTrades.get();
		
		canWitherAspectBeAppliedToBooks = COMMON.canWitherAspectBeAppliedToBooks.get();
		canWitherAspectBeAppliedToItems = COMMON.canWitherAspectBeAppliedToItems.get();
		canWitherAspectGenerateInLoot = COMMON.canWitherAspectGenerateInLoot.get();
		canWitherAspectAppearInTrades = COMMON.canWitherAspectAppearInTrades.get();
		
		enableEndCityLootAdditions = COMMON.enableEndCityLootAdditions.get();
		enableJungleTempleLootAdditions = COMMON.enableJungleTempleLootAdditions.get();
		enableNetherFortressLootAdditions = COMMON.enableNetherFortressLootAdditions.get();
		enablePillagerOutpostLootAdditions = COMMON.enablePillagerOutpostLootAdditions.get();
		enableSmallOceanRuinLootAdditions = COMMON.enableSmallOceanRuinLootAdditions.get();
		enableLargeOceanRuinLootAdditions = COMMON.enableLargeOceanRuinLootAdditions.get();
		enableWoodlandMansionLootAdditions = COMMON.enableWoodlandMansionLootAdditions.get();
		enableIglooLootAdditions = COMMON.enableIglooLootAdditions.get();
		enableAllLootAdditions = COMMON.enableAllLootAdditions.get();
		
		enableSaddlesRandomlyEnchanted = COMMON.enableSaddlesRandomlyEnchanted.get();
		enchantSaddleChance = COMMON.enchantSaddleChance.get();
		
		FFE.LOGGER.info("Baked FFE Config.");
	}
	
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfigEvent configEvent) {
		if (configEvent.getConfig().getSpec() == FFEConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}
	
	@SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
		FFE.LOGGER.debug("Loaded FFE config file", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
    	FFE.LOGGER.debug("FFE config just got changed on the file system!");
    }
	
	public static class Common {
		
		public final ForgeConfigSpec.BooleanValue canAnchoringCurseGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canAnchoringCurseAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canAquaticRejuvenationBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canAquaticRejuvenationBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canAquaticRejuvenationGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canAquaticRejuvenationAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canBloodlustBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canBloodlustBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canBloodlustGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canBloodlustAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canBuoyancyBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canBuoyancyGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canBuoyancyAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canButcheringBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canButcheringBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canButcheringGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canButcheringAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canInfernoBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canInfernoBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canInfernoGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canInfernoAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canLeapingBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canLeapingGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canLeapingAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canMaelstromBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canMaelstromBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canMaelstromGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canMaelstromAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canObsidianSkullBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canObsidianSkullBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canObsidianSkullGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canObsidianSkullAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canOutrushBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canOutrushBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canOutrushGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canOutrushAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canPillagingBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canPillagingBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canPillagingGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canPillagingAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canPointedBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canPointedBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canPointedGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canPointedAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canPoisonAspectBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canPoisonAspectBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canPoisonAspectGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canPoisonAspectAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canQuicknessBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canQuicknessGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canQuicknessAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canSearingBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canSearingBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canSearingGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canSearingAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canSteadfastBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canSteadfastBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canSteadfastGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canSteadfastAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canTorrentBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canTorrentBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canTorrentGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canTorrentAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canVampiricBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canVampiricBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canVampiricGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canVampiricAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canWeightedBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canWeightedBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canWeightedGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canWeightedAppearInTrades;
		
		public final ForgeConfigSpec.BooleanValue canWitherAspectBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canWitherAspectBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canWitherAspectGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canWitherAspectAppearInTrades;

		//Loot configs
		public final ForgeConfigSpec.BooleanValue enableEndCityLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableJungleTempleLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableNetherFortressLootAdditions;
		public final ForgeConfigSpec.BooleanValue enablePillagerOutpostLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableSmallOceanRuinLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableLargeOceanRuinLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableWoodlandMansionLootAdditions;
		public final ForgeConfigSpec.BooleanValue enableIglooLootAdditions;		
		
		public final ForgeConfigSpec.BooleanValue enableAllLootAdditions;
		
		public final ForgeConfigSpec.BooleanValue enableSaddlesRandomlyEnchanted;
		public final ForgeConfigSpec.DoubleValue enchantSaddleChance;
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Enchantment Configuration")
			.push("enchantments");
			
			builder.push("anchoring_curse");
			canAnchoringCurseGenerateInLoot = builder
					.comment("Whether or not Curse of Anchoring can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canAnchoringCurseGenerateInLoot", true);
			
			canAnchoringCurseAppearInTrades = builder
					.comment("Whether or not Curse of Anchoring can be traded by villagers.")
					.worldRestart()
					.define("canAnchoringCurseAppearInTrades", true);
			builder.pop();
			
			builder.push("aquatic_rejuvenation");
			canAquaticRejuvenationBeAppliedToItems = builder
					.comment("Whether or not Aquatic Rejuvenation can be applied to possible items.")
					.worldRestart()
					.define("canAquaticRejuvenationBeAppliedToItems", false);
			
			canAquaticRejuvenationBeAppliedToBooks = builder
					.comment("Whether or not Aquatic Rejuvenation can be applied to books.")
					.worldRestart()
					.define("canAquaticRejuvenationBeAppliedToBooks", false);			
			
			canAquaticRejuvenationGenerateInLoot = builder
					.comment("Whether or not Aquatic Rejuvenation can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canAquaticRejuvenationGenerateInLoot", true);
			
			canAquaticRejuvenationAppearInTrades = builder
					.comment("Whether or not Aquatic Rejuvenation can be traded by villagers.")
					.worldRestart()
					.define("canAquaticRejuvenationAppearInTrades", true);
			builder.pop();
			
			builder.push("bloodlust");
			canBloodlustBeAppliedToItems = builder
					.comment("Whether or not Bloodlust can be applied to possible items.")
					.worldRestart()
					.define("canBloodlustBeAppliedToItems", true);
			
			canBloodlustBeAppliedToBooks = builder
					.comment("Whether or not Bloodlust can be applied to books.")
					.worldRestart()
					.define("canBloodlustBeAppliedToBooks", true);			
			
			canBloodlustGenerateInLoot = builder
					.comment("Whether or not Bloodlust can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canBloodlustGenerateInLoot", true);
			
			canBloodlustAppearInTrades = builder
					.comment("Whether or not Bloodlust can be traded by villagers.")
					.worldRestart()
					.define("canBloodlustAppearInTrades", true);
			builder.pop();
			
			builder.push("buoyancy");
			canBuoyancyBeAppliedToBooks = builder
					.comment("Whether or not Buoyancy can be applied to books.")
					.worldRestart()
					.define("canBuoyancyBeAppliedToBooks", true);
			
			canBuoyancyGenerateInLoot = builder
					.comment("Whether or not Buoyancy can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canBuoyancyGenerateInLoot", true);
			
			canBuoyancyAppearInTrades = builder
					.comment("Whether or not Buoyancy can be traded by villagers.")
					.worldRestart()
					.define("canBuoyancyAppearInTrades", true);
			builder.pop();
			
			builder.push("butchering");
			canButcheringBeAppliedToItems = builder
					.comment("Whether or not Butchering can be applied to possible items.")
					.worldRestart()
					.define("canButcheringBeAppliedToItems", true);
			
			canButcheringBeAppliedToBooks = builder
					.comment("Whether or not Butchering can be applied to books.")
					.worldRestart()
					.define("canButcheringBeAppliedToBooks", true);			
			
			canButcheringGenerateInLoot = builder
					.comment("Whether or not Butchering can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canButcheringGenerateInLoot", true);
			
			canButcheringAppearInTrades = builder
					.comment("Whether or not Butchering can be traded by villagers.")
					.worldRestart()
					.define("canButcheringAppearInTrades", true);
			builder.pop();
			
			builder.push("inferno");
			canInfernoBeAppliedToItems = builder
					.comment("Whether or not Inferno can be applied to possible items.")
					.worldRestart()
					.define("canInfernoBeAppliedToItems", true);
			
			canInfernoBeAppliedToBooks = builder
					.comment("Whether or not Inferno can be applied to books.")
					.worldRestart()
					.define("canInfernoBeAppliedToBooks", true);			
			
			canInfernoGenerateInLoot = builder
					.comment("Whether or not Inferno can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canInfernoGenerateInLoot", true);
			
			canInfernoAppearInTrades = builder
					.comment("Whether or not Inferno can be traded by villagers.")
					.worldRestart()
					.define("canInfernoAppearInTrades", true);
			builder.pop();
			
			builder.push("leaping");
			canLeapingBeAppliedToBooks = builder
					.comment("Whether or not Leaping can be applied to books.")
					.worldRestart()
					.define("canLeapingBeAppliedToBooks", true);
			
			canLeapingGenerateInLoot = builder
					.comment("Whether or not Leaping can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canLeapingGenerateInLoot", true);
			
			canLeapingAppearInTrades = builder
					.comment("Whether or not Leaping can be traded by villagers.")
					.worldRestart()
					.define("canLeapingAppearInTrades", true);
			builder.pop();
			
			builder.push("maelstrom");
			canMaelstromBeAppliedToItems = builder
					.comment("Whether or not Maelstrom can be applied to possible items.")
					.worldRestart()
					.define("canMaelstromBeAppliedToItems", true);
			
			canMaelstromBeAppliedToBooks = builder
					.comment("Whether or not Maelstrom can be applied to books.")
					.worldRestart()
					.define("canMaelstromBeAppliedToBooks", true);			
			
			canMaelstromGenerateInLoot = builder
					.comment("Whether or not Maelstrom can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canMaelstromGenerateInLoot", true);
			
			canMaelstromAppearInTrades = builder
					.comment("Whether or not Maelstrom can be traded by villagers.")
					.worldRestart()
					.define("canMaelstromAppearInTrades", true);
			builder.pop();
			
			builder.push("obsidian_skull");
			canObsidianSkullBeAppliedToItems = builder
					.comment("Whether or not Obsidian Skull can be applied to possible items.")
					.worldRestart()
					.define("canObsidianSkullBeAppliedToItems", false);
			
			canObsidianSkullBeAppliedToBooks = builder
					.comment("Whether or not Obsidia nSkull can be applied to books.")
					.worldRestart()
					.define("canObsidianSkullBeAppliedToBooks", false);			
			
			canObsidianSkullGenerateInLoot = builder
					.comment("Whether or not Obsidian Skull can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canObsidianSkullGenerateInLoot", true);
			
			canObsidianSkullAppearInTrades = builder
					.comment("Whether or not Obsidian Skull can be traded by villagers.")
					.worldRestart()
					.define("canObsidianSkullAppearInTrades", true);
			builder.pop();
			
			builder.push("outrush");
			canOutrushBeAppliedToItems = builder
					.comment("Whether or not Outrush can be applied to possible items.")
					.worldRestart()
					.define("canOutrushBeAppliedToItems", true);
			
			canOutrushBeAppliedToBooks = builder
					.comment("Whether or not Outrush can be applied to books.")
					.worldRestart()
					.define("canOutrushBeAppliedToBooks", true);			
			
			canOutrushGenerateInLoot = builder
					.comment("Whether or not Outrush can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canOutrushGenerateInLoot", true);
			
			canOutrushAppearInTrades = builder
					.comment("Whether or not Outrush can be traded by villagers.")
					.worldRestart()
					.define("canOutrushAppearInTrades", true);
			builder.pop();
			
			builder.push("pillaging");
			canPillagingBeAppliedToItems = builder
					.comment("Whether or not Pillaging can be applied to possible items.")
					.worldRestart()
					.define("canPillagingBeAppliedToItems", true);
			
			canPillagingBeAppliedToBooks = builder
					.comment("Whether or not Pillaging can be applied to books.")
					.worldRestart()
					.define("canPillagingBeAppliedToBooks", true);			
			
			canPillagingGenerateInLoot = builder
					.comment("Whether or not Pillaging can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canPillagingGenerateInLoot", true);
			
			canPillagingAppearInTrades = builder
					.comment("Whether or not Pillaging can be traded by villagers.")
					.worldRestart()
					.define("canPillagingAppearInTrades", true);
			builder.pop();
			
			builder.push("pointed");
			canPointedBeAppliedToItems = builder
					.worldRestart()
					.define("canPointedBeAppliedToItems", true);
			
			canPointedBeAppliedToBooks = builder
					.worldRestart()
					.define("canPointedBeAppliedToBooks", true);			
			
			canPointedGenerateInLoot = builder
					.worldRestart()
					.define("canPointedGenerateInLoot", true);
			
			canPointedAppearInTrades = builder
					.worldRestart()
					.define("canPointedAppearInTrades", true);
			builder.pop();
			
			builder.push("poison_aspect");
			canPoisonAspectBeAppliedToItems = builder
					.comment("Whether or not Poison Aspect can be applied to possible items.")
					.worldRestart()
					.define("canPoisonAspectBeAppliedToItems", true);
			
			canPoisonAspectBeAppliedToBooks = builder
					.comment("Whether or not Poison Aspect can be applied to books.")
					.worldRestart()
					.define("canPoisonAspectBeAppliedToBooks", true);			
			
			canPoisonAspectGenerateInLoot = builder
					.comment("Whether or not Poison Aspect can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canPoisonAspectGenerateInLoot", true);
			
			canPoisonAspectAppearInTrades = builder
					.comment("Whether or not Poison Aspect can be traded by villagers.")
					.worldRestart()
					.define("canPoisonAspectAppearInTrades", true);
			builder.pop();
			
			builder.push("quickness");
			canQuicknessBeAppliedToBooks = builder
					.comment("Whether or not Quickness can be applied to books.")
					.worldRestart()
					.define("canQuicknessBeAppliedToBooks", true);
			
			canQuicknessGenerateInLoot = builder
					.comment("Whether or not Quickness can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canQuicknessGenerateInLoot", true);
			
			canQuicknessAppearInTrades = builder
					.comment("Whether or not Quickness can be traded by villagers.")
					.worldRestart()
					.define("canQuicknessAppearInTrades", true);
			builder.pop();
			
			builder.push("searing");
			canSearingBeAppliedToItems = builder
					.comment("Whether or not Searing can be applied to possible items.")
					.worldRestart()
					.define("canSearingBeAppliedToItems", true);
			
			canSearingBeAppliedToBooks = builder
					.comment("Whether or not Searing can be applied to books.")
					.worldRestart()
					.define("canSearingBeAppliedToBooks", true);
		
			canSearingGenerateInLoot = builder
					.comment("Whether or not Searing can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canSearingGenerateInLoot", true);
			
			canSearingAppearInTrades = builder
					.comment("Whether or not Searing can be traded by villagers.")
					.worldRestart()
					.define("canSearingAppearInTrades", true);
			builder.pop();
			
			builder.push("steadfast");
			canSteadfastBeAppliedToItems = builder
					.comment("Whether or not Steadfast can be applied to possible items.")
					.worldRestart()
					.define("canSteadfastBeAppliedToItems", true);
			
			canSteadfastBeAppliedToBooks = builder
					.comment("Whether or not Steadfast can be applied to books.")
					.worldRestart()
					.define("canSteadfastBeAppliedToBooks", true);			
			
			canSteadfastGenerateInLoot = builder
					.comment("Whether or not Steadfast can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canSteadfastGenerateInLoot", true);
			
			canSteadfastAppearInTrades = builder
					.comment("Whether or not Steadfast can be traded by villagers.")
					.worldRestart()
					.define("canSteadfastAppearInTrades", true);
			builder.pop();
			
			builder.push("torrent");
			canTorrentBeAppliedToItems = builder
					.comment("Whether or not Torrent can be applied to possible items.")
					.worldRestart()
					.define("canTorrentBeAppliedToItems", true);
			
			canTorrentBeAppliedToBooks = builder
					.comment("Whether or not Torrent can be applied to books.")
					.worldRestart()
					.define("canTorrentBeAppliedToBooks", true);			
			
			canTorrentGenerateInLoot = builder
					.comment("Whether or not Torrent can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canTorrentGenerateInLoot", true);
			
			canTorrentAppearInTrades = builder
					.comment("Whether or not Torrent can be traded by villagers.")
					.worldRestart()
					.define("canTorrentAppearInTrades", true);
			builder.pop();
			
			builder.push("vampiric");
			canVampiricBeAppliedToItems = builder
					.comment("Whether or not Vampiric can be applied to possible items.")
					.worldRestart()
					.define("canVampiricBeAppliedToItems", false);
			
			canVampiricBeAppliedToBooks = builder
					.comment("Whether or not Vampiric can be applied to books.")
					.worldRestart()
					.define("canVampiricBeAppliedToBooks", false);			
			
			canVampiricGenerateInLoot = builder
					.comment("Whether or not Vampiric can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canVampiricGenerateInLoot", true);
			
			canVampiricAppearInTrades = builder
					.comment("Whether or not Vampiric can be traded by villagers.")
					.worldRestart()
					.define("canVampiricAppearInTrades", true);
			builder.pop();
			
			builder.push("weighted");
			canWeightedBeAppliedToItems = builder
					.comment("Whether or not Weighted can be applied to possible items.")
					.worldRestart()
					.define("canWeightedBeAppliedToItems", true);
			
			canWeightedBeAppliedToBooks = builder
					.comment("Whether or not Weighted can be applied to books.")
					.worldRestart()
					.define("canWeightedBeAppliedToBooks", true);			
			
			canWeightedGenerateInLoot = builder
					.comment("Whether or not Weighted can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canWeightedGenerateInLoot", true);
			
			canWeightedAppearInTrades = builder
					.comment("Whether or not Weighted can be traded by villagers.")
					.worldRestart()
					.define("canWeightedAppearInTrades", true);
			builder.pop();				
			
			builder.push("wither_aspect");
			canWitherAspectBeAppliedToItems = builder
					.comment("Whether or not Wither Aspect can be applied to possible items.")
					.worldRestart()
					.define("canWitherAspectBeAppliedToItems", false);
			
			canWitherAspectBeAppliedToBooks = builder
					.comment("Whether or not Wither Aspect can be applied to books.")
					.worldRestart()
					.define("canWitherAspectBeAppliedToBooks", false);			
			
			canWitherAspectGenerateInLoot = builder
					.comment("Whether or not Wither Aspect can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canWitherAspectGenerateInLoot", true);
			
			canWitherAspectAppearInTrades = builder
					.comment("Whether or not Wither Aspect can be traded by villagers.")
					.worldRestart()
					.define("canWitherAspectAppearInTrades", true);
			builder.pop();
			
			builder.pop();
			
			builder.comment("Loot Table Additions Configuration")
			.push("loot_tables");

			enableAllLootAdditions = builder
					.comment("Enable/disable all chest loot additions")
					.worldRestart()
					.define("enableLootAdditions", true);
			
			enableEndCityLootAdditions = builder
					.comment("Enable/disable additions to end city chest loot")
					.worldRestart()
					.define("enableEndCityLootAdditions", true);
			
			enableJungleTempleLootAdditions = builder
					.comment("Enable/disable additions to jungle temple chest loot")
					.worldRestart()
					.define("enableJungleTempleLootAdditions", true);
			
			enableNetherFortressLootAdditions = builder
					.comment("Enable/disable additions to nether fortress chest loot")
					.worldRestart()
					.define("enableNetherFortressLootAdditions", true);
			
			enablePillagerOutpostLootAdditions = builder
					.comment("Enable/disable additions to pillager outpost chest loot")
					.worldRestart()
					.define("enablePillagerOutpostLootAdditions", true);
			
			enableSmallOceanRuinLootAdditions = builder
					.comment("Enable/disable additions to small ocean ruin chest loot")
					.worldRestart()
					.define("enableSmallOceanRuinLootAdditions", true);
			
			enableLargeOceanRuinLootAdditions = builder
					.comment("Enable/disable additions to large ocean ruin chest loot")
					.worldRestart()
					.define("enableLargeOceanRuinLootAdditions", true);
			
			enableWoodlandMansionLootAdditions = builder
					.comment("Enable/disable additions to woodland mansion chest loot")
					.worldRestart()
					.define("enableWoodlandMansionLootAdditions", true);
			
			enableIglooLootAdditions = builder
					.comment("Enable/disable additions to igloo chest loot")
					.worldRestart()
					.define("enableIglooLootAdditions", true);
			
			builder.pop();
			
			builder.comment("Loot Modifier Configuration")
			.push("loot_modifiers");
			
			enableSaddlesRandomlyEnchanted = builder
					.comment("Allow/disallow saddles as part of loot to be randomly enchanted.")
					.worldRestart()
					.define("enableSaddlesRandomlyEnchanted", true);
			
			enchantSaddleChance = builder
					.comment("The chance for a saddle to be enchanted with a random saddle enchantment. 1.0 means it will be guaranteed to be enchanted.")
					.worldRestart()
					.defineInRange("enchantSaddleChance", 0.5F, 0.0F, 1.0F);
			
			builder.pop();
		}
	}
}