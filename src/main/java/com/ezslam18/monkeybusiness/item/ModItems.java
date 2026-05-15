package com.ezslam18.monkeybusiness.item;

import com.ezslam18.monkeybusiness.MonkeyBusiness;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MonkeyBusiness.MOD_ID);

    public static final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties().setId(ITEMS.key("banana"))));

    public static void register(BusGroup eventBus) {
        ITEMS.register(eventBus);
    }
}