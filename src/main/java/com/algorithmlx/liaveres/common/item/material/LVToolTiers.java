package com.algorithmlx.liaveres.common.item.material;

import com.algorithmlx.liaveres.api.util.tools.InLineMaterial;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class LVToolTiers {
    public static Tier MATTER_CRYSTAL = InLineMaterial.item()
            .harvestLevel(Integer.MAX_VALUE)
            .maxUses(-1)
            .speed(Float.MAX_VALUE)
            .attackBonus(Float.MAX_VALUE)
            .enchant(Integer.MAX_VALUE)
            .build();

    public static Tier GILDED_NETHERITE = InLineMaterial.item()
            .harvestLevel(5)
            .maxUses(8124)
            .speed(36)
            .attackBonus(16)
            .enchant(60)
            .repair(() -> Ingredient.of(new ItemStack(LVRegister.GILDED_NETHERITE_INGOT.get(), 5)))
            .build();
}
