package com.flashfyre.ffenchants.misc;

import org.apache.commons.lang3.tuple.Pair;

import com.flashfyre.ffenchants.FFE;

import net.minecraft.util.ResourceLocation;
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
	
	public static boolean canAnchoringCurseSpawnOnBooks;
	
	public static boolean canAquaticRejuvenationBeAppliedToBooks;
	public static boolean canAquaticRejuvenationBeAppliedToItems;
	public static boolean canAquaticRejuvenationSpawnOnBooks;

	public static boolean canBloodlustBeAppliedToBooks;
	public static boolean canBloodlustBeAppliedToItems;
	public static boolean canBloodlustSpawnOnBooks;
	
	public static boolean canBuoyancyBeAppliedToBooks;
	public static boolean canBuoyancySpawnOnBooks;
	
	public static boolean canButcheringBeAppliedToBooks;
	public static boolean canButcheringBeAppliedToItems;
	public static boolean canButcheringSpawnOnBooks;
	
	public static boolean canLeapingBeAppliedToBooks;
	public static boolean canLeapingSpawnOnBooks;
	
	public static boolean canObsidianSkullBeAppliedToBooks;
	public static boolean canObsidianSkullBeAppliedToItems;
	public static boolean canObsidianSkullSpawnOnBooks;
	
	public static boolean canOutrushBeAppliedToBooks;
	public static boolean canOutrushBeAppliedToItems;
	public static boolean canOutrushSpawnOnBooks;

	public static boolean canPillagingBeAppliedToBooks;
	public static boolean canPillagingBeAppliedToItems;
	public static boolean canPillagingSpawnOnBooks;

	public static boolean canPoisonAspectBeAppliedToBooks;
	public static boolean canPoisonAspectBeAppliedToItems;
	public static boolean canPoisonAspectSpawnOnBooks;
	
	public static boolean canQuicknessBeAppliedToBooks;
	public static boolean canQuicknessSpawnOnBooks;
	
	public static boolean canSearingBeAppliedToBooks;
	public static boolean canSearingBeAppliedToItems;
	//public static boolean isSearingApplicableOnOtherArmourAnvil;
	public static boolean canSearingSpawnOnBooks;
	
	public static boolean canSharpshooterBeAppliedToBooks;
	public static boolean canSharpshooterBeAppliedToItems;
	public static boolean canSharpshooterSpawnOnBooks;
	
	public static boolean canSteadfastBeAppliedToBooks;
	public static boolean canSteadfastBeAppliedToItems;
	public static boolean canSteadfastSpawnOnBooks;
	
	public static boolean canTorrentBeAppliedToBooks;
	public static boolean canTorrentBeAppliedToItems;
	public static boolean canTorrentSpawnOnBooks;

	public static boolean canVampiricBeAppliedToBooks;
	public static boolean canVampiricBeAppliedToItems;
	public static boolean canVampiricSpawnOnBooks;
	
	public static boolean canWeightedBeAppliedToBooks;
	public static boolean canWeightedBeAppliedToItems;
	public static boolean canWeightedSpawnOnBooks;
	
	public static boolean canWitherAspectBeAppliedToBooks;
	public static boolean canWitherAspectBeAppliedToItems;
	public static boolean canWitherAspectSpawnOnBooks;
	
	
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
		
		canAnchoringCurseSpawnOnBooks = COMMON.canAnchoringCurseSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "anchoring_curse"), canAnchoringCurseSpawnOnBooks);
		
		canAquaticRejuvenationBeAppliedToBooks = COMMON.canAquaticRejuvenationBeAppliedToBooks.get();
		canAquaticRejuvenationBeAppliedToItems = COMMON.canAquaticRejuvenationBeAppliedToItems.get();
		canAquaticRejuvenationSpawnOnBooks = COMMON.canAquaticRejuvenationSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "aquatic_rejuvenation"), canAquaticRejuvenationSpawnOnBooks);
		
		canBloodlustBeAppliedToBooks = COMMON.canBloodlustBeAppliedToBooks.get();
		canBloodlustBeAppliedToItems = COMMON.canBloodlustBeAppliedToItems.get();
		canBloodlustSpawnOnBooks = COMMON.canBloodlustSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "bloodlust"), canBloodlustSpawnOnBooks);
		
		canButcheringBeAppliedToBooks = COMMON.canButcheringBeAppliedToBooks.get();
		canButcheringBeAppliedToItems = COMMON.canButcheringBeAppliedToItems.get();
		canButcheringSpawnOnBooks = COMMON.canButcheringSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "butchering"), canButcheringSpawnOnBooks);
		
		canBuoyancyBeAppliedToBooks = COMMON.canBuoyancyBeAppliedToBooks.get();
		canBuoyancySpawnOnBooks = COMMON.canBuoyancySpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "buoyancy_horse"), canBuoyancySpawnOnBooks);
		
		canLeapingBeAppliedToBooks = COMMON.canLeapingBeAppliedToBooks.get();
		canLeapingSpawnOnBooks = COMMON.canLeapingSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "leaping_horse"), canLeapingSpawnOnBooks);
		
		canObsidianSkullBeAppliedToBooks = COMMON.canObsidianSkullBeAppliedToBooks.get();
		canObsidianSkullBeAppliedToItems = COMMON.canObsidianSkullBeAppliedToItems.get();
		canObsidianSkullSpawnOnBooks = COMMON.canObsidianSkullSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "obsidian_skull"), canObsidianSkullSpawnOnBooks);
		
		canOutrushBeAppliedToBooks = COMMON.canOutrushBeAppliedToBooks.get();
		canOutrushBeAppliedToItems = COMMON.canOutrushBeAppliedToItems.get();
		canOutrushSpawnOnBooks = COMMON.canOutrushSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "outrush"), canOutrushSpawnOnBooks);
		
		canPillagingBeAppliedToBooks = COMMON.canPillagingBeAppliedToBooks.get();
		canPillagingBeAppliedToItems = COMMON.canPillagingBeAppliedToItems.get();
		canPillagingSpawnOnBooks = COMMON.canPillagingSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "pillaging"), canPillagingSpawnOnBooks);
		
		canPoisonAspectBeAppliedToBooks = COMMON.canPoisonAspectBeAppliedToBooks.get();
		canPoisonAspectBeAppliedToItems = COMMON.canPoisonAspectBeAppliedToItems.get();
		canPoisonAspectSpawnOnBooks = COMMON.canPoisonAspectSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "poison_aspect"), canPoisonAspectSpawnOnBooks);
		
		canQuicknessBeAppliedToBooks = COMMON.canQuicknessBeAppliedToBooks.get();
		canQuicknessSpawnOnBooks = COMMON.canQuicknessSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "quickness_horse"), canQuicknessSpawnOnBooks);
		
		canSearingBeAppliedToBooks = COMMON.canSearingBeAppliedToBooks.get();
		canSearingBeAppliedToItems = COMMON.canSearingBeAppliedToItems.get();
		canSearingSpawnOnBooks = COMMON.canSearingSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "searing"), canSearingSpawnOnBooks);
		
		canSharpshooterBeAppliedToBooks = COMMON.canSharpshooterBeAppliedToBooks.get();
		canSharpshooterBeAppliedToItems = COMMON.canSharpshooterBeAppliedToItems.get();
		canSharpshooterSpawnOnBooks = COMMON.canSharpshooterSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "sharpshooter"), canSharpshooterSpawnOnBooks);
		
		canSteadfastBeAppliedToBooks = COMMON.canSteadfastBeAppliedToBooks.get();
		canSteadfastBeAppliedToItems = COMMON.canSteadfastBeAppliedToItems.get();
		canSteadfastSpawnOnBooks = COMMON.canSteadfastSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "steadfast"), canSteadfastSpawnOnBooks);
		
		canTorrentBeAppliedToBooks = COMMON.canTorrentBeAppliedToBooks.get();
		canTorrentBeAppliedToItems = COMMON.canTorrentBeAppliedToItems.get();
		canTorrentSpawnOnBooks = COMMON.canTorrentSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "torrent"), canTorrentSpawnOnBooks);
		
		canVampiricBeAppliedToBooks = COMMON.canVampiricBeAppliedToBooks.get();
		canVampiricBeAppliedToItems = COMMON.canVampiricBeAppliedToItems.get();
		canVampiricSpawnOnBooks = COMMON.canVampiricSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "vampiric"), canVampiricSpawnOnBooks);
		
		canWeightedBeAppliedToBooks = COMMON.canWeightedBeAppliedToBooks.get();
		canWeightedBeAppliedToItems = COMMON.canWeightedBeAppliedToItems.get();
		canWeightedSpawnOnBooks = COMMON.canWeightedSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "weighted"), canWeightedSpawnOnBooks);
		
		canWitherAspectBeAppliedToBooks = COMMON.canWitherAspectBeAppliedToBooks.get();
		canWitherAspectBeAppliedToItems = COMMON.canWitherAspectBeAppliedToItems.get();
		canWitherAspectSpawnOnBooks = COMMON.canWitherAspectSpawnOnBooks.get();
		addEnchantmentIfValid(new ResourceLocation(FFE.MOD_ID, "wither_aspect"), canWitherAspectSpawnOnBooks);
		
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
	
	public static void addEnchantmentIfValid(ResourceLocation resourceLocation, boolean isValid) {
		
		if(isValid) {
			FFE.validEnchantmentsForChestLoot.add(resourceLocation);
		}
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
		
		public final ForgeConfigSpec.BooleanValue canAnchoringCurseSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canAquaticRejuvenationBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canAquaticRejuvenationBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canAquaticRejuvenationSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canBloodlustBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canBloodlustBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canBloodlustSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canBuoyancyBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canBuoyancySpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canButcheringBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canButcheringBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canButcheringSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canLeapingBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canLeapingSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canObsidianSkullBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canObsidianSkullBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canObsidianSkullSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canOutrushBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canOutrushBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canOutrushSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canPillagingBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canPillagingBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canPillagingSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canPoisonAspectBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canPoisonAspectBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canPoisonAspectSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canQuicknessBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canQuicknessSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canSearingBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canSearingBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canSearingSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canSharpshooterBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canSharpshooterBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canSharpshooterSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canSteadfastBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canSteadfastBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canSteadfastSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canTorrentBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canTorrentBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canTorrentSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canVampiricBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canVampiricBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canVampiricSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canWeightedBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canWeightedBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canWeightedSpawnOnBooks;
		
		public final ForgeConfigSpec.BooleanValue canWitherAspectBeAppliedToItems;
		public final ForgeConfigSpec.BooleanValue canWitherAspectBeAppliedToBooks;
		public final ForgeConfigSpec.BooleanValue canWitherAspectSpawnOnBooks;

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
			canAnchoringCurseSpawnOnBooks = builder
					.comment("Whether or not Curse of Anchoring can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canAnchoringCurseSpawnOnBooks", true);
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
			
			canAquaticRejuvenationSpawnOnBooks = builder
					.comment("Whether or not Aquatic Rejuvenation can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canAquaticRejuvenationSpawnOnBooks", true);
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
			
			canBloodlustSpawnOnBooks = builder
					.comment("Whether or not Bloodlust can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canBloodlustSpawnOnBooks", true);				
			builder.pop();
			
			builder.push("buoyancy");
			canBuoyancyBeAppliedToBooks = builder
					.comment("Whether or not Buoyancy can be applied to books.")
					.worldRestart()
					.define("canBuoyancyBeAppliedToBooks", true);
			
			canBuoyancySpawnOnBooks = builder
					.comment("Whether or not Buoyancy can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canBuoyancySpawnOnBooks", true);
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
			
			canButcheringSpawnOnBooks = builder
					.comment("Whether or not Butchering can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canButcheringSpawnOnBooks", true);
			builder.pop();
			
			builder.push("leaping");
			canLeapingBeAppliedToBooks = builder
					.comment("Whether or not Leaping can be applied to books.")
					.worldRestart()
					.define("canLeapingBeAppliedToBooks", true);
			
			canLeapingSpawnOnBooks = builder
					.comment("Whether or not Leaping can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canLeapingSpawnOnBooks", true);
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
			
			canObsidianSkullSpawnOnBooks = builder
					.comment("Whether or not Obsidian Skull can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canObsidianSkullSpawnOnBooks", true);
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
			
			canOutrushSpawnOnBooks = builder
					.comment("Whether or not Outrush can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canOutrushSpawnOnBooks", true);
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
			
			canPillagingSpawnOnBooks = builder
					.comment("Whether or not Pillaging can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canPillagingSpawnOnBooks", true);
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
			
			canPoisonAspectSpawnOnBooks = builder
					.comment("Whether or not Poison Aspect can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canPoisonAspectSpawnOnBooks", true);
			builder.pop();
			
			builder.push("quickness");
			canQuicknessBeAppliedToBooks = builder
					.comment("Whether or not Quickness can be applied to books.")
					.worldRestart()
					.define("canQuicknessBeAppliedToBooks", true);
			
			canQuicknessSpawnOnBooks = builder
					.comment("Whether or not Quickness can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canQuicknessSpawnOnBooks", true);
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
		
			canSearingSpawnOnBooks = builder
					.comment("Whether or not Searing can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canSearingSpawnOnBooks", true);
			builder.pop();
			
			builder.push("sharpshooter");
			canSharpshooterBeAppliedToItems = builder
					.comment("Whether or not Sharpshooter can be applied to possible items.")
					.worldRestart()
					.define("canSharpshooterBeAppliedToItems", true);
			
			canSharpshooterBeAppliedToBooks = builder
					.comment("Whether or not Sharpshooter can be applied to books.")
					.worldRestart()
					.define("canSharpshooterBeAppliedToBooks", true);			
			
			canSharpshooterSpawnOnBooks = builder
					.comment("Whether or not Sharpshooter can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canSharpshooterSpawnOnBooks", true);
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
			
			canSteadfastSpawnOnBooks = builder
					.comment("Whether or not Steadfast can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canSteadfastSpawnOnBooks", true);
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
			
			canTorrentSpawnOnBooks = builder
					.comment("Whether or not Torrent can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canTorrentSpawnOnBooks", true);
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
			
			canVampiricSpawnOnBooks = builder
					.comment("Whether or not Vampiric can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canVampiricSpawnOnBooks", true);
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
			
			canWeightedSpawnOnBooks = builder
					.comment("Whether or not Weighted can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canWeightedSpawnOnBooks", true);
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
			
			canWitherAspectSpawnOnBooks = builder
					.comment("Whether or not Wither Aspect can be found on enchanted books in naturally generated chests.")
					.worldRestart()
					.define("canWitherAspectSpawnOnBooks", true);
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