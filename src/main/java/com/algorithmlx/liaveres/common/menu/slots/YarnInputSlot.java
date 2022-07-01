package com.algorithmlx.liaveres.common.menu.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class YarnInputSlot extends SlotItemHandler {
    public YarnInputSlot(IItemHandler pContainer, int pIndex, int pX, int pY) {
        super(pContainer, pIndex, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return true;
    }
}
