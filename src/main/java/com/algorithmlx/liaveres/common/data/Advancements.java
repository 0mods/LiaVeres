package com.algorithmlx.liaveres.common.data;

import com.algorithmlx.liaveres.common.setup.Constants;
import com.algorithmlx.liaveres.common.setup.Registration;
import liquid.objects.data.gen.AdvancementGenerator;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class Advancements extends AdvancementGenerator implements Consumer<Consumer<Advancement>> {
    String m = Constants.ModId;

    public Advancements(DataGenerator generatorIn, ExistingFileHelper helper) {
        super(generatorIn, helper);
    }

    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {
        Advancement main = Advancement.Builder.advancement().display(Registration.CRYSTALLINE.get(),
                        Component.translatable("advancement." + m + ".main"),
                        Component.translatable("advancement." + m + ".main.desc"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                        FrameType.TASK, true, true, false)
                .addCriterion("crystalline", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.CRYSTALLINE.get()))
                .save(advancementConsumer, m + "/main/main");

        advancement(main, Registration.AMDANOR_UNLOCKER_KEY.get(), "get_key", FrameType.TASK, true, true, false, m);
        advancement(main, Registration.MATTER_CRYSTAL.get(), "matter_crystal", FrameType.CHALLENGE, true, true, false, m);
    }
}
