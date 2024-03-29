package com.algorithmlx.liaveres.common.item.armor;

import com.algorithmlx.liaveres.common.entity.Amdanor;
import com.algorithmlx.liaveres.common.item.material.LVArmorMaterials;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.ModSetup;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatterCrystalArmor extends ArmorItem {

    public MatterCrystalArmor(EquipmentSlot pSlot) {
        super(LVArmorMaterials.MATTER_CRYSTAL, pSlot, new Properties().rarity(Constants.getLegendary()));
        MinecraftForge.EVENT_BUS.addListener(this::updatePlayerData);
        MinecraftForge.EVENT_BUS.addListener(this::cancelDamage);
    }

    public void updatePlayerData(TickEvent.PlayerTickEvent event) {
        Player livingEntity = event.player;

        ItemStack head = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = livingEntity.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = livingEntity.getItemBySlot(EquipmentSlot.FEET);

        boolean isFullMCA =
                head.getItem() == LVRegister.MATTER_CRYSTAL_HELMET.get() &&
                        chest.getItem() == LVRegister.MATTER_CRYSTAL_CHESTPLATE.get() &&
                        legs.getItem() == LVRegister.MATTER_CRYSTAL_LEGS.get() &&
                        feet.getItem() == LVRegister.MATTER_CRYSTAL_BOOTS.get();


        if (livingEntity.level.isClientSide()) {
            if (isFullMCA && livingEntity.isOnGround() && livingEntity.zza > 0F) {
                livingEntity.moveRelative(1.4F, new Vec3( 0, 0, 1));
            }
        }
    }

    public void cancelDamage(LivingDamageEvent event) {
        LivingEntity entity = (LivingEntity) event.getEntity();
        Random random = new Random();

        ItemStack head = entity.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = entity.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = entity.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = entity.getItemBySlot(EquipmentSlot.FEET);

        boolean isFullMatterCrystalArmor =
                head.getItem() == LVRegister.MATTER_CRYSTAL_HELMET.get() &&
                        chest.getItem() == LVRegister.MATTER_CRYSTAL_CHESTPLATE.get() &&
                        legs.getItem() == LVRegister.MATTER_CRYSTAL_LEGS.get() &&
                        feet.getItem() == LVRegister.MATTER_CRYSTAL_BOOTS.get();

        if (event.isCanceled() || event.getAmount() <= 0 || event.getEntity().level.isClientSide()) return;

        if (!isFullMatterCrystalArmor) return;

        if (entity instanceof Amdanor amdanor)
            event.setAmount(((float) amdanor.getAttributes().getBaseValue(Attributes.ATTACK_DAMAGE) / 10));

        if (random.nextDouble(Double.MAX_VALUE) > 0) event.setCanceled(true);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
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
                headSlot.getItem() == LVRegister.MATTER_CRYSTAL_HELMET.get() &&
                        chestSlot.getItem() == LVRegister.MATTER_CRYSTAL_CHESTPLATE.get() &&
                        legsSlot.getItem() == LVRegister.MATTER_CRYSTAL_LEGS.get() &&
                        feetSlot.getItem() == LVRegister.MATTER_CRYSTAL_BOOTS.get();

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
        } else {
            player.displayClientMessage(new TranslatableComponent("msg." + Constants.ModId + ".matter_crystal_armor.is_null." + values).withStyle(ChatFormatting.RED), true);
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

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlot slot, String layer) {
        if (slot == EquipmentSlot.LEGS) {
            return Constants.ModId + ":textures/armor/matter_crystal/2.png";
        } else {
            return Constants.ModId + ":textures/armor/matter_crystal/1.png";
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@NotNull ItemStack p_41421_, Level p_41422_, List<Component> p_41423_, @NotNull TooltipFlag p_41424_) {
        p_41423_.add(new TranslatableComponent("msg." + Constants.ModId + ".matter_crystal_armor"));
        p_41423_.add(new TranslatableComponent("msg." + Constants.ModId + ".matter_crystal_msg"));
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        return stack.copy();
    }
}
