package com.github.graeme22.vmcraft.init;

import com.github.graeme22.vmcraft.VMCraft;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, VMCraft.MOD_ID);

	public static final RegistryObject<Item> CONSOLE_BLOCK_ITEM = ITEMS.register("console_block", () -> new BlockItem(ModBlocks.CONSOLE_BLOCK.get(),
																					new Item.Properties().group(VMCraft.TAB).maxStackSize(1)));

}
