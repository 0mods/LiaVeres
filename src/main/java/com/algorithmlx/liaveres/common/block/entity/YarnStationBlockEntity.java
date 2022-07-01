package com.algorithmlx.liaveres.common.block.entity;

import com.algorithmlx.liaveres.common.menu.YarnStationContainerMenu;
import com.algorithmlx.liaveres.common.recipe.RecipeTypes;
import com.algorithmlx.liaveres.common.recipe.YarnRecipe;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.Registration;
import liquid.helper.StackHelper;
import liquid.objects.block.entity.TickingBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class YarnStationBlockEntity extends TickingBlockEntity implements MenuProvider {
    private final BlockPos pos;

    public YarnStationBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(Registration.YARN_STATION_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
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
    public Component getDisplayName() {
        return Component.translatable("block." + Constants.ModId + ".yarn_station");
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inv, Player player) {
        return new YarnStationContainerMenu(i, inv, player.getLevel(), pos);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(handler.getSlots());
        for (int i = 0; i < handler.getSlots(); i++) {
            inventory.setItem(i, handler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    private static boolean hasRecipe(YarnStationBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.handler.getSlots());
        for (int i = 0; i < entity.handler.getSlots(); i++) {
            inventory.setItem(i, entity.handler.getStackInSlot(i));
        }

        Optional<YarnRecipe> match = level.getRecipeManager()
                .getRecipeFor(RecipeTypes.YARN_RECIPE_TYPE, inventory, level);

        return match.isPresent();
    }

    private static void craftItem(YarnStationBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.handler.getSlots());
        for (int i = 0; i < entity.handler.getSlots(); i++) {
            inventory.setItem(i, entity.handler.getStackInSlot(i));
        }

        Optional<YarnRecipe> match = level.getRecipeManager()
                .getRecipeFor(RecipeTypes.YARN_RECIPE_TYPE, inventory, level);

        if(match.isPresent()) {
            entity.handler.extractItem(0,1, false);
            entity.handler.extractItem(1,1, false);
            entity.handler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(),
                    entity.handler.getStackInSlot(3).getCount() + 1));

        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, TickingBlockEntity tickingBlockEntity) {
        YarnStationBlockEntity be = (YarnStationBlockEntity) tickingBlockEntity;
        if(hasRecipe(be)) {
            setChanged(level, blockPos, blockState);
            craftItem(be);
        } else {
            setChanged(level, blockPos, blockState);
        }
    }

    public static void tick0(Level level, BlockPos blockPos, BlockState blockState, YarnStationBlockEntity tickingBlockEntity) {
        if(hasRecipe(tickingBlockEntity)) {
            setChanged(level, blockPos, blockState);
            craftItem(tickingBlockEntity);
        } else {
            setChanged(level, blockPos, blockState);
        }
    }
}
