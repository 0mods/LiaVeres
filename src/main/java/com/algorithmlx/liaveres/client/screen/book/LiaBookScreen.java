package com.algorithmlx.liaveres.client.screen.book;

import com.algorithmlx.liaveres.client.screen.book.page.LiaBookInfoPage;
import com.algorithmlx.liaveres.client.screen.button.ButtonBase;
import com.algorithmlx.liaveres.common.setup.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("ConstantConditions")
@OnlyIn(Dist.CLIENT)
public class LiaBookScreen extends ScreenBase {
    public LiaBookScreen() {
        super(Component.translatable("book." + Constants.ModId + ".liaBook"));
    }

    @Override
    protected void init() {
        this.addRenderableWidget(new ButtonBase(37, 23, 16, 16,
                        pButton -> minecraft.setScreen(new LiaBookInfoPage()),
                        new ResourceLocation("")
                )
        );
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new LiaBookScreen());
    }

    @Override
    public void texts() {
        this.text("mainPage", 0x000000,275, 24);
        this.text("mainPage.1", 0x000000, 275, 34);
    }
}
