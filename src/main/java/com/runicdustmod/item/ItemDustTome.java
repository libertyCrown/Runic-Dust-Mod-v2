package com.runicdustmod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDustTome extends Item {
	
	public ItemDustTome()
	{
		super();
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ireg)
	{
		this.itemIcon = ireg.registerIcon("runicdustmod:dustTome");
	}

}
