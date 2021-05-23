package com.sammy.malum.common.blocks.totem.pole;

import com.sammy.malum.MalumHelper;
import com.sammy.malum.common.items.SpiritItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class TotemPoleBlock extends Block
{
    public TotemPoleBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
    }
    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TotemPoleTileEntity();
    }
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (MalumHelper.areWeOnClient(worldIn))
        {
            return ActionResultType.SUCCESS;
        }
        if (worldIn.getTileEntity(pos) instanceof TotemPoleTileEntity)
        {
            TotemPoleTileEntity tileEntity = (TotemPoleTileEntity) worldIn.getTileEntity(pos);

            ItemStack stack = player.getHeldItem(handIn);
            if (stack.getItem() instanceof SpiritItem)
            {
                if (tileEntity.type == null)
                {
                    SpiritItem item = (SpiritItem) stack.getItem();
                    tileEntity.type = item.type;
                    stack.shrink(1);
                    MalumHelper.updateState(worldIn, pos);
                }
                else
                {
                    tileEntity.desiredColor = tileEntity.desiredColor == 0 ? 20 : 0;
                    MalumHelper.updateState(worldIn, pos);
                }
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}