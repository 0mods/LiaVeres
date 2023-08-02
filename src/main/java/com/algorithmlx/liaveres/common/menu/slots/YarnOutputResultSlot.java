package com.algorithmlx.liaveres.common.menu.slots;

import com.algorithmlx.liaveres.common.menu.container.YarnResultContainer;
import com.algorithmlx.liaveres.common.recipe.RecipeTypes;
import com.algorithmlx.liaveres.common.recipe.YarnRecipe;
import com.algorithmlx.liaveres.common.menu.container.YarnCraftContainer;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class YarnOutputResultSlot extends Slot {
    private final YarnCraftContainer craftSlots;
    private final Player player;
    private int removeCount;

    public YarnOutputResultSlot(Player player, YarnCraftContainer slot, Container pContainer, int pIndex, int pX, int pY) {
        super(pContainer, pIndex, pX, pY);
        this.craftSlots = slot;
        this.player = player;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return false;
    }

    @Override
    public ItemStack remove(int pAmount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(pAmount, this.getItem().getCount());
        }

        return super.remove(pAmount);
    }

    @Override
    protected void onQuickCraft(@NotNull ItemStack pStack, int pAmount) {
        this.removeCount += pAmount;
        this.checkTakeAchievements(pStack);
    }

    @Override
    protected void onSwapCraft(int pNumItemsCrafted) {
        this.removeCount += pNumItemsCrafted;
    }

    @Override
    protected void checkTakeAchievements(@NotNull ItemStack pStack) {
        List<ItemStack> stacks = new ArrayList<>();
        stacks.add(pStack);

        if (this.removeCount > 0) {
            pStack.onCraftedBy(this.player.level(), this.player, this.removeCount);
            ForgeEventFactory.firePlayerCraftingEvent(this.player, pStack, this.craftSlots);
        }

        if (this.container instanceof RecipeHolder holder) {
            holder.awardUsedRecipes(this.player, stacks);
        }

        this.removeCount = 0;
    }

    @Override
    public void onTake(@NotNull Player pPlayer, @NotNull ItemStack pStack) {
        this.checkTakeAchievements(pStack);
        ForgeHooks.setCraftingPlayer(pPlayer);
        YarnResultContainer container1 = new YarnResultContainer();
        Optional<YarnRecipe> recipe = pPlayer.level().getRecipeManager().getRecipeFor(RecipeTypes.YARN_RECIPE_TYPE, container1, pPlayer.level());

        NonNullList<ItemStack> nonNullList = pPlayer.level().getRecipeManager().getRemainingItemsFor(RecipeTypes.YARN_RECIPE_TYPE, container1, pPlayer.level());
        ForgeHooks.setCraftingPlayer(null);
        for (int integer = 0; integer < nonNullList.size(); ++integer) {
            ItemStack original = this.craftSlots.getItem(integer);
            ItemStack itemFromList = nonNullList.get(integer);

            if (!original.isEmpty()) {
                int count = 1;
                if (recipe.isPresent()) {
                    count = recipe.get().getIngredients().get(0).getItems()[integer].getCount();
                }
                this.craftSlots.removeItemWithoutUpdate(integer, count);
                original = this.craftSlots.getItem(integer);
            }

            if (!itemFromList.isEmpty()) {
                if (original.isEmpty()) {
                    this.craftSlots.setItem(integer, itemFromList);
                } else if (ItemStack.isSameItemSameTags(original, itemFromList) && ItemStack.matches(original, itemFromList)) {
                    itemFromList.grow(original.getCount());
                    this.craftSlots.setItem(integer, itemFromList);
                } else if (!this.player.getInventory().add(itemFromList)) {
                    this.player.drop(itemFromList, false);
                }
            }
            this.craftSlots.setChanged();
        }
    }
}