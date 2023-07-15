package com.algorithmlx.liaveres.common.block;

import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class AmdanorSpawner extends Block {
    public AmdanorSpawner() {
        super(Properties.of().strength(-1F, 340282356779733661637539395458142568447F).noOcclusion());
    }

    @Override
    public @NotNull BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(BlockStateProperties.FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        ItemStack mainItem = pPlayer.getMainHandItem();
        Item activator = LVRegister.AMDANOR_UNLOCKER_KEY.get();
        if (!pLevel.isClientSide) {
            if ((pLevel.getDifficulty() != Difficulty.PEACEFUL) && (mainItem.getItem() == activator && pHand == InteractionHand.MAIN_HAND)) {
                LVRegister.AMDANOR.get().spawn((ServerLevel) pLevel, mainItem, pPlayer, pPos, MobSpawnType.SPAWN_EGG, true, false);
                if (!pPlayer.isCreative()) {
                    mainItem.shrink(1);
                }
                pPlayer.sendSystemMessage(Component.translatable("msg." + Constants.ModId + ".amdanor.spawn.success"));
                return InteractionResult.SUCCESS;
            } else if ((pLevel.getDifficulty() == Difficulty.PEACEFUL) && (mainItem.getItem() == activator && pHand == InteractionHand.MAIN_HAND)) {
                pPlayer.sendSystemMessage(Component.translatable("msg." + Constants.ModId + ".amdanor.spawn.peaceful"));
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.SUCCESS;
    }
}
