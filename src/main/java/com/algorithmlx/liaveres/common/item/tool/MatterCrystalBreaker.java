package com.algorithmlx.liaveres.common.item.tool;

import com.algorithmlx.liaveres.common.item.material.LVToolTiers;
import com.algorithmlx.liaveres.common.setup.CommonConfig;
import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MatterCrystalBreaker extends PickaxeItem {
    public static boolean skip = false;

    public MatterCrystalBreaker() {
        super(LVToolTiers.MATTER_CRYSTAL, Integer.MAX_VALUE, Float.MAX_VALUE,
                new Properties().fireResistant().rarity(Constants.getLegendary()));

        LVRegister.WITHOUT_TABS_ITEMS.add(this);
    }

    @Override
    public boolean mineBlock(ItemStack p_40998_, Level p_40999_, BlockState p_41000_, BlockPos p_41001_, LivingEntity p_41002_) {
        Player player = (Player) p_41002_;
        var radius = (CommonConfig.pickaxeRadius + 1);

        if (!p_40999_.isClientSide() && !player.isShiftKeyDown()
                && !player.getCooldowns().isOnCooldown(p_40998_.getItem()) && player instanceof ServerPlayer serverPlayer) {
            if (!skip) {
                List<BlockPos> blocks = new ArrayList<>();

                for (int x = 0; x < (radius * 2 - 2); x++) {
                    for (int y = 0; y < (radius * 2 - 2); y++) {
                        for (int z = 0; z < (radius * 2 - 2); z++) {
                            int posX = p_41001_.getX();
                            int posY = p_41001_.getY();
                            int posZ = p_41001_.getZ();

                            switch (player.getDirection()) {
                                case SOUTH -> blocks.add(new BlockPos(
                                        posX + radius - x,
                                        posY - 1 + y,
                                        posZ + z)
                                );
                                case NORTH -> blocks.add(new BlockPos(
                                        posX - radius + x,
                                        posY - 1 + y,
                                        posZ - z)
                                );
                                case EAST -> blocks.add(new BlockPos(
                                        posX + x,
                                        posY - 1 + y,
                                        posZ + radius - z)
                                );
                                case WEST -> blocks.add(new BlockPos(
                                        posX - x,
                                        posY - 1 + y,
                                        posZ - radius + z)
                                );
                            }
                        }
                    }
                }

                skip = true;

                for (BlockPos position : blocks) {
                    BlockState state = p_40999_.getBlockState(position);

                    if (!state.isAir()) serverPlayer.gameMode.destroyBlock(position);
                }

                skip = false;
            }

            player.getCooldowns().addCooldown(this, 240);
            return true;
        } else {
            return super.mineBlock(p_40998_, p_40999_, p_41000_, p_41001_, p_41002_);
        }
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player pPlayer = context.getPlayer();

        if (pPlayer == null) return InteractionResult.FAIL;

        if (pPlayer.isShiftKeyDown()) {
            if (pPlayer.getItemInHand(context.getHand()).getItem() == LVRegister.MATTER_CRYSTAL_BREAKER.get()) {
                pPlayer.setItemInHand(context.getHand(), new ItemStack(LVRegister.MATTER_CRYSTAL_PICKAXE.get()));
                return InteractionResult.SUCCESS;
            }
        }

        return super.useOn(context);
    }
}
