package com.algorithmlx.liaveres.common.setup;

import liquid.objects.annotations.Config;

public class CommonConfig {
    @Config(comment = """
            Lia Pickaxe excavation radius.\s
            It working like this:\s
            16 + 1 = 17; 17 * 2 = 34; 34 - 2 = 32 - pickaxe excavation radius""")
    public static int pickaxeRadius = 16 + 1;
}
