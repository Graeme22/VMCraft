package com.github.graeme22.vmcraft.blocks;

import com.github.graeme22.vmcraft.gui.ConsoleBlockScreen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class ConsoleBlock extends Block {
	
	private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	protected static final VoxelShape SHAPE_N = Block.makeCuboidShape(1.0D, 0.0D, 5.0D, 15.0D, 1.0D, 15.0D);
	protected static final VoxelShape SHAPE_S = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 1.0D, 11.0D);
	protected static final VoxelShape SHAPE_W = Block.makeCuboidShape(5.0D, 0.0D, 1.0D, 15.0D, 1.0D, 15.0D);
	protected static final VoxelShape SHAPE_E = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 11.0D, 1.0D, 15.0D);

    public ConsoleBlock() {
        super(Block.Properties.create(Material.IRON).sound(SoundType.METAL));
    }
    
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		// client-side only
    	DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> openGui(worldIn, pos));
    	return ActionResultType.SUCCESS;
    }
    
    private void openGui(World worldIn, BlockPos pos) {
    	// handle on logical client
    	if(worldIn.isRemote)
    		Minecraft.getInstance().displayGuiScreen(new ConsoleBlockScreen());
    }
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    	switch(state.get(FACING)) {
    	case NORTH:
    		return SHAPE_N;
    	case SOUTH:
    		return SHAPE_S;
    	case WEST:
    		return SHAPE_W;
    	case EAST:
    		return SHAPE_E;
    	default: // unreachable
    		return SHAPE_N;
    	}
    }
    
    @Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
	}
    
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
    
}