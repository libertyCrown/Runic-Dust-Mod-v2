package com.runicdust.dustcore.item;

import com.runicdust.dustcore.DustModCore;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.runicdust.dustcore.DustShape;
import com.runicdust.dustcore.api.DustManager;
import com.runicdust.dustcore.core.DustContent;
import com.runicdust.dustcore.util.References;

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
			EntityPlayer p)
	{
		if (p.isSneaking())
		{
			DustModCore.proxy.openTomeGUI(itemstack, p);
		}

		return super.onItemRightClick(itemstack, world, p);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer p, World world,
			int i, int j, int k, int l, float x, float y, float z)
	{

		int page = itemstack.getItemDamage();

		if (world.isRemote || page == 0 || p.isSneaking()
				|| !p.capabilities.isCreativeMode)
		{
			return false;
		}

		DustShape ds = DustManager.getShape(page - 1);
		int r = (MathHelper
				.floor_double((double) (p.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3);

		if (DustModCore.isDust(world.getBlockId(i, j, k)))
		{
			if (world.getBlockMetadata(i, j, k) == References.DustMetaUsed)
			{
				world.setBlock(i, j, k, 0, 0, 3);
				j--;
			} else
			{
				return false;
			}
		}

		return true;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(References.spritePath
				+ "dustlibrary");
	}
}