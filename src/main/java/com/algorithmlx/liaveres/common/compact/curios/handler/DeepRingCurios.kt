package com.algorithmlx.liaveres.common.compact.curios.handler

import com.algorithmlx.liaveres.common.setup.LVRegister
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.ItemStack
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.type.capability.ICurio

class DeepRingCurios: ICurio {
    override fun getStack(): ItemStack = ItemStack(LVRegister.DEEP_RING.get())

    override fun canEquipFromUse(slotContext: SlotContext): Boolean = true

    override fun curioTick(slotContext: SlotContext) {
        val entity = slotContext.entity

        entity.addEffect(MobEffectInstance(MobEffects.NIGHT_VISION, 300))
        entity.addEffect(MobEffectInstance(MobEffects.REGENERATION))
        entity.addEffect(MobEffectInstance(MobEffects.WATER_BREATHING))
        entity.addEffect(MobEffectInstance(MobEffects.DIG_SPEED))
        entity.addEffect(MobEffectInstance(MobEffects.SATURATION))
        entity.addEffect(MobEffectInstance(MobEffects.DAMAGE_BOOST))
        entity.addEffect(MobEffectInstance(MobEffects.FIRE_RESISTANCE))
        entity.addEffect(MobEffectInstance(MobEffects.LUCK))
    }

    override fun canUnequip(slotContext: SlotContext): Boolean {
        val entity = slotContext.entity
        return entity.removeEffect(MobEffects.NIGHT_VISION)
                && entity.removeEffect(MobEffects.REGENERATION)
                && entity.removeEffect(MobEffects.WATER_BREATHING)
                && entity.removeEffect(MobEffects.DIG_SPEED)
                && entity.removeEffect(MobEffects.SATURATION)
                && entity.removeEffect(MobEffects.DAMAGE_BOOST)
                && entity.removeEffect(MobEffects.FIRE_RESISTANCE)
                && entity.removeEffect(MobEffects.LUCK)
    }
}