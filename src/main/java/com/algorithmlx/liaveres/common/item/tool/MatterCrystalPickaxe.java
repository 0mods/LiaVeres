package com.algorithmlx.liaveres.common.item.tool;

import com.algorithmlx.liaveres.common.item.material.LVToolTiers;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.ModSetup;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MatterCrystalPickaxe extends PickaxeItem {
    public MatterCrystalPickaxe() {
        super(LVToolTiers.MATTER_CRYSTAL, Integer.MAX_VALUE, Float.MAX_VALUE,
                new Properties().fireResistant().rarity(Constants.getLegendary()));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player pPlayer = context.getPlayer();
        if (pPlayer == null) return InteractionResult.FAIL;
        if (pPlayer.isShiftKeyDown()) {
            if (pPlayer.getItemInHand(context.getHand()).getItem() == LVRegister.MATTER_CRYSTAL_PICKAXE.get()) {
                pPlayer.setItemInHand(context.getHand(), new ItemStack(LVRegister.MATTER_CRYSTAL_BREAKER.get()));
                return InteractionResult.SUCCESS;
            }
        }

        return super.useOn(context);
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@NotNull ItemStack pItemStack, Level p_41422_, List<Component> p_41423_, @NotNull TooltipFlag p_41424_) {
        p_41423_.add(Component.translatable("msg."+Constants.ModId+".matter_crystal_pickaxe"));


        p_41423_.add(Component.translatable("msg."+Constants.ModId+".matter_crystal_msg").withStyle(ChatFormatting.values()));
        super.appendHoverText(pItemStack, p_41422_, p_41423_, p_41424_);
    }
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.copy();
    }
}