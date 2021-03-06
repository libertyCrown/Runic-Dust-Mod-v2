/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runicdustmod.runes.standard;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.runicdustmod.entity.EntityDust;
import com.runicdustmod.event.DustEvent;
import com.runicdustmod.tileentity.TileEntityDust;

/**
 * 
 * @author billythegoat101
 */
public class RuneRabbitHole extends DustEvent
{
	public static final int thick = 2;

	public RuneRabbitHole()
	{
		super();
	}

	@Override
	public void initGraphics(EntityDust e)
	{
		super.initGraphics(e);

		e.setRenderStar(true);
		e.setColorStar(255, 255, 255);

	}

	@Override
	public void onInit(EntityDust e)
	{
		super.onInit(e);

		if (!this.takeHunger(e, 4))
		{
			e.fizzle();
			return;
		}

		e.setRenderStar(true);
		e.setColorStar(255, 255, 255);
		int x = e.getX();
		int y = e.getY();
		int z = e.getZ();
		World world = e.worldObj;

		int r = 1;
		int h = 3;

		Block b = Block.blocksList[world.getBlockId(x, y - h - thick - 1, z)];

		if (world.getBlockId(x, y - thick - 1, z) == 0)
		{
			doCheck(e);
			world.setBlock(x, y - h - thick - 1, z, Block.cobblestone.blockID,
					0, 0);
			world.setBlock(x, y - h - thick, z, Block.torchWood.blockID, 0, 0);
			return;
		}

		switch (e.dustID)
		{
			case 100:
				r = 1;
				h = 3;
				break;
			case 200:
				r = 2;
				h = 3;
				break;
			case 300:
				r = 2;
				h = 5;
				break;
			case 400:
				r = 4;
				h = 6;
				break;
		}
		for (int i = -r; i <= r; i++)
		{
			for (int k = -r; k <= r; k++)
			{
				for (int j = -thick; j >= -h - thick; j--)
				{
					if (j == -thick)
					{
						Block above = Block.blocksList[world.getBlockId(x + i,
								y + j + 1, z + k)];

						if (above != null && above instanceof BlockSand)
						{
							world.setBlock(x + i, y + j, z + k,
									Block.sandStone.blockID, 0, 3);
						}
					} else if (canBreakBlock(e, x + i, y + j, z + k))
					{
						world.setBlock(x + i, y + j, z + k, 0, 0, 3);
					}
				}
			}
		}

		if (b != null && !(b instanceof BlockFluid))
		{
			world.setBlock(x, y - h - thick - 1, z, Block.cobblestone.blockID,
					0, 0);
			world.setBlock(x, y - h - thick, z, Block.torchWood.blockID, 0, 0);
		}

		doCheck(e);
	}

	@Override
	public void onTick(EntityDust e)
	{
		super.onTick(e);

		if (e.ticksExisted % 10 == 0)
		{
			yCheck(e);
		}

		List<Entity> ents;

		if (e.ram <= 0)
		{
			e.setColorStar(255, 255, 255);
			ents = this.getEntities(e, 0.2D);

			for (Entity ei : ents)
			{
				if (ei instanceof EntityPlayer)
				{
					EntityPlayer ep = (EntityPlayer) ei;
					e.ram = 45;
					// ep.setVelocity(0, 0, 0);
					ep.setPositionAndUpdate((double) e.getX() + 0.5D,
							e.data[0] + 1/* + ei.yOffset */+ 0.5D,
							(double) e.getZ() + 0.5D);
					ep.fallDistance = 0;
				}
			}

			ents = this.getEntities(e.worldObj, e.getX(), e.data[0] + 2,
					e.getZ(), 0.5D);

			for (Entity ei : ents)
			{
				if (ei instanceof EntityPlayer && ei.isSneaking())
				{
					EntityPlayer ep = (EntityPlayer) ei;
					ep.setPositionAndUpdate((double) e.getX() + 0.5D,
							(double) e.getY() /* + 0 + ei.yOffset */+ 0.5D,
							(double) e.getZ() + 0.5D);
					ep.fallDistance = 0;
					e.ram = 45;
				}
			}
		} else
		{
			e.setColorStar(255, 255, 0);
			e.ram--;
		}
	}

	public boolean canBreakBlock(EntityDust e, int x, int y, int z)
	{

		if (!e.canAlterBlock(x, y, z))
			return false;

		int id = e.worldObj.getBlockId(x, y, z);
		Block b = Block.blocksList[id];
		if (b == null)
			return false;

		if (b.getBlockHardness(e.worldObj, x, y, z) >= Block.obsidian
				.getBlockHardness(e.worldObj, x, y, z))
		{
			return false;
		} else if (b == Block.bedrock)
		{
			return false;
		}
		return true;
	}

	private void yCheck(EntityDust e)
	{
		int x = e.getX();
		int y = e.data[0];
		int z = e.getZ();
		World w = e.worldObj;
		int id1 = w.getBlockId(x, y, z);
		int id2 = w.getBlockId(x, y + 1, z);
		Block b1 = Block.blocksList[id1];
		Block b2 = Block.blocksList[id2];

		if ((b1 != null && !b1.isOpaqueCube()) || b1 == null)
		{
			doCheck(e);
		} else if (b2 != null && b2.isOpaqueCube())
		{
			doCheck(e);
		}
	}

	private void doCheck(EntityDust e)
	{
		int y;

		for (y = e.getY() - 1 - thick; y > 3 && y > e.getY() - 1 - thick - 64; y--)
		{
			int id = e.worldObj.getBlockId(e.getX(), y, e.getZ());
			Block block = Block.blocksList[id];

			if (block != null && (block.isOpaqueCube()))
			{
				break;
			}
		}

		e.data[0] = y;
	}

	@Override
	public void onRightClick(EntityDust e, TileEntityDust ted, EntityPlayer p)
	{
		super.onRightClick(e, ted, p);
	}

	@Override
	public void onUnload(EntityDust e)
	{
		super.onUnload(e);
	}
}
