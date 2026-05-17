package com.ezslam18.monkeybusiness.block.custom;

import com.ezslam18.monkeybusiness.block.ModBlocks;
import com.ezslam18.monkeybusiness.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonkeyBlock extends Block {

    private record VariableRecipe(List<ItemStack> ingredients, ItemStack output) {}
    private static List<VariableRecipe> RECIPES = null;

    private static List<VariableRecipe> getRecipes() {
        if (RECIPES == null) {
            RECIPES = new ArrayList<>();
            RECIPES.add(new VariableRecipe(
                    List.of(
                            new ItemStack(ModBlocks.BANANITE_BLOCK.get(), 1)
                    ),
                    new ItemStack(ModItems.BANANA.get(), 64)
            ));

            RECIPES.add(new VariableRecipe(
                    List.of(
                            new ItemStack(ModItems.MONKONIUM_INGOT.get(), 4),
                            new ItemStack(ModItems.BANANA.get(), 5)
                    ),
                    new ItemStack(ModItems.BANANITE_INGOT.get(), 1)
            ));
        }
        return RECIPES;
    }

    public MonkeyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if (!level.isClientSide() && entity instanceof ItemEntity) {
            AABB searchBox = new AABB(blockPos.above());
            List<ItemEntity> itemsOnBlock = level.getEntitiesOfClass(ItemEntity.class, searchBox);

            for (VariableRecipe recipe : getRecipes()) {
                Map<ItemEntity, Integer> entitiesToConsume = tryMatchRecipe(itemsOnBlock, recipe);

                if (entitiesToConsume != null) {
                    executeCrafting(level, blockPos, entitiesToConsume, recipe.output());
                    break;
                }
            }
        }
        super.stepOn(level, blockPos, blockState, entity);
    }

    private Map<ItemEntity, Integer> tryMatchRecipe(List<ItemEntity> floorItems, VariableRecipe recipe) {
        Map<ItemEntity, Integer> consumptionMap = new HashMap<>();

        Map<ItemEntity, Integer> availableQuantities = new HashMap<>();
        for (ItemEntity ie : floorItems) {
            availableQuantities.put(ie, ie.getItem().getCount());
        }

        for (ItemStack ingredient : recipe.ingredients()) {
            Item requiredItem = ingredient.getItem();
            int requiredCount = ingredient.getCount();
            int accumulated = 0;

            for (ItemEntity ie : floorItems) {
                if (ie.getItem().is(requiredItem)) {
                    int available = availableQuantities.get(ie);
                    if (available > 0) {
                        int taken = Math.min(requiredCount - accumulated, available);
                        accumulated += taken;

                        consumptionMap.put(ie, consumptionMap.getOrDefault(ie, 0) + taken);
                        availableQuantities.put(ie, available - taken);
                    }
                }
                if (accumulated >= requiredCount) break;
            }

            if (accumulated < requiredCount) {
                return null;
            }
        }
        return consumptionMap;
    }

    private void executeCrafting(Level level, BlockPos pos, Map<ItemEntity, Integer> targets, ItemStack outputStack) {
        targets.forEach((itemEntity, countToTake) -> {
            ItemStack stack = itemEntity.getItem();
            stack.shrink(countToTake);
            itemEntity.setItem(stack);
        });

        ItemEntity resultEntity = new ItemEntity(
                level,
                pos.getX() + 0.5,
                pos.getY() + 1.0,
                pos.getZ() + 0.5,
                outputStack.copy()
        );
        level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f);
        level.addFreshEntity(resultEntity);
    }
}