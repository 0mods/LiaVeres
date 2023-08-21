package com.algorithmlx.liaveres.common.capability

import com.algorithmlx.api.gltf.animations.core.AnimationType
import com.mojang.math.Vector3f
import kotlinx.serialization.Serializable
import net.minecraft.core.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional

@Serializable
class AnimationCapability {
    val animationsToStart = HashSet<String>()
    val animtaionsToStop = HashSet<String>()
    var model = ""
    var animations = HashMap<AnimationType, String>()
    var textures = HashMap<String, String>()
    var transform = Transform()

    class Provider: ICapabilityProvider {
        private var wrapper: AnimationCapability? = null
        private val lazy = LazyOptional.of(::createCap)

        private fun createCap(): AnimationCapability {
            if (this.wrapper == null) this.wrapper = AnimationCapability()
            return this.wrapper!!
        }

        override fun <T : Any?> getCapability(p0: Capability<T>, p1: Direction?): LazyOptional<T> {
            if (p0 == LVCapabilities.animation) return lazy.cast()
            return LazyOptional.empty()
        }
    }
}

@Serializable
data class Transform(
    var tX: Float = 0f,
    var tY: Float = 0f,
    var tZ: Float = 0f,
    var rX: Float = 0f,
    var rY: Float = 0f,
    var rZ: Float = 0f,
    var sX: Float = 1f,
    var sY: Float = 1f,
    var sZ: Float = 1f
) {
    fun vectorTransform() = Vector3f(tX, tY, tZ)
}