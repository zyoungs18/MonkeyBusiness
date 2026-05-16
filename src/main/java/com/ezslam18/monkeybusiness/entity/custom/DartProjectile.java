package com.ezslam18.monkeybusiness.entity.custom;

import com.ezslam18.monkeybusiness.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class DartProjectile extends ThrowableItemProjectile {


    public DartProjectile(EntityType<? extends DartProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurt(
                this.damageSources().thrown(this, this.getOwner()),
                6.0F
        );
        this.discard();
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        this.discard();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.DART.get();
    }
}