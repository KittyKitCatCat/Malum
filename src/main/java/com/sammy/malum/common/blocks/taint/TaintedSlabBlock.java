package com.sammy.malum.common.blocks.taint;

import com.sammy.malum.core.recipes.TaintTransfusion;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class TaintedSlabBlock extends SlabBlock implements ITaintSpreader
{
    public TaintedSlabBlock(Properties properties)
    {
        super(properties);
    }
    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        super.tick(state, worldIn, pos, rand);
        spread(worldIn,pos);
    }
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        TaintTransfusion.issueSpread(worldIn, pos);
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }
    
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        TaintTransfusion.issueSpread((World) worldIn, currentPos);
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}