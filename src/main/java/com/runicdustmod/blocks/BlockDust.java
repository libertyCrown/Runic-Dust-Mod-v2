package com.runicdustmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDust extends Block{
	
	public BlockDust() {
		
		//TODO- define correct material
		super(Material.field_151566_D);
		
		//TODO- this.setCreativeTab
		this.func_149647_a(CreativeTabs.tabMisc);
		
		//TODO- this.setBlockHardness
		this.func_149711_c(1.5F);
		
		//TODO- this.setBlockResistance
		this.func_149752_b(10.0F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	//TODO-     registerIcons
	public void func_149651_a(IIconRegister ireg)
	{
		//TODO- blockIcon
		this.field_149761_L = ireg.registerIcon("runicdustmod:test");
	}
}
