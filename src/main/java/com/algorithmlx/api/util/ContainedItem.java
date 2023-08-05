package com.algorithmlx.api.util;

import com.algorithmlx.api.util.tools.ContainedItemBuilder;
import lombok.Getter;
import net.minecraft.world.item.Item;

public class ContainedItem extends Item {
    private final ContainedItemBuilder builder;
    private static final Properties properties = new Properties();

    public ContainedItem(ContainedItemBuilder builder) {
        super(properties);
        this.builder = builder;
    }

    public ContainedItemBuilder getBuilder() {
        return this.builder;
    }
}