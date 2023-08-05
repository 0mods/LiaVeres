package com.algorithmlx.liaveres.common.menu;

import com.algorithmlx.api.util.container.AdvancedContainerMenu;
import com.algorithmlx.api.util.tools.helper.StackHandler;
import com.algorithmlx.liaveres.common.block.entity.YarnStationBlockEntity;
import com.algorithmlx.liaveres.common.menu.slots.YarnInputSlot;
import com.algorithmlx.liaveres.common.menu.slots.YarnOutputResultSlot;
import com.algorithmlx.liaveres.common.recipe.RecipeTypes;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import com.algorithmlx.liaveres.common.menu.slots.YarnInputSkeinSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("all")
public class YarnStationContainerMenu extends AdvancedContainerMenu {
    private final BlockEntity blockEntity;
    private final Inventory inv;
    private final StackHandler handler;
    private final ContainerLevelAccess access;

    public YarnStationContainerMenu(int windowId, Inventory playerSlots, Level level, BlockPos pos) {
        this(windowId, playerSlots, YarnStationBlockEntity.createHandler(), level, pos, ContainerLevelAccess.create(level, pos));
    }

    public YarnStationContainerMenu(int windowId, Inventory inv, StackHandler h, Level level, BlockPos pos, ContainerLevelAccess access) {
        super(LVRegister.YARN_STATION_CONTAINER.get(), windowId, pos, inv);

        this.inv = inv;
        this.handler = h;
        this.access = access;
        this.blockEntity = level.getBlockEntity(pos);

        this.playerSlots(8, 84);
        this.addSlot(new YarnInputSlot(h, 0, 25, 17));
        this.addSlot(new YarnInputSkeinSlot(h, 1, 25, 53));
        this.addSlot(new YarnOutputResultSlot(h, 2, 134, 35));
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return super.stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()),
                pPlayer, LVRegister.YARN_STATION.get());
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
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

    @Override
    public void slotsChanged(Container p_38868_) {
        this.access.execute((level, _u)-> changeContainer(this, level, this.inv.player));
    }

    private static void changeContainer(YarnStationContainerMenu container, Level level, Player player) {
        if (!level.isClientSide()) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            ItemStack stack = ItemStack.EMPTY;
            var recipe = level.getRecipeManager().getRecipeFor(RecipeTypes.YARN_RECIPE_TYPE, container.handler.toContainer(), level)
                    .orElse(null);

            ItemStack itemStack = recipe.assemble(container.handler.toContainer(), level.registryAccess());
            if (itemStack.isItemEnabled(level.enabledFeatures())) stack = itemStack;

            container.handler.setStackInSlot(2, stack);
            container.setRemoteSlot(2, stack);
            serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(container.containerId, container.incrementStateId(), 0, stack));
        }
    }
}
