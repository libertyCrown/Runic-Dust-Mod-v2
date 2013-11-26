package com.dustthaum;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import thaumcraft.api.ItemApi;

import com.dustcore.api.DustItemManager;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "DustThaum", name= "Runic Dust Mod- Thaumcraft Compat", version="v2.0", dependencies = "required-after:DustModCore;required-after:Thaumcraft")

public class ThaumcraftDusts
{
	@Instance("DustThaum")
	public static ThaumcraftDusts instance;
	
	@EventHandler
	public static void init(FMLPostInitializationEvent event)
	{
		DustItemManager.registerDust(30, "Thaumium", "ThaumDust", 
				0x4A3D72, 0xA69BCA, 0x625197);
		
		GameRegistry.addShapedRecipe(new ItemStack(DustItemManager.idust, 8, 30), new Object[] { 
			"XYX",
			"YXY",
			"XYX",
			'X', Block.sand, 'Y', ItemApi.getItem("itemResource", 2)});
	}
}
