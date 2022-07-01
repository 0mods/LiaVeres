package com.algorithmlx.liaveres.common.block;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;

public class Crystallite extends Block {
    public Crystallite() {
        super(Properties.of(Material.METAL)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .lightLevel((blockState) -> 10)
                .strength(5f, Float.MAX_VALUE)
        );
    }

    @Override
    public @NotNull BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(BlockStateProperties.FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }
}
