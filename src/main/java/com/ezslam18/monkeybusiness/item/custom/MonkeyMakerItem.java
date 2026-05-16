package com.ezslam18.monkeybusiness.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import com.ezslam18.monkeybusiness.block.ModBlocks;

import java.util.Map;
import java.util.function.Supplier;

public class MonkeyMakerItem extends Item {

    public MonkeyMakerItem(Properties pProperties) {
        super(pProperties);
    }

    private static final Map<Block, Supplier<Block>> MONKEY_MAP =
            Map.ofEntries(
                    Map.entry(Blocks.DIRT, () -> Blocks.PUMPKIN),
                    Map.entry(Blocks.GRASS_BLOCK, () -> Blocks.PUMPKIN),
                    Map.entry(Blocks.DIRT_PATH, () -> Blocks.PUMPKIN),
                    Map.entry(Blocks.STONE, () -> Blocks.LAVA),
                    Map.entry(Blocks.NETHERRACK, () -> Blocks.AMETHYST_BLOCK),
                    Map.entry(Blocks.COBBLESTONE, () -> Blocks.LAVA),
                    Map.entry(Blocks.MELON, ModBlocks.BANANA_BLOCK),
                    Map.entry(Blocks.ACACIA_LEAVES, () -> Blocks.WATER),
                    Map.entry(Blocks.BIRCH_LEAVES, () -> Blocks.WATER),
                    Map.entry(Blocks.OAK_LEAVES, () -> Blocks.WATER),
                    Map.entry(Blocks.DARK_OAK_LEAVES, () -> Blocks.WATER),
                    Map.entry(Blocks.JUNGLE_LEAVES, () -> Blocks.WATER),
                    Map.entry(Blocks.MANGROVE_LEAVES, () -> Blocks.WATER),
                    Map.entry(Blocks.SPRUCE_LEAVES, () -> Blocks.WATER),
                    Map.entry(Blocks.PALE_OAK_LEAVES, () -> Blocks.WATER),
                    Map.entry(Blocks.FLOWERING_AZALEA_LEAVES, () -> Blocks.WATER)
            );

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if (MONKEY_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(pContext.getClickedPos(), MONKEY_MAP.get(clickedBlock).get().defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1,
                        ((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item ->pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                level.playSound(null, pContext.getClickedPos(), SoundEvents.ANVIL_USE, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
