package com.dustthaum;

import thaumcraft.api.ItemApi;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dustcore.DustMod;
import com.dustcore.api.DustItemManager;
import com.dustcore.config.DustContent;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "DustModThaum", name= "Runic Dust Mod- Thaumcraft Compat", version="v2.0", dependencies = "required-after:DustModCore;required-after:Thaumcraft")

public class ThaumcraftDusts
{
	@EventHandler
	public static void init(FMLPostInitializationEvent event)
	{
		DustItemManager.registerDust(30, "Thaumium", "ThaumDust", 
				0x4A3D72, 0xA69BCA, 0x625197);
		
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 4, 388),
				new Object[] { ItemApi.getItem("itemResource", 2), Item.coal });
	}
}
