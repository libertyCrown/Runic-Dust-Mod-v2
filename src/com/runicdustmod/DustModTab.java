package com.runicdustmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.runicdustmod.core.DustContent;

public class DustModTab
{
	public static CreativeTabs dustTab = new CreativeTabs(
			"Runic Dust Mod")
	{
		public ItemStack getIconItemStack()
		{
			return new ItemStack(DustContent.tome);
		}

		public String getTabLabel()
		{
			return "Runic Dust Mod";
		}

		public String getTranslatedTabLabel()
		{
			return getTabLabel();
		}
	};

}
