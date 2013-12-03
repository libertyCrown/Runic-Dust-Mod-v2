package com.dustdefault;

import net.minecraft.client.Minecraft;

import com.dustcore.api.InscriptionManager;
import com.dustcore.event.InscriptionEvent;
import com.dustcore.handlers.XMLDustShapeReader;
import com.dustdefault.inscriptions.InscriptionBlinker;
import com.dustdefault.inscriptions.InscriptionBounce;
import com.dustdefault.inscriptions.InscriptionTeleport;
import com.dustdefault.inscriptions.InscriptionForesight;
import com.dustdefault.inscriptions.InscriptionRespawn;
import com.dustdefault.inscriptions.InscriptionRocketLaunch;
import com.dustdefault.inscriptions.InscriptionVoidStorage;
import com.dustdefault.runes.RuneDetonation;
import com.dustdefault.runes.RuneTrapCage;
import com.dustdefault.runes.RuneCampfire;
import com.dustdefault.runes.RuneChargeInscription;
import com.dustdefault.runes.RuneCompression;
import com.dustdefault.runes.RuneDawn;
import com.dustdefault.runes.RuneRebirth;
import com.dustdefault.runes.RuneFarm;
import com.dustdefault.runes.RuneEnchFireBow;
import com.dustdefault.runes.RuneHellstorm;
import com.dustdefault.runes.RuneFireSprite;
import com.dustdefault.runes.RuneTrapFire;
import com.dustdefault.runes.RuneLevelEarth;
import com.dustdefault.runes.RuneForcefield;
import com.dustdefault.runes.RuneEnchFortune;
import com.dustdefault.runes.RuneHealing;
import com.dustdefault.runes.RuneRabbitHole;
import com.dustdefault.runes.RuneMountain;
import com.dustdefault.runes.RuneTrapLightning;
import com.dustdefault.runes.RuneLillyBridge;
import com.dustdefault.runes.RuneDusk;
import com.dustdefault.runes.RuneMiniTeleport;
import com.dustdefault.runes.RuneHeights;
import com.dustdefault.runes.RuneDepths;
import com.dustdefault.runes.RuneTrapPoison;
import com.dustdefault.runes.RunePowerRelay;
import com.dustdefault.runes.RuneResurrection;
import com.dustdefault.runes.RuneEnchSilkTouch;
import com.dustdefault.runes.RuneRecord;
import com.dustdefault.runes.RuneTorch;
import com.dustdefault.runes.RuneSpawnerCollector;
import com.dustdefault.runes.RuneSpawnerReprog;
import com.dustdefault.runes.RuneSpeed;
import com.dustdefault.runes.RuneSpiritTool;
import com.dustdefault.runes.RuneTeleport;
import com.dustdefault.runes.RuneTimeLock;
import com.dustdefault.runes.RuneVoidStorage;
import com.dustdefault.runes.RuneBarrier;
import com.dustdefault.runes.RuneSarlacc;
import com.dustdefault.runes.RuneWisdom;
import com.dusttest.runes.RuneBounce;
import com.dusttest.runes.RuneEarthSprite;
import com.dusttest.runes.RuneLumberjack;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

/**
 * This pack is meant for testing runes & inscriptions as a separate download to
 * make sure that the added content is balanced and fair.
 * 
 */
@Mod(modid = "DustDefaults", name = "Runic Dust Mod- Default Runes", version = "v2.0", dependencies = "after:DustModCore")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class DustModDefaults
{

	@Instance("DustDefaults")
	public static DustModDefaults instance;
	
	public static String langPath = Minecraft.getMinecraft().gameSettings.language;

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt)
	{
		registerDusts();
		registerRunes();
		registerInscriptions();
	}

	public void registerDusts()
	{
		// Default dusts come with the actual mod to start
	}

	public void registerRunes()
	{

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/torch.xml", new RuneTorch());

		XMLDustShapeReader.readAndRegisterShape(
						"/assets/dustdefault/runedata/" + langPath + "/rabbithole.xml", new RuneRabbitHole());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/healing.xml", new RuneHealing());

		XMLDustShapeReader.readAndRegisterShape(
						"/assets/dustdefault/runedata/" + langPath + "/campfire.xml", new RuneCampfire());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/depths.xml", new RuneDepths());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/heights.xml", new RuneHeights());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/farm.xml", new RuneFarm());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata" + langPath + "lillybridge.xml",
				new RuneLillyBridge());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/dawn.xml", new RuneDawn());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/dusk.xml", new RuneDusk());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/trap.fire.xml",
				new RuneTrapFire());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/trap.lightning.xml",
				new RuneTrapLightning());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/trap.poison.xml",
				new RuneTrapPoison());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/trap.detonation.xml",
				new RuneDetonation());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/trap.entrap.xml",
				new RuneTrapCage());

		XMLDustShapeReader.readAndRegisterShape(
						"/assets/dustdefault/runedata/" + langPath + "/timelock.xml", new RuneTimeLock());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/void.xml", new RuneVoidStorage());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/barrier.xml", new RuneBarrier());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/wisdom.xml", new RuneWisdom());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/speed.xml", new RuneSpeed());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/compression.xml",
				new RuneCompression());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/firerain.xml",
				new RuneHellstorm());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/rebirth.xml", new RuneRebirth());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/resurrection.xml",
				new RuneResurrection());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/power_relay.xml",
				new RunePowerRelay());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/charge_inscriptions.xml",
				new RuneChargeInscription());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/spawner_collection.xml",
				new RuneSpawnerCollector());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/spawner_reassignment.xml",
				new RuneSpawnerReprog());

		XMLDustShapeReader.readAndRegisterShape(
						"/assets/dustdefault/runedata/" + langPath + "/teleport.xml", new RuneTeleport());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/minitele.xml",
				new RuneMiniTeleport());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/sprite.fire.xml",
				new RuneFireSprite());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/spawn_record.xml",
				new RuneRecord());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/spirit_tools.xml",
				new RuneSpiritTool());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/ench.firebow.xml",
				new RuneEnchFireBow());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/ench.silktouch.xml",
				new RuneEnchSilkTouch());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "entry/ench.fortune.xml",
				new RuneEnchFortune());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/forcefield.xml",
				new RuneForcefield());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/level_earth.xml",
				new RuneLevelEarth());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/mountain.xml",
				new RuneMountain());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/dustdefault/runedata/" + langPath + "/sarlacc.xml", new RuneSarlacc());

		// last id used: 46
		// notes for reanimation:
		// all numbers are cut off at the end of the name to preserve lexicon
		// page picture names

	}

	public void registerInscriptions()
	{
		int N = -1;
		int P = 100;
		int G = 200;
		int L = 300;
		int B = 400;

		InscriptionEvent evt = null;
		int[][] design;

		design = new int[][] { { 0, 0, 0, G, G, 0, 0, 0 },
				{ 0, 0, 0, G, G, 0, 0, 0 }, { 0, 0, G, 0, 0, G, 0, 0 },
				{ 0, P, G, G, G, G, P, 0 }, { 0, 0, P, G, G, P, 0, 0 },
				{ 0, P, P, 0, 0, P, P, 0 }, { 0, P, P, 0, 0, P, P, 0 },
				{ P, 0, 0, 0, 0, 0, 0, P } };
		evt = new InscriptionRocketLaunch(design, "leapI", "Leap I", 0, 1);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, L, 0, 0, L, 0 }, { 0, L, G, G, L, 0 },
				{ L, 0, G, G, 0, L }, { 0, G, L, L, G, 0 },
				{ L, G, 0, 0, G, L }, { L, L, 0, 0, L, L },
				{ L, L, 0, 0, L, L }, { G, L, 0, 0, L, G } };
		evt = new InscriptionRocketLaunch(design, "leapII", "Leap II", 1, 2);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] {

		{ 0, 0, 0, G, L, L, 0, L, 0, 0, 0, 0 },
				{ 0, 0, G, G, L, G, L, L, L, L, 0, 0 },
				{ G, G, G, G, L, G, G, 0, G, 0, L, 0 },
				{ 0, L, 0, G, 0, G, G, L, G, G, G, G },
				{ 0, 0, L, L, L, L, G, L, G, G, 0, 0 },
				{ 0, 0, 0, 0, L, 0, L, L, G, 0, 0, 0 }, };
		evt = new InscriptionRespawn(design, "respawn", "Return I", 3);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, 0, L, 0, 0, 0, 0, L, 0, 0 },
				{ 0, G, L, L, 0, 0, L, L, G, 0 },
				{ G, G, L, G, L, L, G, L, G, G },
				{ 0, L, G, 0, G, G, 0, G, L, 0 },
				{ 0, L, G, 0, G, G, 0, G, L, 0 },
				{ G, G, L, G, L, L, G, L, G, G },
				{ 0, G, L, L, 0, 0, L, L, G, 0 },
				{ 0, 0, L, 0, 0, 0, 0, L, 0, 0 } };
		evt = new InscriptionVoidStorage(design, "voidinscription", "Void I", 4);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, 0, 0, P, P, 0, 0, 0 },
				{ 0, G, G, G, P, P, P, 0 }, { 0, G, P, G, G, P, P, 0 },
				{ P, P, G, P, 0, G, G, G }, { P, P, P, 0, G, P, G, G },
				{ 0, G, G, P, P, G, P, 0 }, { 0, G, G, G, P, P, P, 0 },
				{ 0, 0, 0, G, G, 0, 0, 0 } };
		evt = new InscriptionBounce(design, "bouncy", "Bounce I", 5);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, 0, 0, 0, G, 0, 0, 0, G, 0, 0, 0 },
				{ 0, 0, 0, G, P, P, 0, P, P, G, 0, 0 },
				{ 0, P, P, P, P, G, G, P, P, P, P, P },
				{ G, G, G, G, G, P, P, G, G, G, G, 0 },
				{ 0, 0, P, G, G, 0, G, G, P, 0, 0, 0 },
				{ 0, 0, 0, P, 0, 0, 0, P, 0, 0, 0, 0 } };
		evt = new InscriptionTeleport(design, "blinkI", "Blink I", 6);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, 0, 0, 0, G, G, 0, G, G, G, 0, 0 },
				{ 0, 0, L, L, G, G, L, L, 0, G, 0, 0 },
				{ 0, G, G, G, G, L, G, L, L, L, L, L },
				{ L, L, L, L, L, G, L, G, G, G, G, 0 },
				{ 0, G, 0, 0, L, L, G, G, L, L, 0, 0 },
				{ 0, G, G, G, 0, 0, G, G, 0, 0, 0, 0 } };
		evt = new InscriptionBlinker(design, "blinkII", "Blink II", 7);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, 0, G, 0, 0, 0 }, { 0, 0, G, L, 0, 0 },
				{ 0, 0, G, G, 0, 0 }, { 0, G, 0, G, L, 0 },
				{ 0, G, G, G, L, 0 }, { G, G, L, L, G, G },
				{ G, G, L, L, G, G }, { 0, L, G, G, G, 0 },
				{ 0, L, G, 0, G, 0 }, { 0, 0, G, G, 0, 0 },
				{ 0, 0, L, G, 0, 0 }, { 0, 0, 0, G, 0, 0 }, };
		evt = new InscriptionForesight(design, "foresight", "Foresight I", 8);
		evt.setAuthor("billythegoat101");
		InscriptionManager.registerInscriptionEvent(evt);

		// Last ID used: 8
	}

}
