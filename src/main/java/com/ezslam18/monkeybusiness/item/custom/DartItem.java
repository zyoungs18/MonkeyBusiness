package com.ezslam18.monkeybusiness.item.custom;

import com.ezslam18.monkeybusiness.entity.ModEntities;
import com.ezslam18.monkeybusiness.entity.custom.DartProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DartItem extends Item {
    public DartItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            DartProjectile dart = new DartProjectile(ModEntities.DART.get(), level);
            dart.setOwner(player);
            dart.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            dart.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(dart);

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }
}