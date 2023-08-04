package com.algorithmlx.liaveres.common.setup;

import com.algorithmlx.liaveres.api.config.KConfigBuilder;
import com.algorithmlx.liaveres.api.network.Direction;
import com.algorithmlx.liaveres.common.event.LVEvents;
import com.algorithmlx.liaveres.common.setup.config.LVCommon;
import com.algorithmlx.liaveres.proxy.ClientProxy;
import com.algorithmlx.liaveres.proxy.ServerProxy;
import com.algorithmlx.liaveres.server.network.Network;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModSetup {
    public static Direction proxy = Direction.of(ClientProxy::new, ServerProxy::new);

    public static void init() {
        KConfigBuilder.Companion.build(LVCommon.class, "common");

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        LVRegister.init();

        forgeBus.addListener(ModSetup::commonSetup);
        modBus.addListener(LVEvents::registryEntityAttributes);
    }

    public static void commonSetup(final FMLCommonSetupEvent event) {
        Network.messageRegister();
//        OreConfigured.register();
//        OrePlacement.register();
        proxy.init();
    }
}
