package com.algorithmlx.liaveres.common.event;

import com.algorithmlx.api.gltf.animations.IAnimatable;
import com.algorithmlx.liaveres.common.capability.AnimationCapability;
import com.algorithmlx.liaveres.common.entity.Amdanor;
import com.algorithmlx.liaveres.common.setup.LVRegister;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.Objects;

import static com.algorithmlx.liaveres.common.setup.Constants.reloc;

public final class LVEvents {
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

    public static void attachCapabilityToIAnimatable(AttachCapabilitiesEvent<IAnimatable> event) {
        event.addCapability(reloc("animations"), new AnimationCapability.Provider());
    }
}
