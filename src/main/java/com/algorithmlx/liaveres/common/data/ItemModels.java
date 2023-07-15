//package com.algorithmlx.liaveres.common.data;
//
//import com.algorithmlx.liaveres.common.setup.Constants;
//import com.algorithmlx.liaveres.common.setup.LVRegister;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.ItemLike;
//import net.minecraftforge.client.model.generators.ItemModelProvider;
//import net.minecraftforge.common.data.ExistingFileHelper;
//import net.minecraftforge.registries.ForgeRegistries;
//
//@SuppressWarnings("ConstantConditions")
//public class ItemModels extends ItemModelProvider {
//    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
//        super(generator, Constants.ModId, existingFileHelper);
//    }
//
//    @Override
//    protected void registerModels() {
//        standardModel(LVRegister.GILDED_NETHERITE_INGOT.get());
//        standardModel(LVRegister.GILDED_NETHERITE_HELMET.get());
//        standardModel(LVRegister.GILDED_NETHERITE_CHESTPLATE.get());
//        standardModel(LVRegister.GILDED_NETHERITE_LEGS.get());
//        standardModel(LVRegister.GILDED_NETHERITE_BOOTS.get());
//        standardModel(LVRegister.BASIC_BACKPACK.get());
//
//        toolModel(LVRegister.GILDED_NETHERITE_SWORD.get());
//        toolModel(LVRegister.GILDED_NETHERITE_AXE.get());
//        toolModel(LVRegister.GILDED_NETHERITE_PICKAXE.get());
//        toolModel(LVRegister.GILDED_NETHERITE_SHOVEL.get());
//        toolModel(LVRegister.GILDED_NETHERITE_HOE.get());
//
//        blockItemModel(LVRegister.GILDED_NETHERITE_BLOCK.get());
//    }
//
//    private void standardModel(ItemLike itemName) {
//        String itemId = ForgeRegistries.ITEMS.getKey(itemName.asItem()).getPath();
//        singleTexture(itemId, new ResourceLocation("item/generated"), "layer0", new ResourceLocation(Constants.ModId, "item/" + itemId));
//    }
//
//    private void blockItemModel(ItemLike block) {
//        String blockId = ForgeRegistries.ITEMS.getKey(block.asItem()).getPath();
//        withExistingParent(blockId, new ResourceLocation(Constants.ModId, "block/" + blockId));
//    }
//
//    private void toolModel(ItemLike itemName) {
//        String itemId = ForgeRegistries.ITEMS.getKey(itemName.asItem()).getPath();
//        singleTexture(itemId, new ResourceLocation("item/handheld"), "layer0", new ResourceLocation(Constants.ModId, "item/" + itemId));
//    }
//}
