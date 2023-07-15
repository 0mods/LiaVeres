//package com.algorithmlx.liaveres.common.data;
//
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.DataProvider;
//import net.minecraftforge.common.data.ExistingFileHelper;
//import net.minecraftforge.data.event.GatherDataEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//import java.util.List;
//import java.util.concurrent.atomic.AtomicReference;
//
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
//public class DataGenerators {
//    @SubscribeEvent
//    public static void gatherData(GatherDataEvent event) {
//        DataGenerator generator = event.getGenerator();
//        ExistingFileHelper helper = event.getExistingFileHelper();
//
////        AtomicReference<List<DataProvider>> serverProvider = new AtomicReference<>();
////        AtomicReference<List<DataProvider>> clientProvider = new AtomicReference<>();
////
////        serverProvider.get().add(new Advancements(generator, helper));
////        serverProvider.get().add(new Recipes(generator));
////        serverProvider.get().add(new LootTables(generator));
////
////        clientProvider.get().add(new BlockStates(generator, helper));
////        clientProvider.get().add(new ItemModels(generator, helper));
//
//        DataProvider[] serverProvider = new DataProvider[] {
//                new Advancements(generator, helper),
//                new Recipes(generator),
//                new LootTables(generator)
//        };
//
//        DataProvider[] clientProvider = new DataProvider[] {
//                new BlockStates(generator, helper),
//                new ItemModels(generator, helper)
//        };
//
//        if (event.includeServer())
//            for (DataProvider providers : serverProvider)
//                generator.addProvider(true, providers);
//
//
//        if (event.includeClient())
//            for (DataProvider providers: clientProvider)
//                generator.addProvider(true, providers);
//    }
//}
