package com.algorithmlx.liaveres.common.setup;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Constants {
    public static final String ModId = "liaveres";

    public static final CreativeModeTab CLASSIC_TAB =
            FabricItemGroupBuilder.create(rl("classic_tab"))
                    .icon(()-> new ItemStack(Registration.MATTER_SHARD))
                    .build();
    public static final CreativeModeTab ARTIFACT_TAB =
            FabricItemGroupBuilder.create(rl("artifact_tab"))
                    .icon(()-> new ItemStack(Registration.MATTER_SHARD))
                    .build();
    public static final CreativeModeTab SUMMON_TAB =
            FabricItemGroupBuilder.create(rl("mobs_tab"))
                    .icon(()-> new ItemStack(Registration.MATTER_SHARD))
                    .build();

    public static ResourceLocation rl(String id) {
        return new ResourceLocation(Constants.ModId, id);
    }
    public static Item.Properties standard() {
        return new Item.Properties().tab(Constants.CLASSIC_TAB).fireResistant();
    }
}
