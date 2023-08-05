package com.algorithmlx.api.recipe;

import com.algorithmlx.api.util.tools.helper.IngredientHelper;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class LVAbstractRecipe implements Recipe<Container> {
    public final RecipeType<?> type;
    public final ResourceLocation id;
    public final Ingredient ingredient;
    public final ItemStack result;
    public final float experience;
    public final int time;

    public LVAbstractRecipe(RecipeType<?> type, ResourceLocation id, Ingredient ingredient, ItemStack result, float xp,
                            int time) {
        this.type = type;
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.experience = xp;
        this.time = time;
    }

    @Override
    public boolean matches(Container p_44002_, Level p_44003_) {
        return IngredientHelper.test(this.ingredient, p_44002_);
    }

    @Override
    public ItemStack assemble(Container p_44001_, RegistryAccess p_267165_) {
        return this.result.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonNullList = NonNullList.create();
        nonNullList.add(this.ingredient);
        return nonNullList;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return this.result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeType<?> getType() {
        return this.type;
    }
}
