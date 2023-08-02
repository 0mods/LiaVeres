package com.algorithmlx.liaveres.client.screen.button;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class ButtonBase extends Button {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Component text;
    private final ResourceLocation texLocation;
    private final ItemStack stack;
    private final boolean textureFromStack;

    public ButtonBase(Builder builder, ResourceLocation texture) {
        super(builder);
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.x = this.getX();
        this.y = this.getY();
        this.text = this.getMessage();
        this.texLocation = texture;
        this.textureFromStack = false;
        this.stack = ItemStack.EMPTY;
    }

    public ButtonBase(Builder builder, ItemStack stack) {
        super(builder);
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.x = this.getX();
        this.y = this.getY();
        this.text = this.getMessage();
        this.texLocation = new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, "");
        this.textureFromStack = true;
        this.stack = stack;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int p_282682_, int p_281714_, float p_282542_) {
        Minecraft minecraft = Minecraft.getInstance();
        Font fr = minecraft.font;
        if (!this.textureFromStack) {
            PoseStack stack = graphics.pose();
            stack.pushPose();
            stack.translate(0.0D, 0.0D, 100.0D);
            graphics.drawString(fr, this.text, this.x, this.y + this.height / 4, 0xFFFFFF);
            stack.popPose();

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            RenderSystem.setShaderTexture(0, texLocation);

            graphics.blit(texLocation, this.x, this.y, 0, isCursorAtButton(x, y) ? this.height : 0, this.width, this.height, this.width, this.height * 2);
        } else {
            Lighting.setupFor3DItems();

            graphics.renderItem(this.stack, this.x + 2, this.y + 2);
            graphics.renderItemDecorations(fr, this.stack, this.x, this.y);
        }
    }

    public boolean isCursorAtButton(int cursorX, int cursorY) {
        return cursorX >= this.x && cursorY >= this.y && cursorX <= this.x + this.width && cursorY <= this.y + this.height;
    }
}
