package com.algorithmlx.liaveres.common.setup.config

import com.algorithmlx.api.config.Config

object LVCommon {
    @Config(
        comment = """
            Lia Pickaxe excavation radius.
            It working like this:
            16 * 2 = 32 - pickaxe excavation radius
        """
    )
    var pickaxeRadius: Int = (16).coerceAtLeast(3).coerceAtMost(32)
}