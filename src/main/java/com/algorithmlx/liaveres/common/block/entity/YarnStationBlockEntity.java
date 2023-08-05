package com.algorithmlx.liaveres.common.block.entity;

import com.algorithmlx.api.util.entity.AbstractContainerBlockEntity;
import com.algorithmlx.api.util.tools.helper.StackHandler;
import com.algorithmlx.liaveres.common.menu.YarnStationContainerMenu;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("DuplicatedCode")
public class YarnStationBlockEntity extends AbstractContainerBlockEntity implements MenuProvider {
    private final BlockPos pos;
    private final StackHandler handler;

    public YarnStationBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(LVRegister.YARN_STATION_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.level = Minecraft.getInstance().level;
        this.pos = pWorldPosition;
        this.handler = createHandler(this::addChangesToBlockEntity);
    }

    @NotNull
    @Override
    public StackHandler getStackHandler() {
        return this.handler;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block." + Constants.ModId + ".yarn_station");
    }

    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory inv, Player player) {
        return new YarnStationContainerMenu(i, inv, player.level(), pos);
    }

    public static StackHandler createHandler() {
        return createHandler(null);
    }

    public static StackHandler createHandler(Runnable runnable) {
        return StackHandler.of(3, runnable, builder -> builder.setOutputs(1));
    }
}
