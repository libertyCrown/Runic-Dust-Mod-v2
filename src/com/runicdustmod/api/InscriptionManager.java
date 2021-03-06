package com.runicdustmod.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.Configuration;

import com.runicdustmod.RunicDustMod;
import com.runicdustmod.event.InscriptionEvent;
import com.runicdustmod.item.ItemInscription;
import com.runicdustmod.util.References;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class InscriptionManager
{

	public static ArrayList<InscriptionEvent> events = new ArrayList<InscriptionEvent>();
	public static ArrayList<InscriptionEvent> eventsRemote = new ArrayList<InscriptionEvent>();

	public static HashMap<String, ItemStack> lastArmor = new HashMap<String, ItemStack>();

	public static Configuration config;

	public static void registerInscriptionEvent(InscriptionEvent evt)
	{
		if (getEvent(evt.id) != null)
		{
			throw new IllegalArgumentException("Inscription ID already taken! "
					+ evt.idName);
		}
		events.add(evt);

		RunicDustMod.log(Level.FINER, "Registering inscription " + evt.idName);
		if (config == null)
		{
			config = new Configuration(RunicDustMod.suggestedConfig);
			config.load();
			config.addCustomCategoryComment("INSCRIPTIONS",
					"Allow specific inscriptions to be used. Options: ALL, NONE, OPS");
			config.addCustomCategoryComment("RUNES",
					"Allow specific runes to be used. Options: ALL, NONE, OPS");
		}
		if (!evt.secret)
		{
			String permission = config
					.get("INSCRIPTIONS",
							"Allow-"
									+ evt.getInscriptionName()
											.replace(' ', '_'), evt.permission)
					.getString().toUpperCase();

			if (permission.equals("ALL") || permission.equals("NONE")
					|| permission.equals("OPS"))
			{
				evt.permission = permission;
			} else
				evt.permission = "NONE";
			if (!evt.permission.equals("ALL"))
			{
				RunicDustMod.log(Level.FINE, "Inscription permission for "
                        + evt.idName + " set to " + evt.permission);
			}
			RunicDustMod.log(Level.FINER, "Registering local inscription " + evt.idName);
			LanguageRegistry.instance().addStringLocalization(
					"insc." + evt.idName + ".name", evt.properName);
			RunicDustMod.proxy.checkInscriptionPage(evt);
			evt.isRemote = true;
		}

		config.save();
	}

	public static void registerRemoteInscriptionEvent(InscriptionEvent evt)
	{
		eventsRemote.add(evt);
		RunicDustMod.log(Level.FINER, "Registering remote inscription " + evt.idName);
		LanguageRegistry.instance().addStringLocalization(
				"insc." + evt.idName + ".name", evt.properName);
		RunicDustMod.proxy.checkInscriptionPage(evt);
		evt.isRemote = true;
	}

	public static void resetRemoteInscriptions()
	{
		RunicDustMod.log(Level.FINE, "Reseting remote inscriptions.");

		eventsRemote = new ArrayList<InscriptionEvent>();
	}

	public static void tickInscription(Player p, boolean[] buttons,
			ItemStack item)
	{
		if (((EntityPlayer) p).worldObj.isRemote)
			return;
		if (item == null || item.getItemDamage() == ItemInscription.max)
		{
			return;
		}
		InscriptionEvent event = getEvent(p);
		ItemStack last = lastArmor.get(RunicDustMod.getUsername(p));
		boolean equal = (item != null && last != null
				&& item.itemID == last.itemID && item.getTagCompound().equals(
				last.getTagCompound()));
		if (event != null && equal)
		{
			event.onUpdate((EntityPlayer) p, item, buttons);
		}
	}

	public static void tick(Player p, boolean[] buttons, ItemStack item)
	{
		if (item == null || item.getItemDamage() == ItemInscription.max)
		{
			return;
		}
		InscriptionEvent event = getEvent(item);
		ItemStack last = lastArmor.get(RunicDustMod.getUsername(p));
		boolean equal = (item != null && last != null
				&& item.itemID == last.itemID && item.hasTagCompound() && item
				.getTagCompound().equals(last.getTagCompound()));

		if (getEvent(last) != null && !equal)
		{
			getEvent(last).onRemoval((EntityPlayer) p, last);
		}

		if (event != null)
		{
			if (!equal)
			{
				event.onEquip((EntityPlayer) p, item);
			}
		}
		lastArmor.put(RunicDustMod.getUsername(p), item);
	}

	public static InscriptionEvent getEvent(Player p)
	{
		EntityPlayer ep = (EntityPlayer) p;
		ItemStack item = ep.inventory.getStackInSlot(38);
		if (item == null || item.itemID != References.getWornInscription().itemID)
			return null;
		if (item.getItemDamage() >= item.getMaxDamage() - 1)
			return null;
		return getEvent(item);
	}

	/**
	 * Get the event from this ItemWornInscription. If it is not already
	 * identified, it will be identified by the design and then saved in the
	 * tag.
	 * 
	 * @param item
	 * @return
	 */
	public static InscriptionEvent getEvent(ItemStack item)
	{
		if (item != null && item.hasTagCompound())
		{
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("eventID"))
			{
				return getEvent(tag.getInteger("eventID"));
			} else
			{
				int[][] ink;
				ink = ItemInscription.getDesign(item);
				if (ink == null)
				{
					return null;
				}
				InscriptionEvent event = null;
				for (InscriptionEvent ievt : events)
				{
					if (event != null)
						break;
					int[][] design = ievt.referenceDesign;
					for (int i = 0; i <= ink[0].length - design.length
							&& event == null; i++)
					{
						for (int j = 0; j <= ink.length - design[0].length; j++)
						{
							boolean found = true;
							for (int x = 0; x < design.length && found; x++)
							{
								for (int y = 0; y < design[0].length; y++)
								{
									if (design[x][y] != ink[j + y][i + x])
									{
										found = false;
										break;
									}
								}
							}
							if (found)
							{
								event = ievt;
								break;
							}
						}
					}
				}

				if (event != null)
				{
					RunicDustMod.log(Level.FINER, "Inscription Identified: "
                            + event.idName);
					tag = new NBTTagCompound();
					item.setTagCompound(tag);
					tag.setInteger("eventID", event.id);
					return event;
				}
			}
		}
		return null;
	}

	/**
	 * Rewrites/sets the inscription type of this inscription item
	 * 
	 * @param item
	 * @param inscription
	 */
	public static void setEvent(ItemStack item, String inscription)
	{
		InscriptionEvent evt = null;
		for (InscriptionEvent e : events)
		{
			if (e != null && e.idName.equals(inscription))
			{
				evt = e;
				break;
			}
		}
		if (evt != null)
		{
			if (!item.hasTagCompound())
				item.setTagCompound(new NBTTagCompound());
			NBTTagCompound tag = item.getTagCompound();
			tag.setInteger("eventID", evt.id);
		}
	}

	public static InscriptionEvent getEventInOrder(int ind)
	{
		return getEvents().get(ind);
	}

	public static InscriptionEvent getEvent(int id)
	{
		for (InscriptionEvent evt : events)
		{
			if (evt.id == id)
				return evt;
		}
		return null;
	}

	public static ArrayList<InscriptionEvent> getEvents()
	{
		if (RunicDustMod.proxy.isClient())
			return eventsRemote;
		return events;
	}

	public static int getArmor(EntityPlayer player, ItemStack item)
	{
		InscriptionEvent event = getEvent(item);
		if (event == null)
			return 0;
		return event.getArmorPoints(player, item);
	}

	public static void onDamage(EntityLivingBase entity, ItemStack item,
			DamageSource source, int damage)
	{
		if (entity.worldObj.isRemote)
			return;

		InscriptionEvent event = getEvent(item);
		if (event == null)
			return;
		event.onDamage(entity, item, source, damage);
	}

	public static int getPreventedDamage(EntityLivingBase player,
			ItemStack item, DamageSource source, int damage)
	{
		if (player.worldObj.isRemote)
			return damage;

		InscriptionEvent event = getEvent(item);
		// System.out.println("Hey wtf " + event);
		if (event == null)
			return damage;
		return event.getPreventedDamage(player, item, source, damage);
	}

	public static void onEquip(EntityPlayer player, ItemStack item)
	{
		if (player.worldObj.isRemote)
			return;
		InscriptionEvent event = getEvent(item);
		if (event == null)
			return;
		event.onEquip(player, item);
	}

	public static void onRemoval(EntityPlayer player, ItemStack item)
	{
		if (player.worldObj.isRemote)
			return;
		InscriptionEvent event = getEvent(item);
		if (event == null)
			return;
		event.onRemoval(player, item);
	}

	public static void onCreate(EntityPlayer player, ItemStack item)
	{
		if (player.worldObj.isRemote)
			return;
		InscriptionEvent event = getEvent(item);
		if (event == null)
			return;
		event.onCreate(player, item);
	}

	public static ItemStack onItemPickup(EntityPlayer player, ItemStack item)
	{
		if (player.worldObj.isRemote)
			return item;
		Player p = (Player) player;
		InscriptionEvent event = getEvent(p);
		InscriptionEvent last = getEvent(lastArmor.get(p));
		ItemStack insc = ((EntityPlayer) p).inventory.getStackInSlot(38);
		if (event == null)
			return item;
		return event.onItemPickup(player, insc, item);
	}

	public static ItemStack onItemRightClick(EntityPlayer player, ItemStack item)
	{
		if (player.worldObj.isRemote)
			return item;
		InscriptionEvent evt = getEvent(item);
		if (evt != null)
			return evt.onItemRightClick(player, item);
		return item;
	}

	public static void registerDefaultInscriptions()
	{
		// None yet! See the test pack.

	}

	public static boolean isEmpty()
	{
		return eventsRemote.isEmpty();
	}

}
