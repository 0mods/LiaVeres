package com.algorithmlx.liaveres.common.compact.curios

import com.algorithmlx.liaveres.common.compact.curios.handler.DeepRingCurios
import net.minecraft.core.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.common.util.NonNullSupplier
import org.jetbrains.annotations.NotNull
import top.theillusivec4.curios.api.CuriosCapability
import top.theillusivec4.curios.api.type.capability.ICurio

class LVCuriosCapabilities {
    val deepRingCurios = lazyCap { DeepRingCurios() }

    private fun curioCapProvider(lazy: LazyOptional<ICurio>): ICapabilityProvider = object : ICapabilityProvider {
        override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> =
            CuriosCapability.ITEM.orEmpty(cap, lazy)
    }

    private fun <T: ICurio> lazyGen(sup: NonNullSupplier<T>): LazyOptional<T> = LazyOptional.of(sup)

    private fun lazyCap(sup: NonNullSupplier<ICurio>) = curioCapProvider(lazyGen(sup))

    companion object {
        @JvmStatic
        @get:NotNull
        var instance: LVCuriosCapabilities? = null
            get() {
                if (field == null) field = LVCuriosCapabilities()
                return field!!
            }
            private set
    }
}
