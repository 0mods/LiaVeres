package com.algorithmlx.liaveres.common.item.tool;

import com.algorithmlx.liaveres.common.item.material.LVToolTiers;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.ModSetup;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class MatterCrystalHoe extends HoeItem {
    public MatterCrystalHoe() {
        super(LVToolTiers.MATTER_CRYSTAL, Integer.MAX_VALUE, Float.MAX_VALUE, new Item.Properties().fireResistant().rarity(Constants.getLegendary()));
    }
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.translatable("msg."+Constants.ModId+".matter_crystal_msg").withStyle(ChatFormatting.RED, ChatFormatting.YELLOW, ChatFormatting.GREEN, ChatFormatting.AQUA, ChatFormatting.BLUE, ChatFormatting.LIGHT_PURPLE));
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
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
