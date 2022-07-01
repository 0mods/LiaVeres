package com.algorithmlx.liaveres.common.setup;

import liquid.LiquidCore;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.Curios;

public class Constants {
    private static final Rarity legendary = Rarity.create("legendary_" + Constants.ModId, ChatFormatting.GOLD);

    public static final Rarity getLegendary = Rarity.valueOf(legendary.name());

    public static final String ModId = "liaveres";
    public static final String ModName = "LiaVeres";
    public static final String CurioID = Curios.MODID;
    public static final String LiquidID = LiquidCore.ModId;
//    public static final String CTId = "crafttweaker";
}
