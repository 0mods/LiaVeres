package com.algorithmlx.liaveres.common.recipe;

import com.algorithmlx.liaveres.api.recipe.LVAbstractRecipe;
import com.algorithmlx.liaveres.api.recipe.LVRecipeType;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeType;

@SuppressWarnings("SameParameterValue")
public class RecipeTypes {
    public static final RecipeType<YarnRecipe> YARN_RECIPE_TYPE = standardLiaVeresRecipe("yarn");

    private static <A extends Container, T extends LVAbstractRecipe<A>> LVRecipeType<A, T> standardLiaVeresRecipe(String recipeId) {
        return new LVRecipeType<>(recipeId);
    }
}
