package com.algorithmlx.api.block

import com.algorithmlx.api.block.entity.TickedBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class EntityBlockBase<T: BlockEntity>(private val blockEntity: (BlockPos, BlockState) -> T, properties: Properties): Block(properties), EntityBlock {
    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity = blockEntity.invoke(pos, state)

    @Suppress("UNCHECKED_CAST")
    override fun <T : BlockEntity?> getTicker(
        methodLevel: Level,
        methodState: BlockState,
        methodType: BlockEntityType<T>
    ): BlockEntityTicker<T> = BlockEntityTicker { level: Level, pos: BlockPos, state: BlockState, entity: T ->
        if (entity is TickedBlockEntity<*>) {
            if (level.isClientSide) (entity as TickedBlockEntity<T>).clientTick(level, pos, state, entity)
            else (entity as TickedBlockEntity<T>).serverTick(level, pos, state, entity)
        }
    }

    @Deprecated("Deprecated in java", ReplaceWith("use(context)", "net.minecraft.world.item.context.UseOnContext"))
    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): InteractionResult {
        val context = UseOnContext(level, player, hand, player.getItemInHand(hand), hitResult)
        return this.use(context)
    }

    fun use(context: UseOnContext): InteractionResult {
        return InteractionResult.PASS
    }

    fun onBreak(oldState: BlockState, level: Level, pos: BlockPos, newState: BlockState, isMoving: Boolean) {
        if (oldState.hasBlockEntity() && (!oldState.`is`(newState.block) || !newState.hasBlockEntity()))
            level.removeBlockEntity(pos)
    }

    @Deprecated("Deprecated in Java", ReplaceWith("onBreak(oldState, level, pos, newState, isMoving)"))
    override fun onRemove(
        oldState: BlockState,
        level: Level,
        pos: BlockPos,
        newState: BlockState,
        isMoving: Boolean
    ) {
        onBreak(oldState, level, pos, newState, isMoving)
    }
}