package com.github.graeme22.vmcraft.init;

import com.github.graeme22.vmcraft.VMCraft;
import com.github.graeme22.vmcraft.blocks.ConsoleBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, VMCraft.MOD_ID);

    // Blocks
    public static final RegistryObject<Block> CONSOLE_BLOCK = BLOCKS.register("console_block", ConsoleBlock::new);

}
