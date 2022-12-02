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
        return AmdanorStages.values()[stageId];
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
