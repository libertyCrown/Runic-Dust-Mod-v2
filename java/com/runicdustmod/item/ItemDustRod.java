package com.runicdustmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemDustRod extends Item{
	
	public ItemDustRod()
	{
		super();
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ireg)
	{
		this.itemIcon = ireg.registerIcon("runicdustmod:dustRod");
	}

}
