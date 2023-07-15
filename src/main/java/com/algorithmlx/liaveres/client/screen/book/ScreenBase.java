package com.algorithmlx.liaveres.client.screen.book;

import com.algorithmlx.liaveres.common.setup.Constants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public abstract class ScreenBase extends Screen {
    private static final int imgWidth = 512;
    private static final int imgHeight = 256;

    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.ModId, "textures/gui/lia_book.png");

    public ScreenBase(Component pTitle) {
        super(pTitle);
    }

    public abstract void texts();

    private GuiGraphics guiGraphics;

    @Override
    public void render(@NotNull GuiGraphics g, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(g, pMouseX, pMouseY, pPartialTick);
        guiGraphics = g;

        int j = ((this.width / 2) - (imgWidth / 2));
        int k = ((this.height / 2) - (imgHeight / 2));

        this.renderBackground(g);
        RenderSystem.setShaderTexture(0, TEXTURE);
        g.blit(TEXTURE, j, k, 0, 0, imgWidth, imgHeight, imgWidth, imgHeight);
        this.texts();
        super.render(g, 0, 0, pPartialTick);
    }

    public void text(String translatableText, int color, int x, int y) {
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("text." + Constants.ModId + "liaBook." + translatableText), x, y, color);
    }

    public Component translatableTextButton(String buttonName) {
        return Component.translatable("text." + Constants.ModId + ".button." + buttonName);
    }
}
