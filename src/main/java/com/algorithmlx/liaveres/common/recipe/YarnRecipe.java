package com.algorithmlx.liaveres.common.recipe;

import com.algorithmlx.liaveres.api.recipe.LVAbstractRecipe;
import com.algorithmlx.liaveres.common.menu.container.YarnResultContainer;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

@MethodsReturnNonnullByDefault
public class YarnRecipe extends LVAbstractRecipe<YarnResultContainer> {
    protected final Ingredient ingredient;
    public YarnRecipe(ResourceLocation idIn, Ingredient ingredientIn, ItemStack resultIn, float xp, int time) {
        super(RecipeTypes.YARN_RECIPE_TYPE, idIn, ingredientIn,  resultIn, xp,time);

        this.ingredient = ingredientIn;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return LVRegister.YARN_RECIPE.get();
    }
}
