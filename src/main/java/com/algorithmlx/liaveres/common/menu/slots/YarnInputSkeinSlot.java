package com.algorithmlx.liaveres.common.menu.slots;

import com.algorithmlx.liaveres.common.setup.Registration;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class YarnInputSkeinSlot extends SlotItemHandler {
    public YarnInputSkeinSlot(IItemHandler pContainer, int pIndex, int pX, int pY) {
        super(pContainer, pIndex, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return pStack.getItem() == Registration.STRING_SKEIN.get();
    }
}
