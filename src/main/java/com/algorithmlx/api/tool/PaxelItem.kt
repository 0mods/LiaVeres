package com.algorithmlx.api.tool

import com.algorithmlx.liaveres.common.tags.LVTags
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.DiggerItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Tier
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.block.CampfireBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.ToolAction
import net.minecraftforge.common.ToolActions
import java.util.Optional

class PaxelItem(tier: Tier, properties: Properties): DiggerItem(
    4F,
    -3.5F,
    tier,
    LVTags.paxelMineable,
    properties
) {
    override fun canPerformAction(stack: ItemStack?, toolAction: ToolAction?): Boolean =
        ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction) || ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction) || ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction)

    override fun useOn(context: UseOnContext): InteractionResult {
        var result = useAxe(context)

        if (result == InteractionResult.PASS) {
            result = useShovel(context)
        }

        return result
    }

    private fun useAxe(context: UseOnContext): InteractionResult {
        val level = context.level
        val pos = context.clickedPos
        val player = context.player
        val stack = context.itemInHand
        val hand = context.hand
        val state = level.getBlockState(pos)

        val stripped = Optional.ofNullable(state.getToolModifiedState(context, ToolActions.AXE_STRIP, false))
        val scraped = Optional.ofNullable(state.getToolModifiedState(context, ToolActions.AXE_SCRAPE, false))
        val waxedOff = Optional.ofNullable(state.getToolModifiedState(context, ToolActions.AXE_WAX_OFF, false))

        var modified: Optional<BlockState> = Optional.empty()

        if (scraped.isPresent) {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1F, 1F)
            modified = stripped
        } else if (scraped.isPresent) {
            level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F)
            level.levelEvent(player, 3005, pos, 0)
            modified = waxedOff
        } else if (waxedOff.isPresent) {
            level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F)
            level.levelEvent(player, 3004, pos, 0)
            modified = waxedOff
        }

        if (modified.isPresent) {
            if (player is ServerPlayer) CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(player, pos, stack)

            level.setBlock(pos, modified.get(), 11)

            if (player != null) stack.hurtAndBreak(1, player) { e -> e.broadcastBreakEvent(hand) }

            return InteractionResult.sidedSuccess(level.isClientSide)
        }

        return InteractionResult.PASS
    }

    private fun useShovel(context: UseOnContext): InteractionResult {
        val level = context.level
        val pos = context.clickedPos
        val player = context.player
        val stack = context.itemInHand
        val hand = context.hand
        val state = level.getBlockState(pos)

        val modified = state.getToolModifiedState(context, ToolActions.SHOVEL_FLATTEN, false)

        var newState: BlockState? = null

        if (modified != null && level.isEmptyBlock(pos.above())) {
            level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1F, 1F)
            newState = modified
        } else if (state.block is CampfireBlock && state.getValue(CampfireBlock.LIT)) {
            if (!level.isClientSide) level.levelEvent(null, 1009, pos, 0)
            CampfireBlock.dowse(player, level, pos, state)
            newState = state.setValue(CampfireBlock.LIT, false)
        }

        if (newState != null) {
            if (!level.isClientSide) {
                level.setBlock(pos, newState, 11)
                if (player != null) stack.hurtAndBreak(1, player) { e -> e.broadcastBreakEvent(hand) }
            }

            return InteractionResult.sidedSuccess(level.isClientSide)
        }

        return InteractionResult.PASS
    }
}