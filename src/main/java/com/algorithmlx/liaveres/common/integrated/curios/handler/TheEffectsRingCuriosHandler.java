package com.algorithmlx.liaveres.common.integrated.curios.handler;

import com.algorithmlx.liaveres.common.setup.Registration;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class TheEffectsRingCuriosHandler implements ICurio {
    @Override
    public ItemStack getStack() {
        return new ItemStack(Registration.THE_EFFECTS_RING.get());
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext) {
        return true;
    }

    @Override
    public void curioTick(SlotContext slotContext) {
        LivingEntity entity = slotContext.entity();

    }

    @Override
    public boolean canUnequip(SlotContext slotContext) {
        LivingEntity entity = slotContext.entity();

        return entity.removeEffect(MobEffects.NIGHT_VISION)
                && entity.removeEffect(MobEffects.REGENERATION)
                && entity.removeEffect(MobEffects.WATER_BREATHING)
                && entity.removeEffect(MobEffects.DIG_SPEED)
                && entity.removeEffect(MobEffects.SATURATION)
                && entity.removeEffect(MobEffects.DAMAGE_BOOST)
                && entity.removeEffect(MobEffects.FIRE_RESISTANCE)
                && entity.removeEffect(MobEffects.LUCK);
    }
}
