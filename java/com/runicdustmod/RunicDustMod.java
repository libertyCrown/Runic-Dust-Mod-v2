package com.runicdustmod;

import com.runicdustmod.core.RDRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod
(	modid="RunicDustMod",
	name="The Runic Dust Mod v2",
	version="v2.1.0 DEV")

public class RunicDustMod {
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent evnt)
	{
		RDRegistry.initBlocks();
		RDRegistry.initItems();
	}
	
	@EventHandler
	public static void modInit(FMLInitializationEvent evnt)
	{
		
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent evnt)
	{
		
	}

}
