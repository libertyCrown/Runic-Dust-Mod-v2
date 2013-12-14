package com.runicdust.dustdecor;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.runicdust.dustcore.DustModCore;
import com.runicdust.dustcore.api.DustItemManager;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "DustDeco", name = "Runic Dust Mod- Decorative Dusts", version = "v2.0", dependencies = "after:DustModCore")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class DustModDecor
{
	public static final int[] colors = new int[] { 0x000000, 0xCC4C4C,
			0x667F33, 0x7F664C, 0x3366CC, 0xB266E5, 0x4C99B2, 0x999999,
			0x4C4C4C, 0xF2B2CC, 0x7FCC19, 0xE5E533, 0x99B2F2, 0xE57FD8,
			0xF2B233, 0xFFFFFF };

	public static final int[] floorcolors = new int[] { 0x000000, 0xFF0000,
			0x667F33, 0x826A4C, 0x00B7FF, 0xB266E5, 0x4C99B2, 0xB9B9B9,
			0x666464, 0xF9B2CC, 0x7FCC19, 0xE5E533, 0x99B2F2, 0xE57FD8,
			0xF7B233, 0xFFFFFF };

	@Instance("DustDeco")
	public static DustModDecor instance;

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt)
	{
		registerDusts();
		registerRunes();
	}

	public void registerDusts()
	{
		DustItemManager.registerDust(10, "Black", "decorBlack", colors[0],
				colors[0], floorcolors[0]);
		DustItemManager.registerDust(11, "Red", "decorRed", colors[1],
				colors[1], floorcolors[1]);
		DustItemManager.registerDust(12, "Green", "decorGreen", colors[2],
				colors[2], floorcolors[2]);
		DustItemManager.registerDust(13, "Brown", "decorBrown", colors[3],
				colors[3], floorcolors[3]);
		DustItemManager.registerDust(14, "Blue", "decorBlue", colors[4],
				colors[4], floorcolors[4]);
		DustItemManager.registerDust(15, "Purple", "decorPurple", colors[5],
				colors[5], floorcolors[5]);
		DustItemManager.registerDust(16, "Cyan", "decorCyan", colors[6],
				colors[6], floorcolors[6]);
		DustItemManager.registerDust(17, "Light Gray", "decorLGray", colors[7],
				colors[7], floorcolors[7]);
		DustItemManager.registerDust(18, "Gray", "decorGray", colors[8],
				colors[8], floorcolors[8]);
		DustItemManager.registerDust(19, "Pink", "decorPink", colors[9],
				colors[9], floorcolors[9]);
		DustItemManager.registerDust(20, "Lime", "decorLime", colors[10],
				colors[10], floorcolors[10]);
		DustItemManager.registerDust(21, "Yellow", "decorYellow", colors[11],
				colors[11], floorcolors[11]);
		DustItemManager.registerDust(22, "Light Blue", "decorLBlue", colors[12],
				colors[12], floorcolors[12]);
		DustItemManager.registerDust(23, "Magenta", "decorMagenta", colors[13],
				colors[13], floorcolors[13]);
		DustItemManager.registerDust(24, "Orange", "decorOrange", colors[14],
				colors[14], floorcolors[14]);
		DustItemManager.registerDust(25, "White", "decorWhite", colors[15],
				colors[15], floorcolors[15]);

		for (int i = 0; i < 16; i++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(
					DustModCore.getItemDust(), 4, i + 10), new ItemStack(
					Item.dyePowder, 1, i), new ItemStack(Block.sand, 1));
		}

	}

	public void registerRunes()
	{
		// this mod adds dusts, not runes!(yet :P)
	}
}
