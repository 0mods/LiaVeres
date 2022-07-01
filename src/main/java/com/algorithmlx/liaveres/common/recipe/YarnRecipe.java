package com.algorithmlx.liaveres.common.recipe;

import com.algorithmlx.liaveres.common.setup.Registration;
import liquid.recipes.LiquidRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class YarnRecipe extends LiquidRecipes {
    protected final Ingredient ingredient;
    public YarnRecipe(ResourceLocation idIn, Ingredient ingredientIn, ItemStack resultIn, float xp, int time) {
        super(RecipeTypes.YARN_RECIPE_TYPE, idIn, ingredientIn,  resultIn, xp,time);

        this.ingredient = ingredientIn;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registration.YARN_RECIPE.get();
    }
}
