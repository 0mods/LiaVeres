package com.algorithmlx.liaveres.common.item.artifact;

import com.algorithmlx.liaveres.common.setup.Constants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DeepRing extends Item {
    public DeepRing() {
        super(new Properties().fireResistant());
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int p_41407_, boolean p_41408_) {
        if (!ModList.get().isLoaded(Constants.CurioID)) {
            if (entity instanceof Player player) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300));
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION));
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED));
                player.addEffect(new MobEffectInstance(MobEffects.SATURATION));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST));
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE));
                player.addEffect(new MobEffectInstance(MobEffects.LUCK));
            }
        }
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
//        if (ModList.get().isLoaded(Constants.CurioID))
//            return CuriosLoader.effectRingCapability();
        return super.initCapabilities(stack, nbt);
    }
}
