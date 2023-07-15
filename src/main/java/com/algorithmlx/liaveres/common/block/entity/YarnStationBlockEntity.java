package com.algorithmlx.liaveres.common.block.entity;

import com.algorithmlx.liaveres.api.util.entity.AbstractBlockEntity;
import com.algorithmlx.liaveres.api.util.tools.StackHelper;
import com.algorithmlx.liaveres.common.menu.YarnStationContainerMenu;
import com.algorithmlx.liaveres.common.menu.container.YarnResultContainer;
import com.algorithmlx.liaveres.common.recipe.RecipeTypes;
import com.algorithmlx.liaveres.common.recipe.YarnRecipe;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@SuppressWarnings("DuplicatedCode")
public class YarnStationBlockEntity extends AbstractBlockEntity implements MenuProvider {
    private final BlockPos pos;

    public YarnStationBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(LVRegister.YARN_STATION_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.level = Minecraft.getInstance().level;
        this.pos = pWorldPosition;
    }

    @Override
    public @NotNull ItemStackHandler getStackHandler() {
        return this.handler;
    }

    @Override
    public StackHelper load(Runnable runnable) {
        return new StackHelper(3, runnable);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block." + Constants.ModId + ".yarn_station");
    }

    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory inv, Player player) {
        return new YarnStationContainerMenu(i, inv, player.level(), pos);
    }

    @Override
    public void drops() {
        super.drops();
    }

    private static boolean hasRecipe(YarnStationBlockEntity entity) {
        Level level = entity.level;
        if (level == null) return false;
        YarnResultContainer inventory = new YarnResultContainer();
        for (int i = 0; i < entity.handler.getSlots(); i++) inventory.setItem(i, entity.handler.getStackInSlot(i));

        Optional<YarnRecipe> match = level.getRecipeManager()
                .getRecipeFor(RecipeTypes.YARN_RECIPE_TYPE, inventory, level);

        return match.isPresent();
    }

    private static void craftItem(YarnStationBlockEntity entity) {
        Level level = entity.level;
        if (level == null) return;
        YarnResultContainer inventory = new YarnResultContainer();
        for (int i = 0; i < entity.handler.getSlots(); i++) {
            inventory.setItem(i, entity.handler.getStackInSlot(i));
        }

        Optional<YarnRecipe> match = level.getRecipeManager()
                .getRecipeFor(RecipeTypes.YARN_RECIPE_TYPE, inventory, level);

        if(match.isPresent()) {
            entity.handler.extractItem(0, 1, false);
            entity.handler.extractItem(1, 1, false);
            entity.handler.setStackInSlot(2, new ItemStack(match.get().getResultItem(level.registryAccess()).getItem(),
                    entity.handler.getStackInSlot(2).getCount() + 1));

        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, YarnStationBlockEntity blockEntity) {
        if (hasRecipe(blockEntity)) {
            setChanged(level, pos, state);
            craftItem(blockEntity);
        } else {
            setChanged(level, pos, state);
        }
    }
}
