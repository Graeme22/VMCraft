package com.github.graeme22.vmcraft.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ConsoleBlock extends Block {

    public ConsoleBlock() {
        super(Block.Properties.create(Material.IRON)
        					  .hardnessAndResistance(5.0f, 6.0f)
        		   			  .sound(SoundType.METAL)
                   			  .harvestLevel(2)
                   			  .harvestTool(ToolType.PICKAXE));
    }
    
}