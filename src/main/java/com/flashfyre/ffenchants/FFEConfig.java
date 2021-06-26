package com.flashfyre.ffenchants;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

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
	public static boolean canAnchoringAppearInTrades;
	
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
	
	public static boolean canSharpshooterBeAppliedToBooks;
	public static boolean canSharpshooterBeAppliedToItems;
	public static boolean canSharpshooterGenerateInLoot;
	public static boolean canSharpshooterAppearInTrades;
	
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
	
	public static boolean enableSaddlesRandomlyEnchanted;
	public static double enchantSaddleChance;
	
	public static void bakeConfig() {
		
		canAnchoringCurseGenerateInLoot = COMMON.canAnchoringCurseGenerateInLoot.get();
		canAnchoringAppearInTrades = COMMON.canAnchoringCurseAppearInTrades.get();
		
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
		
		canSharpshooterBeAppliedToBooks = COMMON.canSharpshooterBeAppliedToBooks.get();
		canSharpshooterBeAppliedToItems = COMMON.canSharpshooterBeAppliedToItems.get();
		canSharpshooterGenerateInLoot = COMMON.canSharpshooterGenerateInLoot.get();
		canSharpshooterAppearInTrades = COMMON.canSharpshooterAppearInTrades.get();
		
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
		
		enableSaddlesRandomlyEnchanted = COMMON.enableSaddlesRandomlyEnchanted.get();
		enchantSaddleChance = COMMON.enchantSaddleChance.get();
		
		FFE.LOGGER.info("Baked FFE Config.");
	}
	
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
		if (configEvent.getConfig().getSpec() == FFEConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}
	
	@SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
		FFE.LOGGER.debug("Loaded FFE config file", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading configEvent) {
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
		
		public final ForgeConfigSpec.BooleanValue canSharpshooterBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canSharpshooterBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canSharpshooterGenerateInLoot;
		public final ForgeConfigSpec.BooleanValue canSharpshooterAppearInTrades;
		
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
		
		public final ForgeConfigSpec.BooleanValue enableSaddlesRandomlyEnchanted;
		public final ForgeConfigSpec.DoubleValue enchantSaddleChance;
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Enchantment Configuration")
			.comment("Note: Due to vanilla behaviour, disabling enchantments from being generated in loot also prevents them from being in enchantment tables.")
			.push("enchantments");
			
			builder.push("anchoring_curse");
			canAnchoringCurseGenerateInLoot = builder
					.worldRestart()
					.define("canAnchoringCurseGenerateInLoot", true);
			
			canAnchoringCurseAppearInTrades = builder
					.worldRestart()
					.define("canAnchoringCurseAppearInTrades", true);
			builder.pop();
			
			builder.push("aquatic_rejuvenation");
			canAquaticRejuvenationBeAppliedToItems = builder
					.worldRestart()
					.define("canAquaticRejuvenationBeAppliedToItems", false);
			
			canAquaticRejuvenationBeAppliedToBooks = builder
					.worldRestart()
					.define("canAquaticRejuvenationBeAppliedToBooks", false);
			
			canAquaticRejuvenationGenerateInLoot = builder
					.worldRestart()
					.define("canAquaticRejuvenationGenerateInLoot", true);
			
			canAquaticRejuvenationAppearInTrades = builder
					.worldRestart()
					.define("canAquaticRejuvenationAppearInTrades", true);
			builder.pop();
			
			builder.push("bloodlust");
			canBloodlustBeAppliedToItems = builder
					.worldRestart()
					.define("canBloodlustBeAppliedToItems", true);
			
			canBloodlustBeAppliedToBooks = builder
					.worldRestart()
					.define("canBloodlustBeAppliedToBooks", true);			
			
			canBloodlustGenerateInLoot = builder
					.worldRestart()
					.define("canBloodlustGenerateInLoot", true);
			
			canBloodlustAppearInTrades = builder
					.worldRestart()
					.define("canBloodlustAppearInTrades", true);
			builder.pop();
			
			builder.push("buoyancy");
			canBuoyancyBeAppliedToBooks = builder
					.worldRestart()
					.define("canBuoyancyBeAppliedToBooks", true);
			
			canBuoyancyGenerateInLoot = builder
					.worldRestart()
					.define("canBuoyancyGenerateInLoot", true);
			
			canBuoyancyAppearInTrades = builder
					.worldRestart()
					.define("canBuoyancyAppearInTrades", true);
			builder.pop();
			
			builder.push("butchering");
			canButcheringBeAppliedToItems = builder
					.worldRestart()
					.define("canButcheringBeAppliedToItems", true);
			
			canButcheringBeAppliedToBooks = builder
					.worldRestart()
					.define("canButcheringBeAppliedToBooks", true);			
			
			canButcheringGenerateInLoot = builder
					.worldRestart()
					.define("canButcheringGenerateInLoot", true);
			
			canButcheringAppearInTrades = builder
					.worldRestart()
					.define("canButcheringAppearInTrades", true);
			builder.pop();
			
			builder.push("inferno");
			canInfernoBeAppliedToItems = builder
					.worldRestart()
					.define("canInfernoBeAppliedToItems", true);
			
			canInfernoBeAppliedToBooks = builder
					.worldRestart()
					.define("canInfernoBeAppliedToBooks", true);			
			
			canInfernoGenerateInLoot = builder
					.worldRestart()
					.define("canInfernoGenerateInLoot", true);
			
			canInfernoAppearInTrades = builder
					.worldRestart()
					.define("canInfernoAppearInTrades", true);
			builder.pop();
			
			builder.push("leaping");
			canLeapingBeAppliedToBooks = builder
					.worldRestart()
					.define("canLeapingBeAppliedToBooks", true);
			
			canLeapingGenerateInLoot = builder
					.worldRestart()
					.define("canLeapingGenerateInLoot", true);
			
			canLeapingAppearInTrades = builder
					.worldRestart()
					.define("canLeapingAppearInTrades", true);
			builder.pop();
			
			builder.push("maelstrom");
			canMaelstromBeAppliedToItems = builder
					.worldRestart()
					.define("canMaelstromBeAppliedToItems", true);
			
			canMaelstromBeAppliedToBooks = builder
					.worldRestart()
					.define("canMaelstromBeAppliedToBooks", true);			
			
			canMaelstromGenerateInLoot = builder
					.worldRestart()
					.define("canMaelstromGenerateInLoot", true);
			
			canMaelstromAppearInTrades = builder
					.worldRestart()
					.define("canMaelstromAppearInTrades", true);
			builder.pop();
			
			builder.push("obsidian_skull");
			canObsidianSkullBeAppliedToItems = builder
					.worldRestart()
					.define("canObsidianSkullBeAppliedToItems", false);
			
			canObsidianSkullBeAppliedToBooks = builder
					.worldRestart()
					.define("canObsidianSkullBeAppliedToBooks", false);			
			
			canObsidianSkullGenerateInLoot = builder
					.worldRestart()
					.define("canObsidianSkullGenerateInLoot", true);
			
			canObsidianSkullAppearInTrades = builder
					.worldRestart()
					.define("canObsidianSkullAppearInTrades", true);
			builder.pop();
			
			builder.push("outrush");
			canOutrushBeAppliedToItems = builder
					.worldRestart()
					.define("canOutrushBeAppliedToItems", true);
			
			canOutrushBeAppliedToBooks = builder
					.worldRestart()
					.define("canOutrushBeAppliedToBooks", true);			
			
			canOutrushGenerateInLoot = builder
					.worldRestart()
					.define("canOutrushGenerateInLoot", true);
			
			canOutrushAppearInTrades = builder
					.worldRestart()
					.define("canOutrushAppearInTrades", true);
			builder.pop();
			
			builder.push("pillaging");
			canPillagingBeAppliedToItems = builder
					.worldRestart()
					.define("canPillagingBeAppliedToItems", true);
			
			canPillagingBeAppliedToBooks = builder
					.worldRestart()
					.define("canPillagingBeAppliedToBooks", true);			
			
			canPillagingGenerateInLoot = builder
					.worldRestart()
					.define("canPillagingGenerateInLoot", true);
			
			canPillagingAppearInTrades = builder
					.worldRestart()
					.define("canPillagingAppearInTrades", true);
			builder.pop();
			
			builder.push("poison_aspect");
			canPoisonAspectBeAppliedToItems = builder
					.worldRestart()
					.define("canPoisonAspectBeAppliedToItems", true);
			
			canPoisonAspectBeAppliedToBooks = builder
					.worldRestart()
					.define("canPoisonAspectBeAppliedToBooks", true);			
			
			canPoisonAspectGenerateInLoot = builder
					.worldRestart()
					.define("canPoisonAspectGenerateInLoot", true);
			
			canPoisonAspectAppearInTrades = builder
					.worldRestart()
					.define("canPoisonAspectAppearInTrades", true);
			builder.pop();
			
			builder.push("quickness");
			canQuicknessBeAppliedToBooks = builder
					.worldRestart()
					.define("canQuicknessBeAppliedToBooks", true);
			
			canQuicknessGenerateInLoot = builder
					.worldRestart()
					.define("canQuicknessGenerateInLoot", true);
			
			canQuicknessAppearInTrades = builder
					.worldRestart()
					.define("canQuicknessAppearInTrades", true);
			builder.pop();
			
			builder.push("searing");
			canSearingBeAppliedToItems = builder
					.worldRestart()
					.define("canSearingBeAppliedToItems", true);
			
			canSearingBeAppliedToBooks = builder
					.worldRestart()
					.define("canSearingBeAppliedToBooks", true);
		
			canSearingGenerateInLoot = builder
					.worldRestart()
					.define("canSearingGenerateInLoot", true);
			
			canSearingAppearInTrades = builder
					.worldRestart()
					.define("canSearingAppearInTrades", true);
			builder.pop();
			
			builder.push("sharpshooter");
			canSharpshooterBeAppliedToItems = builder
					.worldRestart()
					.define("canSharpshooterBeAppliedToItems", true);
			
			canSharpshooterBeAppliedToBooks = builder
					.worldRestart()
					.define("canSharpshooterBeAppliedToBooks", true);			
			
			canSharpshooterGenerateInLoot = builder
					.worldRestart()
					.define("canSharpshooterGenerateInLoot", true);
			
			canSharpshooterAppearInTrades = builder
					.worldRestart()
					.define("canSharpshootertAppearInTrades", true);
			builder.pop();
			
			builder.push("steadfast");
			canSteadfastBeAppliedToItems = builder
					.worldRestart()
					.define("canSteadfastBeAppliedToItems", true);
			
			canSteadfastBeAppliedToBooks = builder
					.worldRestart()
					.define("canSteadfastBeAppliedToBooks", true);			
			
			canSteadfastGenerateInLoot = builder
					.worldRestart()
					.define("canSteadfastGenerateInLoot", true);
			
			canSteadfastAppearInTrades = builder
					.worldRestart()
					.define("canSteadfastAppearInTrades", true);
			builder.pop();
			
			builder.push("torrent");
			canTorrentBeAppliedToItems = builder
					.worldRestart()
					.define("canTorrentBeAppliedToItems", true);
			
			canTorrentBeAppliedToBooks = builder
					.worldRestart()
					.define("canTorrentBeAppliedToBooks", true);			
			
			canTorrentGenerateInLoot = builder
					.worldRestart()
					.define("canTorrentGenerateInLoot", true);
			
			canTorrentAppearInTrades = builder
					.worldRestart()
					.define("canTorrentAppearInTrades", true);
			builder.pop();
			
			builder.push("vampiric");
			canVampiricBeAppliedToItems = builder
					.worldRestart()
					.define("canVampiricBeAppliedToItems", false);
			
			canVampiricBeAppliedToBooks = builder
					.worldRestart()
					.define("canVampiricBeAppliedToBooks", false);			
			
			canVampiricGenerateInLoot = builder
					.worldRestart()
					.define("canVampiricGenerateInLoot", true);
			
			canVampiricAppearInTrades = builder
					.worldRestart()
					.define("canVampiricAppearInTrades", true);
			builder.pop();
			
			builder.push("weighted");
			canWeightedBeAppliedToItems = builder
					.worldRestart()
					.define("canWeightedBeAppliedToItems", true);
			
			canWeightedBeAppliedToBooks = builder
					.worldRestart()
					.define("canWeightedBeAppliedToBooks", true);			
			
			canWeightedGenerateInLoot = builder
					.worldRestart()
					.define("canWeightedGenerateInLoot", true);
			
			canWeightedAppearInTrades = builder
					.worldRestart()
					.define("canWeightedAppearInTrades", true);
			builder.pop();				
			
			builder.push("wither_aspect");
			canWitherAspectBeAppliedToItems = builder
					.worldRestart()
					.define("canWitherAspectBeAppliedToItems", false);
			
			canWitherAspectBeAppliedToBooks = builder
					.worldRestart()
					.define("canWitherAspectBeAppliedToBooks", false);			
			
			canWitherAspectGenerateInLoot = builder
					.worldRestart()
					.define("canWitherAspectGenerateInLoot", true);
			
			canWitherAspectAppearInTrades = builder
					.worldRestart()
					.define("canWitherAspectAppearInTrades", true);
			builder.pop();
			
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