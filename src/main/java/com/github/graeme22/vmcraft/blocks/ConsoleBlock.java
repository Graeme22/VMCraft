package com.github.graeme22.vmcraft.blocks;

import java.io.IOException;

import com.github.graeme22.vmcraft.VMCraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ConsoleBlock extends Block {

    public ConsoleBlock() {
        super(Block.Properties.create(Material.IRON)
        					  .hardnessAndResistance(5.0f, 6.0f)
        		   			  .sound(SoundType.METAL));
    }
    
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		// launch GUI/CLI
		try {
			Process process = Runtime.getRuntime().exec("virt-viewer -cf qemu:///system fedora");
		} catch (IOException e) {
			VMCraft.LOGGER.error("Failed to launch machine.");
			e.printStackTrace();
		}
		
		return ActionResultType.SUCCESS;
    }
    
}