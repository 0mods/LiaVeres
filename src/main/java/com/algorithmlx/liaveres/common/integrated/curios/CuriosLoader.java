package com.algorithmlx.liaveres.common.integrated.curios;

import com.algorithmlx.liaveres.common.integrated.curios.capability.CapabilityHandler;
import com.algorithmlx.liaveres.common.setup.Constants;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

public class CuriosLoader {
    public static void createCurioSlots(final InterModEnqueueEvent event) {
        InterModComms.sendTo(Constants.CurioID, SlotTypeMessage.REGISTER_TYPE,
                ()-> SlotTypePreset.RING.getMessageBuilder().build());
        InterModComms.sendTo(Constants.CurioID, SlotTypeMessage.REGISTER_TYPE,
                ()-> SlotTypePreset.CURIO.getMessageBuilder().build());
    }

    public static ICapabilityProvider effectRingCapability() {
        return new CapabilityHandler();
    }
}
