package com.algorithmlx.liaveres.client

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.world.item.ItemStack

object RenderHelper {
    fun transferStackToGL(stack: PoseStack, runnable: Runnable) {
        val stack1 = RenderSystem.getModelViewStack()
        stack1.pushPose()
        stack1.mulPoseMatrix(stack.last().pose())
        RenderSystem.applyModelViewMatrix()
        runnable.run()
        stack1.popPose()
        RenderSystem.applyModelViewMatrix()
    }
}

fun PoseStack.renderItem(stack: ItemStack, x: Int, y: Int) {
    RenderHelper.transferStackToGL(this) {
        Minecraft.getInstance().itemRenderer.renderAndDecorateItem(stack, x, y)
    }
}