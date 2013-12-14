package com.runicdust.dustcore.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;

import com.runicdust.dustcore.DustModCore;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;

import com.runicdust.dustcore.config.DustContent;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class DustItemManager
{
	/**
	 * The item that must be used to register new dusts into the game.
	 */
	public static Item idust;
	
	public static DustColor[] colors = new DustColor[1000];
	public static String[] names = new String[1000];
	public static String[] ids = new String[1000];
	public static DustColor[] colorsRemote = new DustColor[1000];
	public static String[] namesRemote = new String[1000];
	public static String[] idsRemote = new String[1000];

	/**
	 * Register a new dust type to the system. You'll have to manually set the
	 * recipe/method of getting the dust. The item will just be DustModCore.idust
	 * with the damage value equal to this passed value parameter.
	 * 
	 * @param value
	 *            Worth of the dust. Bigger number means more worth (999 max)
	 * @param primaryColor
	 *            Color of the base of the item dust
	 * @param secondaryColor
	 *            Color of the sparkles on the item dust
	 * @param floorColor
	 *            Color of the dust when placed on the ground.
	 */
	public static void registerDust(int value, String name, String idName,
			int primaryColor, int secondaryColor, int floorColor)
	{
		if (colors[value] != null)
		{
			throw new IllegalArgumentException(
					"[DustModCore] Dust value already taken! " + value);
		}

		colors[value] = colorsRemote[value] = new DustColor(primaryColor,
				secondaryColor, floorColor);
		names[value] = namesRemote[value] = name;
		ids[value] = idsRemote[value] = idName;

		LanguageRegistry.instance().addStringLocalization(
				"tile.dust." + idName + ".name", "en_US", name + " Runic Dust");
		LanguageRegistry.instance().addStringLocalization(
				"tile.ink." + idName + ".name", "en_US", name + " Runic Ink");
		LanguageRegistry.instance().addStringLocalization(
				"pouch." + idName + ".name", "en_US", name + " Dust Pouch");

		GameRegistry.addShapelessRecipe(DustContent.ink.getInk(value),
				new Object[] { new ItemStack(Item.potion.itemID, 1, 0),
						new ItemStack(idust, 1, value),
						Item.ghastTear });
		GameRegistry.addShapelessRecipe(DustContent.ink.getInk(value),
				new Object[] { new ItemStack(Item.potion.itemID, 1, 0),
						new ItemStack(DustContent.pouch, 1, value * 2 + 1),
						Item.ghastTear });

		ItemStack craft = new ItemStack(DustContent.pouch, 1, value * 2);
		GameRegistry.addRecipe(craft, new Object[] { " s ", "ldl", " l ", 's',
				new ItemStack(Item.silk, 1), 'd',
				new ItemStack(idust, 1, value), 'l',
				new ItemStack(Item.leather, 1) });
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 1,
				value), new ItemStack(DustContent.pouch, 1, value * 2 + 1));
	}

	public static void registerRemoteDust(int value, String name,
			String idName, int primaryColor, int secondaryColor, int floorColor)
	{
		if (colorsRemote[value] != null)
		{
			throw new IllegalArgumentException(
					"[DustModCore] Remote error! Dust value already taken! "
							+ value);
		}

		colorsRemote[value] = new DustColor(primaryColor, secondaryColor,
				floorColor);
		namesRemote[value] = name;
		idsRemote[value] = idName;

		LanguageRegistry.instance().addStringLocalization(
				"tile.dust." + idName + ".name", name + " Runic Dust");
		LanguageRegistry.instance().addStringLocalization(
				"tile.ink." + idName + ".name", name + " Runic Ink");
		LanguageRegistry.instance().addStringLocalization(
				"pouch." + idName + ".name", name + " Dust Pouch");

		GameRegistry.addShapelessRecipe(DustContent.ink.getInk(value),
				new Object[] { new ItemStack(Item.potion.itemID, 1, 0),
						new ItemStack(idust, 1, value),
						Item.ghastTear });
		GameRegistry.addShapelessRecipe(DustContent.ink.getInk(value),
				new Object[] { new ItemStack(Item.potion.itemID, 1, 0),
						new ItemStack(DustContent.pouch, 1, value * 2 + 1),
						Item.ghastTear });

		ItemStack craft = new ItemStack(DustContent.pouch, 1, value * 2);
		GameRegistry.addRecipe(craft, new Object[] { " s ", "ldl", " l ", 's',
				new ItemStack(Item.silk, 1), 'd',
				new ItemStack(idust, 1, value), 'l',
				new ItemStack(Item.leather, 1) });
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 1,
				value), new ItemStack(DustContent.pouch, 1, value * 2 + 1));
	}

	
	//TODO- This is old, needs to be updated
	private static void loadLanguage(StringTranslate st,
			Properties par1Properties, String par2Str) throws IOException
	{
		BufferedReader var3 = new BufferedReader(new InputStreamReader(
				StringTranslate.class.getResourceAsStream("/lang/" + par2Str
						+ ".lang"), "UTF-8"));

		for (String var4 = var3.readLine(); var4 != null; var4 = var3
				.readLine())
		{
			var4 = var4.trim();

			if (!var4.startsWith("#"))
			{
				String[] var5 = var4.split("=");

				if (var5 != null && var5.length == 2)
				{
					par1Properties.setProperty(var5[0], var5[1]);
				}
			}
		}
		LanguageRegistry.instance().loadLanguageTable(par1Properties, par2Str);
	}

	public static String[] getNames()
	{
		return (DustModCore.proxy.isClient() ? namesRemote : names);
	}

	public static String[] getIDS()
	{
		return (DustModCore.proxy.isClient() ? idsRemote : ids);
	}

	public static DustColor[] getColors()
	{
		return (DustModCore.proxy.isClient() ? colorsRemote : colors);
	}

	public static int getPrimaryColor(int value)
	{
		if (value <= 0)
			return 0x8F25A2;
		if (value > colorsRemote.length || colorsRemote[value] == null)
			return 0;
		return colorsRemote[value].primaryColor;
	}

	public static int getSecondaryColor(int value)
	{
		if (value <= 0)
			return 0xDB73ED1;
		if (value > colorsRemote.length || colorsRemote[value] == null)
			return 0;
		return colorsRemote[value].secondaryColor;
	}

	public static int getFloorColor(int value)
	{
		if (value <= 0)
			return 0xCE00E0;
		if (value > colorsRemote.length || colorsRemote[value] == null)
			return 0;
		return colorsRemote[value].floorColor;
	}

	public static int[] getFloorColorRGB(int value)
	{
		if (value <= 0)
			return new int[] { 206, 0, 224 }; // 00CE00E0 variable

		if (value > colorsRemote.length || colorsRemote[value] == null)
			return new int[] { 0, 0, 0 };

		int[] rtn = new int[3];

		int col = colorsRemote[value].floorColor;

		rtn[0] = (col & 0xFF0000) >> 16;
		rtn[1] = (col & 0xFF00) >> 8;
		rtn[2] = (col & 0xFF);

		return rtn;
	}

	public static void reset()
	{
		DustModCore.log(Level.FINE, "Reseting remote dusts.");
		colorsRemote = new DustColor[1000];
		namesRemote = new String[1000];
		idsRemote = new String[1000];
	}

	public static void registerDefaultDusts()
	{
		registerDust(100, "Plant", "plantdust", 0x629B26, 0x8AD041, 0xC2E300);
		registerDust(200, "Gunpowder", "gundust", 0x696969, 0x979797, 0x666464);
		registerDust(300, "Lapis", "lapisdust", 0x345EC3, 0x5A82E2, 0x0087FF);
		registerDust(400, "Blaze", "blazedust", 0xEA8A00, 0xFFFE31, 0xFF6E1E);
	}

	public static class DustColor
	{
		public int primaryColor;
		public int secondaryColor;
		public int floorColor;

		public DustColor(int primaryColor, int secondaryColor, int floorColor)
		{
			this.primaryColor = primaryColor;
			this.secondaryColor = secondaryColor;
			this.floorColor = floorColor;
		}
	}
}
