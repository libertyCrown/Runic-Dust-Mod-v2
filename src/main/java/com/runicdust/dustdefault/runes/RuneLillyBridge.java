/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runicdust.dustdefault.runes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.runicdust.dustcore.entity.EntityDust;
import com.runicdust.dustcore.event.DustEvent;

/**
 * 
 * @author billythegoat101
 */
public class RuneLillyBridge extends DustEvent
{
	public RuneLillyBridge()
	{
		super();
	}

	@Override
	public void initGraphics(EntityDust e)
	{
		super.initGraphics(e);

		e.setRenderStar(true);
		e.setColorStarOuter(0, 255, 0);

	}

	public void onInit(EntityDust e)
	{
		World world = e.worldObj;
		ItemStack[] req = this.sacrifice(e, new ItemStack[]{ 
				new ItemStack(Block.leaves, 4, 0)
		});

		if (req[0].stackSize != 0)
		{
			e.fizzle();
			return;
		}

		e.rotationYaw = ((e.rot + 1) % 4) * 90;

		e.setRenderStar(true);
		e.setColorStarOuter(0, 255, 0);
	}

	public void onTick(EntityDust e)
	{
		int period = 20;

		if (e.ticksExisted % period == 0)
		{
			World world = e.worldObj;
			int dist = (int) (e.ticksExisted / period + 1) * 2;
			int y = e.getY() - 1;
			int x = e.getX();
			int z = e.getZ();

			if (e.rotationYaw == 90)
			{
				x -= dist;
			} else if (e.rotationYaw == 270)
			{
				x += dist;
			} else if (e.rotationYaw == 180)
			{
				z -= dist;
			} else if (e.rotationYaw == 0)
			{
				z += dist;
			}

			for (int i = -1; i <= 1; i++)
			{
				if (world.getBlockMaterial(x, y + i - 1, z) == Material.water
						&& world.getBlockId(x, y + i, z) == 0)
				{
					world.setBlock(x, y + i, z, Block.waterlily.blockID, 0, 3);
				}
			}
		}

		if (e.ticksExisted > 16 * period)
		{
			e.fade();
		}
	}
}
