package com.algorithmlx.api.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import kotlin.math.hypot

object BlockEntityHelper {
    @JvmStatic
    fun dispatchToPlayers(blockEntity: BlockEntity) {
        val level = blockEntity.level ?: return
        val packet = blockEntity.updatePacket ?: return

        val players = level.players()
        val pos = blockEntity.blockPos

        for (player in players) {
            if (player is ServerPlayer)
                if (playerMath(player.getX(), player.getZ(), pos.x + 0.5, pos.z + 0.5))
                    player.connection.send(packet)
        }
    }

    @JvmStatic
    fun dispatchToPlayers(level: Level, x: Int, y: Int, z: Int) {
        val blockEntity = level.getBlockEntity(BlockPos(x, y, z))
        if (blockEntity != null) dispatchToPlayers(blockEntity)
    }

    @JvmStatic
    private fun playerMath(x: Double, z: Double, x1: Double, z1: Double): Boolean = hypot(x - x1, z - z1) < 64
}