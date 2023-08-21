package com.algorithmlx.liaveres.common.setup;

import com.algorithmlx.liaveres.common.block.*;
import com.algorithmlx.liaveres.common.entity.*;
import com.algorithmlx.liaveres.common.item.material.*;
import com.algorithmlx.liaveres.common.item.armor.*;
import com.algorithmlx.liaveres.common.item.artifact.*;
import com.algorithmlx.liaveres.common.item.basic.*;
import com.algorithmlx.liaveres.common.item.tool.*;
import com.algorithmlx.liaveres.common.item.food.*;
import com.algorithmlx.liaveres.common.world.AmdanorHouse;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

import java.util.function.Function;
import java.util.function.Supplier;

public class LVRegister {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.ModId);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.ModId);
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, Constants.ModId);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Constants.ModId);
    public static final DeferredRegister<Biome> BIOME = DeferredRegister.create(ForgeRegistries.BIOMES, Constants.ModId);
    public static final DeferredRegister<Fluid> FLUID = DeferredRegister.create(ForgeRegistries.FLUIDS, Constants.ModId);
    public static final DeferredRegister<StructureFeature<?>> STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Constants.ModId);
    public static final DeferredRegister<MenuType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, Constants.ModId);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.ModId);

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

    //BLOCk
    public static final RegistryObject<Block> MATTER_CRYSTAL_BLOCK =
        register("matter_crystal_block", ()-> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                        .strength(Float.MAX_VALUE, Float.MAX_VALUE).requiresCorrectToolForDrops()),
        block -> new BlockItem(block, new Item.Properties()));
    public static final RegistryObject<Block> AMDANOR_SPAWNER = register("amdanor_spawner", ParticleCollector::new,
            block -> new BlockItem(block, new Item.Properties()));
    public static final RegistryObject<Block> GILDED_NETHERITE_BLOCK = register("gilded_netherite_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(80f, 240000f)
                    .requiresCorrectToolForDrops()),
            block -> new BlockItem(block, new Item.Properties()));
    public static final RegistryObject<Block> CRYSTALLITE = register("crystallite", Crystallite::new,
            block -> new BlockItem(block, new Item.Properties()));
    public static final RegistryObject<Block> MATTER_BLOCK = register("matter_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(500f, 700000000f)
                    .requiresCorrectToolForDrops()),
            block -> new BlockItem(block, new Item.Properties()));
    //ITEM
    public static final RegistryObject<Item> MATTER_CRYSTAL_HELMET = ITEM.register("matter_crystal_helmet",
            ()-> new MatterCrystalArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> MATTER_CRYSTAL_CHESTPLATE = ITEM.register("matter_crystal_chestplate",
            ()-> new MatterCrystalArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> MATTER_CRYSTAL_LEGS = ITEM.register("matter_crystal_leggings",
            ()-> new MatterCrystalArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> MATTER_CRYSTAL_BOOTS = ITEM.register("matter_crystal_boots",
            ()-> new MatterCrystalArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> MATTER_CRYSTAL = ITEM.register("matter_crystal",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MATTER = ITEM.register("matter",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MATTER_SHARD = ITEM.register("matter_shard",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMDANOR_SKELETON_EGG = ITEM.register("amdanor_skeleton_egg",
            ()-> new ForgeSpawnEggItem(LVRegister.AMDANOR, 0x000000, 0xffffff,
                    new Item.Properties()));
    public static final RegistryObject<Item> MATTER_CRYSTAL_SWORD = ITEM.register("matter_crystal_sword", MatterCrystalSword::new);
    public static final RegistryObject<Item> CRYSTALLINE = ITEM.register("crystalline",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MATTER_CRYSTAL_AXE = ITEM.register("matter_crystal_axe", MatterCrystalAxe::new);
    public static final RegistryObject<Item> MATTER_CRYSTAL_PICKAXE = ITEM.register("matter_crystal_pickaxe", MatterCrystalPickaxe::new);
    public static final RegistryObject<Item> MATTER_CRYSTAL_BREAKER = ITEM.register("matter_crystal_breaker", MatterCrystalBreaker::new);
    public static final RegistryObject<Item> MATTER_CRYSTAL_SHOVEL = ITEM.register("matter_crystal_shovel", MatterCrystalShovel::new);
    public static final RegistryObject<Item> MATTER_CRYSTALR_HOE = ITEM.register("matter_crystal_hoe", MatterCrystalHoe::new);
    public static final RegistryObject<Item> EFFECT_CATALYST = ITEM.register("effect_catalyst", EffectCatalyst::new);
    public static final RegistryObject<Item> ENCHANTED_APPLE = ITEM.register("enchanted_apple", EnchantedApple::new);
    public static final RegistryObject<Item> LIGHTNING_ARTIFACT =  ITEM.register("lightning_artifact", LightningArtifact::new);
    public static final RegistryObject<Item> EMPTY_ARTIFACT =  ITEM.register("empty_artifact",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMDANOR_UNLOCKER_KEY = ITEM.register("amdanor_key", AmdanorKey::new);
    public static final RegistryObject<Item> WITHERING_BONE = ITEM.register("withering_bone",
            ()-> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GILDED_NETHERITE_INGOT = ITEM.register("gilded_netherite_ingot",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GILDED_NETHERITE_HELMET = ITEM.register("gilded_netherite_helmet",
            ()-> new GildedNetheriteArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> GILDED_NETHERITE_CHESTPLATE = ITEM.register("gilded_netherite_chestplate",
            ()-> new GildedNetheriteArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> GILDED_NETHERITE_LEGS = ITEM.register("gilded_netherite_leggings",
            ()-> new GildedNetheriteArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> GILDED_NETHERITE_BOOTS = ITEM.register("gilded_netherite_boots",
            ()-> new GildedNetheriteArmor(EquipmentSlot.FEET));
    public static final RegistryObject<Item> GILDED_NETHERITE_SWORD = ITEM.register("gilded_netherite_sword",
            ()-> new SwordItem(LVToolTiers.GILDED_NETHERITE, 12, 10F,
                    new Item.Properties().rarity(Constants.getLegendary())));
    public static final RegistryObject<Item> GILDED_NETHERITE_PICKAXE = ITEM.register("gilded_netherite_pickaxe",
            ()-> new PickaxeItem(LVToolTiers.GILDED_NETHERITE, 4, 12F,
                    new Item.Properties().rarity(Constants.getLegendary())));
    public static final RegistryObject<Item> GILDED_NETHERITE_AXE = ITEM.register("gilded_netherite_axe",
            ()-> new AxeItem(LVToolTiers.GILDED_NETHERITE, 20F, 11.2F,
                    new Item.Properties().rarity(Constants.getLegendary())));
    public static final RegistryObject<Item> GILDED_NETHERITE_SHOVEL = ITEM.register("gilded_netherite_shovel",
            ()-> new ShovelItem(LVToolTiers.GILDED_NETHERITE, 6F, 12F,
                    new Item.Properties().rarity(Constants.getLegendary())));
    public static final RegistryObject<Item> GILDED_NETHERITE_HOE = ITEM.register("gilded_netherite_hoe",
            ()-> new HoeItem(LVToolTiers.GILDED_NETHERITE, -16, 0F,
                    new Item.Properties().rarity(Constants.getLegendary())));
    public static final RegistryObject<Item> DEEP_RING = ITEM.register("deep_ring", DeepRing::new);
    // MENU TYPES

    // BLOCK ENTITIES
    
    // ENTITIES
    public static final RegistryObject<EntityType<Amdanor>> AMDANOR =
            ENTITY.register("amdanor",
                    ()-> EntityType.Builder.of(Amdanor::new, MobCategory.MISC)
                            .sized(0.55f, 1.5f).fireImmune().immuneTo(Blocks.WITHER_ROSE)
                            .clientTrackingRange(16).build("amdanor"));
    // STRUCTURES
    public static final RegistryObject<StructureFeature<?>> AMDANOR_HOUSE = STRUCTURE.register("amdanor_base", AmdanorHouse::new);
    // RECIPES

    private static <B extends Block> RegistryObject<B> register(String name, Supplier<B> blockSupplier, Function<B, Item> itemFactory) {
        RegistryObject<B> block = BLOCKS.register(name, blockSupplier);
        ITEM.register(name, ()-> itemFactory.apply(block.get()));
        return block;
    }
}
