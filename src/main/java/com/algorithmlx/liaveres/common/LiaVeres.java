package com.algorithmlx.liaveres.common;

import com.algorithmlx.liaveres.api.config.ConfigBuilder;
import com.algorithmlx.liaveres.common.setup.*;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Constants.ModId)
public class LiaVeres {
    public static final Logger LOGGER = LoggerFactory.getLogger(Constants.ModName + "Logger");

    public LiaVeres() {
        ConfigBuilder.build(CommonConfig.class, "common");

        LVRegister.init();
        ModSetup.init();
//        if (list.isLoaded(Constants.CurioID)) bus.addListener(CuriosLoader::createCurioSlots);
    }
}
