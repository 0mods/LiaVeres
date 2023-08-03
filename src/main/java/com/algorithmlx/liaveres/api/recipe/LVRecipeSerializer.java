package com.algorithmlx.liaveres.api.recipe;

import com.algorithmlx.liaveres.api.util.tools.IngredientHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lombok.Getter;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class LVRecipeSerializer<B extends LVAbstractRecipe> implements RecipeSerializer<B> {
    @Getter private final SerializerFactory<B> serializerFactory;

    public LVRecipeSerializer(SerializerFactory<B> serializerFactory) {
        this.serializerFactory = serializerFactory;
    }

    @Override
    public B fromJson(ResourceLocation p_44103_, JsonObject p_44104_) {
        JsonElement jsonIngredient = GsonHelper.isArrayNode(p_44104_, "ingredients")
                ? GsonHelper.getAsJsonArray(p_44104_, "ingredients"): GsonHelper.getAsJsonObject(p_44104_, "ingredients");
        Ingredient ingredient = IngredientHelper.fromJson(jsonIngredient);
        ItemStack itemStack;

        if (!p_44104_.has("result"))
            throw new JsonSyntaxException("RECIPE CAN'T BEEN CREATED! MISSING THIS ARGUMENT: 'result'");

        if (p_44104_.get("result").isJsonObject())
            itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_44104_, "result"));
        else {
            String string = GsonHelper.getAsString(p_44104_, "result");
            ResourceLocation location = new ResourceLocation(string);
            itemStack = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(location)));
        }

        float xp = GsonHelper.getAsFloat(p_44104_, "xp", 0.0F);
        int time = GsonHelper.getAsInt(p_44104_, "time", 0);

        return this.getSerializerFactory().create(p_44103_, ingredient, itemStack, xp, time);
    }

    @Override
    public B fromNetwork(ResourceLocation p_44105_, FriendlyByteBuf p_44106_) {
        Ingredient ingredient = Ingredient.fromNetwork(p_44106_);
        ItemStack itemStack = p_44106_.readItem();
        float xp = p_44106_.readFloat();
        int time = p_44106_.readInt();
        return this.getSerializerFactory().create(p_44105_, ingredient, itemStack, xp, time);
    }

    @Override
    public void toNetwork(FriendlyByteBuf p_44101_, B p_44102_) {
        p_44102_.ingredient.toNetwork(p_44101_);
        p_44101_.writeItem(p_44102_.result);
        p_44101_.writeFloat(p_44102_.experience);
        p_44101_.writeVarInt(p_44102_.time);
    }

    public interface SerializerFactory<T extends LVAbstractRecipe> {
        T create(ResourceLocation id, Ingredient ingredient, ItemStack result, float xp, int time);
    }
}
