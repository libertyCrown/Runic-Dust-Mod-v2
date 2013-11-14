package com.dustcore.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

import com.dustcore.util.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class DustModItem extends Item 
{

	public DustModItem(int par1) {
		super(par1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.itemIcon = par1IconRegister.registerIcon(References.spritePath + this.getUnlocalizedName().replace("item.", ""));
	}
}
