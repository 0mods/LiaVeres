package com.algorithmlx.api.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

interface TickedBlockEntity<T: BlockEntity?> {
    fun clientTick(level: Level, pos: BlockPos, state: BlockState, blockEntity: T) {}

    fun serverTick(level: Level, pos: BlockPos, state: BlockState, blockEntity: T) {}
}