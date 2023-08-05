package com.algorithmlx.api.util.tools;

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

@SuppressWarnings("ClassEscapesDefinedScope")
public class InLineMaterial {
    public static Armor.Builder armor(String name) {
        return Armor.of().name(name);
    }

    public static ItemTier.Builder item() {
        return ItemTier.of();
    }

    // Lombok is break :(
    protected static class Armor extends InLineMaterial implements ArmorMaterial {
        private final String name;
        private final int maxDamage;
        private final int helmetDef;
        private final int chestDef;
        private final int legsDef;
        private final int bootsDef;
        private final int enchant;
        private final SoundEvent sound;
        private final float toughness;
        private final float knockback;
        private final NonNullSupplier<Ingredient> repair;

        private final int[] armorDurability =  new int[]{13, 15, 16, 11};

        private Armor(String name, int maxDamage, int helmetDef, int chestDef, int legsDef, int bootsDef, int enchant,
                      SoundEvent sound, float toughness, float knockback, NonNullSupplier<Ingredient> repair) {
            this.name = name;
            this.maxDamage = maxDamage;
            this.helmetDef = helmetDef;
            this.chestDef = chestDef;
            this.legsDef = legsDef;
            this.bootsDef = bootsDef;
            this.enchant = enchant;
            this.sound = sound;
            this.toughness = toughness;
            this.knockback = knockback;
            this.repair = repair;
        }

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
        public int getDefenseForType(@NotNull ArmorItem.Type p_267168_) {
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

        protected static Builder of() {
            return new Builder();
        }

        public static class Builder {
            private String name = "";
            private int maxDamage = 15;
            private int helmetDef = 2;
            private int chestDef = 6;
            private int legsDef = 5;
            private int bootsDef = 2;
            private int enchant = 9;
            private SoundEvent sound = SoundEvents.ARMOR_EQUIP_DIAMOND;
            private float toughness = 0;
            private float knockback = 0;
            private NonNullSupplier<Ingredient> repair = ()-> Ingredient.EMPTY;

            protected Builder() {}

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder maxDamage(int maxDamage) {
                this.maxDamage = maxDamage;
                return this;
            }

            public Builder helmetDef(int helmetDef) {
                this.helmetDef = helmetDef;
                return this;
            }

            public Builder chestDef(int chestDef) {
                this.chestDef = chestDef;
                return this;
            }

            public Builder legsDef(int legsDef) {
                this.legsDef = legsDef;
                return this;
            }

            public Builder bootsDef(int bootsDef) {
                this.bootsDef = bootsDef;
                return this;
            }

            public Builder enchant(int enchant) {
                this.enchant = enchant;
                return this;
            }

            public Builder sound(SoundEvent sound) {
                this.sound = sound;
                return this;
            }

            public Builder toughness(float toughness) {
                this.toughness = toughness;
                return this;
            }

            public Builder knockback(float knockback) {
                this.knockback = knockback;
                return this;
            }

            public Builder repair(NonNullSupplier<Ingredient> repair) {
                this.repair = repair;
                return this;
            }
            public Armor build() {
                return new Armor(this.name, this.maxDamage, this.helmetDef, this.chestDef, this.legsDef, this.bootsDef,
                        this.enchant, this.sound, this.toughness, this.knockback, this.repair);
            }
        }
    }

    static class ItemTier extends InLineMaterial implements Tier {
        private final int maxUses;
        private final float speed;
        private final float attackBonus;
        private final int harvestLevel;
        private final int enchant;
        private final NonNullSupplier<Ingredient> repair;
        private final TagKey<Block> tags;

        private ItemTier(int maxUses, float speed, float attackBonus, int harvestLevel, int enchant,
                         NonNullSupplier<Ingredient> repair, TagKey<Block> tags) {
            this.maxUses = maxUses;
            this.speed = speed;
            this.attackBonus = attackBonus;
            this.harvestLevel = harvestLevel;
            this.enchant = enchant;
            this.repair = repair;
            this.tags = tags;
        }

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

        protected static Builder of() {
            return new Builder();
        }

        public static class Builder {
            private int maxUses = 250;
            private float speed = 6F;
            private float attackBonus = 2.0F;
            private int harvestLevel = 2;
            private int enchant = 14;
            private NonNullSupplier<Ingredient> repair = ()-> Ingredient.EMPTY;
            private TagKey<Block> tags = null;

            public Builder maxUses(int maxUses) {
                this.maxUses = maxUses;
                return this;
            }

            public Builder speed(float speed) {
                this.speed = speed;
                return this;
            }

            public Builder attackBonus(float attackBonus) {
                this.attackBonus = attackBonus;
                return this;
            }

            public Builder harvestLevel(int harvestLevel) {
                this.harvestLevel = harvestLevel;
                return this;
            }

            public Builder enchant(int enchant) {
                this.enchant = enchant;
                return this;
            }

            public Builder repair(NonNullSupplier<Ingredient> repair) {
                this.repair = repair;
                return this;
            }

            public Builder tags(TagKey<Block> tags) {
                this.tags = tags;
                return this;
            }

            public ItemTier build() {
                return new ItemTier(this.maxUses, this.speed, this.attackBonus, this.harvestLevel, this.enchant, this.repair, this.tags);
            }
        }
    }
}
