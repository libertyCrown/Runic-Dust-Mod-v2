/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runicdustmod.runes.standard;

import java.util.List;

import net.minecraft.entity.Entity;

import com.runicdustmod.RunicDustMod;
import com.runicdustmod.entity.EntityDust;
import com.runicdustmod.event.DustEvent;
import com.runicdustmod.util.References;

/**
 * 
 * @author billythegoat101
 */
public abstract class RuneTrap extends DustEvent
{
	protected double range = 1D;

	public RuneTrap()
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
		int compare = RunicDustMod.compareDust(References.gunDID, e.dustID);

		if (compare < 0)
		{
			e.fizzle();
			return;
		}

		e.setRenderStar(true);
	}

	public void onTick(EntityDust e)
	{
		e.setRenderStar(true);

		if (e.ticksExisted < 80)
		{
			e.setColorStarInner(140, 140, 140);
			e.setColorStarOuter(140, 140, 140);
			return;
		}

		e.setColorStarInner(0, 0, 255);
		e.setColorStarOuter(0, 0, 255);
		List<Entity> entities = this.getEntitiesExcluding(e.worldObj, e,
				e.posX, e.posY, e.posZ, 2D);

		if (entities.size() > 0)
		{
			trigger(e, e.dustID);
			e.fade();
		}
	}

	public abstract void trigger(EntityDust e, int dustLevel);
}
