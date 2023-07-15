package com.algorithmlx.liaveres.common.setup;

import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class Constants {
    private static final Rarity legendaryReg = Rarity.create("legendary_" + Constants.ModId, ChatFormatting.GOLD);

    @Getter
    public static final Rarity legendary = Rarity.valueOf(legendaryReg.name());

    public static final String ModId = "liaveres";
    public static final String ModName = "LiaVeres";
//    public static final String CurioID = Curios.MODID;
//    public static final String LiquidID = LiquidCore.ModId;
//    public static final String CTId = "crafttweaker";
}
