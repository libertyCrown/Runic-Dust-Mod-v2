package com.runicdustmod.inscriptions;

import com.runicdustmod.api.InscriptionManager;
import com.runicdustmod.event.InscriptionEvent;
import com.runicdustmod.inscriptions.standard.InscriptionBlinker;
import com.runicdustmod.inscriptions.standard.InscriptionBounce;
import com.runicdustmod.inscriptions.standard.InscriptionForesight;
import com.runicdustmod.inscriptions.standard.InscriptionRespawn;
import com.runicdustmod.inscriptions.standard.InscriptionRocketLaunch;
import com.runicdustmod.inscriptions.standard.InscriptionTeleport;
import com.runicdustmod.inscriptions.standard.InscriptionVoidStorage;
import com.runicdustmod.inscriptions.testing.InscriptionErfBend;
import com.runicdustmod.inscriptions.testing.InscriptionFireball;
import com.runicdustmod.inscriptions.testing.InscriptionGlide;
import com.runicdustmod.inscriptions.testing.InscriptionMountainCutter;
import com.runicdustmod.inscriptions.testing.InscriptionWaterAffinity;

public class InscriptionRegistry {
	
	public static void registerDefaultInscriptions()
	{
		//TODO- register with xml files
		int N = -1;
		int P = 1;
		int G = 2;
		int L = 3;
		int B = 4;

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
	
	public static void registerTestingInscriptions()
	{
		//TODO- xmls for these, too!!
		int N = -1;
		int P = 1;
		int G = 2;
		int L = 3;
		int B = 4;

		InscriptionEvent evt = null;
		int[][] design;

		design = new int[][] { { 0, P, P, 0 }, { P, G, G, P }, { P, G, G, P },
				{ 0, P, P, 0 } };
		evt = new InscriptionMountainCutter(design, "cut", "Moutain Cutter",
				100);
		evt.setAuthor("billythegoat101");
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, P, P, 0 }, { P, L, L, P }, { P, L, L, P },
				{ 0, P, P, 0 } };
		evt = new InscriptionErfBend(design, "erfbendin", "ERF BENDIN", 1002);
		evt.setAuthor("billythegoat101");
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, G, G, 0 }, { G, B, B, G }, { G, B, B, G },
				{ 0, G, G, 0 } };
		evt = new InscriptionFireball(design, "fireball", "Fire Ball", 105);
		evt.setAuthor("billythegoat101");
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { P, P, P, P, 0, 0, 0, 0, P, P, P, P },
				{ 0, G, G, P, P, 0, 0, P, P, G, G, 0 },
				{ 0, 0, 0, G, P, G, G, P, G, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, G, G, 0, 0, 0, 0, 0 } };
		evt = new InscriptionGlide(1, design, "glideI", "Glide I", 106);
		evt.setAuthor("billythegoat101 -TestPack");
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { 0, 0, 0, 0, 0, 0, G, G, 0, 0, 0, 0, 0, 0 },
				{ G, G, G, L, L, L, G, G, L, L, L, G, G, G },
				{ 0, 0, G, G, G, L, L, L, L, G, G, G, 0, 0 },
				{ 0, 0, 0, 0, 0, G, L, L, G, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, L, L, 0, 0, 0, 0, 0, 0 } };
		evt = new InscriptionGlide(2, design, "glideII", "Glide II", 109);
		evt.setAuthor("billythegoat101");
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { { G, G, G, 0 }, { G, B, B, G }, { G, B, B, G },
				{ 0, G, G, 0 } };
		evt = new InscriptionWaterAffinity(design, "watertest", "Water Affinity", 107);
		evt.setAuthor("billythegoat101");
		InscriptionManager.registerInscriptionEvent(evt);
		
	}
}
