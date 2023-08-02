package com.algorithmlx.liaveres.api.util.tools;

import lombok.Builder;
import lombok.Getter;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

@Builder(builderClassName = "Building", buildMethodName = "of")
public class ContainedItemBuilder {
    @Getter int rowWidth;
    @Getter int rowHeight;
    @Getter @Builder.Default SoundEvent soundEvent = SoundEvents.ARMOR_EQUIP_CHAIN;
    @Getter @Builder.Default boolean isFireResistance = false;
}
