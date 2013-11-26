package com.dusttest;

import com.dustcore.DustShape;
import com.dustcore.api.InscriptionManager;
import com.dustcore.event.InscriptionEvent;
import com.dusttest.inscriptions.InscriptionErfBend;
import com.dusttest.inscriptions.InscriptionFireball;
import com.dusttest.inscriptions.InscriptionGlide;
import com.dusttest.inscriptions.InscriptionMountainCutter;
import com.dusttest.inscriptions.InscriptionWaterAffinity;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

/**
 * This pack is meant for testing runes & inscriptions as a separate download to
 * make sure that the added content is balanced and fair.
 * 
 * @author billythegoat101
 * 
 */
@Mod(modid = "DustModTestPack", name = "Runic Dust Mod- Testing Pack", version = "v2.0", dependencies = "after:DustMod")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class DustModTestPack
{

	@Instance("DustModTestPack")
	public static DustModTestPack instance;

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt)
	{
		registerDusts();
		registerRunes();
		registerInscriptions();
	}

	public void registerDusts()
	{

	}

	public void registerRunes()
	{

		int N = -1;
		int P = 100;
		int G = 200;
		int L = 300;
		int B = 400;
		DustShape s;
		int[][][] values;

		s = new DustShape(4, 4, "launchtest", false, 0, 0, 0, 0, 200);
		values = new int[][][] { { { G, 0, 0, G }, { 0, G, G, 0 },
				{ 0, G, G, 0 }, { G, 0, 0, G } } };
		s.setData(values);
		s.setRuneName("Launch test Rune");
		s.setNotes("Sacrifice:\n\n"
				+ "-None: Normal torch spawn.\n"
				+ "-1xFlint: Beacon rune.\n"
				+ "\nNotes:\n\n"
				+ "=Sacrificing a dye to an existing beacon will change its color.");
		s.setDesc("Description:\n\n"
				+ "Spawns a torch or, if a piece of flint is sacrficed, a beacon.");
		s.setAuthor("billythegoat101 -TestPack");
		s.setRotationMatrix(new int[] { 00, 00, 0, 0, 0, 0, 0, 0 });
		// DustManager.registerLocalDustShape(s, new LaunchTestRune());
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

		design = new int[][] { { 0, P, P, 0 }, { P, G, G, P }, { P, G, G, P },
				{ 0, P, P, 0 } };
		evt = new InscriptionMountainCutter(design, "cut", "Moutain Cutter",
				100);
		evt.setAuthor("billythegoat101 -TestPack");
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, P, P, 0 }, { P, L, L, P }, { P, L, L, P },
				{ 0, P, P, 0 } };
		evt = new InscriptionErfBend(design, "erfbendin", "ERF BENDIN", 1002);
		evt.setAuthor("billythegoat101 -TestPack");
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, G, G, 0 }, { G, B, B, G }, { G, B, B, G },
				{ 0, G, G, 0 } };
		evt = new InscriptionFireball(design, "fireball", "Fire Ball", 105);
		evt.setAuthor("billythegoat101 -TestPack");
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { P, P, P, P, 0, 0, 0, 0, P, P, P, P },
				{ 0, G, G, P, P, 0, 0, P, P, G, G, 0 },
				{ 0, 0, 0, G, P, G, G, P, G, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, G, G, 0, 0, 0, 0, 0 } };
		evt = new InscriptionGlide(1, design, "glideI1", "Glide I", 106);
		evt.setAuthor("billythegoat101 -TestPack");
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, 0, 0, 0, 0, 0, G, G, 0, 0, 0, 0, 0, 0 },
				{ G, G, G, L, L, L, G, G, L, L, L, G, G, G },
				{ 0, 0, G, G, G, L, L, L, L, G, G, G, 0, 0 },
				{ 0, 0, 0, 0, 0, G, L, L, G, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, L, L, 0, 0, 0, 0, 0, 0 } };
		evt = new InscriptionGlide(2, design, "glideII1", "Glide II", 109);
		evt.setAuthor("billythegoat101 -TestPack");
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { G, G, G, 0 }, { G, B, B, G }, { G, B, B, G },
				{ 0, G, G, 0 } };
		evt = new InscriptionWaterAffinity(design, "watertest", "Water Affinity", 107);
		evt.setAuthor("billythegoat101 -TestPack");
		InscriptionManager.registerInscriptionEvent(evt);

		// last id used: 10
	}

}
