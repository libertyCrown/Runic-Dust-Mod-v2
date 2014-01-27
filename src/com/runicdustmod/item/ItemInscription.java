package com.runicdustmod.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.runicdustmod.RunicDustMod;
import com.runicdustmod.api.InscriptionManager;
import com.runicdustmod.client.inventory.InventoryInscription;
import com.runicdustmod.event.InscriptionEvent;
import com.runicdustmod.util.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemInscription extends Item
{

	public static final int max = 3600 * 2;

	private Icon dryingIcon;
	private Icon driedIcon;
	private Icon blankIcon;

	public ItemInscription(int par1)
	{
		super(par1);
		this.setMaxStackSize(1);
		this.setMaxDamage(max);
	}

	public InventoryInscription getInventory(ItemStack item)
	{
		return new InventoryInscription(item);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world,
			EntityPlayer player)
	{
		player.openGui(RunicDustMod.instance, 0, world, (int) player.posX,
				(int) player.posY, (int) player.posZ);

		return item;
	}

	public static int[][] getDesign(ItemStack item)
	{
		if (item == null || !item.hasTagCompound())
			return null;

		int[][] rtn = new int[16][16];

		NBTTagCompound tag = item.getTagCompound();
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 16; j++)
			{
				rtn[i][j] = tag.getInteger(i + "," + j);
			}
		}

		return rtn;
	}

	public static void removeDesign(ItemStack item)
	{

		if (item == null || !item.hasTagCompound())
			return;

		NBTTagCompound tag = item.getTagCompound();
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 16; j++)
			{
				tag.removeTag(i + "," + j);
			}
		}
	}

	public static boolean isDesignEmpty(ItemStack item)
	{
		if (item == null || !item.hasTagCompound())
			return false;
		NBTTagCompound tag = item.getTagCompound();
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 16; j++)
			{
				int check = tag.getInteger(i + "," + j);
				if (check != 0)
					return false;
			}
		}

		return true;
	}

	@Override
	public void onUpdate(ItemStack item, World world, Entity ent, int par4,
			boolean par5)
	{
		int meta = item.getItemDamage();
		if (isDried(item))
		{
			item.setItemDamage(0);
			return;
		}
		if (meta > 0)
		{
			if (isDesignEmpty(item))
			{
				item.setItemDamage(0);
			} else
			{
				int amt = 3;
				int x = (int) ent.posX;
				int z = (int) ent.posZ;

				if (world.isRaining())
				{
					amt = 1;
				}
				if (world.getBiomeGenForCoords(x, z).getFloatTemperature() > 1.0F)
				{
					amt = 6;
				}
				if (!References.debug)
					item.setItemDamage(meta + amt);
			}
		}
		if (meta >= max)
		{
			// DUDE FUCKING LOOK AT THIS COMMENT NEXT TIME THIS DROVE ME MAD FOR
			// 15 MINUTES****************************
			// Get event also logs the EventID into the item's nbt
			InscriptionEvent event = InscriptionManager.getEvent(item);
			int id = -1;
			if (event != null)
			{
				id = event.id;
				InscriptionManager.onCreate((EntityPlayer) ent, item);
				item.itemID = References.getWornInscription().itemID;
				item.setItemDamage(ItemInscription.max);
			} else
			{
				setDried(item);
			}
		}
		super.onUpdate(item, world, ent, par4, par5);
	}

	@Override
	public boolean getShareTag()
	{
		return true;
	}

	public boolean isDried(ItemStack item)
	{
		if (item.hasTagCompound())
		{
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("dried") && tag.getBoolean("dried"))
				return true;
			else
			{
				tag.setBoolean("dried", false);
				return false;
			}
		}
		return false;
	}

	public void setDried(ItemStack item)
	{
		item.getTagCompound().setBoolean("dried", true);
		item.setItemDamage(0);
	}

	public String getUnlocalizedName(ItemStack itemstack)
	{
		boolean isDried = isDried(itemstack);
		int damage = itemstack.getItemDamage();
		if (isDried)
			return "driedinsc";
		else if (damage != 0)
			return "dryinginsc";
		else
			return "emptyinsc";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	public Icon getIcon(ItemStack stack, int renderPass)
	{
		int meta = stack.getItemDamage();
		boolean isDried = isDried(stack);
		int damage = stack.getItemDamage();
		if (isDried)
			return driedIcon;
		else if (damage != 0)
			return dryingIcon;
		else
			return blankIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.blankIcon = iconRegister.registerIcon(References.spritePath
				+ "blankInscription");
		this.dryingIcon = iconRegister.registerIcon(References.spritePath
				+ "dryingInscription");
		this.driedIcon = iconRegister.registerIcon(References.spritePath
				+ "driedInscription");
	}
}
