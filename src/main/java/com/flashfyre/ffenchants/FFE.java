package com.flashfyre.ffenchants;

import java.util.Optional;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.flashfyre.ffenchants.capability.IShooterEnchantments;
import com.flashfyre.ffenchants.capability.ISteadfastHandler;
import com.flashfyre.ffenchants.capability.ShooterEnchantments;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsStorage;
import com.flashfyre.ffenchants.capability.SteadfastHandler;
import com.flashfyre.ffenchants.capability.SteadfastHandlerStorage;
import com.flashfyre.ffenchants.enchantments.AnchoringCurseEnchantment;
import com.flashfyre.ffenchants.enchantments.BloodlustEnchantment;
import com.flashfyre.ffenchants.enchantments.BuoyancyHorseEnchantment;
import com.flashfyre.ffenchants.enchantments.ButcheringEnchantment;
import com.flashfyre.ffenchants.enchantments.LeapingHorseEnchantment;
import com.flashfyre.ffenchants.enchantments.ObsidianSkullEnchantment;
import com.flashfyre.ffenchants.enchantments.OutrushEnchantment;
import com.flashfyre.ffenchants.enchantments.PillagingEnchantment;
import com.flashfyre.ffenchants.enchantments.PoisonAspectEnchantment;
import com.flashfyre.ffenchants.enchantments.QuicknessHorseEnchantment;
import com.flashfyre.ffenchants.enchantments.RejuvenationEnchantment;
import com.flashfyre.ffenchants.enchantments.SearingEnchantment;
import com.flashfyre.ffenchants.enchantments.SharpshooterEnchantment;
import com.flashfyre.ffenchants.enchantments.SteadfastEnchantment;
import com.flashfyre.ffenchants.enchantments.TorrentEnchantment;
import com.flashfyre.ffenchants.enchantments.VampiricEnchantment;
import com.flashfyre.ffenchants.enchantments.WeightedEnchantment;
import com.flashfyre.ffenchants.enchantments.WitherAspectEnchantment;
import com.flashfyre.ffenchants.misc.BuoyancyPacket;
import com.flashfyre.ffenchants.misc.FFEConfig;
import com.flashfyre.ffenchants.misc.FFELootTables;
import com.flashfyre.ffenchants.misc.LeapingToClientPacket;
import com.flashfyre.ffenchants.misc.LeapingToServerPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
@Mod("ffenchants")
public class FFE
{
	public static FFE instance;
	public static final String MOD_ID = "ffenchants";    
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	public FFE()
	{
		instance = this;
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FFEConfig.COMMON_SPEC, "ffenchants-common.toml");
	}
		
	private static final EquipmentSlotType[] ARMOUR_SLOTS = new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
	
	@ObjectHolder("ffenchants:bloodlust")
	public static Enchantment BLOODLUST = null;
	@ObjectHolder("ffenchants:vampiric")
	public static Enchantment VAMPIRIC = null;
	@ObjectHolder("ffenchants:weighted")
	public static Enchantment WEIGHTED = null;
	@ObjectHolder("ffenchants:poison_aspect")
	public static Enchantment POISON_ASPECT = null;
	@ObjectHolder("ffenchants:wither_aspect")
	public static Enchantment WITHER_ASPECT = null;
	@ObjectHolder("ffenchants:pillaging")
	public static Enchantment PILLAGING = null;
	@ObjectHolder("ffenchants:searing")
	public static Enchantment SEARING = null;
	@ObjectHolder("ffenchants:steadfast")
	public static Enchantment STEADFAST = null;
	@ObjectHolder("ffenchants:outrush")
	public static Enchantment OUTRUSH = null;
	@ObjectHolder("ffenchants:torrent")
	public static Enchantment TORRENT = null;
	@ObjectHolder("ffenchants:sharpshooter")
	public static Enchantment SHARPSHOOTER = null;
	@ObjectHolder("ffenchants:rejuvenation")
	public static Enchantment REJUVENATION = null;
	@ObjectHolder("ffenchants:obsidian_skull")
	public static Enchantment OBSIDIAN_SKULL = null;
	@ObjectHolder("ffenchants:butchering")
	public static Enchantment BUTCHERING = null;
	@ObjectHolder("ffenchants:leaping_horse")
	public static Enchantment LEAPING_HORSE = null;
	@ObjectHolder("ffenchants:buoyancy_horse")
	public static Enchantment BUOYANCY_HORSE = null;
	@ObjectHolder("ffenchants:quickness_horse")
	public static Enchantment QUICKNESS_HORSE= null;
	@ObjectHolder("ffenchants:anchoring_curse")
	public static Enchantment ANCHORING_CURSE = null;
	
	@SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) 
	{
		LOGGER.info("Registering FFE Enchantments.");
		final IForgeRegistry<Enchantment> registry = event.getRegistry();
		
		EquipmentSlotType[] emptySlots = {};
		
		BLOODLUST = new BloodlustEnchantment(Enchantment.Rarity.RARE, EnchantmentType.ALL, EquipmentSlotType.MAINHAND); //Only on Axes
		BUTCHERING = new ButcheringEnchantment(Enchantment.Rarity.RARE, EnchantmentType.ALL, EquipmentSlotType.MAINHAND); //Only on Axes
		VAMPIRIC = new VampiricEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND);
		WEIGHTED = new WeightedEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND);
		POISON_ASPECT = new PoisonAspectEnchantment(Enchantment.Rarity.RARE, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND);
		WITHER_ASPECT = new WitherAspectEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND);
		PILLAGING = new PillagingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.CROSSBOW, EquipmentSlotType.MAINHAND);
		SEARING = new SearingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.ARMOR_CHEST, ARMOUR_SLOTS);
		STEADFAST = new SteadfastEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.ARMOR_CHEST, EquipmentSlotType.CHEST);
		OUTRUSH = new OutrushEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.TRIDENT, EquipmentSlotType.MAINHAND);
		TORRENT = new TorrentEnchantment(Enchantment.Rarity.RARE, EnchantmentType.TRIDENT, EquipmentSlotType.MAINHAND);
		SHARPSHOOTER = new SharpshooterEnchantment(Enchantment.Rarity.RARE, EnchantmentType.CROSSBOW, EquipmentSlotType.MAINHAND);
		REJUVENATION = new RejuvenationEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.TRIDENT, EquipmentSlotType.MAINHAND);	
		OBSIDIAN_SKULL = new ObsidianSkullEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD);
		LEAPING_HORSE = new LeapingHorseEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.ALL, emptySlots); //Only on horse armour
		BUOYANCY_HORSE = new BuoyancyHorseEnchantment(Enchantment.Rarity.RARE, EnchantmentType.ALL, emptySlots); //Only on horse armour
		QUICKNESS_HORSE = new QuicknessHorseEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.ALL, emptySlots); //Only on horse armour
		ANCHORING_CURSE = new AnchoringCurseEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.ARMOR_FEET, EquipmentSlotType.FEET);
		registerEnchantment(registry, BLOODLUST, FFEConfig.COMMON.enableBloodlust.get());
		registerEnchantment(registry, VAMPIRIC, FFEConfig.COMMON.enableVampiric.get());
		registerEnchantment(registry, PILLAGING, FFEConfig.COMMON.enablePillaging.get());
		registerEnchantment(registry, WEIGHTED, FFEConfig.COMMON.enableWeighted.get());
		registerEnchantment(registry, POISON_ASPECT, FFEConfig.COMMON.enablePoisonAspect.get());
		registerEnchantment(registry, WITHER_ASPECT, FFEConfig.COMMON.enableWitherAspect.get());
		registerEnchantment(registry, SEARING, FFEConfig.COMMON.enableSearing.get());
		registerEnchantment(registry, STEADFAST, FFEConfig.COMMON.enableSteadfast.get());
		registerEnchantment(registry, OUTRUSH, FFEConfig.COMMON.enableOutrush.get());
		registerEnchantment(registry, TORRENT, FFEConfig.COMMON.enableTorrent.get());
		registerEnchantment(registry, SHARPSHOOTER, FFEConfig.COMMON.enableSharpshooter.get());
		registerEnchantment(registry, REJUVENATION, FFEConfig.COMMON.enableRejuvenation.get());
		registerEnchantment(registry, OBSIDIAN_SKULL, FFEConfig.COMMON.enableObsidianSkull.get());
		registerEnchantment(registry, BUTCHERING, FFEConfig.COMMON.enableButchering.get());
		registerEnchantment(registry, LEAPING_HORSE, FFEConfig.COMMON.enableLeaping.get());
		registerEnchantment(registry, BUOYANCY_HORSE, FFEConfig.COMMON.enableBuoyancy.get());
		registerEnchantment(registry, QUICKNESS_HORSE, FFEConfig.COMMON.enableQuickness.get());
		registerEnchantment(registry, ANCHORING_CURSE, FFEConfig.COMMON.enableAnchoringCurse.get());
	}
	
	public static void registerEnchantment(IForgeRegistry<Enchantment> registry, Enchantment enchantment, boolean enabledInConfig) 
	{
		
		if(enabledInConfig) 
		{
			registry.register(enchantment);
			LOGGER.info("Registered enchantment " + enchantment.getRegistryName() + ".");
		}
	}
	
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		
		int packetIndex = 0;
		FFE.PacketHandler.INSTANCE.registerMessage(packetIndex++, LeapingToServerPacket.class, (packet, buffer) -> {}, buffer -> new LeapingToServerPacket(), LeapingToServerPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
		FFE.PacketHandler.INSTANCE.registerMessage(packetIndex++, LeapingToClientPacket.class, LeapingToClientPacket::encode, LeapingToClientPacket::decode, LeapingToClientPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
		FFE.PacketHandler.INSTANCE.registerMessage(packetIndex++, BuoyancyPacket.class, BuoyancyPacket::encode, BuoyancyPacket::decode, BuoyancyPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
		
		CapabilityManager.INSTANCE.register(IShooterEnchantments.class, new ShooterEnchantmentsStorage(), ShooterEnchantments::new);
		CapabilityManager.INSTANCE.register(ISteadfastHandler.class, new SteadfastHandlerStorage(), SteadfastHandler::new);
		
		if(!FFEConfig.COMMON.enableAllLootModifications.get()) return;
		if(FFEConfig.COMMON.enableEndCityLootModifications.get()) FFELootTables.CHESTS.add("end_city_treasure");
		if(FFEConfig.COMMON.enableJungleTempleLootModifications.get()) FFELootTables.CHESTS.add("jungle_temple");
		if(FFEConfig.COMMON.enableNetherFortressLootModifications.get()) FFELootTables.CHESTS.add("nether_bridge");
		if(FFEConfig.COMMON.enablePillagerOutpostLootModifications.get()) FFELootTables.CHESTS.add("pillager_outpost");
		if(FFEConfig.COMMON.enableSmallOceanRuinLootModifications.get()) FFELootTables.CHESTS.add("underwater_ruin_small");
		if(FFEConfig.COMMON.enableLargeOceanRuinLootModifications.get()) FFELootTables.CHESTS.add("underwater_ruin_big");
		if(FFEConfig.COMMON.enableWoodlandMansionLootModifications.get()) FFELootTables.CHESTS.add("woodland_mansion");
		if(FFEConfig.COMMON.enableIglooLootModifications.get()) FFELootTables.CHESTS.add("igloo_chest");
	}
	
	public static int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
		if(stack.isEmpty()) return 0;
		int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
		if(level < 0) level = 0;
		return level;
	}
	
	public static class PacketHandler {
		private static final String PROTOCOL_VERSION = "1";
		public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			    new ResourceLocation("ffenchants", "main"),
			    () -> PROTOCOL_VERSION,
			    PROTOCOL_VERSION::equals,
			    PROTOCOL_VERSION::equals
			);
	}
	
	public static class ClientPacketHandler {
		
		public static void handleLeapingPacket(LeapingToClientPacket packet, Supplier<NetworkEvent.Context> ctx) {
			Minecraft instance = Minecraft.getInstance();
	    	World world = instance.world;
	    	if(world == null) return;
	    	AbstractHorseEntity horse = (AbstractHorseEntity) world.getEntityByID(packet.entityId);
	    	double velocity = LeapingHorseEnchantment.getYVelocity(packet.enchantmentLevel);
	    	horse.addVelocity(0, velocity, 0);
		}		
	}
}
