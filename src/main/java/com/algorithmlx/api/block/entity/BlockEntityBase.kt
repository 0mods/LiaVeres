package com.algorithmlx.api.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

open class BlockEntityBase(blockEntity: BlockEntityType<*>, pos: BlockPos, state: BlockState): BlockEntity(blockEntity, pos,
    state
) {
    override fun getUpdatePacket(): Packet<ClientGamePacketListener> =
        ClientboundBlockEntityDataPacket.create(this, BlockEntity::saveWithFullMetadata)

    override fun getUpdateTag(): CompoundTag = this.saveWithFullMetadata()

    fun addChangesToBlockEntity() {
        super.setChanged()
        BlockEntityHelper.playerDispatch(this)
    }
}