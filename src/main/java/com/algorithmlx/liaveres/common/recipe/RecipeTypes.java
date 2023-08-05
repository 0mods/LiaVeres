package com.algorithmlx.liaveres.common.recipe;

import com.algorithmlx.api.recipe.LVAbstractRecipe;
import com.algorithmlx.api.recipe.LVRecipeType;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeType;

@SuppressWarnings("SameParameterValue")
public class RecipeTypes {
    public static final RecipeType<YarnRecipe> YARN_RECIPE_TYPE = standardLiaVeresRecipe("yarn");

    private static <T extends LVAbstractRecipe> LVRecipeType< T> standardLiaVeresRecipe(String recipeId) {
        return new LVRecipeType<>(recipeId);
    }
}
