package com.algorithmlx.liaveres.common.menu.slots;

import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class YarnInputSkeinSlot extends SlotItemHandler {
    public YarnInputSkeinSlot(IItemHandler pContainer, int pIndex, int pX, int pY) {
        super(pContainer, pIndex, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return pStack.getItem() == LVRegister.STRING_SKEIN.get();
    }
}
