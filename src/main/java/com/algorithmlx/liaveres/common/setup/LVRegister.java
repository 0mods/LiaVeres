package com.algorithmlx.liaveres.common.setup;

import com.algorithmlx.api.recipe.LVRecipeSerializer;
import com.algorithmlx.api.util.ContainedItem;
import com.algorithmlx.api.util.container.SizedContainer;
import com.algorithmlx.api.util.tools.ContainedItemBuilder;
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
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("all")
public class LVRegister {
    public static final List<Item> WITHOUT_TABS_ITEMS = new ArrayList<>();

    public static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.ModId);
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.ModId);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.ModId);
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Constants.ModId);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Constants.ModId);
    public static final DeferredRegister<Biome> BIOME = DeferredRegister.create(ForgeRegistries.BIOMES, Constants.ModId);
    public static final DeferredRegister<Fluid> FLUID = DeferredRegister.create(ForgeRegistries.FLUIDS, Constants.ModId);
    public static final DeferredRegister<StructureType<?>> STRUCTURE = DeferredRegister.create(Registries.STRUCTURE_TYPE, Constants.ModId);
    public static final DeferredRegister<MenuType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Constants.ModId);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.ModId);
    public static final DeferredRegister<Codec<? extends BiomeModifier>> MODIFIER_CODEC = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Constants.ModId);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        TAB.register(bus);
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
    private static final RegistryObject<CreativeModeTab> LV_MAIN_TAB = TAB.register("classic_tab", CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.liaveres.classic_tab"))
            .icon(()-> new ItemStack(LVRegister.MATTER_CRYSTAL_BLOCK.get()))
            .displayItems((d, o)-> {
                ITEM.getEntries().stream().map(RegistryObject::get).forEach(i -> {
                    for (Item noTabItem : WITHOUT_TABS_ITEMS) {
                        if (i != noTabItem) o.accept(i);
                    }
                });
            })
            ::build
    );
    //BLOCk
    public static final RegistryObject<Block> MATTER_CRYSTAL_BLOCK =
        register("matter_crystal_block", ()-> new Block(BlockBehaviour.Properties.of()
                        .strength(Float.MAX_VALUE, Float.MAX_VALUE).requiresCorrectToolForDrops()),
        block -> new BlockItem(block, new Item.Properties()));
    public static final RegistryObject<Block> AMDANOR_SPAWNER = register("amdanor_spawner", AmdanorSpawner::new,
            block -> new BlockItem(block, new Item.Properties()));
    public static final RegistryObject<Block> GILDED_NETHERITE_BLOCK = register("gilded_netherite_block",
            ()-> new Block(BlockBehaviour.Properties.of().strength(80f, 240000f)
                    .requiresCorrectToolForDrops()),
            block -> new BlockItem(block, new Item.Properties()));
    public static final RegistryObject<Block> CRYSTALLITE = register("crystallite", Crystallite::new,
            block -> new BlockItem(block, new Item.Properties()));
    public static final RegistryObject<Block> MATTER_BLOCK = register("matter_block",
            ()-> new Block(BlockBehaviour.Properties.of().strength(500f, 700000000f)
                    .requiresCorrectToolForDrops()),
            block -> new BlockItem(block, new Item.Properties()));
    public static final RegistryObject<Block> YARN_STATION = register("yarn_station", YarnStation::new,
        block -> new BlockItem(block, new Item.Properties()));
    //ITEM
    public static final RegistryObject<Item> MATTER_CRYSTAL_HELMET = ITEM.register("matter_crystal_helmet",
            ()-> new MatterCrystalArmor(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> MATTER_CRYSTAL_CHESTPLATE = ITEM.register("matter_crystal_chestplate",
            ()-> new MatterCrystalArmor(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> MATTER_CRYSTAL_LEGS = ITEM.register("matter_crystal_leggings",
            ()-> new MatterCrystalArmor(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> MATTER_CRYSTAL_BOOTS = ITEM.register("matter_crystal_boots",
            ()-> new MatterCrystalArmor(ArmorItem.Type.BOOTS));
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
    public static final RegistryObject<Item> MATTER_HELMET = ITEM.register("matter_helmet",
            ()-> new MatterArmor(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> MATTER_CHESTPLATE = ITEM.register("matter_chestplate",
            ()-> new MatterArmor(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> MATTER_LEGS = ITEM.register("matter_leggings",
            ()-> new MatterArmor(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> MATTER_BOOTS = ITEM.register("matter_boots",
            ()-> new MatterArmor(ArmorItem.Type.BOOTS));
    public static final RegistryObject<Item> CRYSTALLINE = ITEM.register("crystalline",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MATTER_CRYSTAL_AXE = ITEM.register("matter_crystal_axe", MatterCrystalAxe::new);
    public static final RegistryObject<Item> MATTER_CRYSTAL_PICKAXE = ITEM.register("matter_crystal_pickaxe", MatterCrystalPickaxe::new);
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
    public static final RegistryObject<Item> LIA_BOOK = ITEM.register("lia_book", LiaBook::new);
    public static final RegistryObject<Item> GILDED_NETHERITE_INGOT = ITEM.register("gilded_netherite_ingot",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GILDED_NETHERITE_HELMET = ITEM.register("gilded_netherite_helmet",
            ()-> new GildedNetheriteArmor(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> GILDED_NETHERITE_CHESTPLATE = ITEM.register("gilded_netherite_chestplate",
            ()-> new GildedNetheriteArmor(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> GILDED_NETHERITE_LEGS = ITEM.register("gilded_netherite_leggings",
            ()-> new GildedNetheriteArmor(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> GILDED_NETHERITE_BOOTS = ITEM.register("gilded_netherite_boots",
            ()-> new GildedNetheriteArmor(ArmorItem.Type.BOOTS));
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
    public static final RegistryObject<Item> LEATHER_STRAP = ITEM.register("leather_strap",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STITCHED_LEATHER = ITEM.register("stitched_leather",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STRING_SKEIN = ITEM.register("string_skein",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMPTY_SKEIN = ITEM.register("empty_skein",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BASIC_BACKPACK = ITEM.register("basic_backpack",
            ()-> new ContainedItem(ContainedItemBuilder.builder().rowWidth(9).rowHeight(1).of()));
    public static final RegistryObject<Item> MATTER_CRYSTAL_BREAKER = ITEM.register("matter_crystal_breaker",
            MatterCrystalBreaker::new);
    public static final RegistryObject<Item> DEEP_RING = ITEM.register("deep_ring", DeepRing::new);
    //MENU TYPES
    public static final RegistryObject<MenuType<YarnStationContainerMenu>> YARN_STATION_CONTAINER =
            CONTAINER.register("yarn_station", ()-> IForgeMenuType.create(
                    (windowId, inv, data)-> {
                        BlockPos pos = data.readBlockPos();
                        Level level = inv.player.getCommandSenderWorld();
                        return new YarnStationContainerMenu(windowId, inv, level, pos);
                    }));
    public static final RegistryObject<MenuType<SizedContainer>> SC = CONTAINER.register("sized_container", ()->
            IForgeMenuType.create((id, inv, byteBuf)-> new SizedContainer(id, inv, inv.player.getUsedItemHand())));
    // BLOCK ENTITIES
    public static final RegistryObject<BlockEntityType<YarnStationBlockEntity>> YARN_STATION_BLOCK_ENTITY =
            BLOCK_ENTITY.register("yarn_station", ()-> BlockEntityType.Builder.of(YarnStationBlockEntity::new, YARN_STATION.get()).build(null));
    // ENTITIES
    public static final RegistryObject<EntityType<Amdanor>> AMDANOR =
            ENTITY.register("amdanor",
                    ()-> EntityType.Builder.of(Amdanor::new, MobCategory.MISC)
                            .sized(0.55f, 1.5f).fireImmune().immuneTo(Blocks.WITHER_ROSE)
                            .clientTrackingRange(16).build("amdanor"));
    // STRUCTURES
    public static final RegistryObject<StructureType<?>> AMDANOR_BASE = register("amdanor_base", AmdanorBaseStructure.CODEC);
    // RECIPES
    public static final RegistryObject<LVRecipeSerializer<YarnRecipe>> YARN_RECIPE = RECIPE.register("yarn",
            ()-> new LVRecipeSerializer<>(YarnRecipe::new));
    // ORES
    public static final RegistryObject<Codec<OreModifier>> ORE_CODEC = MODIFIER_CODEC.register("ore_gen_codec",
            ()-> RecordCodecBuilder.create(BUILDER -> BUILDER.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(OreModifier::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(OreModifier::feature)
            ).apply(BUILDER, OreModifier::new)));

    private static <S extends Structure> RegistryObject<StructureType<?>> register(String id, Codec<S> codec) {
        return STRUCTURE.register(id, ()-> codecConvertor(codec));
    }
    private static <S extends Structure> @NotNull StructureType<S> codecConvertor(Codec<S> codec) {
        return ()-> codec;
    }

    private static <B extends Block> RegistryObject<B> register(String name, Supplier<B> blockSupplier, Function<B, Item> itemFactory) {
        RegistryObject<B> block = BLOCKS.register(name, blockSupplier);
        ITEM.register(name, ()-> itemFactory.apply(block.get()));
        return block;
    }
}
