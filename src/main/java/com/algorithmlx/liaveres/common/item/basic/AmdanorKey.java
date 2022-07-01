package com.algorithmlx.liaveres.common.item.basic;

import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.ModSetup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AmdanorKey extends Item {
    public AmdanorKey() {
        super(new Properties().fireResistant().tab(ModSetup.CLASSIC_TAB).rarity(Constants.getLegendary).stacksTo(1));
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
