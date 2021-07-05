package net.fabricmc.example.blocks;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;

import java.util.Map;

import static net.fabricmc.example.ExampleMod.CHEESE_CAULDRON;
import static net.fabricmc.example.ExampleMod.CHEESE_ITEM;
import static net.fabricmc.example.blocks.CheeseCauldron.MAX_TIME;
import static net.fabricmc.example.blocks.CheeseCauldron.TIME;

public interface CheeseCauldronBehavior extends CauldronBehavior {
    Map<Item, CauldronBehavior> CHEESE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();

    CauldronBehavior FILL_WITH_MILK = (state, world, pos, player, hand, stack) ->
            CauldronBehavior.fillCauldron(world, pos, player, hand, stack, CHEESE_CAULDRON.getDefaultState(), SoundEvents.ENTITY_COW_MILK );

    static void init() {
        EMPTY_CAULDRON_BEHAVIOR.put(Items.MILK_BUCKET, FILL_WITH_MILK);
        CHEESE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.MILK_BUCKET), (statex) -> statex.get(TIME) == 0, SoundEvents.ENTITY_COW_MILK));
        CHEESE_CAULDRON_BEHAVIOR.put(Items.AIR, (state, world, pos, player, hand, stack) -> CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(CHEESE_ITEM), (statex) -> statex.get(TIME) == MAX_TIME, SoundEvents.ENTITY_COW_MILK));
    }

}
