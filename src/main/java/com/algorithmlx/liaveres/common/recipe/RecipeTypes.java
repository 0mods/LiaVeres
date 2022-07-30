package com.algorithmlx.liaveres.common.recipe;

import com.algorithmlx.liaveres.common.setup.Constants;
import liquid.recipes.LiquidRecipeType;
import liquid.recipes.LiquidRecipes;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

@SuppressWarnings("SameParameterValue")
public class RecipeTypes {
    public static final RecipeType<YarnRecipe> YARN_RECIPE_TYPE = standardLiaVeresRecipe("yarn");

    private static <T extends LiquidRecipes> LiquidRecipeType<T> standardLiaVeresRecipe(String recipeId) {
        return new LiquidRecipeType<>(Constants.ModId, recipeId);
    }
}
