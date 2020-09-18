package com.github.graeme22.vmcraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.graeme22.vmcraft.init.ModBlocks;
import com.github.graeme22.vmcraft.init.ModItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VMCraft.MOD_ID)
public class VMCraft {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "vmcraft";

    public VMCraft() {
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    // Custom ItemGroup TAB
    public static final ItemGroup TAB = new ItemGroup("vmcraft") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.CONSOLE_BLOCK_ITEM.get());
        }
    };
    
}
