package com.runicdustmod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDust extends Item{
	
	public ItemDust()
	{
		super();
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
	//commented out for when it actually does something :P
//	@Override
//	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
//	{
//		return true;
//	}
//	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ireg)
	{
		this.itemIcon = ireg.registerIcon("runicdustmod:itemDust");
	}
	
	
}
