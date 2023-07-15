package com.algorithmlx.liaveres.server.network;

import com.algorithmlx.liaveres.common.setup.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Network {
    private static SimpleChannel SIMPLE_CHANNEL;
    private static int ID = 0;
    private static final String VERSION = "1.0";

    private static int nextID() {
        return ID++;
    }

    public static void messageRegister() {
        SIMPLE_CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(Constants.ModId, "main"),
                ()-> VERSION,
                VERSION::equals,
                VERSION::equals
        );

        SIMPLE_CHANNEL.messageBuilder(OpenScreenPacket.class, nextID())
                .encoder(((openScreenPacket, friendlyByteBuf) -> {}))
                .decoder(friendlyByteBuf -> new OpenScreenPacket())
                .consumerMainThread(OpenScreenPacket::liaBookHandler)
                .add();
    }

    public static void clientSender(Object obj, ServerPlayer serverPlayer) {
        SIMPLE_CHANNEL.sendTo(obj, serverPlayer.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void serverSender(Object obj) {
        SIMPLE_CHANNEL.sendToServer(obj);
    }
}
