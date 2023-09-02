package com.flashfyre.ffenchantments;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.flashfyre.ffenchantments.capability.MaelstromTridentReturning;
import com.flashfyre.ffenchantments.capability.ShooterEnchantments;
import com.flashfyre.ffenchantments.enchantments.AnchoringCurseEnchantment;
import com.flashfyre.ffenchantments.enchantments.AquaticRejuvenationEnchantment;
import com.flashfyre.ffenchantments.enchantments.BloodlustEnchantment;
import com.flashfyre.ffenchantments.enchantments.BuoyancyHorseEnchantment;
import com.flashfyre.ffenchantments.enchantments.ButcheringEnchantment;
import com.flashfyre.ffenchantments.enchantments.EndCurseEnchantment;
import com.flashfyre.ffenchantments.enchantments.EnderShroudEnchantment;
import com.flashfyre.ffenchantments.enchantments.InfernoEnchantment;
import com.flashfyre.ffenchantments.enchantments.LeapingHorseEnchantment;
import com.flashfyre.ffenchantments.enchantments.MaelstromEnchantment;
import com.flashfyre.ffenchantments.enchantments.ObsidianSkullEnchantment;
import com.flashfyre.ffenchantments.enchantments.OutrushEnchantment;
import com.flashfyre.ffenchantments.enchantments.PillagingEnchantment;
import com.flashfyre.ffenchantments.enchantments.PointedEnchantment;
import com.flashfyre.ffenchantments.enchantments.PoisonAspectEnchantment;
import com.flashfyre.ffenchantments.enchantments.QuicknessHorseEnchantment;
import com.flashfyre.ffenchantments.enchantments.SearingTouchEnchantment;
import com.flashfyre.ffenchantments.enchantments.SteadfastEnchantment;
import com.flashfyre.ffenchantments.enchantments.TorrentEnchantment;
import com.flashfyre.ffenchantments.enchantments.VampiricEnchantment;
import com.flashfyre.ffenchantments.enchantments.WeightedBladeEnchantment;
import com.flashfyre.ffenchantments.enchantments.WitherAspectEnchantment;
import com.flashfyre.ffenchantments.loot_modifiers.FFELootModifiers;
import com.flashfyre.ffenchantments.packets.BuoyancyPacket;
import com.flashfyre.ffenchantments.packets.LeapingToClientPacket;
import com.flashfyre.ffenchantments.packets.LeapingToServerPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
@Mod("ffenchantments")
public class FFECore {
	public static final String MOD_ID = "ffenchantments";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	public FFECore() {
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		FFECore.Enchantments.ENCHANTMENTS.register(modBus);
		FFELootModifiers.GLM_CODECS.register(modBus);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FFEConfig.COMMON_SPEC, "ffenchantments-common.toml");
		forgeBus.addListener(EnderShroudEnchantment::onEndermanAnger);
		forgeBus.addListener(WeightedBladeEnchantment::onCrit);		
	}
	
	
	
	
	public static class Enchantments {		
		public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, FFECore.MOD_ID);
		
		// Sword or Axe
		public static final RegistryObject<Enchantment> BLOODLUST = register("bloodlust", new BloodlustEnchantment(Rarity.RARE));
		public static final RegistryObject<Enchantment> BUTCHERING = register("butchering", new ButcheringEnchantment(Rarity.RARE));
		public static final RegistryObject<Enchantment> POISON_ASPECT = register("poison_aspect", new PoisonAspectEnchantment(Rarity.RARE));
		public static final RegistryObject<Enchantment> VAMPIRIC = register("vampiric", new VampiricEnchantment(Rarity.VERY_RARE));
		public static final RegistryObject<Enchantment> WEIGHTED_BLADE = register("weighted_blade", new WeightedBladeEnchantment(Rarity.UNCOMMON));
		public static final RegistryObject<Enchantment> WITHER_ASPECT = register("wither_aspect", new WitherAspectEnchantment(Rarity.VERY_RARE));		
		
		// Crossbow
		public static final RegistryObject<Enchantment> PILLAGING = register("pillaging", new PillagingEnchantment(Rarity.UNCOMMON));
		public static final RegistryObject<Enchantment> POINTED = register("pointed", new PointedEnchantment(Rarity.COMMON));
		
		// Trident
		public static final RegistryObject<Enchantment> AQUATIC_REJUVENATION = register("aquatic_rejuvenation", new AquaticRejuvenationEnchantment(Enchantment.Rarity.COMMON));
		public static final RegistryObject<Enchantment> INFERNO = register("inferno", new InfernoEnchantment(Rarity.VERY_RARE));
		public static final RegistryObject<Enchantment> MAELSTROM = register("maelstrom", new MaelstromEnchantment(Rarity.RARE));
		public static final RegistryObject<Enchantment> OUTRUSH = register("outrush", new OutrushEnchantment(Rarity.UNCOMMON));
		public static final RegistryObject<Enchantment> TORRENT = register("torrent", new TorrentEnchantment(Rarity.RARE));
		
		// Saddle
		public static final RegistryObject<Enchantment> BUOYANCY_HORSE = register("buoyancy_horse", new BuoyancyHorseEnchantment(Enchantment.Rarity.RARE));
		public static final RegistryObject<Enchantment> LEAPING_HORSE = register("leaping_horse", new LeapingHorseEnchantment(Enchantment.Rarity.UNCOMMON));
		public static final RegistryObject<Enchantment> QUICKNESS_HORSE = register("quickness_horse", new QuicknessHorseEnchantment(Rarity.UNCOMMON));
		
		// Armour		
		public static final RegistryObject<Enchantment> ANCHORING_CURSE = register("anchoring_curse", new AnchoringCurseEnchantment(Rarity.VERY_RARE));
		public static final RegistryObject<Enchantment> END_CURSE = register("end_curse", new EndCurseEnchantment(Rarity.VERY_RARE));
		public static final RegistryObject<Enchantment> ENDER_SHROUD = register("ender_shroud", new EnderShroudEnchantment(Rarity.VERY_RARE));
		public static final RegistryObject<Enchantment> OBSIDIAN_SKULL = register("obsidian_skull", new ObsidianSkullEnchantment(Rarity.VERY_RARE));
		public static final RegistryObject<Enchantment> SEARING_TOUCH = register("searing_touch", new SearingTouchEnchantment(Rarity.UNCOMMON));
		public static final RegistryObject<Enchantment> STEADFAST = register("steadfast", new SteadfastEnchantment(Rarity.UNCOMMON));

		private static RegistryObject<Enchantment> register(String id, Enchantment enchantment) {
			RegistryObject<Enchantment> regObject = ENCHANTMENTS.register(id, () -> enchantment);			
			return regObject;		
		}
	}
	
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {		
		int packetIndex = 0;
		FFECore.PacketHandler.INSTANCE.registerMessage(packetIndex++, LeapingToServerPacket.class, (packet, buffer) -> {}, buffer -> new LeapingToServerPacket(), LeapingToServerPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
		FFECore.PacketHandler.INSTANCE.registerMessage(packetIndex++, LeapingToClientPacket.class, LeapingToClientPacket::encode, LeapingToClientPacket::decode, LeapingToClientPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
		FFECore.PacketHandler.INSTANCE.registerMessage(packetIndex++, BuoyancyPacket.class, BuoyancyPacket::encode, BuoyancyPacket::decode, BuoyancyPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
	}
	
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(MaelstromTridentReturning.class);
		event.register(ShooterEnchantments.class);
	}
	
	public static class PacketHandler {
		private static final String PROTOCOL_VERSION = "1";
		public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			    new ResourceLocation("ffenchantments", "main"),
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
			ClientLevel level = instance.level;
			AbstractHorse horse = (AbstractHorse) level.getEntity(packet.entityId);
	    	horse.push(0, 0.04D, 0);	
		}
	}
	
	public static DamageSource maelstromDamage(Entity source, @Nullable Entity indirectEntityIn) {
		return new IndirectEntityDamageSource(FFECore.MOD_ID+":maelstrom", source, indirectEntityIn).setMagic();
	}
}
