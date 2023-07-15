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

        if (e instanceof ItemEntity entity) {
            if (et == EntityType.LIGHTNING_BOLT) {
                BlockPos pos = event.getEntity().blockPosition();
            Level level = entity.level();

            Item item = entity.getItem().getItem();

            if (item == LVRegister.EMPTY_ARTIFACT.get()) {
                    int x = entity.getBlockX();
                    int y = entity.getBlockY();
                    int z = entity.getBlockZ();

                    List<ItemEntity> itemEntityList =
                        level.getEntitiesOfClass(ItemEntity.class, new AABB(pos.mutable().move(x, y, z)));
                    for (ItemEntity entity0 : itemEntityList) {
                        if (entity0.getItem().getItem() == LVRegister.EMPTY_ARTIFACT.get())
                           entity0.spawnAtLocation(LVRegister.LIGHTNING_ARTIFACT.get(), 1);
                    }
                }
            }
        }
    }

    public static void registryEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(LVRegister.AMDANOR.get(), Amdanor.prepareAttributes().build());
    }
}
