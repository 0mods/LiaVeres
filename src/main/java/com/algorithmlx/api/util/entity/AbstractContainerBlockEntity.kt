package com.algorithmlx.api.util.entity

import com.algorithmlx.api.util.tools.helper.StackHandler
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.IItemHandler

abstract class AbstractContainerBlockEntity(blockEntity: BlockEntityType<*>, pos: BlockPos, state: BlockState) : BlockEntityBase(
    blockEntity, pos, state
) {
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
        if (!this.isRemoved && cap == ForgeCapabilities.ITEM_HANDLER) {
            return  ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, this.itemCapability)
        }

        return super.getCapability(cap, side)
    }

    fun usedByPlayer(player: Player): Boolean {
        val pos = this.blockPos
        return player.distanceToSqr(pos.x + 0.5, pos.y + 0.5, pos.z + 0.5) <= 64
    }
}