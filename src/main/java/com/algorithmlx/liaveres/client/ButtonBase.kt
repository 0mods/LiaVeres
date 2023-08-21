package com.algorithmlx.liaveres.client

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.components.Button
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack

class ButtonBase: Button {
    private val kx: Int
    private val ky: Int
    private val kwidth: Int
    private val kheight: Int
    private val text: Component
    val texture: ResourceLocation
    val stackedTexture: Boolean
    val stack: ItemStack

    constructor(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        component: Component,
        press: OnPress,
        reloc: ResourceLocation
    ): super(x, y, width, height, component, press) {
        this.kx = x
        this.ky = y
        this.kwidth = width
        this.kheight = height
        this.text = component
        this.texture = reloc
        this.stackedTexture = false
        this.stack = ItemStack.EMPTY
    }

    constructor(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        component: Component,
        press: OnPress,
        stack: ItemStack
    ): super(x, y, width, height, component, press) {
        this.kx = x
        this.ky = y
        this.kwidth = width
        this.kheight = height
        this.text = component
        this.texture = ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, "")
        this.stackedTexture = true
        this.stack = stack
    }

    override fun renderButton(stack: PoseStack, x: Int, y: Int, partialTick: Float) {
        val mc = Minecraft.getInstance()
        val ft = mc.font

        if (!this.stackedTexture) {
            stack.pushPose()
            stack.translate(0.0, 0.0, 100.0)
            ft.draw(stack, this.text, this.kx.toFloat(), (this.ky + this.kheight / 4).toFloat(), 0xFFFFFF)
            stack.popPose()

            RenderSystem.enableBlend()
            RenderSystem.defaultBlendFunc()

            RenderSystem.setShaderTexture(0, texture)
            blit(stack, this.kx, this.ky, 0F, if (isCursorAtButton(x, y)) this.kheight.toFloat() else 0F, this.kwidth, this.kheight, this.kwidth, this.kheight * 2)
        } else {
            stack.renderItem(this.stack, this.kx, this.ky)
        }
    }

    fun isCursorAtButton(cursorX: Int, cursorY: Int): Boolean {
        return cursorX >= x && cursorY >= y && cursorX <= x + width && cursorY <= y + height
    }
}