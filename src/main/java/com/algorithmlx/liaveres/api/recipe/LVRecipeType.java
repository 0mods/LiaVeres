package com.algorithmlx.liaveres.api.recipe;

import com.algorithmlx.liaveres.common.setup.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeType;

public class LVRecipeType<A extends Container, T extends LVAbstractRecipe<A>> implements RecipeType<T> {
    private final ResourceLocation recipeLocation;

    public LVRecipeType(String recipeId) {
        recipeLocation = new ResourceLocation(Constants.ModId, recipeId);
    }

    @Override
    public String toString() {
        return recipeLocation.toString();
    }
}
