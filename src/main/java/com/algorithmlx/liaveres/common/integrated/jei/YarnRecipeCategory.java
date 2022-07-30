package com.algorithmlx.liaveres.common.integrated.jei;

import com.algorithmlx.liaveres.common.LiaVeres;
import com.algorithmlx.liaveres.common.recipe.RecipeTypes;
import com.algorithmlx.liaveres.common.recipe.YarnRecipe;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.Registration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("all")
public class YarnRecipeCategory implements IRecipeCategory<YarnRecipe> {
    private final IDrawable bg;
    private final IDrawable icon;

    public YarnRecipeCategory(IGuiHelper helper) {
        this.bg = helper.createDrawable(new ResourceLocation(Constants.ModId, "textures/gui/jei.png"), 0, 0, 144, 81);
        this.icon = helper.createDrawableItemStack(new ItemStack(Registration.YARN_STATION.get()));
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
    }

    //import com.algorithmlx.liaveres.common.recipe.YarnRecipe;
//import com.algorithmlx.liaveres.common.setup.Constants;
//import com.algorithmlx.liaveres.common.setup.Registration;
//import mezz.jei.api.constants.VanillaTypes;
//import mezz.jei.api.gui.IRecipeLayout;
//import mezz.jei.api.gui.drawable.IDrawable;
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.ingredients.IIngredients;
//import mezz.jei.api.recipe.category.IRecipeCategory;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.chat.TranslatableComponent;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//
//@SuppressWarnings("all")
//public class YarnRecipeCategory implements IRecipeCategory<YarnRecipe> {
//    private final IDrawable bg;
//    private final IDrawable icon;
//
//    public YarnRecipeCategory(IGuiHelper helper) {
//        this.bg = helper.createDrawable(new ResourceLocation(Constants.ModId, "textures/gui/jei.png"), 0, 0, 144, 81);
//        this.icon = helper.createDrawableIngredient(new ItemStack(Registration.YARN_STATION.get()));
//    }
//
//    @Override
//    public Component getTitle() {
//        return new TranslatableComponent("jei." + Constants.ModId + ".yarn_recipe_jei");
//    }
//
//    @Override
//    public IDrawable getBackground() {
//        return bg;
//    }
//
//    @Override
//    public IDrawable getIcon() {
//        return null;
//    }
//
//    @Override
//    public ResourceLocation getUid() {
//        return new ResourceLocation(Constants.ModId, "jei_yarn");
//    }
//
//    @Override
//    public Class<? extends YarnRecipe> getRecipeClass() {
//        return YarnRecipe.class;
//    }
//
//    @Override
//    public void setIngredients(YarnRecipe recipe, IIngredients ingredients) {
//        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
//        ingredients.setInputIngredients(recipe.getIngredients());
//    }
//
//    @Override
//    public void setRecipe(IRecipeLayout recipeLayout, YarnRecipe recipe, IIngredients ingredients) {
//        var stacks = recipeLayout.getItemStacks();
//        var inputs = ingredients.getInputs(VanillaTypes.ITEM);
//        var outputs = ingredients.getOutputs(VanillaTypes.ITEM);
//    }
//}

}
