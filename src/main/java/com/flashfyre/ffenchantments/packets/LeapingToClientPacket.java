package com.flashfyre.ffenchantments.packets;

import java.util.function.Supplier;

import com.flashfyre.ffenchantments.FFE;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkEvent;

public class LeapingToClientPacket {
	
	public final int entityId;
	public final int enchantmentLevel;
	
	public LeapingToClientPacket(int id, int level) {
		entityId = id;
		enchantmentLevel = level;
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeInt(entityId);
		buffer.writeInt(enchantmentLevel);
	}
	
	public static LeapingToClientPacket decode(FriendlyByteBuf buffer) {
	    return new LeapingToClientPacket(buffer.readInt(), buffer.readInt());
	}
	
	public static void handle(LeapingToClientPacket packet, Supplier<NetworkEvent.Context> ctx) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			ctx.get().enqueueWork(() -> FFE.ClientPacketHandler.handleLeapingPacket(packet, ctx));
		}
	    ctx.get().setPacketHandled(true);
	}
	
	

}
