package com.algorithmlx.liaveres.common.integrated.jei;

import com.algorithmlx.liaveres.common.recipe.YarnRecipe;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class YarnRecipeCategory implements IRecipeCategory<YarnRecipe> {
    public static final RecipeType<YarnRecipe> RECIPE_TYPE = RecipeType.create(Constants.ModId, "yarn_recipe", YarnRecipe.class);
    private final IDrawable bg;
    private final IDrawable icon;

    public YarnRecipeCategory(IGuiHelper helper) {
        this.bg = helper.createDrawable(new ResourceLocation(Constants.ModId, "textures/gui/jei.png"), 0, 0, 144, 81);
        this.icon = helper.createDrawableItemStack(new ItemStack(LVRegister.YARN_STATION.get()));
    }

    @Override
    public RecipeType<YarnRecipe> getRecipeType() {
        return RecipeType.create(Constants.ModId, "yarn_station", YarnRecipe.class);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei." + Constants.ModId + ".yarn_recipe_jei");
    }

    @Override
    public IDrawable getBackground() {
        return this.bg;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, YarnRecipe recipe, IFocusGroup focuses) {
        var result = recipe.result;
        var ingredients = recipe.getIngredients();

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(ingredients.get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 19).addIngredients(ingredients.get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 20).addItemStack(result);
    }
}
