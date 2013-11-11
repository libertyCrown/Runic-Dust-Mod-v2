package com.runicdust;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.runicdust.config.DustContent;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DustModTab
{
	public static CreativeTabs dustTab = new CreativeTabs("Runic Dust Mod- Core")
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
