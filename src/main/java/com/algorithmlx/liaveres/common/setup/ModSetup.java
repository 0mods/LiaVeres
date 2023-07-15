package com.algorithmlx.liaveres.common.setup;

import com.algorithmlx.liaveres.api.network.Direction;
import com.algorithmlx.liaveres.common.event.LVEvents;
import com.algorithmlx.liaveres.proxy.ClientProxy;
import com.algorithmlx.liaveres.proxy.ServerProxy;
import com.algorithmlx.liaveres.server.network.Network;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = Constants.ModId, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
    public static Direction proxy = Direction.of(ClientProxy::new, ServerProxy::new);

    public static void init() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

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
