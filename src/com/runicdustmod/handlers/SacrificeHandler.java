package com.runicdustmod.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import com.runicdustmod.entity.EntityDust;

public class SacrificeHandler
{
	public ItemStack itemType;
	public int entityType = -1;
	public boolean isComplete = false;
	public Entity entity;

	public SacrificeHandler(ItemStack item)
	{
		this.itemType = item;
	}

	public SacrificeHandler(EntityLiving entity)
	{
		this.entityType = EntityList.getEntityID(entity);
	}

	public SacrificeHandler(int ent)
	{
		this.entityType = ent;
	}

	public SacrificeHandler(String entityName)
	{
		Entity ent = EntityList.createEntityByName(entityName, null);
		this.entityType = EntityList.getEntityID(ent);
	}

	public boolean handleObject(EntityDust e, Entity ent)
	{
		if (ent == null)
		{
			return isComplete;
		}

		if (ent instanceof EntityItem)
		{
			ItemStack in = ((EntityItem) ent).getEntityItem();

			if (in.itemID == itemType.itemID
					&& itemType.stackSize > 0
					&& (in.getItemDamage() == itemType.getItemDamage() || itemType
							.getItemDamage() == -1))
			{
				int amt = in.stackSize;
				in.stackSize -= itemType.stackSize;

				if (in.stackSize <= 0)
				{
					ent.setDead();
				}

				itemType.stackSize -= amt;

				if (itemType.stackSize < 0)
				{
					itemType.stackSize = 0;
				}

				e.data[15] = in.itemID;
				((EntityItem) ent).setEntityItemStack(in);
				return true;
			}
		} else if (ent instanceof EntityLiving)
		{
			int id = EntityList.getEntityID(ent);

			if (id == entityType)
			{
				e.data[15] = id;
				EntityLiving el = (EntityLiving) ent;
				el.attackEntityFrom(DamageSource.magic, el.getHealth() * 10);
				return true;
			}
		}

		return false;
	}

	public boolean matchObject(Entity ent)
	{
		if (ent == null)
		{
			return false;
		}

		if (ent instanceof EntityItem && itemType != null)
		{
			ItemStack in = ((EntityItem) ent).getEntityItem();

			if (in.itemID == itemType.itemID
					&& (in.getItemDamage() == itemType.getItemDamage() || itemType
							.getItemDamage() == -1))
			{
				return true;
			}
		} else if (ent instanceof EntityLiving && entityType != -1)
		{
			int id = EntityList.getEntityID(ent);

			if (id == entityType)
			{
				return true;
			}
		}

		return false;
	}

	public SacrificeHandler clone()
	{
		SacrificeHandler rtn = null;

		if (itemType != null)
		{
			rtn = new SacrificeHandler(new ItemStack(itemType.itemID,
					itemType.stackSize, itemType.getItemDamage()));
		} else
		{
			rtn = new SacrificeHandler(entityType);
		}

		return rtn;
	}
}
