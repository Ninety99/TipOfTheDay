package me.NinetyNine.totd.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class TOTDPacketHandler implements Listener {

	/*
	 * Credits to a guy from bukkit dev site, forgot his name.F
	 */

	public static void sendPacket(Player player) {
		try {
			PacketContainer pc = ProtocolLibrary.getProtocolManager()
					.createPacket(PacketType.Play.Server.CUSTOM_PAYLOAD);
			pc.getModifier().writeDefaults();
			ByteBuf bf = Unpooled.buffer(256);
			bf.setByte(0, (byte) 0);
			bf.writerIndex(1);
			pc.getModifier().write(1, MinecraftReflection.getPacketDataSerializer(bf));
			pc.getStrings().write(0, "MC|BOpen");
			ProtocolLibrary.getProtocolManager().sendServerPacket(player, pc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
