package com.algorithmlx.liaveres.common;

import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.ModConfig;
import com.algorithmlx.liaveres.common.setup.Registration;
import liquid.config.ConfigBuilder;
import net.fabricmc.api.ModInitializer;

public class LiaVeres implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigBuilder.build(Constants.ModId, ModConfig.class);
        Registration.init();
    }
}
