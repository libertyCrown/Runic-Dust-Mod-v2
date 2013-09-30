package com.runicdust.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**Licensed under the GPLv3.  Please take the time to read the license before
 * modifying this code.
 *
 * @author zombiepig333
 *
 */

public class BlockDust extends BlockContainer
{
	public static final int DUST_UNUSED = 0;
	public static final int DUST_ACTIVATING = 1;
	public static final int DUST_ACTIVE = 2;
	public static final int DUST_DEAD = 3;
	
	private Icon topTexture;
	private Icon sideTexture;
	
	protected BlockDust(int par1, Material par2Material)
	{
		super(par1, Material.circuits);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
		this.setStepSound(Block.soundGrassFootstep);
		this.setHardness(0.2F);
		this.disableStats();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
		int metadata = world.getBlockMetadata(i, j, k);
		
		if (entity instanceof EntityItem && metadata != DUST_DEAD)
		{
			EntityItem eItem = (EntityItem)entity;
			eItem.age = 0;
			EntityPlayer player = world.getClosestPlayerToEntity(eItem, 0.6);
			
			if (player == null)
			{
				eItem.delayBeforeCanPickup = 10;
				return;
			}
		}
		
		if (entity instanceof EntityXPOrb && metadata != DUST_DEAD)
		{
			EntityXPOrb xp = (EntityXPOrb) entity;
			xp.xpOrbAge = 0;
			EntityPlayer player = world.getClosestPlayerToEntity(xp, 3.0);
			
			if (player == null)
			{
				xp.setPosition(xp.prevPosX, xp.prevPosY, xp.prevPosZ);
				return;
			}
			
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item)
	{
		super.onBlockPlacedBy(world, x, y, z, entity, item);
		
		ItemStack equipped = ((EntityPlayer) entity).getCurrentEquippedItem();
		if (equipped != null)
		{
			//TODO- implement pouch
			if (equipped.itemID != 256/*RunicDust.pouch.itemID*/)
			{
				equipped.stackSize++;
			}
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		//TODO-better block check(this WILL probably fail at 1.7)
		Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
		if (block == null)
		{
			return false;
		}
		else
		{
			return world.isBlockSolidOnSide(x, y - 1, z, ForgeDirection.UP) || block == Block.glass /*|| block == DustMod.rutBlock*/;
		}
	}
	
	//TODO-make a block model
	/*
	@Override
	public int getRenderType()
	{
		return RunicDust.proxy.getBlockModel(this);
	}*/
	
	@Override
	public int colorMultiplier(IBlockAccess blockaccess, int x, int y, int z)
	{
		int metadata = blockaccess.getBlockMetadata(x, y, z);
		switch(metadata)
		{
		case DUST_UNUSED:
			
		case DUST_ACTIVATING:
			
		case DUST_ACTIVE:
			
		case DUST_DEAD:
			
		default:
			return 0;
		
		}
		
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		//TODO-make tile!!
		return null; //new TileEntityDust();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icons)
	{
		this.topTexture = icons.registerIcon("runicdust:dust_Top");
		this.sideTexture = icons.registerIcon("runicdust:dust_Side");
	}
	
}
