package com.algorithmlx.liaveres.common.menu;

import com.algorithmlx.liaveres.common.menu.container.YarnCraftContainer;
import com.algorithmlx.liaveres.common.menu.container.YarnResultContainer;
import com.algorithmlx.liaveres.common.menu.slots.YarnInputSlot;
import com.algorithmlx.liaveres.common.menu.slots.YarnOutputResultSlot;
import com.algorithmlx.liaveres.common.recipe.RecipeTypes;
import com.algorithmlx.liaveres.common.setup.Registration;
import com.algorithmlx.liaveres.common.menu.slots.YarnInputSkeinSlot;
import liquid.objects.container.AdvancedContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;

@SuppressWarnings("all")
public class YarnStationContainerMenu extends AdvancedContainerMenu {
    private final BlockEntity blockEntity;
    private final YarnResultContainer resultSlot;

    public YarnStationContainerMenu(int windowId, Inventory inv, Level level, BlockPos pos) {
        super(Registration.YARN_STATION_CONTAINER.get(), windowId, inv);

        this.makeInventorySlots(8, 84);
        this.blockEntity = level.getBlockEntity(pos);
        YarnCraftContainer craftSlot = new YarnCraftContainer(this, 2);
        this.resultSlot = new YarnResultContainer();

        if (blockEntity != null) {
            this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                this.addSlot(new YarnInputSlot(h, 0, 25, 17));
                this.addSlot(new YarnInputSkeinSlot(h, 1, 25, 53));
            });
        }
        this.addSlot(new YarnOutputResultSlot(inv.player, craftSlot, resultSlot, 2, 134, 35));
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()),
                pPlayer, Registration.YARN_STATION.get());
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        var finalStack = ItemStack.EMPTY;
        var slot = this.slots.get(pIndex);
        var empty = ItemStack.EMPTY;

        if (slot != null && slot.hasItem()) {
            var stack = slot.getItem();
            finalStack = stack.copy();

            if (pIndex < 38)
                if (!this.moveItemStackTo(stack, 0, this.slots.size(), true))
                    return empty;

            if (!this.moveItemStackTo(stack, 1, this.slots.size(), false))
                return empty;

            if (stack.isEmpty()) slot.set(empty);
            else slot.setChanged();

            if (stack.getCount() == finalStack.getCount())
                return empty;

            slot.onTake(pPlayer, stack);
        }
        return finalStack;
    }
}
