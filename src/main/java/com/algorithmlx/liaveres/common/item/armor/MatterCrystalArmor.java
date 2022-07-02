package com.algorithmlx.liaveres.common.item.armor;

import com.algorithmlx.liaveres.common.item.material.LVArmorMaterial;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.Registration;
import liquid.objects.item.TickingArmor;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatterCrystalArmor extends TickingArmor {
    public MatterCrystalArmor(EquipmentSlot slot) {
        super(LVArmorMaterial.MATTER_CRYSTAL, slot, new Item.Properties().tab(Constants.CLASSIC_TAB).fireResistant());
    }

    @Override
    public void onArmorTick(ItemStack stack, Player player) {
        List<MobEffect> badEffects = new ArrayList<>();
        List<MobEffect> effects = new ArrayList<>();
        int values = new Random().nextInt(3);

        effects.add(MobEffects.NIGHT_VISION);
        effects.add(MobEffects.REGENERATION);
        effects.add(MobEffects.WATER_BREATHING);
        effects.add(MobEffects.DIG_SPEED);
        effects.add(MobEffects.SATURATION);
        effects.add(MobEffects.DAMAGE_BOOST);
        effects.add(MobEffects.FIRE_RESISTANCE);
        effects.add(MobEffects.LUCK);
        badEffects.add(MobEffects.BLINDNESS);
        badEffects.add(MobEffects.MOVEMENT_SLOWDOWN);
        badEffects.add(MobEffects.DIG_SLOWDOWN);
        badEffects.add(MobEffects.HARM);
        badEffects.add(MobEffects.CONFUSION);
        badEffects.add(MobEffects.HUNGER);
        badEffects.add(MobEffects.POISON);
        badEffects.add(MobEffects.WITHER);
        badEffects.add(MobEffects.LEVITATION);
        badEffects.add(MobEffects.UNLUCK);
        badEffects.add(MobEffects.WEAKNESS);

        ItemStack headSlot = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestSlot = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legsSlot = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feetSlot = player.getItemBySlot(EquipmentSlot.FEET);

        boolean isFullMCA =
                headSlot.getItem() == Registration.MATTER_CRYSTAL_HELMET &&
                        chestSlot.getItem() == Registration.MATTER_CRYSTAL_CHEST &&
                        legsSlot.getItem() == Registration.MATTER_CRYSTAL_LEGS &&
                        feetSlot.getItem() == Registration.MATTER_CRYSTAL_BOOTS;
        boolean isNullMCA =
                (headSlot.getItem() != Registration.MATTER_CRYSTAL_HELMET && headSlot.getItem() == null) &&
                        (chestSlot.getItem() != Registration.MATTER_CRYSTAL_CHEST && chestSlot.getItem() == null) &&
                        (legsSlot.getItem() != Registration.MATTER_CRYSTAL_LEGS && legsSlot.getItem() == null) &&
                        (feetSlot.getItem() != Registration.MATTER_CRYSTAL_BOOTS && feetSlot.getItem() == null);

        if (!isFullMCA && !player.isCreative()) {
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
        }

        if (isFullMCA) {
            player.getAbilities().mayfly = true;

            for (MobEffect effect : effects) {
                player.addEffect(new MobEffectInstance(effect, 300, 50, false, false));
            }

            for (MobEffect effect : badEffects) {
                if (player.getEffect(effect) != null) {
                    player.removeEffect(effect);
                }
            }
        }

        if (isNullMCA) {
            player.sendSystemMessage(Component.translatable("msg." + Constants.ModId + ".matter_crystal_armor.is_null." + values).withStyle(ChatFormatting.RED));
            for (MobEffect effect : effects) {
                if (player.getEffect(effect) != null) {
                    player.removeEffect(effect);
                }
            }
            for (MobEffect effect : badEffects) {
                player.addEffect(new MobEffectInstance(effect, 100, 0, false, false));
            }
        }
    }
}
