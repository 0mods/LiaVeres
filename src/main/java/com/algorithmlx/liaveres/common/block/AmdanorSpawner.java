package com.algorithmlx.liaveres.common.block;

import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class AmdanorSpawner extends Block {
    public AmdanorSpawner() {
        super(Properties.of(Material.METAL)
                .strength(-1F, Float.MAX_VALUE)
                .noOcclusion()
        );
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return defaultBlockState().setValue(BlockStateProperties.FACING, blockPlaceContext.getNearestLookingDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack mainItem = player.getMainHandItem();

        if (!level.isClientSide()) {
            if ((level.getDifficulty() != Difficulty.PEACEFUL) && (mainItem.getItem() == Registration.AMDANOR_KEY && interactionHand == InteractionHand.MAIN_HAND)) {
                Registration.AMDANOR.spawn((ServerLevel) level, mainItem, player, new BlockPos(blockPos.getX() + 1, blockPos.getY() - 2, blockPos.getZ()), MobSpawnType.SPAWN_EGG, true, false);
                if (!player.isCreative())
                    mainItem.shrink(1);

                player.sendSystemMessage(Component.translatable("msg." + Constants.ModId + ".amdanor.spawn.success"));
                return InteractionResult.SUCCESS;
            }

            if ((level.getDifficulty() == Difficulty.PEACEFUL) && (mainItem.getItem() == Registration.AMDANOR_KEY && interactionHand == InteractionHand.MAIN_HAND)) {
                player.sendSystemMessage(Component.translatable("msg." + Constants.ModId + ".amdanor.spawn.fail"));
                return InteractionResult.FAIL;
            }
        }

        return InteractionResult.PASS;
    }
}
