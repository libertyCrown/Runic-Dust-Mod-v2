package com.runicdust.dustcore.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;

import com.runicdust.dustcore.DustModCore;
import com.runicdust.dustcore.entity.EntityDust;
import com.runicdust.dustcore.item.ItemInscription;
import com.runicdust.dustcore.item.ItemWornInscription;

public class InscriptionEvent
{

	public int[][] referenceDesign;
	public int id;
	public String idName = "";
	public String properName = "";

	public String description = "";
	public String notes = ""; // for sacrifices and stuff
	public String author = "";

	public boolean isRemote = false;

	public boolean secret = false;
	public String permission = "ALL";

	/** Set by the coder, unalterable by user configs.
      * Setting it to false disallows all use, but if true it will not affect anything.
	  * This is for the case where something breaks, it gives the coder time to
	  * fix it later.
	 */
	public boolean permaAllowed = true;

	public InscriptionEvent(int[][] design, String idName, String properName,
			int id)
	{
		if (design.length > 16 || design[0].length > 16)
		{
			throw new IllegalArgumentException(
					"Inscription dimensions too big! " + idName + " Max:16x16");
		}
		this.referenceDesign = design;
		this.id = id;
		this.idName = idName;
		this.properName = properName;
	}

	public boolean canPlayerKnowInscription(EntityPlayer player)
	{
		boolean isOP = true;
		try
		{
			isOP = MinecraftServer.getServer().getConfigurationManager()
					.isPlayerOpped(player.username);
		} catch (Exception e)
		{
		}
		return permaAllowed
				&& (this.permission.equals("ALL") || (this.permission
						.equals("OP") && isOP));
	}

	public String getDescription()
	{
		return description;
	}

	public String getNotes()
	{
		return notes;
	}

	public String getIDName()
	{
		return idName;
	}

	public String getInscriptionName()
	{
		return properName;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setDescription(String desc)
	{
		this.description = desc;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public void setIDName(String idName)
	{
		this.idName = idName;
	}

	public void setInscriptionName(String name)
	{
		this.properName = name;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	/**
	 * Called when this inscription is charged with a charging rune. It is the
	 * responsibility of this function to set the new damage level of the
	 * inscription item. 0 being full power, 999 being empty. The power level to
	 * begin with will be 999.
	 * 
	 * @param rune
	 *            The DustEvent used to check sacrifices
	 * @param e
	 *            The EntityDust used to get items and nearby player things
	 * @param item
	 *            The inscription item.
	 * @return True if charging of the inscription succeeded. False otherwise.
	 */
	public boolean callSacrifice(DustEvent rune, EntityDust e, ItemStack item)
	{
		return false;
	}

	public void onUpdate(EntityLivingBase wearer, ItemStack item,
			boolean[] buttons)
	{
	}

	public void onDamage(EntityLivingBase entity, ItemStack item,
			DamageSource source, int damage)
	{
	}

	public int getPreventedDamage(EntityLivingBase player, ItemStack item,
			DamageSource source, int damage)
	{
		return damage;
	}

	public int getArmorPoints(EntityPlayer wearer, ItemStack item)
	{
		return 0;
	}

	public void onRemoval(EntityPlayer wearer, ItemStack item)
	{
	}

	public void onEquip(EntityPlayer wearer, ItemStack item)
	{
	}

	public void onCreate(EntityPlayer creator, ItemStack item)
	{
	}

	/**
	 * Called when the item right-clicked while in hand
	 * 
	 * @param player
	 * @param item
	 */
	public ItemStack onItemRightClick(EntityPlayer player, ItemStack item)
	{
		return item;
	}

	/**
	 * Called when the player picks up an item in the world returns the mutated
	 * item to be put in the inventory
	 * 
	 * @param player
	 * @param pickedup
	 * @return
	 */
	public ItemStack onItemPickup(EntityPlayer player, ItemStack insc,
			ItemStack pickedup)
	{
		return pickedup;
	}

	public void damage(EntityLivingBase wearer, ItemStack item, int amt)
	{
		int curDamage = item.getItemDamage();
		if (curDamage + amt > item.getMaxDamage() - 1)
		{
			amt = (item.getMaxDamage() - curDamage) - 1;
		}
		item.damageItem(amt, wearer);
		if (item.getItemDamage() >= ItemWornInscription.max - 1)
		{
			DustModCore.sendRenderBreakItem(wearer, item);
		}
		if (item.stackSize <= 0)
		{
			item.stackSize = 1;
			item.setItemDamage(ItemInscription.max);
		}
	}

	@Override
	public String toString()
	{
		return super.toString() + " InscriptionID:[" + this.idName + ":"
				+ this.id + "]";
	}
}
