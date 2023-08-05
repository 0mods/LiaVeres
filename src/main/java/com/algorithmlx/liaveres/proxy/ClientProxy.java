package com.algorithmlx.liaveres.proxy;

import com.algorithmlx.api.network.Direction;
import com.algorithmlx.liaveres.client.render.AmdanorRender;
import com.algorithmlx.liaveres.client.screen.container.YarnStationScreen;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.Objects;

public class ClientProxy implements Direction {
    public void doClient(final FMLClientSetupEvent event) {
//        if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.getGameProfile().getName().equals("AlgorithmLX"))
//            Minecraft.crash(new CrashReport(Component.translatable("developer.crash.desc").toString(), new Throwable()));

        MenuScreens.register(LVRegister.YARN_STATION_CONTAINER.get(), YarnStationScreen::new);
    }

    public void entityRender(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(LVRegister.AMDANOR.get(), AmdanorRender::new);
    }

    @Override
    public void init() {
        if (FMLEnvironment.dist.isClient()) {
            IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
            IEventBus forgeBus = MinecraftForge.EVENT_BUS;

            modBus.addListener(this::doClient);
            modBus.addListener(this::entityRender);
        }
    }

    @Override
    public boolean client() {
        return true;
    }

    @Override
    public Level clientLevel() {
        return Objects.requireNonNull(Minecraft.getInstance().player).level();
    }

    @Override
    public Player clientPlayer() {
        return Minecraft.getInstance().player;
    }
}
