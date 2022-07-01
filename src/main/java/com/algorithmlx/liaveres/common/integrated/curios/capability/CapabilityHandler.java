package com.algorithmlx.liaveres.common.integrated.curios.capability;

import com.algorithmlx.liaveres.common.integrated.curios.handler.TheEffectsRingCuriosHandler;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CapabilityHandler implements ICapabilityProvider {
    private static final LazyOptional<ICurio> curioOpt1 = LazyOptional.of(TheEffectsRingCuriosHandler::new);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        CuriosCapability.ITEM.orEmpty(cap, curioOpt1);
        return this.getCapability(cap, side);
    }
}
