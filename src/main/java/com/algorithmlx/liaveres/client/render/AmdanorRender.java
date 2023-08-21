package com.algorithmlx.liaveres.client.render;

import com.algorithmlx.liaveres.client.model.AmdanorModel;
import com.algorithmlx.liaveres.common.entity.Amdanor;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ResourceLocationException;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class AmdanorRender extends HumanoidMobRenderer<Amdanor, AmdanorModel<Amdanor>> {
    public static final float mobScale = 0.75F;
    private static final ResourceLocation MOB_TEXTURE = new ResourceLocation(Constants.ModId, "textures/entity/amdanor_skeleton.png");

    public AmdanorRender(EntityRendererProvider.Context context) {
        this(context, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }
    public AmdanorRender(EntityRendererProvider.Context context, ModelLayerLocation location, ModelLayerLocation location1, ModelLayerLocation location2) {
        super(context, new AmdanorModel<>(context.bakeLayer(location)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new AmdanorModel<>(context.bakeLayer(location1)), new AmdanorModel<>(context.bakeLayer(location2))));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Amdanor entity) {
        if (entity.getType() == LVRegister.AMDANOR.get()) {
            return MOB_TEXTURE;
        } else throw new ResourceLocationException("Texture can't been bound to other entity!");
    }

    @Override
    protected void scale(@NotNull Amdanor mob, PoseStack poseStack, float partialTickTime) {
        poseStack.scale(mobScale, mobScale, mobScale);
    }
}
