package com.runicdustmod.item;

import com.runicdustmod.util.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemRunicPaper extends Item
{

	public ItemRunicPaper(int id)
	{
		super(id);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(References.spritePath
				+ "runicPaper");
	}
	
}
