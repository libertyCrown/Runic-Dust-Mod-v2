package com.runicdust.dustcore.item;

import com.runicdust.dustcore.util.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemNegateSacrifice extends Item
{

	public ItemNegateSacrifice(int id)
	{
		super(id);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(References.spritePath
				+ "negateSacrifice");
	}

}
