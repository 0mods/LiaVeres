package com.algorithmlx.liaveres.common.setup;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.function.Function;
import java.util.function.Supplier;

public class Registration {
    public static Item MATTER_SHARD;
    public static Block MATTER_CRYSTAL_BLOCK;

    public static void init() {
        MATTER_SHARD = item("matter_shard", ()-> new Item(new Item.Properties()));
        MATTER_CRYSTAL_BLOCK = block("matter_crystal_block",
                ()-> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    }

    private static <T extends Entity> EntityType<T> entity(String id, Supplier<EntityType<T>> toReg) {
        return Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constants.ModId, id), toReg.get());
    }

    private static Item item(String id, Supplier<Item> toReg) {
        return Registry.register(Registry.ITEM, new ResourceLocation(Constants.ModId, id), toReg.get());
    }

    private static <T extends Block> T block(String id, Function<T, Item> toItemBlock, Supplier<T> toReg) {
        T b = Registry.register(Registry.BLOCK, new ResourceLocation(Constants.ModId, id), toReg.get());
        item(id, ()-> toItemBlock.apply(b));
        return b;
    }

    private static Block block(String id, Supplier<Block> toReg) {
        return block(id, block -> new BlockItem(block, new Item.Properties()), toReg);
    }
}
