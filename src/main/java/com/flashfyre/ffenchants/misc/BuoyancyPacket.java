package com.flashfyre.ffenchants.misc;

import java.util.function.Supplier;

import com.flashfyre.ffenchants.FFE;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.network.NetworkEvent;

public class BuoyancyPacket {
	
	public final int entityId;
	
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
		if (FMLEnvironment.dist == Dist.CLIENT) {
			ctx.get().enqueueWork(() ->
			FFE.ClientPacketHandler.handleBuoyancyPacket(packet, ctx));
		}
	    ctx.get().setPacketHandled(true);
	}

}
