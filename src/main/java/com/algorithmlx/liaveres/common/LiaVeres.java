package com.algorithmlx.liaveres.common;

import com.algorithmlx.liaveres.common.integrated.curios.CuriosLoader;
import com.algorithmlx.liaveres.common.setup.Config;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.ModSetup;
import com.algorithmlx.liaveres.common.setup.Registration;
import liquid.config.ExtendableConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Constants.ModId)
public class LiaVeres {
    public static final Logger LOGGER = LogManager.getLogger(Constants.ModName + "Logger");
    private static final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

    public static Config COMMON;

    public LiaVeres() {
        ModList list = ModList.get();

        if (list.isLoaded(Constants.LiquidID)) {
            COMMON = ExtendableConfig.of(ModConfig.Type.COMMON, Config.class);
            Registration.init();
            bus.addListener(ModSetup::init);

            if (list.isLoaded(Constants.CurioID)) {
                bus.addListener(CuriosLoader::createCurioSlots);
            }
        }

        if (!list.isLoaded("liquid")) {
            this.liquidCoreNotInstalledLogger();
        }
    }

    private void liquidCoreNotInstalledLogger() {
        LOGGER.fatal("Liquid core is doesn't found! Mod is can't been loaded!");
        LOGGER.fatal("You can download LiquidCore here:");
        LOGGER.fatal("Modrinth: https://modrinth.com/mod/liquidcore");
        LOGGER.fatal("Curseforge: https://www.curseforge.com/minecraft/mc-mods/liquidcore");
    }
}
