package com.flashfyre.ffenchants;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.flashfyre.ffenchants.capability.MaelstromTridentReturning;
import com.flashfyre.ffenchants.capability.ShooterEnchantments;
import com.flashfyre.ffenchants.enchantments.AnchoringCurseEnchantment;
import com.flashfyre.ffenchants.enchantments.AquaticRejuvenationEnchantment;
import com.flashfyre.ffenchants.enchantments.BloodlustEnchantment;
import com.flashfyre.ffenchants.enchantments.BuoyancyHorseEnchantment;
import com.flashfyre.ffenchants.enchantments.ButcheringEnchantment;
import com.flashfyre.ffenchants.enchantments.InfernoEnchantment;
import com.flashfyre.ffenchants.enchantments.LeapingHorseEnchantment;
import com.flashfyre.ffenchants.enchantments.MaelstromEnchantment;
import com.flashfyre.ffenchants.enchantments.ObsidianSkullEnchantment;
import com.flashfyre.ffenchants.enchantments.OutrushEnchantment;
import com.flashfyre.ffenchants.enchantments.PillagingEnchantment;
import com.flashfyre.ffenchants.enchantments.PointedEnchantment;
import com.flashfyre.ffenchants.enchantments.PoisonAspectEnchantment;
import com.flashfyre.ffenchants.enchantments.QuicknessHorseEnchantment;
import com.flashfyre.ffenchants.enchantments.SearingEnchantment;
import com.flashfyre.ffenchants.enchantments.SteadfastEnchantment;
import com.flashfyre.ffenchants.enchantments.TorrentEnchantment;
import com.flashfyre.ffenchants.enchantments.VampiricEnchantment;
import com.flashfyre.ffenchants.enchantments.WeightedBladeEnchantment;
import com.flashfyre.ffenchants.enchantments.WitherAspectEnchantment;
import com.flashfyre.ffenchants.loot_modifiers.EnchantSaddlesLootModifier;
import com.flashfyre.ffenchants.loot_modifiers.InjectOrReplaceLootModifier;
import com.flashfyre.ffenchants.packets.BuoyancyPacket;
import com.flashfyre.ffenchants.packets.LeapingToClientPacket;
import com.flashfyre.ffenchants.packets.LeapingToServerPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SaddleItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
@Mod("ffenchants")
public class FFE
{
	public static FFE instance;
	public static final String MOD_ID = "ffenchants";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	public FFE() {
		instance = this;
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FFEConfig.COMMON_SPEC, "ffenchants-common.toml");
	}
	
	private static final EquipmentSlot[] ARMOUR_SLOTS = new EquipmentSlot[] {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
	
	private static class FFEEnchantmentCategorys {
		private static final EnchantmentCategory AXE = EnchantmentCategory.create("AXE",  item -> item instanceof AxeItem);
		private static final EnchantmentCategory SADDLE = EnchantmentCategory.create("SADDLE", item -> item instanceof SaddleItem);
	}	
	
	@ObjectHolder("ffenchants:bloodlust")
	public static Enchantment BLOODLUST = null;
	@ObjectHolder("ffenchants:vampiric")
	public static Enchantment VAMPIRIC = null;
	@ObjectHolder("ffenchants:weighted")
	public static Enchantment WEIGHTED_BLADE = null;
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
	@ObjectHolder("ffenchants:inferno")
	public static Enchantment INFERNO = null;
	@ObjectHolder("ffenchants:pointed")
	public static Enchantment POINTED = null;
	
	@SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) 
	{
		LOGGER.info("Registering FFE Enchantments.");
		final IForgeRegistry<Enchantment> registry = event.getRegistry();
		
		EquipmentSlot[] emptySlots = {};
		
		ANCHORING_CURSE = new AnchoringCurseEnchantment(
				Enchantment.Rarity.VERY_RARE, 
				EnchantmentCategory.ARMOR_FEET, 
				EquipmentSlot.FEET)
				.setRegistryName(FFE.MOD_ID, "anchoring_curse");
		AQUATIC_REJUVENATION = new AquaticRejuvenationEnchantment(
				Enchantment.Rarity.COMMON, 
				EnchantmentCategory.TRIDENT, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "aquatic_rejuvenation");
		BLOODLUST = new BloodlustEnchantment(
				Enchantment.Rarity.RARE, 
				FFEEnchantmentCategorys.AXE, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "bloodlust"); //Only on Axes
		BUOYANCY_HORSE = new BuoyancyHorseEnchantment(
				Enchantment.Rarity.RARE, 
				FFEEnchantmentCategorys.SADDLE, 
				emptySlots)
				.setRegistryName(FFE.MOD_ID, "buoyancy_horse"); //Only on horse armour
		BUTCHERING = new ButcheringEnchantment(
				Enchantment.Rarity.RARE, 
				FFEEnchantmentCategorys.AXE, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "butchering"); //Only on Axes
		VAMPIRIC = new VampiricEnchantment(
				Enchantment.Rarity.VERY_RARE, 
				EnchantmentCategory.WEAPON, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "vampiric");
		WEIGHTED_BLADE = new WeightedBladeEnchantment(
				Enchantment.Rarity.UNCOMMON, 
				EnchantmentCategory.WEAPON, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "weighted");
		POINTED = new PointedEnchantment( 
				Enchantment.Rarity.COMMON,
				EnchantmentCategory.CROSSBOW,
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "pointed");
		POISON_ASPECT = new PoisonAspectEnchantment(
				Enchantment.Rarity.RARE, 
				EnchantmentCategory.WEAPON, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "poison_aspect");
		WITHER_ASPECT = new WitherAspectEnchantment(
				Enchantment.Rarity.VERY_RARE, 
				EnchantmentCategory.WEAPON, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "wither_aspect");
		PILLAGING = new PillagingEnchantment(
				Enchantment.Rarity.UNCOMMON, 
				EnchantmentCategory.CROSSBOW, 
				EquipmentSlot.MAINHAND
				).setRegistryName(FFE.MOD_ID, "pillaging");
		SEARING = new SearingEnchantment(
				Enchantment.Rarity.UNCOMMON, 
				EnchantmentCategory.ARMOR_CHEST, 
				ARMOUR_SLOTS)
				.setRegistryName(FFE.MOD_ID, "searing");
		STEADFAST = new SteadfastEnchantment(
				Enchantment.Rarity.UNCOMMON, 
				EnchantmentCategory.ARMOR_CHEST, 
				EquipmentSlot.CHEST)
				.setRegistryName(FFE.MOD_ID, "steadfast");
		OUTRUSH = new OutrushEnchantment(
				Enchantment.Rarity.UNCOMMON, 
				EnchantmentCategory.TRIDENT, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "outrush");
		TORRENT = new TorrentEnchantment(
				Enchantment.Rarity.RARE, 
				EnchantmentCategory.TRIDENT, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "torrent");
		OBSIDIAN_SKULL = new ObsidianSkullEnchantment(
				Enchantment.Rarity.VERY_RARE, 
				EnchantmentCategory.ARMOR_HEAD, 
				EquipmentSlot.HEAD)
				.setRegistryName(FFE.MOD_ID, "obsidian_skull");
		LEAPING_HORSE = new LeapingHorseEnchantment(
				Enchantment.Rarity.UNCOMMON, 
				FFEEnchantmentCategorys.SADDLE, 
				emptySlots)
				.setRegistryName(FFE.MOD_ID, "leaping_horse"); //Only on horse armour
		QUICKNESS_HORSE = new QuicknessHorseEnchantment(
				Enchantment.Rarity.UNCOMMON, 
				FFEEnchantmentCategorys.SADDLE, 
				emptySlots)
				.setRegistryName(FFE.MOD_ID, "quickness_horse"); //Only on horse armour
		MAELSTROM = new MaelstromEnchantment(
				Enchantment.Rarity.RARE, 
				EnchantmentCategory.TRIDENT, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "maelstrom");
		INFERNO = new InfernoEnchantment(
				Enchantment.Rarity.VERY_RARE, 
				EnchantmentCategory.CROSSBOW, 
				EquipmentSlot.MAINHAND)
				.setRegistryName(FFE.MOD_ID, "inferno");
		registerEnchantment(registry, BLOODLUST);
		registerEnchantment(registry, VAMPIRIC);
		registerEnchantment(registry, PILLAGING);
		registerEnchantment(registry, WEIGHTED_BLADE);
		registerEnchantment(registry, POISON_ASPECT);
		registerEnchantment(registry, WITHER_ASPECT);
		registerEnchantment(registry, SEARING);
		registerEnchantment(registry, STEADFAST);
		registerEnchantment(registry, OUTRUSH);
		registerEnchantment(registry, TORRENT);
		registerEnchantment(registry, AQUATIC_REJUVENATION);
		registerEnchantment(registry, OBSIDIAN_SKULL);
		registerEnchantment(registry, BUTCHERING);
		registerEnchantment(registry, LEAPING_HORSE);
		registerEnchantment(registry, BUOYANCY_HORSE);
		registerEnchantment(registry, QUICKNESS_HORSE);
		registerEnchantment(registry, ANCHORING_CURSE);
		registerEnchantment(registry, MAELSTROM);
		registerEnchantment(registry, INFERNO);
		registerEnchantment(registry, POINTED);
	}
	
	public static void registerEnchantment(IForgeRegistry<Enchantment> registry, Enchantment enchantment)
	{
		registry.register(enchantment);
		LOGGER.info("Registered enchantment " + enchantment.getRegistryName() + ".");
	}
	
	@SubscribeEvent
	public static void registerLootModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {		
		event.getRegistry().register(new EnchantSaddlesLootModifier.Serializer().setRegistryName(new ResourceLocation(FFE.MOD_ID, "enchant_saddles")));
		event.getRegistry().register(new InjectOrReplaceLootModifier.Serializer().setRegistryName(new ResourceLocation(FFE.MOD_ID, "loot_modification")));	
	}
	
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		
		int packetIndex = 0;
		FFE.PacketHandler.INSTANCE.registerMessage(packetIndex++, LeapingToServerPacket.class, (packet, buffer) -> {}, buffer -> new LeapingToServerPacket(), LeapingToServerPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
		FFE.PacketHandler.INSTANCE.registerMessage(packetIndex++, LeapingToClientPacket.class, LeapingToClientPacket::encode, LeapingToClientPacket::decode, LeapingToClientPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
		FFE.PacketHandler.INSTANCE.registerMessage(packetIndex++, BuoyancyPacket.class, BuoyancyPacket::encode, BuoyancyPacket::decode, BuoyancyPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
		
		LOGGER.info("Registering FFE capabilities.");
		//CapabilityManager.INSTANCE.register(IShooterEnchantments.class, new ShooterEnchantmentsStorage(), ShooterEnchantments::new);
		//CapabilityManager.INSTANCE.register(IMaelstromApplied.class, new MaelstromTridentReturningCapability(), MaelstromTridentReturningCapability::new);
	}
	
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(MaelstromTridentReturning.class);
		event.register(ShooterEnchantments.class);
	}
	
	public static int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
		if(stack.isEmpty()) return 0;
		int level = EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack);
		if(level < 0) level = 0;
		return level;
	}
	
	public static DamageSource causeMaelstromDamage(Entity source, @Nullable Entity indirectEntityIn) {
		return new IndirectEntityDamageSource(FFE.MOD_ID+":maelstrom", source, indirectEntityIn).setMagic();
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
	    	Level level = instance.level;
	    	if(level == null) return;
	    	AbstractHorse horse = (AbstractHorse) level.getEntity(packet.entityId);
	    	double velocity = LeapingHorseEnchantment.getYVelocity(packet.enchantmentLevel);
	    	horse.push(0, velocity, 0);
		}
		
		public static void handleBuoyancyPacket(BuoyancyPacket packet, Supplier<NetworkEvent.Context> ctx) {
			Minecraft instance = Minecraft.getInstance();
			Level level = instance.level;
			AbstractHorse horse = (AbstractHorse) level.getEntity(packet.entityId);
	    	horse.push(0, 0.06D, 0);
		}
	}
	
	public static List<LivingEntity> getEntitiesInAABB(Level level, double size, Vec3 centrePos) {
		AABB aabb = new AABB(centrePos.add(size, size, size), centrePos.add(-size, -size, -size));
		return level.getEntitiesOfClass(LivingEntity.class, aabb);
	}
}
