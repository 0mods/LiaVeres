package com.algorithmlx.liaveres.common.item.material;

import com.algorithmlx.api.util.tools.InLineMaterial;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;

public class LVArmorMaterials {
    public static ArmorMaterial MATTER_CRYSTAL = InLineMaterial.armor("matter_crystal")
            .maxDamage(-1)
            .helmetDef(Integer.MAX_VALUE)
            .chestDef(Integer.MAX_VALUE)
            .legsDef(Integer.MAX_VALUE)
            .bootsDef(Integer.MAX_VALUE)
            .enchant(Integer.MAX_VALUE)
            .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
            .toughness(Float.MAX_VALUE)
            .knockback(Float.MAX_VALUE)
            .build();
    public static ArmorMaterial MATTER = InLineMaterial.armor("matter")
            .maxDamage(6000)
            .helmetDef(10000)
            .chestDef(20000)
            .legsDef(15000)
            .bootsDef(5000)
            .toughness(20F)
            .knockback(0.5F)
            .build();
    public static ArmorMaterial GILDED_NETHERITE = InLineMaterial.armor("gilded_netherite")
            .maxDamage(65)
            .helmetDef(12)
            .chestDef(32)
            .legsDef(24)
            .bootsDef(8)
            .toughness(20F)
            .knockback(0.5F)
            .build();
}
