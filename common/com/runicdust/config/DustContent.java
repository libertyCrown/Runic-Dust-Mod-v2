package com.runicdust.config;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.runicdust.DustMod;
import com.runicdust.DustModItem;
import com.runicdust.DustModTab;
import com.runicdust.block.BlockDust;
import com.runicdust.block.BlockDustTable;
import com.runicdust.block.BlockRut;
import com.runicdust.item.ItemChisel;
import com.runicdust.item.ItemDust;
import com.runicdust.item.ItemInk;
import com.runicdust.item.ItemInscription;
import com.runicdust.item.ItemPlaceScroll;
import com.runicdust.item.ItemPouch;
import com.runicdust.item.ItemRunicTome;
import com.runicdust.item.ItemSpiritPickaxe;
import com.runicdust.item.ItemSpiritSword;
import com.runicdust.item.ItemWornInscription;
import com.runicdust.tileentity.TileEntityDust;
import com.runicdust.tileentity.TileEntityDustTable;
import com.runicdust.tileentity.TileEntityRut;
import com.runicdust.util.References;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class DustContent 
{
	public static Block dust;
	protected static Block dustTable;
	public static Block rutBlock;
	public static DustModItem idust;
	public static DustModItem tome;
	public static DustModItem dustScroll;
	public static Item spiritPickaxe;
	public static Item spiritSword;
	public static DustModItem chisel;
	public static DustModItem negateSacrifice;
	public static DustModItem runicPaper;
	public static ItemInscription inscription;
	public static ItemInk ink;
	public static ItemWornInscription wornInscription;
	public static ItemPouch pouch;
	
	public static void initContent()
	{
		dust = new BlockDust(References.BLOCK_DustID);
		idust = (DustModItem)(new ItemDust(References.ITEM_DustID, dust)).setUnlocalizedName("idust").setCreativeTab(DustModTab.dustTab);
		dustTable = ((Block) new BlockDustTable(References.BLOCK_DustTableID)).setUnlocalizedName("dustTable").setCreativeTab(DustModTab.dustTab);
		tome = (DustModItem)(new ItemRunicTome(References.ITEM_RunicTomeID)).setUnlocalizedName("dustlibrary").setCreativeTab(DustModTab.dustTab);
		negateSacrifice = (DustModItem)new DustModItem(References.ITEM_SacrificeNegationID).setUnlocalizedName("negateSacrifice").setCreativeTab(DustModTab.dustTab);
		runicPaper = (DustModItem)(new DustModItem(References.ITEM_RunicPaperID)).setUnlocalizedName("runicPaper").setCreativeTab(DustModTab.dustTab);
		dustScroll = (DustModItem)(new ItemPlaceScroll(References.ITEM_DustScrollID)).setUnlocalizedName("dustscroll").setCreativeTab(DustModTab.dustTab);
		rutBlock = new BlockRut(References.BLOCK_RutID).setUnlocalizedName("dustrutblock").setHardness(3.0F).setResistance(5.0F);
		chisel = (DustModItem)new ItemChisel(References.ITEM_ChiselID).setUnlocalizedName("itemdustchisel").setCreativeTab(DustModTab.dustTab);
		spiritPickaxe = (new ItemSpiritPickaxe(References.ITEM_SpiritPickID, EnumToolMaterial.EMERALD)).setUnlocalizedName("spiritPickaxe").setCreativeTab(DustModTab.dustTab);
		spiritSword = (new ItemSpiritSword(References.ITEM_SpiritSwordID)).setUnlocalizedName("spiritSword").setCreativeTab(DustModTab.dustTab);
		inscription = (ItemInscription) (new ItemInscription(References.ITEM_InscriptionID)).setUnlocalizedName("runicinscription").setCreativeTab(DustModTab.dustTab);
		ink = new ItemInk(References.ITEM_InkID);
		wornInscription = new ItemWornInscription(References.ITEM_WornInscriptionID);
		wornInscription.setCreativeTab(DustModTab.dustTab);
		pouch = new ItemPouch(References.ITEM_PouchID, dust);
		pouch.setCreativeTab(DustModTab.dustTab);
		
		GameRegistry.registerBlock(dust, ItemBlock.class, dust.getUnlocalizedName());
		GameRegistry.registerBlock(dustTable, ItemBlock.class, dustTable.getUnlocalizedName());
		GameRegistry.registerBlock(rutBlock, ItemBlock.class, rutBlock.getUnlocalizedName());

		GameRegistry.registerTileEntity(TileEntityDust.class, "dusttileentity");
		GameRegistry.registerTileEntity(TileEntityDustTable.class, "dusttabletileentity");
		GameRegistry.registerTileEntity(TileEntityRut.class, "dustruttileentity");
		
		//naming time!!!
		LanguageRegistry.addName(dustTable, "Runic Lexicon");
		LanguageRegistry.addName(tome, "Runic Tome");
		LanguageRegistry.addName(negateSacrifice, "Negate Sacrifice");
		LanguageRegistry.addName(spiritPickaxe, "Spirit Pickaxe");
		LanguageRegistry.addName(spiritSword, "Spirit Sword");
		LanguageRegistry.addName(tome, "Runic Tome");
		LanguageRegistry.addName(chisel, "Hammer & Chisel");
		LanguageRegistry.addName(runicPaper, "Scroll Paper");
		LanguageRegistry.addName(pouch, "ERROR Runic Pouch");
		LanguageRegistry.addName(inscription, "Blank Inscription");
		LanguageRegistry.addName(wornInscription, "Dried Drawing");
		//LanguageRegistry.addName("dryinginsc.name", "Drying Inscription");
	}
	
	public static void initCrafting()
	{
		GameRegistry.addRecipe(new ItemStack(dustTable, 1), new Object[] {
			"dwd", "wbw", "dwd", 
			'd', new ItemStack(idust, 1, -1), 
			'w', new ItemStack(Block.planks, 1, -1), 
			'b', new ItemStack(tome, -1) 
		});
		GameRegistry.addRecipe(new ItemStack(dustTable, 1), new Object[] {
			"wdw", "dbd", "wdw", 
			'd', new ItemStack(idust, 1, -1), 
			'w',new ItemStack(Block.planks, 1, -1), 
			'b',new ItemStack(tome, -1) 
		});
		GameRegistry.addRecipe(new ItemStack(chisel, 1), new Object[] { 
			"st", "i ", 
			's', new ItemStack(Block.cobblestone, 1), 
			't', new ItemStack(Item.stick, 1), 
			'i', new ItemStack(Item.ingotIron, 1) 
		});
		GameRegistry.addRecipe(new ItemStack(inscription, 1), new Object[] {
			"s", "p", "p", 
			's', new ItemStack(Item.silk, 1), 
			'p', new ItemStack(runicPaper, 1) 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			new ItemStack(Block.tallGrass, 1, -1),
			new ItemStack(Block.tallGrass, 1, -1)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			new ItemStack(Block.leaves, 1, -1),
			new ItemStack(Block.leaves, 1, -1)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			new ItemStack(Block.sapling, 1, -1),
			new ItemStack(Block.sapling, 1, -1)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1), 
			Item.seeds, 
			Item.seeds
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			Block.cactus, 
			Block.cactus
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			Block.cactus, 
			Item.seeds 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			Block.cactus, 
			new ItemStack(Block.sapling, 1, -1) 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			Block.cactus, 
			new ItemStack(Block.leaves, 1, -1) 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			Block.cactus, 
			new ItemStack(Block.tallGrass, 1, -1) 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			Item.seeds, 
			new ItemStack(Block.sapling, 1, -1) 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			Item.seeds, 
			new ItemStack(Block.leaves, 1, -1) 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			Item.seeds, 
			new ItemStack(Block.tallGrass, 1, -1) 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			new ItemStack(Block.sapling, 1, -1),
			new ItemStack(Block.leaves, 1, -1) 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			new ItemStack(Block.sapling, 1, -1),
			new ItemStack(Block.tallGrass, 1, -1)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 100), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			new ItemStack(Block.leaves, 1, -1),
			new ItemStack(Block.tallGrass, 1, -1)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 12, 200), new Object[] { 
			Item.gunpowder, 
			new ItemStack(idust, 1, 100),
			new ItemStack(idust, 1, 100)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 8, 300), new Object[] { 
			new ItemStack(Item.coal.itemID, 1, -1),
			new ItemStack(Item.dyePowder, 2, 4),
			new ItemStack(Item.dyePowder, 2, 4),
			new ItemStack(Item.dyePowder, 2, 4) 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(idust, 12, 400), new Object[] { 
			Item.blazePowder, 
			new ItemStack(idust, 1, 300),
			new ItemStack(idust, 1, 300),
			new ItemStack(idust, 1, 300)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(tome, 1, 0), new Object[] { 
			new ItemStack(idust, 1, 100), 
			Item.book 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(tome, 1, 0), new Object[] { 
			new ItemStack(idust, 1, 200), 
			Item.book 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(tome, 1, 0), new Object[] { 
			new ItemStack(idust, 1, 300), 
			Item.book 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(tome, 1, 0), new Object[] { 
			new ItemStack(idust, 1, 400), 
			Item.book 
		});
		GameRegistry.addShapelessRecipe(new ItemStack(runicPaper, 1), new Object[] { 
			Item.paper, 
			Item.goldNugget, 
			Item.goldNugget 
		});

		for (int i = 1; i < 5; i++) 
		{
			GameRegistry.addShapelessRecipe(
					new ItemStack(idust, 1, i * 100),
					new ItemStack(idust, 1, i)
			);
		}
	}
}
