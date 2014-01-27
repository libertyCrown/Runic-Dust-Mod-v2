package com.runicdustmod.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.runicdustmod.RunicDustMod;
import com.runicdustmod.DustShape;
import com.runicdustmod.api.DustManager;
import com.runicdustmod.core.DustContent;
import com.runicdustmod.util.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRunicTome extends Item
{
	private int blockID;
	private int tex;

	public ItemRunicTome(int i)
	{
		super(i);
		blockID = DustContent.dust.blockID;
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world,
			EntityPlayer p){
	    RunicDustMod.proxy.openTomeGUI(itemstack, p);
		return super.onItemRightClick(itemstack, world, p);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(References.spritePath + "dustTome");
	}
}