package com.algorithmlx.liaveres.common.tags

import com.algorithmlx.liaveres.common.setup.Constants.reloc
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

object LVTags {
    lateinit var paxelMineable: TagKey<Block>

    @JvmStatic
    fun init() {
        paxelMineable = BlockTags.create(reloc("mineable/paxel"))
    }
}