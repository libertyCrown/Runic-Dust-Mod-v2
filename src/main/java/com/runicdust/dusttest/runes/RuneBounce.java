/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runicdust.dusttest.runes;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.runicdust.dustcore.entity.EntityDust;
import com.runicdust.dustcore.event.DustEvent;

/**
 * 
 * @author billythegoat101
 */
public class RuneBounce extends DustEvent
{
	public RuneBounce()
	{
		super();
	}

	@Override
	public void initGraphics(EntityDust e)
	{
		super.initGraphics(e);
		e.setRenderStar(true);

	}

	public void onInit(EntityDust e)
	{
		ItemStack[] req = new ItemStack[] { new ItemStack(Item.slimeBall, 4) };
		sacrifice(e, req);

		if (req[0].stackSize > 0)
		{
			e.fizzle();
			return;
		}

		// e.renderFlamesDust = true;
	}

	public void onTick(EntityDust e)
	{
		List<Entity> entities = this.getEntities(e, 0.35D);
		for (Entity i : entities)
		{
			if (i instanceof EntityLiving)
			{
				EntityLivingBase el = (EntityLivingBase) i;
				double cons = 0;
				double yVel = i.motionY + cons;
				double diff = e.posY - el.prevPosY;
				if (el.isAirBorne && yVel < 0.7D)
				{
					((EntityLiving) el).getJumpHelper().setJumping();
					((EntityLiving) el).getJumpHelper().doJump();
					i.addVelocity(0, 1.27D, 0);
					i.velocityChanged = true;
				}
				if (!el.onGround)
				{
					i.fallDistance = 0;
				} else
				{
					el.setJumping(false);
				}
			}
		}
		entities = this.getEntities(e, 3D);

		for (Entity i : entities)
		{
			if (i instanceof EntityLivingBase)
			{
				EntityLivingBase el = (EntityLivingBase) i;
				if (!el.onGround /* i.motionY < 0 */)
				{
					i.fallDistance = 0;
				} else
				{
					el.setJumping(false);
				}
			}
		}
	}
}
