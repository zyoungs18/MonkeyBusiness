package com.ezslam18.monkeybusiness.item;

import com.ezslam18.monkeybusiness.MonkeyBusiness;
import com.ezslam18.monkeybusiness.item.custom.DartItem;
import com.ezslam18.monkeybusiness.item.custom.MonkeyMakerItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MonkeyBusiness.MOD_ID);

    public static final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties().setId(ITEMS.key("banana")).food((ModFoodProperties.BANANA))));

    public static final RegistryObject<Item> DART = ITEMS.register("dart",
            () -> new DartItem(new Item.Properties().setId(ITEMS.key("dart"))));

    public static final RegistryObject<Item> MONKONIUM_INGOT = ITEMS.register("monkonium_ingot",
            () -> new Item(new Item.Properties().setId(ITEMS.key("monkonium_ingot"))));

    public static final RegistryObject<Item> BANANITE_INGOT = ITEMS.register("bananite_ingot",
            () -> new Item(new Item.Properties().setId(ITEMS.key("bananite_ingot"))));

    public static final RegistryObject<Item> STAMPED_BANANITE_INGOT = ITEMS.register("stamped_bananite_ingot",
            () -> new Item(new Item.Properties().setId(ITEMS.key("stamped_bananite_ingot"))));

    public static final RegistryObject<Item> MONKEY_MAKER = ITEMS.register("monkey_maker",
            () -> new MonkeyMakerItem(new Item.Properties().setId(ITEMS.key("monkey_maker")).durability(100)));

    public static void register(BusGroup eventBus) {
        ITEMS.register(eventBus);
    }
}