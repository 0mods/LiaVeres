package com.algorithmlx.liaveres.common.item.basic;

import com.algorithmlx.liaveres.client.screen.book.LiaBookScreen;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.ModSetup;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LiaBook extends Item {
    public LiaBook() {
        super(new Item.Properties().fireResistant().stacksTo(1));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide) {
            Minecraft.getInstance().setScreen(new LiaBookScreen());
            pPlayer.sendSystemMessage(Component.translatable("msg." + Constants.ModId + ".liaBook.isOpened", pPlayer.getDisplayName()));
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        } else {
            return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("msg." + Constants.ModId + ".liaBook"));
        pTooltipComponents.add(Component.translatable("msg." + Constants.ModId + ".liaBook.edition"));
        pTooltipComponents.add(Component.translatable("msg." + Constants.ModId + ".liaBook.author"));
    }
}
