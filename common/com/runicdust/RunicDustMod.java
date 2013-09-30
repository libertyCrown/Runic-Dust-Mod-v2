package com.runicdust;

import com.runicdust.core.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

/**Licensed under the GPLv3.  Please take the time to read the license before
 * modifying this code.
 *
 * @author zombiepig333
 *
 */

@Mod
(
	modid="RunicDust",
	name="Runic Dust Mod v2",
	version="v2.0.0_1")

@NetworkMod
(
	clientSideRequired=true,
	serverSideRequired=false)

public class RunicDustMod 
{
	@Instance("RunicDust")
	public static RunicDustMod instance;
	
	@SidedProxy(clientSide="com.runicdust.client.ClientProxy", serverSide="com.runicdust.core.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void modStarting(FMLPreInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public static void modLoading(FMLInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public static void modsLoaded(FMLPostInitializationEvent event)
	{
		
	}
	
}
