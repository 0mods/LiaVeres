package com.algorithmlx.liaveres.common.item.artifact;

import com.algorithmlx.liaveres.common.integrated.curios.CuriosLoader;
import com.algorithmlx.liaveres.common.setup.Constants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;

public class TheEffectsRing extends Item {
    public TheEffectsRing(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        if (ModList.get().isLoaded(Constants.CurioID))
            return CuriosLoader.effectRingCapability();
        return super.initCapabilities(stack, nbt);
    }
}
