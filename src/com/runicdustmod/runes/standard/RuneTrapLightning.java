/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runicdustmod.runes.standard;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.runicdustmod.entity.EntityDust;

/**
 * 
 * @author billythegoat101
 */
public class RuneTrapLightning extends RuneTrap
{

	@Override
	public void onInit(EntityDust e)
	{
		super.onInit(e);
		ItemStack[] sac = new ItemStack[] { new ItemStack(Item.ingotIron, 3) };
		sac = this.sacrifice(e, sac);

		if (sac[0].stackSize > 0)
		{
			e.fizzle();
			return;
		}
	}

	public RuneTrapLightning()
	{
		super();
	}

	public void trigger(EntityDust e, int level)
	{
		List<Entity> entities = getEntities(e, 2D * level / 100);

		for (Entity i : entities)
		{
			if (i instanceof EntityLiving
					&& e.getDistanceToEntity(i) < 2D * level / 100)
			{
				e.worldObj.addWeatherEffect(new EntityLightningBolt(e.worldObj,
						e.posX, e.posY - EntityDust.yOffset, e.posZ));
				e.fade();
			}
		}
	}
}
