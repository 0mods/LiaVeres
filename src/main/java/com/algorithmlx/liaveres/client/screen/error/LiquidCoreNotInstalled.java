package com.algorithmlx.liaveres.client.screen.error;

import com.algorithmlx.liaveres.common.setup.Constants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class LiquidCoreNotInstalled extends Screen {

    public LiquidCoreNotInstalled() {
        super(Component.translatable("error." + Constants.ModId + ".liquid"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(PoseStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
        super.render(p_96562_, p_96563_, p_96564_, p_96565_);

        drawCenteredString(p_96562_, this.font, this.title, this.width / 2, 16, 16777215);

        this.renderBackground(p_96562_);
        super.render(p_96562_, 0, 0, p_96565_);
    }
}
