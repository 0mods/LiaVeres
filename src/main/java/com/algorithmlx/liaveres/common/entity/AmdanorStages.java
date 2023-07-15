package com.algorithmlx.liaveres.common.entity;

import net.minecraft.network.chat.Component;

import static com.algorithmlx.liaveres.common.setup.Constants.ModId;

public enum AmdanorStages implements AmdanorStage {
    START_STAGE(Component.translatable("stage." + ModId + ".amdanor.start"), 3600, 250);

    final Component stageName;
    final double health;
    final double damage;

    AmdanorStages(Component stageName, double health, double damage) {
        this.stageName = stageName;

        this.health = health;
        this.damage = damage;
    }

    public static AmdanorStage currentStage(int stageId) {
        if (stageId > 0 && stageId < AmdanorStages.values().length)
            return AmdanorStages.values()[stageId];
        else return AmdanorStages.START_STAGE;
    }

    @Override
    public Component stageName() {
        return this.stageName;
    }

    @Override
    public double healthInStage() {
        return this.health;
    }

    @Override
    public double damageInStage() {
        return this.damage;
    }
}
