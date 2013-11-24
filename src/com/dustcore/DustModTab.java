package com.dustcore;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.dustcore.config.DustContent;

public class DustModTab
{
	public static CreativeTabs dustTab = new CreativeTabs(
			"Runic Dust Mod- Core")
	{
		public ItemStack getIconItemStack()
		{
			return new ItemStack(DustContent.tome);
		}

		public String getTabLabel()
		{
			return "Runic Dust Mod- Core";
		}

		public String getTranslatedTabLabel()
		{
			return getTabLabel();
		}
	};

}
