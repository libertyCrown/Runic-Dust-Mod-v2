package com.runicdustmod.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.runicdustmod.blocks.BlockDust;
import com.runicdustmod.item.ItemDustRod;
import com.runicdustmod.item.ItemDustTome;

import cpw.mods.fml.common.registry.GameRegistry;

public class RDRegistry {
	
	/* Blocks!!! */
	
	public static Block dust;
	
	/* Items!!! */
	
	public static Item dustTome;
	public static Item dustRod;
	
	public static void initBlocks()
	{
		//TODO                .setUnlocalizedName
		dust = new BlockDust().func_149663_c("blockDust");
		GameRegistry.registerBlock(dust, "blockDust");
	}
	
	public static void initItems()
	{
		dustTome = new ItemDustTome().setUnlocalizedName("dustTome");
		GameRegistry.registerItem(dustTome, "dustTome");
		dustRod = new ItemDustRod().setUnlocalizedName("dustRod");
		GameRegistry.registerItem(dustRod, "dustRod");
	}

}
