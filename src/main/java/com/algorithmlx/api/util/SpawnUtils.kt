package com.algorithmlx.api.util

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

// TODO: Very stupid, but I don't give a fuck
fun <T: Entity> EntityType<T>.spawn(player: Player): T {
    return (player.level as ServerLevel).spawn(this, player)
}

fun <T: Entity> ServerLevel.spawn(type: EntityType<T>, player: Player): T {
    val pos = BlockPos(player.x + 1, player.y + 1, player.z + 1)
    return this.spawn(type, player, pos)
}

fun <T: Entity> ServerLevel.spawn(type: EntityType<T>, player: Player?, pos: BlockPos): T {
    val stack: ItemStack? = player?.mainHandItem

    return this.spawn(type, stack, player, pos)
}

fun <T: Entity> ServerLevel.spawn(type: EntityType<T>, stack: ItemStack?, player: Player?, pos: BlockPos): T {
    return this.spawn(type, stack, player, pos, MobSpawnType.MOB_SUMMONED)
}

fun <T: Entity> ServerLevel.spawn(type: EntityType<T>, stack: ItemStack?, player: Player?, pos: BlockPos, spawnType: MobSpawnType): T {
    return this.spawn(type, stack, player, pos, spawnType, true)
}

fun <T: Entity> ServerLevel.spawn(type: EntityType<T>, stack: ItemStack?, player: Player?, pos: BlockPos, spawnType: MobSpawnType, shouldOffsetY: Boolean): T {
    return this.spawn(type, stack, player, pos, spawnType, shouldOffsetY, false)
}

fun <T: Entity> ServerLevel.spawn(type: EntityType<T>, stack: ItemStack?, player: Player?, pos: BlockPos, spawnType: MobSpawnType, shouldOffsetY: Boolean, shouldOffsetYMore: Boolean): T {
    return type.spawn(this, stack, player, pos, spawnType, shouldOffsetY, shouldOffsetYMore) as T
}

fun <T: Entity> ServerLevel.spawn(type: EntityType<T>, tag: CompoundTag?, component: Component?, player: Player?, pos: BlockPos, mobSpawnType: MobSpawnType, shouldOffsetY: Boolean, shouldOffsetYMore: Boolean) {
    type.spawn(this, tag, component, player, pos, mobSpawnType, shouldOffsetY, shouldOffsetYMore)
}
