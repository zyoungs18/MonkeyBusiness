package com.ezslam18.monkeybusiness.creativetab;
import com.ezslam18.monkeybusiness.MonkeyBusiness;
import com.ezslam18.monkeybusiness.block.ModBlocks;
import com.ezslam18.monkeybusiness.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MonkeyBusiness.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MONKEY_BUSINESS_TAB =
            CREATIVE_TABS.register("monkey_business_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.monkeybusiness.monkey_business_tab"))
                    .icon(() -> new ItemStack(ModItems.BANANA.get()))
                    .displayItems((params, output) -> {

                        // ITEMS
                        output.accept(ModItems.BANANA.get());
                        output.accept(ModItems.DART.get());
                        output.accept(ModItems.MONKONIUM_INGOT.get());
                        output.accept(ModItems.BANANITE_INGOT.get());
                        output.accept(ModItems.STAMPED_BANANITE_INGOT.get());
                        output.accept(ModItems.MONKEY_MAKER.get());

                        // BLOCKS
                        output.accept(ModBlocks.BANANA_BLOCK.get());
                        output.accept(ModBlocks.MONKONIUM_ORE.get());
                        output.accept(ModBlocks.MONKONIUM_BLOCK.get());
                        output.accept(ModBlocks.BANANITE_BLOCK.get());
                        output.accept(ModBlocks.MONKEY_BLOCK.get());
                    })
                    .build()
            );

    public static void register(BusGroup bus) {
        CREATIVE_TABS.register(bus);
    }
}
