package com.algorithmlx.liaveres.common.event;

import com.algorithmlx.liaveres.common.entity.Amdanor;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.advancements.critereon.LightningStrikeTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;
import java.util.Objects;

public class LVEvents {
    // FIXME: 2/4/2022
    public void lightningBolt(LivingHurtEvent event) {
        Entity e = event.getEntity();
        EntityType<?> et = e.getType();
//        if (et == EntityType.LIGHTNING_BOLT) {
//            BlockPos pos = event.getEntity().blockPosition();
//            Level level = event.getLevel();
//
//            ItemEntity itemEntity = (ItemEntity) e;
//            Item item = itemEntity.getItem().getItem();
//
//            if (!(item == LVRegister.EMPTY_ARTIFACT.get())) {
//                int x = itemEntity.getBlockX();
//                int y = itemEntity.getBlockY();
//                int z = itemEntity.getBlockZ();
//
//                List<ItemEntity> itemEntityList =
//                        level.getEntitiesOfClass(ItemEntity.class, new AABB(pos.mutable().move(x, y, z)));
//                for (ItemEntity entity : itemEntityList) {
//                    if (entity.getItem().getItem() == LVRegister.EMPTY_ARTIFACT.get())
//                        entity.spawnAtLocation(LVRegister.LIGHTNING_ARTIFACT.get(), 1);
//                }
//            }
//        }

        if (Objects.requireNonNull(event.getSource().getEntity()).getType() == EntityType.LIGHTNING_BOLT) {
            // here code maybe
        }
    }

    public static void registryEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(LVRegister.AMDANOR.get(), Amdanor.prepareAttributes().build());
    }
}
