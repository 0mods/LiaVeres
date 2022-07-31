package com.algorithmlx.liaveres.common.event;

import com.algorithmlx.liaveres.common.entity.Amdanor;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.Registration;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.ModId, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributes {
    @SubscribeEvent
    public static void registryEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(Registration.AMDANOR.get(), Amdanor.prepareAttributes().build());
    }
}
