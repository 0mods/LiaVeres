package com.algorithmlx.liaveres.api.util;

import com.algorithmlx.liaveres.api.util.tools.ContainedItemBuilder;
import lombok.Getter;
import net.minecraft.world.item.Item;

public class ContainedItem extends Item {
    @Getter private final ContainedItemBuilder builder;
    private static final Properties properties = new Properties();

    public ContainedItem(ContainedItemBuilder builder) {
        super(properties);
        this.builder = builder;
    }
}
