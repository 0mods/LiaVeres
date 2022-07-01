package com.algorithmlx.liaveres.common.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeServer()) {
//            generator.addProvider(true, new Recipes(generator));
            generator.addProvider(true, new Advancements(generator, helper));
//            generator.addProvider(true, new LootTables(generator));
        }

        if (event.includeClient()) {
            generator.addProvider(true, new BlockStates(generator, event.getExistingFileHelper()));
//            generator.addProvider(true, new ItemModels(generator, event.getExistingFileHelper()));
        }
    }
}
