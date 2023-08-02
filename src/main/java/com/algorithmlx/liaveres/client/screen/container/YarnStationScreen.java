package com.algorithmlx.liaveres.client.screen.container;

import com.algorithmlx.liaveres.common.menu.YarnStationContainerMenu;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class YarnStationScreen extends AbstractContainerScreen<YarnStationContainerMenu> {
    public YarnStationScreen(YarnStationContainerMenu pMenu, Inventory pPlayerInventory, Component component) {
        super(pMenu, pPlayerInventory, component);
    }

    @Override
    public void render(GuiGraphics p_283479_, int p_283661_, int p_281248_, float p_281886_) {
        this.renderBackground(p_283479_);
        this.renderBg(p_283479_, p_281886_, p_283661_, p_281248_);
        super.render(p_283479_, p_283661_, p_281248_, p_281886_);
        this.renderTooltip(p_283479_, p_283661_, p_281248_);
    }

    @Override
    protected void renderBg(GuiGraphics p_283065_, float p_97788_, int p_97789_, int p_97790_) {

    }
}
