package com.ezslam18.monkeybusiness;

import com.ezslam18.monkeybusiness.creativetab.ModCreativeTabs;
import com.ezslam18.monkeybusiness.entity.ModEntities;
import com.ezslam18.monkeybusiness.item.ModItems;
import com.ezslam18.monkeybusiness.block.ModBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MonkeyBusiness.MOD_ID)
public final class MonkeyBusiness {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "monkeybusiness";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    public MonkeyBusiness(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();

        // Register the commonSetup method for modloading
        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::commonSetup);

        ModItems.register(modBusGroup);
        ModBlocks.register(modBusGroup);
        ModCreativeTabs.register(modBusGroup);
        ModEntities.register(modBusGroup);

        // Register the item to a creative tab
        BuildCreativeModeTabContentsEvent.BUS.addListener(MonkeyBusiness::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModItems.BANANA.get());
        }
        if(event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(ModItems.DART.get());
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.BANANA_BLOCK.get());
            event.accept(ModBlocks.MONKONIUM_BLOCK);
        }
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.MONKONIUM_ORE.get());
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.MONKONIUM_INGOT.get());
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ModItems.MONKEY_MAKER.get());
        }

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.DART.get(), ThrownItemRenderer::new);
        }
    }
}
