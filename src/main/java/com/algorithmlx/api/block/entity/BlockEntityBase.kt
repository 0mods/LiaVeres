package com.algorithmlx.api.block.entity

import com.algorithmlx.api.gltf.render.RenderFactory
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.fml.loading.FMLEnvironment

open class BlockEntityBase<T: BlockEntityBase<T>>: BlockEntity {
    constructor(blockEntity: BlockEntityType<T>, pos: BlockPos, state: BlockState, render: Class<BlockEntityRenderer<T>>?, enableRenderRegistry: Boolean):
            super(blockEntity, pos, state) {
                if (enableRenderRegistry && FMLEnvironment.dist.isClient) RenderFactory.finalize({ blockEntity }, render?: return)
            }

    constructor(be: BlockEntityType<T>, pos: BlockPos, state: BlockState): this(be, pos, state, null, false)

    constructor(be: BlockEntityType<T>, pos: BlockPos, state: BlockState, render: Class<BlockEntityRenderer<T>>):
            this(be, pos, state, render, true)

    override fun getUpdatePacket(): Packet<ClientGamePacketListener> =
        ClientboundBlockEntityDataPacket.create(this, BlockEntity::saveWithFullMetadata)

    override fun getUpdateTag(): CompoundTag = this.saveWithFullMetadata()

    fun addChangesToBlockEntity() {
        super.setChanged()
        BlockEntityHelper.dispatchToPlayers(this)
    }
}