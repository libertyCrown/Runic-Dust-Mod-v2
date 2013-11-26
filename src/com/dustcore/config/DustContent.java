package com.dustcore.config;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.dustcore.DustModTab;
import com.dustcore.api.DustItemManager;
import com.dustcore.block.BlockDust;
import com.dustcore.block.BlockDustTable;
import com.dustcore.block.BlockRut;
import com.dustcore.item.ItemChisel;
import com.dustcore.item.ItemDust;
import com.dustcore.item.ItemInk;
import com.dustcore.item.ItemInscription;
import com.dustcore.item.ItemNegateSacrifice;
import com.dustcore.item.ItemPlaceScroll;
import com.dustcore.item.ItemPouch;
import com.dustcore.item.ItemRunicPaper;
import com.dustcore.item.ItemRunicTome;
import com.dustcore.item.ItemSpiritPickaxe;
import com.dustcore.item.ItemSpiritSword;
import com.dustcore.item.ItemWornInscription;
import com.dustcore.tileentity.TileEntityDust;
import com.dustcore.tileentity.TileEntityDustTable;
import com.dustcore.tileentity.TileEntityRut;
import com.dustcore.util.References;

import cpw.mods.fml.common.registry.GameRegistry;

public class DustContent
{
	public static Block dust;
	public static Block dustTable;
	public static Block rutBlock;
	public static Item tome;
	public static Item dustScroll;
	public static Item spiritPickaxe;
	public static Item spiritSword;
	public static Item chisel;
	public static Item negateSacrifice;
	public static Item runicPaper;
	public static ItemInscription inscription;
	public static ItemInk ink;
	public static ItemWornInscription wornInscription;
	public static ItemPouch pouch;
	
	public static void initContent()
	{
		
		// Block registries
		dust = new BlockDust(References.BLOCK_DustID);
		GameRegistry.registerBlock(dust, ItemBlock.class,
				dust.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileEntityDust.class, "RunicDust");
		dustTable = ((Block) new BlockDustTable(References.BLOCK_DustTableID))
				.setUnlocalizedName("runicdust.dusttable").setCreativeTab(
						DustModTab.dustTab);
		GameRegistry.registerBlock(dustTable, ItemBlock.class, "RunicLexicon");
		GameRegistry.registerTileEntity(TileEntityDustTable.class,
				"RunicLexicon");
		rutBlock = new BlockRut(References.BLOCK_RutID)
				.setUnlocalizedName("runicdust.rutblock").setHardness(3.0F)
				.setResistance(5.0F);
		GameRegistry.registerBlock(rutBlock, ItemBlock.class, "RutBlock");
		GameRegistry.registerTileEntity(TileEntityRut.class, "RutBlock");

		// Item registries
		DustItemManager.idust = new ItemDust(References.ITEM_DustID, dust)
				.setUnlocalizedName("idust").setCreativeTab(DustModTab.dustTab);
		GameRegistry.registerItem(DustItemManager.idust, DustItemManager.idust.getUnlocalizedName());
		tome = new ItemRunicTome(References.ITEM_RunicTomeID)
				.setUnlocalizedName("dustlibrary").setCreativeTab(
						DustModTab.dustTab);
		GameRegistry.registerItem(tome, "DustLibrary");
		negateSacrifice = new ItemNegateSacrifice(
				References.ITEM_SacrificeNegationID).setUnlocalizedName(
				"negatesacrifice").setCreativeTab(DustModTab.dustTab);
		GameRegistry.registerItem(negateSacrifice, "NegateSacrifice");
		runicPaper = new ItemRunicPaper(
				References.ITEM_RunicPaperID).setUnlocalizedName("runicpaper")
				.setCreativeTab(DustModTab.dustTab);
		GameRegistry.registerItem(runicPaper, "RunicPaper");
		dustScroll = new ItemPlaceScroll(
				References.ITEM_DustScrollID).setUnlocalizedName("dustscroll")
				.setCreativeTab(DustModTab.dustTab);
		GameRegistry.registerItem(dustScroll, "DustScroll");
		chisel = new ItemChisel(References.ITEM_ChiselID)
				.setUnlocalizedName("dustchisel").setCreativeTab(
						DustModTab.dustTab);
		GameRegistry.registerItem(chisel, "DustChisel");
		spiritPickaxe = (new ItemSpiritPickaxe(References.ITEM_SpiritPickID,
				EnumToolMaterial.EMERALD)).setUnlocalizedName("spiritpickaxe")
				.setCreativeTab(DustModTab.dustTab);
		GameRegistry.registerItem(spiritPickaxe, "SpiritPickaxe");
		spiritSword = (new ItemSpiritSword(References.ITEM_SpiritSwordID))
				.setUnlocalizedName("spiritsword").setCreativeTab(
						DustModTab.dustTab);
		GameRegistry.registerItem(spiritSword, "SpiritSword");
		inscription = (ItemInscription) (new ItemInscription(
				References.ITEM_InscriptionID)).setUnlocalizedName(
				"runicinscription").setCreativeTab(DustModTab.dustTab);
		GameRegistry.registerItem(inscription, "DustInscription");
		ink = new ItemInk(References.ITEM_InkID);
		GameRegistry.registerItem(ink, ink.getUnlocalizedName());
		wornInscription = new ItemWornInscription(
				References.ITEM_WornInscriptionID);
		wornInscription.setCreativeTab(DustModTab.dustTab);
		pouch = new ItemPouch(References.ITEM_PouchID, dust);
		pouch.setCreativeTab(DustModTab.dustTab);
	}

	public static void initCrafting()
	{
		GameRegistry.addRecipe(new ItemStack(dustTable, 1), new Object[] {
				"dwd", "wbw", "dwd", 'd', new ItemStack(DustItemManager.idust, 1, OreDictionary.WILDCARD_VALUE), 'w',
				new ItemStack(Block.planks, 1, OreDictionary.WILDCARD_VALUE), 'b',
				new ItemStack(tome) });
		GameRegistry.addRecipe(new ItemStack(dustTable, 1), new Object[] {
				"wdw", "dbd", "wdw", 'd', new ItemStack(DustItemManager.idust, 1, OreDictionary.WILDCARD_VALUE), 'w',
				new ItemStack(Block.planks, 1, OreDictionary.WILDCARD_VALUE), 'b',
				new ItemStack(tome) });
		GameRegistry.addRecipe(new ItemStack(chisel, 1), new Object[] { "st",
				"i ", 's', new ItemStack(Block.cobblestone, 1), 't',
				new ItemStack(Item.stick, 1), 'i',
				new ItemStack(Item.ingotIron, 1) });
		GameRegistry.addRecipe(new ItemStack(inscription, 1), new Object[] {
				"s", "p", "p", 's', new ItemStack(Item.silk, 1), 'p',
				new ItemStack(runicPaper, 1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						new ItemStack(Block.tallGrass, 1, -1),
						new ItemStack(Block.tallGrass, 1, -1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						new ItemStack(Block.leaves, 1, -1),
						new ItemStack(Block.leaves, 1, -1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						new ItemStack(Block.sapling, 1, -1),
						new ItemStack(Block.sapling, 1, -1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						Item.seeds, Item.seeds });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						Block.cactus, Block.cactus });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						Block.cactus, Item.seeds });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						Block.cactus, new ItemStack(Block.sapling, 1, OreDictionary.WILDCARD_VALUE) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						Block.cactus, new ItemStack(Block.leaves, 1, -1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						Block.cactus, new ItemStack(Block.tallGrass, 1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						Item.seeds, new ItemStack(Block.sapling, 1, OreDictionary.WILDCARD_VALUE) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						Item.seeds, new ItemStack(Block.leaves, 1, -1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						Item.seeds, new ItemStack(Block.tallGrass, 1, -1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						new ItemStack(Block.sapling, 1, -1),
						new ItemStack(Block.leaves, 1, -1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						new ItemStack(Block.sapling, 1, -1),
						new ItemStack(Block.tallGrass, 1, -1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 100),
				new Object[] { new ItemStack(Item.coal, 1),
						new ItemStack(Block.leaves, 1, -1),
						new ItemStack(Block.tallGrass, 1, -1) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 12, 200),
				new Object[] { Item.gunpowder, new ItemStack(DustItemManager.idust, 1, 100),
						new ItemStack(DustItemManager.idust, 1, 100) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 8, 300),
				new Object[] { new ItemStack(Item.coal, 1),
						new ItemStack(Item.dyePowder, 2, 4),
						new ItemStack(Item.dyePowder, 2, 4),
						new ItemStack(Item.dyePowder, 2, 4) });
		GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 12, 400),
				new Object[] { Item.blazePowder, new ItemStack(DustItemManager.idust, 1, 300),
						new ItemStack(DustItemManager.idust, 1, 300),
						new ItemStack(DustItemManager.idust, 1, 300) });
		GameRegistry.addShapelessRecipe(new ItemStack(tome, 1, 0),
				new Object[] { new ItemStack(DustItemManager.idust, 1, OreDictionary.WILDCARD_VALUE), Item.book });
		GameRegistry.addShapelessRecipe(new ItemStack(runicPaper, 1),
				new Object[] { Item.paper, Item.goldNugget, Item.goldNugget });

		for (int i = 1; i < 5; i++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(DustItemManager.idust, 1, i * 100),
					new ItemStack(DustItemManager.idust, 1, i));
		}
	}
}
