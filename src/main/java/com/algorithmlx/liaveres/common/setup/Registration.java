package com.algorithmlx.liaveres.common.setup;

import com.algorithmlx.liaveres.common.block.*;
import com.algorithmlx.liaveres.common.block.entity.YarnStationBlockEntity;
import com.algorithmlx.liaveres.common.entity.*;
import com.algorithmlx.liaveres.common.item.material.*;
import com.algorithmlx.liaveres.common.item.armor.*;
import com.algorithmlx.liaveres.common.item.artifact.*;
import com.algorithmlx.liaveres.common.item.basic.*;
import com.algorithmlx.liaveres.common.item.tool.*;
import com.algorithmlx.liaveres.common.item.food.*;
import com.algorithmlx.liaveres.common.recipe.*;
import com.algorithmlx.liaveres.common.world.levelgen.OreModifier;
import com.algorithmlx.liaveres.common.world.structures.*;
import com.algorithmlx.liaveres.common.menu.*;
import com.mojang.serialization.Codec;
import liquid.dynamic.item.DynamicItem;
import liquid.objects.data.container.DynamicContainerData;
import liquid.recipes.LiquidRecipeSerializers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("all")
public class Registration {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.ModId);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.ModId);
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, Constants.ModId);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Constants.ModId);
    public static final DeferredRegister<Biome> BIOME = DeferredRegister.create(ForgeRegistries.BIOMES, Constants.ModId);
    public static final DeferredRegister<Fluid> FLUID = DeferredRegister.create(ForgeRegistries.FLUIDS, Constants.ModId);
    public static final DeferredRegister<StructureType<?>> STRUCTURE = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, Constants.ModId);
    public static final DeferredRegister<MenuType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, Constants.ModId);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.ModId);
    public static final DeferredRegister<Codec<? extends BiomeModifier>> MODIFIER_CODEC = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Constants.ModId);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ITEM.register(bus);
        BLOCKS.register(bus);
        ENTITY.register(bus);
        BLOCK_ENTITY.register(bus);
        BIOME.register(bus);
        FLUID.register(bus);
        STRUCTURE.register(bus);
        CONTAINER.register(bus);
        RECIPE.register(bus);
    }
//    public static final RegistryObject<Block> MATTER_CRYSTAL_BLOCK = BLOCKS.register("matter_crystal_block",
//            ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(Float.MAX_VALUE, Float.MAX_VALUE)
//                    .requiresCorrectToolForDrops()));
//    public static final RegistryObject<Block> AMDANOR_SPAWNER = BLOCKS.register("amdanor_spawner", AmdanorSpawner::new);
//    public static final RegistryObject<Block> GILDED_NETHERITE_BLOCK = BLOCKS.register("gilded_netherite_block",
//            ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(80f, 240000f)
//                    .requiresCorrectToolForDrops()));
//    public static final RegistryObject<Block> CRYSTALLITE = BLOCKS.register("crystallite", Crystallite::new);
//    public static final RegistryObject<Block> MATTER_BLOCK = BLOCKS.register("matter_block",
//            ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(500f, 700000000f)
//                    .requiresCorrectToolForDrops()));
//    public static final RegistryObject<Block> YARN_STATION = BLOCKS.register("yarn_station", YarnStation::new);
    public static final RegistryObject<Block> MATTER_CRYSTAL_BLOCK =
        register("matter_crystal_block", ()->
                        new Block(BlockBehaviour.Properties.of(Material.METAL)
                                .strength(Float.MAX_VALUE, Float.MAX_VALUE).requiresCorrectToolForDrops()),
        block -> new BlockItem(block, new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Block> AMDANOR_SPAWNER = register("amdanor_spawner", AmdanorSpawner::new,
            block -> new BlockItem(block, new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Block> GILDED_NETHERITE_BLOCK = register("gilded_netherite_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(80f, 240000f)
                    .requiresCorrectToolForDrops()),
            block -> new BlockItem(block, new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Block> CRYSTALLITE = register("crystallite", Crystallite::new,
            block -> new BlockItem(block, new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Block> MATTER_BLOCK = register("matter_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(500f, 700000000f)
                    .requiresCorrectToolForDrops()),
            block -> new BlockItem(block, new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Block> YARN_STATION = register("yarn_station", YarnStation::new,
        block -> new BlockItem(block, new Item.Properties().tab(ModSetup.CLASSIC_TAB)));

    public static final RegistryObject<Item> MATTER_CRYSTAL_HELMET = ITEM.register("matter_crystal_helmet",
            ()-> new MatterCrystalArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> MATTER_CRYSTAL_CHESTPLATE = ITEM.register("matter_crystal_chestplate",
            ()-> new MatterCrystalArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> MATTER_CRYSTAL_LEGS = ITEM.register("matter_crystal_leggings",
            ()-> new MatterCrystalArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> MATTER_CRYSTAL_BOOTS = ITEM.register("matter_crystal_boots",
            ()-> new MatterCrystalArmor(EquipmentSlot.FEET));
    public static final RegistryObject<Item> MATTER_CRYSTAL = ITEM.register("matter_crystal",
            ()-> new Item(new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Item> MATTER = ITEM.register("matter",
            ()-> new Item(new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Item> MATTER_SHARD = ITEM.register("matter_shard",
            ()-> new Item(new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Item> AMDANOR_SKELETON_EGG = ITEM.register("amdanor_skeleton_egg",
            ()-> new ForgeSpawnEggItem(Registration.AMDANOR_SKELETON, 0x000000, 0xffffff,
                    new Item.Properties().tab(ModSetup.MOBS_TAB)));
    public static final RegistryObject<Item> MATTER_CRYSTAL_SWORD = ITEM.register("matter_crystal_sword", MatterCrystalSword::new);
    public static final RegistryObject<Item> MATTER_HELMET = ITEM.register("matter_helmet",
            ()-> new MatterArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> MATTER_CHESTPLATE = ITEM.register("matter_chestplate",
            ()-> new MatterArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> MATTER_LEGS = ITEM.register("matter_leggings",
            ()-> new MatterArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> MATTER_BOOTS = ITEM.register("matter_boots",
            ()-> new MatterArmor(EquipmentSlot.FEET));
    public static final RegistryObject<Item> CRYSTALLINE = ITEM.register("crystalline",
            ()-> new Item(new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Item> MATTER_CRYSTAL_AXE = ITEM.register("matter_crystal_axe", MatterCrystalAxe::new);
    public static final RegistryObject<Item> MATTER_CRYSTAL_PICKAXE = ITEM.register("matter_crystal_pickaxe", MatterCrystalPickaxe::new);
    public static final RegistryObject<Item> MATTER_CRYSTAL_SHOVEL = ITEM.register("matter_crystal_shovel", MatterCrystalShovel::new);
    public static final RegistryObject<Item> MATTER_CRYSTALR_HOE = ITEM.register("matter_crystal_hoe", MatterCrystalHoe::new);
    public static final RegistryObject<Item> EFFECT_CATALYST = ITEM.register("effect_catalyst", EffectCatalyst::new);
    public static final RegistryObject<Item> ENCHANTED_APPLE = ITEM.register("enchanted_apple", EnchantedApple::new);
    public static final RegistryObject<Item> LIGHTNING_ARTIFACT =  ITEM.register("lightning_artifact", LightningArtifact::new);
    public static final RegistryObject<Item> EMPTY_ARTIFACT =  ITEM.register("empty_artifact",
            ()-> new Item(new Item.Properties().tab(ModSetup.ARTIFACT_TAB)));
    public static final RegistryObject<Item> AMDANOR_UNLOCKER_KEY = ITEM.register("amdanor_key", AmdanorKey::new);
    public static final RegistryObject<Item> WITHERING_BONE = ITEM.register("withering_bone",
            ()-> new Item(new Item.Properties().tab(ModSetup.CLASSIC_TAB).fireResistant()));
    public static final RegistryObject<Item> LIA_BOOK = ITEM.register("lia_book", LiaBook::new);
    public static final RegistryObject<Item> GILDED_NETHERITE_INGOT = ITEM.register("gilded_netherite_ingot",
            ()-> new Item(new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Item> GILDED_NETHERITE_HELMET = ITEM.register("gilded_netherite_helmet",
            ()-> new GildedNetheriteArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> GILDED_NETHERITE_CHESTPLATE = ITEM.register("gilded_netherite_chestplate",
            ()-> new GildedNetheriteArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> GILDED_NETHERITE_LEGS = ITEM.register("gilded_netherite_leggings",
            ()-> new GildedNetheriteArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> GILDED_NETHERITE_BOOTS = ITEM.register("gilded_netherite_boots",
            ()-> new GildedNetheriteArmor(EquipmentSlot.FEET));
    public static final RegistryObject<Item> GILDED_NETHERITE_SWORD = ITEM.register("gilded_netherite_sword",
            ()-> new SwordItem(LVToolMaterial.GILDED_NETHERITE, 12, 10F,
                    new Item.Properties().tab(ModSetup.CLASSIC_TAB).rarity(Constants.getLegendary)));
    public static final RegistryObject<Item> GILDED_NETHERITE_PICKAXE = ITEM.register("gilded_netherite_pickaxe",
            ()-> new PickaxeItem(LVToolMaterial.GILDED_NETHERITE, 4, 12F,
                    new Item.Properties().tab(ModSetup.CLASSIC_TAB).rarity(Constants.getLegendary)));
    public static final RegistryObject<Item> GILDED_NETHERITE_AXE = ITEM.register("gilded_netherite_axe",
            ()-> new AxeItem(LVToolMaterial.GILDED_NETHERITE, 20F, 11.2F,
                    new Item.Properties().tab(ModSetup.CLASSIC_TAB).rarity(Constants.getLegendary)));
    public static final RegistryObject<Item> GILDED_NETHERITE_SHOVEL = ITEM.register("gilded_netherite_shovel",
            ()-> new ShovelItem(LVToolMaterial.GILDED_NETHERITE, 6F, 12F,
                    new Item.Properties().tab(ModSetup.CLASSIC_TAB).rarity(Constants.getLegendary)));
    public static final RegistryObject<Item> GILDED_NETHERITE_HOE = ITEM.register("gilded_netherite_hoe",
            ()-> new HoeItem(LVToolMaterial.GILDED_NETHERITE, -16, 0F,
                    new Item.Properties().tab(ModSetup.CLASSIC_TAB).rarity(Constants.getLegendary)));
    public static final RegistryObject<Item> LEATHER_STRAP = ITEM.register("leather_strap",
            ()-> new Item(new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Item> STITCHED_LEATHER = ITEM.register("stitched_leather",
            ()-> new Item(new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Item> STRING_SKEIN = ITEM.register("string_skein",
            ()-> new Item(new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Item> EMPTY_SKEIN = ITEM.register("empty_skein",
            ()-> new Item(new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Item> BASIC_BACKPACK = ITEM.register("basic_backpack",
            ()-> new DynamicItem(DynamicContainerData.of(9, 1, SoundEvents.ARMOR_EQUIP_CHAIN),
                    new Item.Properties().tab(ModSetup.CLASSIC_TAB)));
    public static final RegistryObject<Item> MATTER_CRYSTAL_BREAKER = ITEM.register("matter_crystal_breaker",
            MatterCrystalBreaker::new);
    public static final RegistryObject<MenuType<YarnStationContainerMenu>> YARN_STATION_CONTAINER =
            CONTAINER.register("yarn_station", ()-> IForgeMenuType.create(
                    (windowId, inv, data)-> {
                        BlockPos pos = data.readBlockPos();
                        Level level = inv.player.getCommandSenderWorld();
                        return new YarnStationContainerMenu(windowId, inv, level, pos);
                    }));

    public static final RegistryObject<BlockEntityType<YarnStationBlockEntity>> YARN_STATION_BLOCK_ENTITY =
            BLOCK_ENTITY.register("yarn_station", ()-> BlockEntityType.Builder.of(YarnStationBlockEntity::new, YARN_STATION.get()).build(null));

    public static final RegistryObject<EntityType<AmdanorMob>> AMDANOR_SKELETON =
            ENTITY.register("amdanor_skeleton",
                    ()-> EntityType.Builder.of(AmdanorMob::new, MobCategory.MONSTER)
                            .sized(0.55f, 1.5f).fireImmune().immuneTo(Blocks.WITHER_ROSE)
                            .clientTrackingRange(16).build("amdanor_skeleton"));

    public static final RegistryObject<StructureType<?>> AMDANOR_BASE = register("amdanor_base", AmdanorBaseStructure.CODEC);

    public static final RegistryObject<LiquidRecipeSerializers<YarnRecipe>> YARN_RECIPE = RECIPE.register("yarn",
            ()-> new LiquidRecipeSerializers<>(YarnRecipe::new));

    public static final RegistryObject<Codec<OreModifier>> ORE_CODEC = MODIFIER_CODEC.register("ore", ()-> OreModifier.CODEC);

    private static <S extends Structure> RegistryObject<StructureType<?>> register(String id, Codec<S> codec) {
        return STRUCTURE.register(id, ()-> codecConvertor(codec));
    }
    private static <S extends Structure> StructureType<S> codecConvertor(Codec<S> codec) {
        return ()-> codec;
    }
    private static <BLOCK extends Block> RegistryObject<BLOCK> register(String name, Supplier<BLOCK> blockSupplier, Function<BLOCK, Item> itemFactory) {
        RegistryObject<BLOCK> block = BLOCKS.register(name, blockSupplier);
        ITEM.register(name, ()-> itemFactory.apply(block.get()));
        return block;
    }
}
