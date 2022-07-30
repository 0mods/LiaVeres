package com.algorithmlx.liaveres.common;

import com.algorithmlx.liaveres.common.integrated.curios.CuriosLoader;
import com.algorithmlx.liaveres.common.setup.*;
import liquid.config.ConfigBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Constants.ModId)
public class LiaVeres {
    public static final Logger LOGGER = LogManager.getLogger(Constants.ModName + "Logger");
    private static final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

    public LiaVeres() {
        ModList list = ModList.get();

        ConfigBuilder.build(CommonConfig.class, "common_lv_config");

        Registration.init();
        bus.addListener(ModSetup::init);
        if (list.isLoaded(Constants.CurioID)) {
            bus.addListener(CuriosLoader::createCurioSlots);
        }

    }
}
