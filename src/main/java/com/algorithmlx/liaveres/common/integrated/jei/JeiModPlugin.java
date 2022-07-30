package com.algorithmlx.liaveres.common.integrated.jei;

import com.algorithmlx.liaveres.common.LiaVeres;
import com.algorithmlx.liaveres.common.integrated.jei.YarnRecipeCategory;
import com.algorithmlx.liaveres.common.menu.container.YarnResultContainer;
import com.algorithmlx.liaveres.common.recipe.RecipeTypes;
import com.algorithmlx.liaveres.common.recipe.YarnRecipe;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.Registration;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

@JeiPlugin
public class JeiModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Constants.ModId, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new YarnRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel world = Minecraft.getInstance().level;
        if (world.isClientSide) {
            RecipeManager manager = world.getRecipeManager();

            registration.addRecipes(new YarnRecipeCategory(registration.getJeiHelpers().getGuiHelper()).getRecipeType(),
                    manager.getAllRecipesFor(RecipeTypes.YARN_RECIPE_TYPE));
        }
    }
}
