package com.algorithmlx.liaveres.common.item.armor;

import com.algorithmlx.liaveres.common.item.material.LVArmorMaterials;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.ModSetup;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GildedNetheriteArmor extends ArmorItem {
    public GildedNetheriteArmor(EquipmentSlot pSlot) {
        super(LVArmorMaterials.GILDED_NETHERITE, pSlot, new Properties().rarity(Constants.getLegendary()).fireResistant());
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);

        boolean isFullGoldenNetheriteArmor =
                head.getItem() == LVRegister.GILDED_NETHERITE_HELMET.get() &&
                chest.getItem() == LVRegister.GILDED_NETHERITE_CHESTPLATE.get() &&
                legs.getItem() == LVRegister.GILDED_NETHERITE_LEGS.get() &&
                feet.getItem() == LVRegister.GILDED_NETHERITE_BOOTS.get();
        if (isFullGoldenNetheriteArmor) {
            MobEffectInstance effectInstance;
            MobEffectInstance effectInstance1;
            effectInstance1 = new MobEffectInstance(MobEffects.FIRE_RESISTANCE);
            effectInstance = new MobEffectInstance(MobEffects.HEAL);
            player.addEffect(effectInstance);
            player.addEffect(effectInstance1);
        }
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlot slot, String layer) {
        if (slot == EquipmentSlot.LEGS) {
            return Constants.ModId + ":textures/armor/golden_netherite/2.png";
        } else {
            return Constants.ModId + ":textures/armor/golden_netherite/1.png";
        }
    }
}
