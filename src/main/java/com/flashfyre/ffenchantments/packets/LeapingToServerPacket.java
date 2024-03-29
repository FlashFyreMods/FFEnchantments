package com.flashfyre.ffenchantments.packets;

import java.util.function.Supplier;

import com.flashfyre.ffenchantments.FFECore;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

public class LeapingToServerPacket {
	
	public static void handle(LeapingToServerPacket packet, Supplier<NetworkEvent.Context> ctx) {
	    ctx.get().enqueueWork(() -> {
	        ServerPlayer sender = ctx.get().getSender(); // the client that sent this packet
	        Entity ridingEntity = sender.getRootVehicle();
	        if(ridingEntity instanceof AbstractHorse) {
	        	AbstractHorse horse = (AbstractHorse) ridingEntity;
	        	ItemStack saddle = horse.inventory.getItem(0);			
				int level = saddle.getEnchantmentLevel(FFECore.Enchantments.LEAPING_HORSE.get());
				if(level > 0) {
					int id = horse.getId();
					FFECore.PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> horse.getCommandSenderWorld().getChunkAt(horse.blockPosition())), new LeapingToClientPacket(id, level));
				}
	        }
	        
	    });
	    ctx.get().setPacketHandled(true);
	}
}
