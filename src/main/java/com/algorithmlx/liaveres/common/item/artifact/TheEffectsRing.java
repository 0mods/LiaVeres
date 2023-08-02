package com.algorithmlx.liaveres.common.item.artifact;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;

public class TheEffectsRing extends Item {
    public TheEffectsRing() {
        super(new Properties().fireResistant());
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {

    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
//        if (ModList.get().isLoaded(Constants.CurioID))
//            return CuriosLoader.effectRingCapability();
        return super.initCapabilities(stack, nbt);
    }
}
