package com.algorithmlx.liaveres.common.menu.slots;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class YarnOutputResultSlot extends SlotItemHandler {

    public YarnOutputResultSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }
}