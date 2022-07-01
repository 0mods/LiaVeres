package com.algorithmlx.liaveres.client.screen.book.page;

import com.algorithmlx.liaveres.client.screen.book.ScreenBase;
import com.algorithmlx.liaveres.common.setup.Constants;
import net.minecraft.network.chat.Component;

public class LiaBookInfoPage extends ScreenBase {
    public LiaBookInfoPage() {
        super(Component.translatable("book." + Constants.ModId + ".liaBook"));
    }

    @Override
    public void texts() {

    }
}
