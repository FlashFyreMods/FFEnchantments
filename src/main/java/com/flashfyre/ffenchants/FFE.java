package com.flashfyre.ffenchants;

import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.flashfyre.ffenchants.capability.IMaelstromApplied;
import com.flashfyre.ffenchants.capability.IShooterEnchantments;
import com.flashfyre.ffenchants.capability.MaelstromApplied;
import com.flashfyre.ffenchants.capability.MaelstromAppliedStorage;
import com.flashfyre.ffenchants.capability.ShooterEnchantments;
import com.flashfyre.ffenchants.capability.ShooterEnchantmentsStorage;
import com.flashfyre.ffenchants.enchantments.AnchoringCurseEnchantment;
import com.flashfyre.ffenchants.enchantments.AquaticRejuvenationEnchantment;
import com.flashfyre.ffenchants.enchantments.BloodlustEnchantment;
import com.flashfyre.ffenchants.enchantments.BuoyancyHorseEnchantment;
import com.flashfyre.ffenchants.enchantments.ButcheringEnchantment;
import com.flashfyre.ffenchants.enchantments.LeapingHorseEnchantment;
import com.flashfyre.ffenchants.enchantments.MaelstromEnchantment;
import com.flashfyre.ffenchants.enchantments.ObsidianSkullEnchantment;
import com.flashfyre.ffenchants.enchantments.OutrushEnchantment;
import com.flashfyre.ffenchants.enchantments.PillagingEnchantment;
import com.flashfyre.ffenchants.enchantments.PoisonAspectEnchantment;
import com.flashfyre.ffenchants.enchantments.QuicknessHorseEnchantment;
import com.flashfyre.ffenchants.enchantments.SearingEnchantment;
import com.flashfyre.ffenchants.enchantments.SharpshooterEnchantment;
import com.flashfyre.ffenchants.enchantments.SteadfastEnchantment;
import com.flashfyre.ffenchants.enchantments.TorrentEnchantment;
import com.flashfyre.ffenchants.enchantments.VampiricEnchantment;
import com.flashfyre.ffenchants.enchantments.WeightedEnchantment;
import com.flashfyre.ffenchants.enchantments.WitherAspectEnchantment;
import com.flashfyre.ffenchants.loot_modifiers.EnchantSaddlesLootModifier;
import com.flashfyre.ffenchants.misc.FFEConfig;
import com.flashfyre.ffenchants.misc.FFELootTables;
import com.flashfyre.ffenchants.packets.BuoyancyPacket;
import com.flashfyre.ffenchants.packets.LeapingToClientPacket;
import com.flashfyre.ffenchants.packets.LeapingToServerPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SaddleItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
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
@Mod(FFE.MOD_ID)
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
	
	private static class FFEEnchantmentTypes {
		private static final EnchantmentType AXE = EnchantmentType.create("AXE",  item -> item instanceof AxeItem);
		private static final EnchantmentType SADDLE = EnchantmentType.create("SADDLE", item -> item instanceof SaddleItem);
	}	
	
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
	@ObjectHolder("ffenchants:aquatic_rejuvenation")
	public static Enchantment AQUATIC_REJUVENATION = null;
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
	@ObjectHolder("ffenchants:maelstrom")
	public static Enchantment MAELSTROM = null;
	
	@SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) 
	{
		LOGGER.info("Registering FFE Enchantments.");
		final IForgeRegistry<Enchantment> registry = event.getRegistry();
		
		EquipmentSlotType[] emptySlots = {};
		
		ANCHORING_CURSE = new AnchoringCurseEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.ARMOR_FEET, EquipmentSlotType.FEET).setRegistryName(FFE.MOD_ID, "anchoring_curse");
		AQUATIC_REJUVENATION = new AquaticRejuvenationEnchantment(Enchantment.Rarity.COMMON, EnchantmentType.TRIDENT, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "aquatic_rejuvenation");
		BLOODLUST = new BloodlustEnchantment(Enchantment.Rarity.RARE, FFEEnchantmentTypes.AXE, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "bloodlust"); //Only on Axes
		BUOYANCY_HORSE = new BuoyancyHorseEnchantment(Enchantment.Rarity.RARE, FFEEnchantmentTypes.SADDLE, emptySlots).setRegistryName(FFE.MOD_ID, "buoyancy_horse"); //Only on horse armour
		BUTCHERING = new ButcheringEnchantment(Enchantment.Rarity.RARE, FFEEnchantmentTypes.AXE, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "butchering"); //Only on Axes
		VAMPIRIC = new VampiricEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "vampiric");
		WEIGHTED = new WeightedEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "weighted");
		POISON_ASPECT = new PoisonAspectEnchantment(Enchantment.Rarity.RARE, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "poison_aspect");
		WITHER_ASPECT = new WitherAspectEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "wither_aspect");
		PILLAGING = new PillagingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.CROSSBOW, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "pillaging");
		SEARING = new SearingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.ARMOR_CHEST, ARMOUR_SLOTS).setRegistryName(FFE.MOD_ID, "searing");
		STEADFAST = new SteadfastEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.ARMOR_CHEST, EquipmentSlotType.CHEST).setRegistryName(FFE.MOD_ID, "steadfast");
		OUTRUSH = new OutrushEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.TRIDENT, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "outrush");
		TORRENT = new TorrentEnchantment(Enchantment.Rarity.RARE, EnchantmentType.TRIDENT, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "torrent");
		SHARPSHOOTER = new SharpshooterEnchantment(Enchantment.Rarity.RARE, EnchantmentType.CROSSBOW, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "sharpshooter");	
		OBSIDIAN_SKULL = new ObsidianSkullEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD).setRegistryName(FFE.MOD_ID, "obsidian_skull");
		LEAPING_HORSE = new LeapingHorseEnchantment(Enchantment.Rarity.UNCOMMON, FFEEnchantmentTypes.SADDLE, emptySlots).setRegistryName(FFE.MOD_ID, "leaping_horse"); //Only on horse armour
		QUICKNESS_HORSE = new QuicknessHorseEnchantment(Enchantment.Rarity.UNCOMMON, FFEEnchantmentTypes.SADDLE, emptySlots).setRegistryName(FFE.MOD_ID, "quickness_horse"); //Only on horse armour
		MAELSTROM = new MaelstromEnchantment(Enchantment.Rarity.RARE, EnchantmentType.TRIDENT, EquipmentSlotType.MAINHAND).setRegistryName(FFE.MOD_ID, "maelstrom");
		registerEnchantment(registry, BLOODLUST);
		registerEnchantment(registry, VAMPIRIC);
		registerEnchantment(registry, PILLAGING);
		registerEnchantment(registry, WEIGHTED);
		registerEnchantment(registry, POISON_ASPECT);
		registerEnchantment(registry, WITHER_ASPECT);
		registerEnchantment(registry, SEARING);
		registerEnchantment(registry, STEADFAST);
		registerEnchantment(registry, OUTRUSH);
		registerEnchantment(registry, TORRENT);
		registerEnchantment(registry, SHARPSHOOTER);
		registerEnchantment(registry, AQUATIC_REJUVENATION);
		registerEnchantment(registry, OBSIDIAN_SKULL);
		registerEnchantment(registry, BUTCHERING);
		registerEnchantment(registry, LEAPING_HORSE);
		registerEnchantment(registry, BUOYANCY_HORSE);
		registerEnchantment(registry, QUICKNESS_HORSE);
		registerEnchantment(registry, ANCHORING_CURSE);
		registerEnchantment(registry, MAELSTROM);
	}
	
	public static void registerEnchantment(IForgeRegistry<Enchantment> registry, Enchantment enchantment)
	{
		registry.register(enchantment);
		LOGGER.info("Registered enchantment " + enchantment.getRegistryName() + ".");
	}
	
	@SubscribeEvent
	public static void registerLootModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) 
	{
		
		event.getRegistry().register(new EnchantSaddlesLootModifier.Serializer().setRegistryName(new ResourceLocation(FFE.MOD_ID, "enchant_saddles")));	
	}
	
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		
		int packetIndex = 0;
		FFE.PacketHandler.INSTANCE.registerMessage(packetIndex++, LeapingToServerPacket.class, (packet, buffer) -> {}, buffer -> new LeapingToServerPacket(), LeapingToServerPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
		FFE.PacketHandler.INSTANCE.registerMessage(packetIndex++, LeapingToClientPacket.class, LeapingToClientPacket::encode, LeapingToClientPacket::decode, LeapingToClientPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
		FFE.PacketHandler.INSTANCE.registerMessage(packetIndex++, BuoyancyPacket.class, BuoyancyPacket::encode, BuoyancyPacket::decode, BuoyancyPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
		
		CapabilityManager.INSTANCE.register(IShooterEnchantments.class, new ShooterEnchantmentsStorage(), ShooterEnchantments::new);
		CapabilityManager.INSTANCE.register(IMaelstromApplied.class, new MaelstromAppliedStorage(), MaelstromApplied::new);
		
		if(!FFEConfig.enableAllLootAdditions) return;
		if(FFEConfig.enableEndCityLootAdditions) FFELootTables.CHESTS.add("end_city_treasure");
		if(FFEConfig.enableJungleTempleLootAdditions) FFELootTables.CHESTS.add("jungle_temple");
		if(FFEConfig.enableNetherFortressLootAdditions) FFELootTables.CHESTS.add("nether_bridge");
		if(FFEConfig.enablePillagerOutpostLootAdditions) FFELootTables.CHESTS.add("pillager_outpost");
		if(FFEConfig.enableSmallOceanRuinLootAdditions) FFELootTables.CHESTS.add("underwater_ruin_small");
		if(FFEConfig.enableLargeOceanRuinLootAdditions) FFELootTables.CHESTS.add("underwater_ruin_big");
		if(FFEConfig.enableWoodlandMansionLootAdditions) FFELootTables.CHESTS.add("woodland_mansion");
		if(FFEConfig.enableIglooLootAdditions) FFELootTables.CHESTS.add("igloo_chest");
		FFE.LOGGER.info("Added enabled loot additions.");
	}
	
	public static int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
		if(stack.isEmpty()) return 0;
		int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
		if(level < 0) level = 0;
		return level;
	}
	
	public static DamageSource causeMaelstromDamage(Entity source, @Nullable Entity indirectEntityIn) {
		return (new IndirectEntityDamageSource(FFE.MOD_ID+":maelstrom", source, indirectEntityIn));
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
		
		public static void handleBuoyancyPacket(BuoyancyPacket packet, Supplier<NetworkEvent.Context> ctx) {
			@SuppressWarnings("resource")
			World world = Minecraft.getInstance().world;
	    	AbstractHorseEntity horse = (AbstractHorseEntity) world.getEntityByID(packet.entityId);
	    	horse.addVelocity(0, 0.06D, 0);
		}
	}
}
