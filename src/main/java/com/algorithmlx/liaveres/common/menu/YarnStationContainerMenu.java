package com.algorithmlx.liaveres.common.menu;

import com.algorithmlx.liaveres.api.util.container.AdvancedContainerMenu;
import com.algorithmlx.liaveres.common.menu.container.YarnCraftContainer;
import com.algorithmlx.liaveres.common.menu.container.YarnResultContainer;
import com.algorithmlx.liaveres.common.menu.slots.YarnInputSlot;
import com.algorithmlx.liaveres.common.menu.slots.YarnOutputResultSlot;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import com.algorithmlx.liaveres.common.menu.slots.YarnInputSkeinSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

@SuppressWarnings("all")
public class YarnStationContainerMenu extends AdvancedContainerMenu {
    private final BlockEntity blockEntity;
    private final YarnResultContainer resultSlot;
    private final Inventory inv;

    public YarnStationContainerMenu(int windowId, Inventory inv, Level level, BlockPos pos) {
        super(LVRegister.YARN_STATION_CONTAINER.get(), windowId, inv);

        this.inv = inv;

        this.makeInventorySlots(8, 84);
        this.blockEntity = level.getBlockEntity(pos);
        YarnCraftContainer craftSlot = new YarnCraftContainer(this, 2);
        this.resultSlot = new YarnResultContainer();

        if (blockEntity != null) {
            this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
                this.addSlot(new YarnInputSlot(h, 0, 25, 17));
                this.addSlot(new YarnInputSkeinSlot(h, 1, 25, 53));
            });
        }
        this.addSlot(new YarnOutputResultSlot(inv.player, craftSlot, resultSlot, 2, 134, 35));
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()),
                pPlayer, LVRegister.YARN_STATION.get());
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack startStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);

        if (slot != null && slot.hasItem()) {
            ItemStack original = slot.getItem();
            startStack = original.copy();

            if (pIndex < this.inv.getContainerSize())
                if (!this.moveItemStackTo(original, this.inv.getContainerSize(), this.slots.size(), true))
                    return ItemStack.EMPTY;
            else if (!this.moveItemStackTo(original, 0, this.inv.getContainerSize(), false)) return ItemStack.EMPTY;

            if (original.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }

        return startStack;
    }
}
