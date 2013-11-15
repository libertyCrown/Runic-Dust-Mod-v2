package com.dustcore;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet5PlayerInventory;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

import com.dustcore.api.DustItemManager;
import com.dustcore.api.DustManager;
import com.dustcore.config.DustConfig;
import com.dustcore.config.DustContent;
import com.dustcore.core.CommonMouseHandler;
import com.dustcore.core.CommonProxy;
import com.dustcore.entity.EntityDustManager;
import com.dustcore.event.InscriptionManager;
import com.dustcore.item.ItemWornInscription;
import com.dustcore.util.GuiHandler;
import com.dustcore.util.PacketHandler;
import com.dustcore.util.VoidStorageManager;
import com.dustcore.util.VoidTeleManager;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

@Mod(modid = "DustMod", name = "RunicDustMod", version = "1.4pre2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class, channels = {
		PacketHandler.CHANNEL_DMRune, PacketHandler.CHANNEL_TEDust,
		PacketHandler.CHANNEL_TELexicon, PacketHandler.CHANNEL_TERut,
		PacketHandler.CHANNEL_DustItem, PacketHandler.CHANNEL_Mouse,
		PacketHandler.CHANNEL_UseInk, PacketHandler.CHANNEL_SetInscription,
		PacketHandler.CHANNEL_DeclareInscription,
		PacketHandler.CHANNEL_SpawnParticles,
		PacketHandler.CHANNEL_SetEntVelocity,
		PacketHandler.CHANNEL_RendBrokenTool })

public class DustMod 
{

	@Instance("DustMod")
	public static DustMod instance;
	
	public static int prevVoidSize;
	public static HashMap<String, ArrayList<ItemStack>> voidInventory;
	public static ArrayList<int[]> voidNetwork;
	
	public static int skipWarpTick = 0;
	public static int numSec = 0;  	// number of secret runes
	public static File suggestedConfig;
	
	@SidedProxy(clientSide = "com.dustcore.client.ClientProxy", serverSide = "com.dustcore.core.CommonProxy")
	public static CommonProxy proxy;
	public static CommonMouseHandler keyHandler = new CommonMouseHandler();
	public static InscriptionManager inscriptionManager = new InscriptionManager();

	private static boolean hasLoaded = false;

	@EventHandler
	public void preInit(FMLPreInitializationEvent evt)
	{
		if (hasLoaded)
		{
			return;
		}
		hasLoaded = true;
		suggestedConfig = new File(evt.getSuggestedConfigurationFile().getParent() + File.separator + "RunicDustCore.cfg");
		DustConfig.configInit(new File(evt.getModConfigurationDirectory().getAbsolutePath() + File.separator + "RunicDustCore" + ".cfg"));
	}

	@EventHandler
	public void load(FMLInitializationEvent evt) 
	{
		DustContent.initContent();
		DustContent.initCrafting();
		
		NetworkRegistry.instance().registerConnectionHandler(new PacketHandler());
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		proxy.registerEventHandlers();
		proxy.registerTileEntityRenderers();
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		proxy.registerRenderInformation();
		DustItemManager.registerDefaultDusts();
		DustManager.registerDefaultShapes();
		InscriptionManager.registerDefaultInscriptions();
	    //lang.addStringLocalization("inscblank.name", "Doodle");

		MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void modsLoaded(FMLPostInitializationEvent evt)
	{  
		
	}

	@ForgeSubscribe
	public void onWorldEvent(WorldEvent.Load evt) {

		if (evt.world.isRemote)
			return;
		// System.out.println("World event Load " + evt.world);

		ISaveHandler save = evt.world.getSaveHandler();
		int nameLength = (new StringBuilder())
				.append(save.getWorldDirectoryName()).append(".dat").length();

		File mapFile = save.getMapFileFromName(save.getWorldDirectoryName());
		if (mapFile == null)
			return;
		String savePath = mapFile.getPath();
		savePath = savePath.substring(0, savePath.length() - nameLength);

		VoidStorageManager.load(savePath);
		VoidTeleManager.load(savePath);
		EntityDustManager.load(savePath);
	}

	public static String getUsername(Player p) {
		if (p instanceof EntityPlayer) {
			return ((EntityPlayer) p).username;
		} else if (p instanceof EntityPlayerMP) {
			return ((EntityPlayerMP) p).username;
		}
		return null;
	}

	public static void spawnParticles(World world, String type, double x,
			double y, double z, double velx, double vely, double velz, int amt,
			double radius) {
		spawnParticles(world, type, x, y, z, velx, vely, velz, amt, radius,
				radius, radius);
	}

	public static void spawnParticles(World world, String type, double x,
			double y, double z, double velx, double vely, double velz, int amt,
			double rx, double ry, double rz) {
		spawnParticles(world, type, new double[] { x, y, z }, velx, vely, velz,
				amt, rx, ry, rz);
	}

	public static void spawnParticles(World world, String type,
			double[] locations, double velx, double vely, double velz, int amt,
			double rx, double ry, double rz) {
		Packet packet = PacketHandler.getParticlePacket(type, (short) 0,
				locations, velx, vely, velz, amt, rx, ry, rz);
		PacketDispatcher.sendPacketToAllInDimension(packet, world
				.getWorldInfo().getVanillaDimension());
	}

	/**
	 * Returns if item.itemID equals the dust item id.
	 * 
	 * @param id
	 *            check ID
	 * @return true if is dust.
	 */
	public static boolean isDust(int id) {
		return id == DustContent.dust.blockID;
	}

	/**
	 * 
	 * Compares the two dust's values to find which is better.
	 * 
	 * @param base
	 *            The reference dust
	 * @param dust
	 *            The check dust
	 * @return 0 if dusts are equal, -1 if the reference is worth less than the
	 *         check, and 1 if the reference is worth more than the check
	 */
	public static int compareDust(int base, int dust) {

		if (base == -1 || dust == -1) {
			throw new IllegalArgumentException("Invalid dust ID.");
		}

		if (base == dust) {
			return 0;
		}

		if (base > dust) {
			return -1;
		}

		if (dust > base) {
			return 1;
		}

		return -1;
	}

	/**
	 * Syncs player inventory of the client with server. Theoretically.
	 * 
	 * @param p
	 *            Player's inventory to sync.
	 */
	public static void sendPlayerInventory(EntityPlayer p) {

		int var1;

		if (!p.worldObj.isRemote)
			for (var1 = 0; var1 < 5; ++var1) {
				ItemStack var2 = p.getCurrentItemOrArmor(var1);
				((WorldServer) p.worldObj).getEntityTracker()
						.sendPacketToAllPlayersTrackingEntity(
								p,
								new Packet5PlayerInventory(p.entityId, var1,
										var2));
			}
	}

	public static void sendEntMotionTraits(EntityLivingBase wearer) {
		PacketDispatcher.sendPacketToAllInDimension(PacketHandler
				.getSetVelocityPacket(wearer), wearer.worldObj.getWorldInfo()
				.getVanillaDimension());
	}
	
	public static void sendRenderBreakItem(EntityLivingBase wearer, ItemStack tool){
		PacketDispatcher.sendPacketToAllInDimension(PacketHandler
				.getRenderBrokenToolPacket(wearer, tool), wearer.worldObj.getWorldInfo()
				.getVanillaDimension());
	}

	/**
	 * Returns the itemstack that represents the given entity
	 * 
	 * @param entityID
	 *            The EntityID of the mob
	 * @return The itemstack that represents that mob
	 */
	public static ItemStack getDrop(int entityID) {
		for (ItemStack i : entdrops.keySet()) {
			if (entdrops.get(i) == entityID)
				return new ItemStack(i.itemID, i.stackSize, i.getItemDamage());
		}
		return null;
	}

	/**
	 * Gets the entityID for a certain mob drop type.
	 * 
	 * @param is
	 *            The item to check
	 * @param mul
	 *            The multiplier for the itemstack size
	 * @return -1 if not found or the item stacksize isn't big enough, else the
	 *         entityID
	 */
	public static int getEntityIDFromDrop(ItemStack is, int mul) {
		for (ItemStack i : entdrops.keySet()) {
			if (i.itemID == is.itemID
					&& (is.stackSize >= i.itemID * mul || is.stackSize == -1)
					&& (i.getItemDamage() == is.getItemDamage() || i
							.getItemDamage() == -1)) {
				return entdrops.get(i);
			}
		}
		return -1;
	}

	/**
	 * Checks to see if a mob is hostile.
	 * 
	 * @param id
	 *            The EntityID of the mob
	 * @return true if hostile, false if not
	 */
	public static boolean isMobIDHostile(int id) {
		Entity ent = EntityList.createEntityByID(id, null);

		if (ent instanceof IMob) {
			return true;
		}

		return false;
	}

	/**
	 * Register an item that should represent the given mob. Used for runes like
	 * the resurrection rune where an item is sacrificed to determine which mob
	 * to spawn. The stacksize should be an amount related to the worth of the
	 * mob/item. For example, to spawn a chicken you need relatively less to
	 * chickenRaw(4) items than you need blazerods to spawn a blaze(16)
	 * 
	 * @param item
	 *            The item that should represent the entity (generally the item
	 *            that the mob should drop)
	 * @param entityID
	 *            The entityID that should be represented by the item.
	 */
	public static void registerNewEntityDropForSacrifice(ItemStack item,
			int entityID) {
		entdrops.put(item, entityID);
	}

	public static ItemWornInscription getWornInscription() {
		return DustContent.wornInscription;
	}

	public static Item getItemDust() {
		return DustContent.idust;
	}

	public static Item getNegator() {
		return DustContent.negateSacrifice;
	}

	public static HashMap<ItemStack, Integer> entdrops;

	public static void log(Level level, String msg, Object... objs) {
		String message = "[DustMod] " + msg;
		for (Object o : Arrays.asList(objs)) {
			message += " " + o;
		}
		FMLLog.log(level, message);
	}

	public static void log(String msg, Object... objs) {
		String message = "[DustMod] " + msg;
		for (Object o : Arrays.asList(objs)) {
			message += " " + o;
		}
		FMLLog.log(Level.INFO, message);
	}

	static {
		entdrops = new HashMap<ItemStack, Integer>();
		entdrops.put(new ItemStack(Item.chickenRaw.itemID, 4, 0), 93); // chicken
		entdrops.put(new ItemStack(Item.beefRaw.itemID, 4, 0), 92); // cow
		entdrops.put(new ItemStack(Block.mushroomCapRed, 16, -1), 96); // mooshroom
		entdrops.put(new ItemStack(Item.fishRaw.itemID, 8, 0), 98); // ocelot
		entdrops.put(new ItemStack(Item.porkRaw.itemID, 4, 0), 90); // pig
		entdrops.put(new ItemStack(Block.cloth.blockID, 8, -1), 91); // sheep
		entdrops.put(new ItemStack(Item.dyePowder.itemID, 4, 0), 94); // squid
		entdrops.put(new ItemStack(Block.brick.blockID, 8, 0), 120); // villager
		entdrops.put(new ItemStack(Item.enderPearl.itemID, 8, 0), 58); // enderman
		entdrops.put(new ItemStack(Item.leather.itemID, 16, 0), 95); // wolf
		entdrops.put(new ItemStack(Item.goldNugget.itemID, 16, 0), 57); // zombie pigman
		entdrops.put(new ItemStack(Item.blazeRod.itemID, 16, 0), 61); // blaze
		entdrops.put(new ItemStack(Item.spiderEye.itemID, 8, 0), 59); // cave spider
		entdrops.put(new ItemStack(Item.gunpowder.itemID, 8, 0), 50); // creeper
		entdrops.put(new ItemStack(Item.ghastTear.itemID, 8, 0), 56); // ghast
		entdrops.put(new ItemStack(Item.magmaCream.itemID, 8, 0), 62); // magma cube
		entdrops.put(new ItemStack(Block.stoneBrick.blockID, 16, 1), 60); // silverfish
		entdrops.put(new ItemStack(Item.bone.itemID, 16, 0), 51); // skeleton
		entdrops.put(new ItemStack(Item.slimeBall.itemID, 16, 0), 55); // slime
		entdrops.put(new ItemStack(Item.silk.itemID, 16, 0), 52); // spider
		entdrops.put(new ItemStack(Item.rottenFlesh.itemID, 8, 0), 54); // zombie
		entdrops.put(new ItemStack(Block.snow.blockID, 8, 0), 97); // snow golem
		entdrops.put(new ItemStack(Block.blockIron.blockID, 8, 0), 99); // iron golem
		entdrops.put(new ItemStack(Block.dragonEgg.blockID, 64, 0), 63); // enderdragon
		entdrops.put(new ItemStack(Block.blockDiamond.blockID, 64, 0), 53); // giant
	}
}
