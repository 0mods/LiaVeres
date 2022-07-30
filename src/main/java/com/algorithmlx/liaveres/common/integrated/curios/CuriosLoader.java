package com.algorithmlx.liaveres.common.integrated.curios;

import com.algorithmlx.liaveres.common.integrated.curios.handler.TheEffectsRingCuriosHandler;
import com.algorithmlx.liaveres.common.setup.Constants;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosLoader {
    private static final LazyOptional<ICurio> effectRing = LazyOptional.of(TheEffectsRingCuriosHandler::new);

    public static void createCurioSlots(final InterModEnqueueEvent event) {
        InterModComms.sendTo(Constants.CurioID, SlotTypeMessage.REGISTER_TYPE,
                ()-> SlotTypePreset.RING.getMessageBuilder().build());
        InterModComms.sendTo(Constants.CurioID, SlotTypeMessage.REGISTER_TYPE,
                ()-> SlotTypePreset.CURIO.getMessageBuilder().build());
    }

    public static ICapabilityProvider effectRingCapability() {
        return new ICapabilityProvider() {
            @Override
            public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                return CuriosCapability.ITEM.orEmpty(cap, effectRing);
            }
        };
    }
}
