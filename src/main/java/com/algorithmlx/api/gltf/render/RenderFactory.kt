package com.algorithmlx.api.gltf.render

import com.algorithmlx.liaveres.common.LiaVeres
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

object RenderFactory {
    fun <T: Entity> finalize(type: ()-> EntityType<T>, render: Class<EntityRenderer<T>>) {
        FMLJavaModLoadingContext.get().modEventBus.addListener<RegisterRenderers> {
            it.registerEntityRenderer(type()) { mgr ->
                try {
                    render.getConstructor(EntityRendererProvider.Context::class.java).newInstance(mgr)
                } catch (e: IllegalAccessException) {
                    LiaVeres.LOGGER.error("Enable to create render for ${type().descriptionId} with render ${render.name}")
                    throw e
                }
            }
        }
    }

    fun <T: BlockEntity> finalize(type: ()-> BlockEntityType<T>, render: Class<BlockEntityRenderer<T>>) {
        FMLJavaModLoadingContext.get().modEventBus.addListener<RegisterRenderers> {
            it.registerBlockEntityRenderer(type()) { mgr ->
                try {
                    render.getConstructor(BlockEntityRendererProvider.Context::class.java).newInstance(mgr)
                } catch (e: IllegalAccessException) {
                    LiaVeres.LOGGER.error("Enable to create block entity render for ${type().registryName.toString()} with render ${render.name}")
                    throw e
                }
            }
        }
    }
}
