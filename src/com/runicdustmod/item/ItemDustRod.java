package com.runicdustmod.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.runicdustmod.DustModTab;
import com.runicdustmod.DustShape;
import com.runicdustmod.RunicDustMod;
import com.runicdustmod.api.DustManager;
import com.runicdustmod.util.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDustRod extends Item{

	public ItemDustRod(int id) {
		super(id);
		this.setCreativeTab(DustModTab.dustTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg)
	{
		this.itemIcon = reg.registerIcon(References.spritePath + "dustRod");
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

		if (RunicDustMod.isDust(world.getBlockId(i, j, k)))
		{
			if (world.getBlockMetadata(i, j, k) == References.DustMetaUsed)
			{
				world.setBlock(i, j, k, 0, 0, 3);
				j--;
			} 
			else
			{
				return false;
			}
		}

		return true;
	}

}
