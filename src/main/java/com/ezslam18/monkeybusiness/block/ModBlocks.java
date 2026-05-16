package com.ezslam18.monkeybusiness.block;

import com.ezslam18.monkeybusiness.MonkeyBusiness;
import com.ezslam18.monkeybusiness.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MonkeyBusiness.MOD_ID);

    public static final RegistryObject<Block> BANANA_BLOCK = registerBlock("banana_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("banana_block"))
                    .mapColor(MapColor.COLOR_YELLOW)
                    .strength(0.5f)
                    .sound(SoundType.WOOD)
            ));

    public static final RegistryObject<Block> MONKONIUM_ORE = registerBlock("monkonium_ore",
            () -> new DropExperienceBlock(UniformInt.of(1,2),BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("monkonium_ore"))
                    .strength(20.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 2)
            ));
    public static final RegistryObject<Block> MONKONIUM_BLOCK = registerBlock("monkonium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("monkonium_block"))
                    .strength(10.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .lightLevel(state -> 5)
            ));








    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(),
                        new Item.Properties().setId(ModItems.ITEMS.key(name))));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn =  BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static void register(BusGroup eventBus) {
        BLOCKS.register(eventBus);
    }
}
