package com.algorithmlx.liaveres.common.setup;

import com.algorithmlx.liaveres.proxy.ClientProxy;
import com.algorithmlx.liaveres.proxy.ServerProxy;
import com.algorithmlx.liaveres.server.network.Network;
import liquid.network.Direction;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Constants.ModId, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
    public static Direction proxy = Direction.of(ClientProxy::new, ServerProxy::new);
    public static Direction _proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static final CreativeModeTab CLASSIC_TAB = new CreativeModeTab(Constants.ModId + ".classic_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.MATTER_CRYSTAL_BLOCK.get());
        }
    };

    public static final CreativeModeTab ARTIFACT_TAB = new CreativeModeTab(Constants.ModId + ".classic_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.EMPTY_ARTIFACT.get());
        }
    };

    public static final CreativeModeTab MOBS_TAB = new CreativeModeTab(Constants.ModId + ".classic_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.AMDANOR_SKELETON_EGG.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
        Network.messageRegister();
//        OreConfigured.register();
//        OrePlacement.register();
        proxy.init();
    }
}
