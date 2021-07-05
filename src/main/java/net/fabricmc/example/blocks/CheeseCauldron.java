package net.fabricmc.example.blocks;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class CheeseCauldron extends AbstractCauldronBlock {

    public static int MAX_TIME = 3;
    public static IntProperty TIME = IntProperty.of("time", 0, MAX_TIME);


    public CheeseCauldron(Settings settings) {
            super(settings, CheeseCauldronBehavior.CHEESE_CAULDRON_BEHAVIOR);
            setDefaultState(getStateManager().getDefaultState().with(TIME, 0));
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return 0.9375D; // TODO: Tweak height and make dynamic for different block states
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> StateManager) {
        StateManager.add(TIME);
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 3;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(state.get(TIME) < MAX_TIME && world.isSkyVisible(pos) && random.nextInt(3) == 0 && !world.isClient()) {
            world.setBlockState(pos, state.with(TIME, state.get(TIME) + 1));
        }
    }

    public boolean isDone(BlockState state) {
        return state.get(TIME) == MAX_TIME;
    }
}
