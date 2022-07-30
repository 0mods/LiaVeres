//package com.algorithmlx.liaveres.common.integrated.crafttweaker;
//
//import com.algorithmlx.liaveres.common.recipe.RecipeTypes;
//import com.algorithmlx.liaveres.common.recipe.YarnRecipe;
//import com.algorithmlx.liaveres.common.setup.Constants;
//import com.blamejared.crafttweaker.api.CraftTweakerAPI;
//import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
//import com.blamejared.crafttweaker.api.annotation.ZenRegister;
//import com.blamejared.crafttweaker.api.ingredient.IIngredient;
//import com.blamejared.crafttweaker.api.item.IItemStack;
//import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
//import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.crafting.RecipeType;
//import org.openzen.zencode.java.ZenCodeType;
//
//@IRecipeHandler.For(YarnRecipe.class)
//@ZenRegister
//@ZenCodeType.Name("mods.liaveres.yarn")
//public final class CTYarnRecipe implements IRecipeManager<YarnRecipe>, IRecipeHandler<YarnRecipe> {
//    @Override
//    public String dumpToCommandString(IRecipeManager manager, YarnRecipe recipe) {
//        return manager.getCommandString() + recipe.getId() + recipe.getResult() + recipe.getIngredients();
//    }
//
//    @Override
//    public RecipeType<YarnRecipe> getRecipeType() {
//        return RecipeTypes.YARN_RECIPE_TYPE;
//    }
//
//    @ZenCodeType.Method
//    public void addRecipe(String id, IItemStack output, IIngredient input, float xp, int time) {
//        id = fixRecipeName(id);
//        ResourceLocation rl = new ResourceLocation(Constants.CTId, id);
//
//        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, new YarnRecipe(
//                rl, input.asVanillaIngredient(), output.getInternal(), xp, time)));
//    }
//}
