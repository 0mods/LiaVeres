package com.algorithmlx.liaveres.api.util.tools;

import lombok.Builder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.util.NonNullSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static lombok.Builder.*;

public class InLineMaterial {
    public static Armor.Builder armor(String name) {
        return Armor.of().name(name);
    }

    public static ItemTier.Builder item() {
        return ItemTier.of();
    }

    @Builder(builderClassName = "Builder", builderMethodName = "of")
    static class Armor extends InLineMaterial implements ArmorMaterial {
        @Default
        private String name = "";
        @Default
        private int maxDamage = 15;
        @Default
        private int helmetDef = 2;
        @Default
        private int chestDef = 6;
        @Default
        private int legsDef = 5;
        @Default
        private int bootsDef = 2;
        @Default
        private int enchant = 9;
        @Default
        private SoundEvent sound = SoundEvents.ARMOR_EQUIP_DIAMOND;
        @Default
        private float toughness = 0;
        @Default
        private float knockback = 0;
        @Default
        private NonNullSupplier<Ingredient> repair = ()-> Ingredient.EMPTY;

        private final int[] armorDurability =  new int[]{13, 15, 16, 11};

//        @Override
//        public int getDurabilityForSlot(@NotNull EquipmentSlot equipmentSlot) {
//            switch (equipmentSlot.getIndex()) {
//                case 0 -> /* Boots */ {
//                    return bootsDef;
//                }
//                case 1 -> /* Legs */ {
//                    return legsDef;
//                }
//                case 2 -> /* ChestPlate */ {
//                    return chestDef;
//                }
//                case 3 -> /* Head */ {
//                    return helmetDef;
//                }
//            }
//            return 0;
//        }
//
//        @Override
//        public int getDefenseForSlot(@NotNull EquipmentSlot equipmentSlot) {
//            return armorDurability[equipmentSlot.getIndex()] * this.maxDamage;
//        }

        @Override
        public int getDurabilityForType(ArmorItem.Type p_266807_) {
            var index = p_266807_.getSlot().getIndex();
            if (p_266807_ == ArmorItem.Type.BOOTS) return armorDurability[index] * this.maxDamage;
            else if (p_266807_ == ArmorItem.Type.HELMET) return armorDurability[index] * this.maxDamage;
            else if (p_266807_ == ArmorItem.Type.CHESTPLATE) return armorDurability[index] * this.maxDamage;
            else if (p_266807_ == ArmorItem.Type.LEGGINGS) return armorDurability[index] * this.maxDamage;
            return 0;
        }

        @Override
        public int getDefenseForType(ArmorItem.Type p_267168_) {
            if (p_267168_ == ArmorItem.Type.HELMET) return helmetDef;
            else if (p_267168_ == ArmorItem.Type.CHESTPLATE) return chestDef;
            else if (p_267168_ == ArmorItem.Type.LEGGINGS) return legsDef;
            else if (p_267168_ == ArmorItem.Type.BOOTS) return bootsDef;
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return this.enchant;
        }

        @Override
        public @NotNull SoundEvent getEquipSound() {
            return this.sound;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return this.repair.get();
        }

        @Override
        public @NotNull String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return this.knockback;
        }
    }

    @Builder(builderClassName = "Builder", builderMethodName = "of")
    static class ItemTier extends InLineMaterial implements Tier {
        @Default
        private int maxUses = 250;
        @Default
        private float speed = 6F;
        @Default
        private float attackBonus = 2.0F;
        @Default
        private int harvestLevel = 2;
        @Default
        private int enchant = 14;
        @Default
        private NonNullSupplier<Ingredient> repair = ()-> Ingredient.EMPTY;
        @Default
        private TagKey<Block> tags = null;

        @Override
        public int getUses() {
            return this.maxUses;
        }

        @Override
        public float getSpeed() {
            return this.speed;
        }

        @Override
        public float getAttackDamageBonus() {
            return this.attackBonus;
        }

        @Override
        public int getLevel() {
            return this.harvestLevel;
        }

        @Override
        public int getEnchantmentValue() {
            return this.enchant;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return this.repair.get();
        }

        @Override
        public @Nullable TagKey<Block> getTag() {
            return this.tags;
        }
    }
}
