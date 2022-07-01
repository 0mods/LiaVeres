package com.algorithmlx.liaveres.common.setup;

import liquid.config.ExtendableConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.logging.log4j.Level;

public class Config extends ExtendableConfig {
    public final ForgeConfigSpec.IntValue pickaxeExcavationRadius;

    public Config(ForgeConfigSpec.Builder b) {
        super(b);
        b.push("LiaVeres Config");
        pickaxeExcavationRadius = b.comment("Lia Pickaxe excavation radius.", "It working like this: ",
                        "16 + 1 = 17; 17 * 2 = 34; 34 - 2 = 32 - pickaxe excavation radius")
                .defineInRange("pickaxeExcavationRadius", 16 + 1, 2 + 1, 64 + 1);
        b.pop();
    }

    @Override
    public void reloadContext(ModConfigEvent modConfigEvent) {

    }
}
