package com.algorithmlx.liaveres.common.setup;

import com.algorithmlx.liaveres.common.block.AmdanorSpawner;
import com.algorithmlx.liaveres.common.entity.Amdanor;
import com.algorithmlx.liaveres.common.item.armor.MatterCrystalArmor;
import com.algorithmlx.liaveres.common.world.structures.AmdanorBase;
import com.mojang.serialization.Codec;
import liquid.recipes.LiquidRecipeSerializers;
import liquid.recipes.LiquidRecipes;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.material.Material;

import java.util.function.Function;
import java.util.function.Supplier;

public class Registration {
    //block
    public static Block MATTER_CRYSTAL_BLOCK;
    public static Block AMDANOR_SPAWNER;
    public static Block GILDED_NETHERITE_BLOCK;
    public static Block CRYSTALLITE;
    public static Block MATTER_BLOCK;
    public static Block YARN_STATION;
    //items
    public static Item MATTER_CRYSTAL_HELMET;
    public static Item MATTER_CRYSTAL_CHEST;
    public static Item MATTER_CRYSTAL_LEGS;
    public static Item MATTER_CRYSTAL_BOOTS;
    public static Item MATTER_CRYSTAL;
    public static Item MATTER;
    public static Item MATTER_SHARD;
    public static Item AMDANOR_SPAWN_EGG;
    public static Item MATTER_CRYSTAL_SWORD;
    public static Item MATTER_HELMET;
    public static Item MATTER_CHEST;
    public static Item MATTER_LEGS;
    public static Item MATTER_BOOTS;
    public static Item CRYSTALLINE;
    public static Item MATTER_CRYSTAL_AXE;
    public static Item MATTER_CRYSTAL_PICKAXE;
    public static Item MATTER_CRYSTAL_HOE;
    public static Item EFFECT_CATALYST;
    public static Item ENCHANTED_APPLE;
    public static Item LIGHTNING_ARTIFACT;
    public static Item EMPTY_ARTIFACT;
    public static Item AMDANOR_KEY;
    public static Item WITHERING_BONE;
    public static Item LIA_BOOK;
    public static Item GILDED_NETHERITE_INGOT;
    public static Item GILDED_NETHERITE_HELMET;
    public static Item GILDED_NETHERITE_CHEST;
    public static Item GILDED_NETHERITE_LEGS;
    public static Item GILDED_NETHERITE_BOOTS;
    public static Item GILDED_NETHERITE_SWORD;
    public static Item GILDED_NETHERITE_PICKAXE;
    public static Item GILDED_NETHERITE_SHOVEL;
    public static Item GILDED_NETHERITE_HOE;
    public static Item STITCHED_LEATHER;
    public static Item EMPTY_SKEIN;
    public static Item BASIC_BACKPACK;
    //utility
    //public static MenuType<YarnStationContainerMenu> YARN_STATION_CONTAINER;
    //public static BlockEntityType<YarnStationBlockEntity> YARN_STATION_BLOCK_ENTITY;
    public static EntityType<Amdanor> AMDANOR;
    public static StructureType<?> AMDANOR_BASE;
    //public static LiquidRecipeSerializers<YarnRecipe> YARN_RECIPE;

    public static void startRegistry() {
        //block
        MATTER_CRYSTAL_BLOCK = block("matter_crystal_block", b -> new BlockItem(b, Constants.standard()),
                ()-> new Block(BlockBehaviour.Properties.of(Material.METAL)
                        .strength(Float.MAX_VALUE, Float.MAX_VALUE).requiresCorrectToolForDrops()));
        AMDANOR_SPAWNER = block("amdanor_spawner", b -> new BlockItem(b, Constants.standard()), AmdanorSpawner::new);
        GILDED_NETHERITE_BLOCK = block("gilded_netherite_block", b -> new BlockItem(b, Constants.standard()),
                ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(80f, 240000f)
                        .requiresCorrectToolForDrops()));

        MATTER_BLOCK = block("matter_block", b -> new BlockItem(b, Constants.standard()),
                ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(500f, 700000000f)
                .requiresCorrectToolForDrops()));
        //item
        MATTER_CRYSTAL_HELMET = item("matter_crystal_helmet", ()-> new MatterCrystalArmor(EquipmentSlot.HEAD));
        MATTER_CRYSTAL_CHEST = item("matter_crystal_chestplate", ()-> new MatterCrystalArmor(EquipmentSlot.CHEST));
        MATTER_CRYSTAL_LEGS = item("matter_crystal_leggings", ()-> new MatterCrystalArmor(EquipmentSlot.LEGS));
        MATTER_CRYSTAL_BOOTS = item("matter_crystal_boots", ()-> new MatterCrystalArmor(EquipmentSlot.FEET));
        MATTER_CRYSTAL = item("matter_crystal", ()-> new Item(Constants.standard()));
        MATTER = item("matter", ()-> new Item(Constants.standard()));
        MATTER_SHARD = item("matter_shard", ()-> new Item(Constants.standard()));

        AMDANOR_BASE = structure("amdanor_base", ()-> AmdanorBase.CODEC);
    }

    private static <T extends Entity> EntityType<T> entity(String id, Supplier<EntityType<T>> toReg) {
        return Registry.register(Registry.ENTITY_TYPE, Constants.rl(id), toReg.get());
    }

    private static <T extends Item> T item(String id, Supplier<T> toReg) {
        return Registry.register(Registry.ITEM, Constants.rl(id), toReg.get());
    }

    private static <T extends Block> T block(String id, Function<T, Item> toItemBlock, Supplier<T> toReg) {
        T b = Registry.register(Registry.BLOCK, Constants.rl(id), toReg.get());
        item(id, ()-> toItemBlock.apply(b));
        return b;
    }

    private static <T extends AbstractContainerMenu> MenuType<T> container(String id, MenuType.MenuSupplier<T> toReg) {
        return Registry.register(Registry.MENU, Constants.rl(id), new MenuType<>(toReg));
    }

    private static <T extends Structure> StructureType<T> structure(String id, Supplier<Codec<T>> codec) {
        return Registry.register(Registry.STRUCTURE_TYPES, Constants.rl(id), codec::get);
    }

    private static <T extends LiquidRecipes> LiquidRecipeSerializers<T> recipe(String id, Supplier<LiquidRecipeSerializers.SerializerFactory<T>> factorySupplier) {
        LiquidRecipeSerializers<T> serializer = new LiquidRecipeSerializers<>(factorySupplier.get());
        return Registry.register(Registry.RECIPE_SERIALIZER, Constants.rl(id), serializer);
    }
}
