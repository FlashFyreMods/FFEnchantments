package com.flashfyre.ffenchants.misc;

import java.util.function.Supplier;

import com.flashfyre.ffenchants.FFE;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class LeapingToServerPacket {
	
	public static void handle(LeapingToServerPacket packet, Supplier<NetworkEvent.Context> ctx) {
	    ctx.get().enqueueWork(() -> {
	        ServerPlayerEntity sender = ctx.get().getSender(); //the client that sent this packet
	        Entity ridingEntity = sender.getLowestRidingEntity();
	        if(ridingEntity instanceof AbstractHorseEntity) {
	        	AbstractHorseEntity horse = (AbstractHorseEntity) ridingEntity;
	        	ItemStack saddle = horse.horseChest.getStackInSlot(0);			
				int level = FFE.getEnchantmentLevel(saddle, FFE.LEAPING_HORSE);
				if(level > 0) {
					int id = horse.getEntityId();
					FFE.PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> horse.getEntityWorld().getChunkAt(horse.getPosition())), new LeapingToClientPacket(id, level));
				}
	        }
	        
	    });
	    ctx.get().setPacketHandled(true);
	}

}
