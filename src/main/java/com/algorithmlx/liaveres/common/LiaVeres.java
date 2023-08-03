package com.algorithmlx.liaveres.common;

import com.algorithmlx.liaveres.common.setup.*;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Constants.ModId)
public class LiaVeres {
    public static final Logger LOGGER = LoggerFactory.getLogger(Constants.ModName + "Logger");

    public LiaVeres() {
        ModSetup.init();
    }
}
