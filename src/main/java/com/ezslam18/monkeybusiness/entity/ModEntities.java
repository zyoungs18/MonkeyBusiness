package com.ezslam18.monkeybusiness.entity;

import com.ezslam18.monkeybusiness.MonkeyBusiness;
import com.ezslam18.monkeybusiness.entity.custom.DartProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MonkeyBusiness.MOD_ID);


    public static final RegistryObject<EntityType<DartProjectile>> DART =
            ENTITY_TYPES.register("dart",
                    () -> EntityType.Builder.<DartProjectile>of(DartProjectile::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(20)
                            .updateInterval(10)
                            .build(ENTITY_TYPES.key("dart"))
            );

    public static void register(BusGroup eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
