package com.runicdustmod.core;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import com.runicdustmod.util.References;

import cpw.mods.fml.common.FMLLog;

public class DustConfig
{
	public static void configInit(File file)
	{
		Configuration config = new Configuration(file);
		try
		{
			config.load();

			References.BLOCK_RutID = config.getBlock("RutBlock",
					References.BLOCK_RutID).getInt(References.BLOCK_RutID);
			References.BLOCK_DustTableID = config.getBlock("DustTableBlock",
					References.BLOCK_DustTableID).getInt(
					References.BLOCK_DustTableID);
			References.BLOCK_DustID = config.getBlock("DustBlock",
					References.BLOCK_DustID).getInt(References.BLOCK_DustID);

			References.ITEM_DustID = config.getItem("DustItem",
					References.ITEM_DustID).getInt(References.ITEM_DustID);
			References.ITEM_RunicTomeID = config.get(
					Configuration.CATEGORY_ITEM, "TomeItem",
					References.ITEM_RunicTomeID).getInt(
					References.ITEM_RunicTomeID);
			References.ITEM_DustScrollID = config.getItem("ScrollItem",
					References.ITEM_DustScrollID).getInt(
					References.ITEM_DustScrollID);
			References.ITEM_SpiritSwordID = config.getItem("SpirtSwordItem",
					References.ITEM_SpiritSwordID).getInt(
					References.ITEM_SpiritSwordID);
			References.ITEM_SpiritPickID = config.getItem("SpiritPickItem",
					References.ITEM_SpiritPickID).getInt(
					References.ITEM_SpiritPickID);
			References.ITEM_ChiselID = config.getItem("ChiselItem",
					References.ITEM_ChiselID).getInt(References.ITEM_ChiselID);
			References.ITEM_SacrificeNegationID = config
					.getItem("SacrificeNegatorItem",
							References.ITEM_SacrificeNegationID).getInt(
							References.ITEM_SacrificeNegationID);
			References.ITEM_RunicPaperID = config.getItem("RunicPaperItem",
					References.ITEM_RunicPaperID).getInt(
					References.ITEM_RunicPaperID);
			References.ITEM_InscriptionID = config.getItem(
					"RunicInscriptionTag", References.ITEM_InscriptionID)
					.getInt(References.ITEM_InscriptionID);
			References.ITEM_InkID = config.getItem("RunicInk",
					References.ITEM_InkID).getInt(References.ITEM_InkID);
			References.ITEM_WornInscriptionID = config.getItem(
					"WearableInscription", References.ITEM_WornInscriptionID)
					.getInt(References.ITEM_WornInscriptionID);
			References.ITEM_PouchID = config.getItem("DustPouch",
					References.ITEM_PouchID).getInt(References.ITEM_PouchID);

			References.ENTITY_FireSpriteID = config.get(
					Configuration.CATEGORY_GENERAL, "FireSpriteEntityID",
					References.ENTITY_FireSpriteID).getInt(
					References.ENTITY_FireSpriteID);
			References.ENTITY_BlockEntityID = config.get(
					Configuration.CATEGORY_GENERAL, "BlockEntityID",
					References.ENTITY_BlockEntityID).getInt(
					References.ENTITY_BlockEntityID);

			References.Enable_Decorative_Ruts = config.get("config",
					"DecorativeRuts", References.Enable_Decorative_Ruts)
					.getBoolean(References.Enable_Decorative_Ruts);
			References.verbose = config.get("config", "verbose",
					References.verbose).getBoolean(References.verbose);
		} catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "[DustMod] : Error loading config.");
		} finally
		{
			config.save();
		}
	}
}
