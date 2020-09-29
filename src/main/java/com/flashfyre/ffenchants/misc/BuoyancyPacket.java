package com.flashfyre.ffenchants.misc;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

public class BuoyancyPacket {
	
	private final int entityId;
	
	public BuoyancyPacket(int id) {
		entityId = id;
	}
	
	public void encode(PacketBuffer buffer) {
		buffer.writeInt(entityId);
	}
	
	public static BuoyancyPacket decode(PacketBuffer buffer)
	{
	    return new BuoyancyPacket(buffer.readInt());
	}
	
	public static void handle(BuoyancyPacket packet, Supplier<NetworkEvent.Context> ctx) {
	    ctx.get().enqueueWork(() -> {
	    	World world = Minecraft.getInstance().world;
	    	AbstractHorseEntity horse = (AbstractHorseEntity) world.getEntityByID(packet.entityId);
	    	horse.addVelocity(0, 0.06D, 0);	        
	    });
	    ctx.get().setPacketHandled(true);
	}

}
