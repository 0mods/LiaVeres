package com.algorithmlx.liaveres.common.menu.container;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.Nullable;

public class YarnResultContainer implements Container, RecipeHolder {
    private final NonNullList<ItemStack> itemStacks = NonNullList.withSize(3, ItemStack.EMPTY);
    @Nullable
    private Recipe<?> recipeUsed;

    @Override
    public int getContainerSize() {
        return itemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.itemStacks) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int pIndex) {
        return this.itemStacks.get(pIndex);
    }

    @Override
    public ItemStack removeItem(int pIndex, int pCount) {
        return ContainerHelper.takeItem(this.itemStacks, pIndex);
    }

    @Override
    public ItemStack removeItemNoUpdate(int pIndex) {
        return ContainerHelper.takeItem(this.itemStacks, pIndex);
    }

    @Override
    public void setItem(int pIndex, ItemStack pStack) {
        this.itemStacks.set(pIndex, pStack);
    }

    @Override
    public void setChanged() {}

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    @Override
    public void clearContent() {
        this.itemStacks.clear();
    }

    @Override
    public void setRecipeUsed(@Nullable Recipe<?> pRecipe) {
        this.recipeUsed = pRecipe;
    }

    @Nullable
    @Override
    public Recipe<?> getRecipeUsed() {
        return this.recipeUsed;
    }
}
