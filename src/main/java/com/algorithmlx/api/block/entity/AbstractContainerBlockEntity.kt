package com.algorithmlx.api.block.entity

import com.algorithmlx.api.util.tools.helper.StackHandler
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

abstract class AbstractContainerBlockEntity<T: AbstractContainerBlockEntity<T>>: BlockEntityBase<T> {
    constructor(
        blockEntity: BlockEntityType<T>,
        pos: BlockPos,
        state: BlockState,
        renderer: Class<BlockEntityRenderer<T>>
    ): super(blockEntity, pos, state, renderer)

    constructor(
        blockEntity: BlockEntityType<T>,
        pos: BlockPos,
        state: BlockState,
        render: Class<BlockEntityRenderer<T>>?,
        enableRenderRegistry: Boolean
    ): super(blockEntity, pos, state, render, enableRenderRegistry)

    constructor(blockEntity: BlockEntityType<T>, pos: BlockPos, state: BlockState): super(blockEntity, pos, state)

    private val itemCapability: LazyOptional<IItemHandler> = LazyOptional.of(::getStackHandler)

    abstract fun getStackHandler(): StackHandler

    override fun load(tag: CompoundTag) {
        super.load(tag)
        this.getStackHandler().deserializeNBT(tag)
    }

    override fun saveAdditional(tag: CompoundTag) {
        tag.merge(this.getStackHandler().serializeNBT())
    }

    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        if (!this.isRemoved && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, this.itemCapability)
        }

        return super.getCapability(cap, side)
    }

    fun usedByPlayer(player: Player): Boolean {
        val pos = this.blockPos
        return player.distanceToSqr(pos.x + 0.5, pos.y + 0.5, pos.z + 0.5) <= 64
    }
}