package com.dustdefault.runes;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

import com.dustcore.DustMod;
import com.dustcore.api.InscriptionManager;
import com.dustcore.entity.EntityDust;
import com.dustcore.event.DustEvent;
import com.dustcore.event.InscriptionEvent;

public class RuneChargeInscription extends DustEvent
{

	@Override
	public void initGraphics(EntityDust e)
	{
		super.initGraphics(e);

		e.setRenderBeam(true);
		e.setRenderStar(true);
		e.setColorStarOuter(0, 0, 255);
		e.setColorBeam(0, 0, 255);

	}

	@Override
	protected void onInit(EntityDust e)
	{
		super.onInit(e);
		e.setRenderBeam(true);
		e.setRenderStar(true);
		e.setColorStarOuter(0, 0, 255);
		e.setColorBeam(0, 0, 255);
	}

	@Override
	protected void onTick(EntityDust e)
	{
		super.onTick(e);
		e.setStarScale(e.getStarScale() + 0.005F);
		if (e.ticksExisted > 20)
		{
			ItemStack inscription;
			List<EntityItem> items = this.getItems(e);

			for (EntityItem ei : items)
			{
				ItemStack i = ei.getEntityItem();
				if (i.itemID == DustMod.getWornInscription().itemID
						&& i.getItemDamage() != 0)
				{ // If the inscription is charged, it is ignored
					InscriptionEvent evt = InscriptionManager.getEvent(i);
					// DustMod.log("Charge ins found", evt);
					if (evt != null)
					{
						boolean sucess = evt.callSacrifice(this, e, i);
						// DustMod.log("Charging:", sucess);
						ei.setEntityItemStack(i);
						if (sucess)
						{
							e.fade();
							return;
						}
					}
				}
			}
			e.fizzle();
			return;
		}
	}
}
