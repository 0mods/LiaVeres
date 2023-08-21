package com.algorithmlx.liaveres.common.capability

import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken

object LVCapabilities {
    @JvmStatic
    val animation: Capability<AnimationCapability> = CapabilityManager.get(object : CapabilityToken<AnimationCapability>() {})
}