package net.fabricmc.example.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Random;

import static net.fabricmc.example.ExampleMod.CHEESE_ITEM; // TODO Replace this with the cheese item class

public class CheeseCauldron extends CauldronBlock {

    public static final int MAX_TIME = 4;
    public static final int EMPTY_STATE = 0;
    public static final IntProperty TIME = IntProperty.of("time", 0, MAX_TIME); // TODO Modify max and name

    public CheeseCauldron(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(TIME, 0));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(random.nextInt(25) == 0 && world.isSkyVisible(pos) && state.get(TIME) < MAX_TIME && state.get(TIME) > EMPTY_STATE) { // TODO Tune chance of increase
            world.setBlockState(pos, state.with(TIME, state.get(TIME) + 1));
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(isDone(world, pos)) {
            double d = (world.random.nextFloat() * 0.7F) + 0.15000000596046448D; // Randomness for item spawning
            double e = (world.random.nextFloat() * 0.7F) + 0.06000000238418579D + 0.6D;
            double g = (world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + d, pos.getY() + e, pos.getZ() + g, new ItemStack(CHEESE_ITEM));
            world.spawnEntity(itemEntity);
            world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(TIME);
    }

    public boolean isDone(World world, BlockPos pos) {
        return world.getBlockState(pos).get(TIME) == MAX_TIME;
    }
}
