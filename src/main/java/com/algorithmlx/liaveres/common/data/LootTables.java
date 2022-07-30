package com.algorithmlx.liaveres.common.data;

import com.algorithmlx.liaveres.common.setup.Registration;
import liquid.objects.data.gen.LootTableProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ForgeRegistries;

public class LootTables extends LootTableProviderBase {
    public LootTables(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void addTables() {
        this.noBlockEntity(Registration.GILDED_NETHERITE_BLOCK.get());
    }

    private void noBlockEntity(Block block) {
        lootTable.put(block, createWithoutBlockEntity(ForgeRegistries.ITEMS.getKey(((ItemLike) block).asItem()).getPath(), block));
    }

    private void blockEntity(Block block, BlockEntityType<?> entity) {
        lootTable.put(block, createStandard(ForgeRegistries.ITEMS.getKey(((ItemLike) block).asItem()).getPath(), block, entity));
    }
}
