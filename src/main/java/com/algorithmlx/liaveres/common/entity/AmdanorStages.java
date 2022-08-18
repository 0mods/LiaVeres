package com.algorithmlx.liaveres.common.entity;

public enum AmdanorStages {
    STAGE_1(0),
    STAGE_2(1),
    STAGE_3(2);

    private int stageId;

    AmdanorStages(int stageId) {
        this.stageId = stageId;
    }

    public static AmdanorStages[] getStages() {
        return AmdanorStages.values();
    }

    public static AmdanorStages setStage(AmdanorStages stage) {
        return stage;
    }
}
