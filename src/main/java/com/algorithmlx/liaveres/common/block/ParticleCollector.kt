package com.algorithmlx.liaveres.common.block

import com.algorithmlx.api.util.spawn
import com.algorithmlx.liaveres.common.setup.LVRegister
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.world.Difficulty
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.material.Material
import net.minecraft.world.phys.BlockHitResult

class ParticleCollector: Block(Properties.of(Material.HEAVY_METAL).strength(-1F, Float.MAX_VALUE).noOcclusion()) {
    override fun getStateForPlacement(context: BlockPlaceContext): BlockState = this.defaultBlockState()
        .setValue(BlockStateProperties.FACING, context.nearestLookingDirection)

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(BlockStateProperties.FACING)
    }

    @Deprecated("Deprecated in Java", ReplaceWith("InteractionResult.PASS", "net.minecraft.world.InteractionResult"))
    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): InteractionResult {
        val mainItem = player.mainHandItem
        val item = mainItem.item

        if (!level.isClientSide) {
            if (item == LVRegister.AMDANOR_UNLOCKER_KEY.get()) {
                if (level.difficulty != Difficulty.PEACEFUL) {
                    runBlocking {
                        this.async {
                            player.translate("msg.liaveres.amdanor.spawn.success.1")
                            delay(1)
                            player.translate("msg.liaveres.amdanor.spawn.success.2")
                            delay(2)
                            player.translate("msg.liaveres.amdanor.spawn.success.3")
                            delay(3)
                            player.translate("msg.liaveres.amdanor.spawn.success.4")
                            delay(4)
                            player.translate("msg.liaveres.amdanor.spawn.success.5")
                            delay(5)
                            player.translate("msg.liaveres.amdanor.spawn.success.6")
                            delay(6)
                            LVRegister.AMDANOR.get().spawn(player)
                        }.await()
                    }
                } else player.translate("msg.liaveres.amdanor.spawn.peaceful")
            }
        }

        return InteractionResult.PASS
    }

    private fun Player.translate(text: String) {
        this.translate(TranslatableComponent(text))
    }

    private fun Player.translate(text: TranslatableComponent) {
        this.sendMessage(text)
    }

    private fun Player.sendMessage(text: Component) {
        this.sendMessage(text, this.uuid)
    }
}