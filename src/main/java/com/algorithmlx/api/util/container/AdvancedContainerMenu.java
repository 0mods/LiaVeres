package com.algorithmlx.api.util.container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AdvancedContainerMenu extends AbstractContainerMenu {
    private final BlockPos blockPos;
    private final IItemHandler playerInventory;

    protected AdvancedContainerMenu(@Nullable MenuType<?> p_38851_, int p_38852_, BlockPos pos, Inventory playerInv) {
        super(p_38851_, p_38852_);
        this.playerInventory = new InvWrapper(playerInv);
        this.blockPos = pos;
    }

    public int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    public void addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int p_38942_) {
        return ItemStack.EMPTY;
    }

    public void playerSlots(int leftColX, int topRowY) {
        addSlotBox(playerInventory, 9, leftColX, topRowY, 9, 18, 3, 18);

        topRowY += 58;
        addSlotRange(playerInventory, 0, leftColX, topRowY, 9, 18);
    }

    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(this.getBlockPos().getX() + 0.5, this.getBlockPos().getY() + 0.5,
                this.getBlockPos().getZ() + 0.5) <= 64;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }
}
