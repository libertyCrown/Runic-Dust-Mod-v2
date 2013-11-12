package com.runicdust;

import com.runicdust.event.InscriptionEvent;
import com.runicdust.event.InscriptionManager;
import com.runicdust.inscriptions.BlinkerInscription;
import com.runicdust.inscriptions.BounceInscription;
import com.runicdust.inscriptions.EnderInscription;
import com.runicdust.inscriptions.ForesightInscription;
import com.runicdust.inscriptions.RespawnInscription;
import com.runicdust.inscriptions.RocketLaunch;
import com.runicdust.inscriptions.VoidInscription;
import com.runicdust.runes.DEBomb;
import com.runicdust.runes.DEBounce;
import com.runicdust.runes.DECage;
import com.runicdust.runes.DECampFire;
import com.runicdust.runes.DEChargeInscription;
import com.runicdust.runes.DECompression;
import com.runicdust.runes.DEDawn;
import com.runicdust.runes.DEEarthSprite;
import com.runicdust.runes.DEEggifier;
import com.runicdust.runes.DEFarm;
import com.runicdust.runes.DEFireBowEnch;
import com.runicdust.runes.DEFireRain;
import com.runicdust.runes.DEFireSprite;
import com.runicdust.runes.DEFireTrap;
import com.runicdust.runes.DEFlatten;
import com.runicdust.runes.DEForcefield;
import com.runicdust.runes.DEFortuneEnch;
import com.runicdust.runes.DEHeal;
import com.runicdust.runes.DEHideout;
import com.runicdust.runes.DELiftTerrain;
import com.runicdust.runes.DELightning;
import com.runicdust.runes.DELillyBridge;
import com.runicdust.runes.DELumberjack;
import com.runicdust.runes.DELunar;
import com.runicdust.runes.DEMiniTele;
import com.runicdust.runes.DEObelisk;
import com.runicdust.runes.DEPit;
import com.runicdust.runes.DEPoisonTrap;
import com.runicdust.runes.DEPowerRelay;
import com.runicdust.runes.DEResurrection;
import com.runicdust.runes.DESilkTouchEnch;
import com.runicdust.runes.DESpawnRecord;
import com.runicdust.runes.DESpawnTorch;
import com.runicdust.runes.DESpawnerCollector;
import com.runicdust.runes.DESpawnerReprog;
import com.runicdust.runes.DESpeed;
import com.runicdust.runes.DESpiritTool;
import com.runicdust.runes.DETeleportation;
import com.runicdust.runes.DETimeLock;
import com.runicdust.runes.DEVoid;
import com.runicdust.runes.DEWall;
import com.runicdust.runes.DEXP;
import com.runicdust.runes.DEXPStore;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

/**
 * This pack is meant for testing runes & inscriptions as a separate download to
 * make sure that the added content is balanced and fair.
 * 
 * @author billythegoat101
 * 
 */
@Mod(modid = "DustModDefaults", name = "Default Pack", version = "1.6.1", dependencies = "after:DustMod")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class DustModDefaults {

	@Instance("DustModDefaults")
	public static DustModDefaults instance;

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt) {
		registerDusts();
		registerRunes();
		registerInscriptions();
	}

	public void registerDusts() 
	{
		// Default dusts come with the actual mod to start
	}

	public void registerRunes() {
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/torch.xml", new DESpawnTorch());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/rabbit.xml", new DEHideout());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/healing.xml", new DEHeal());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/lumber.xml", new DELumberjack());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/campfire.xml", new DECampFire());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/depths.xml", new DEPit());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/heights.xml", new DEObelisk());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/farm.xml", new DEFarm());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/leapfrog.xml", new DELillyBridge());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/dawn.xml", new DEDawn());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/dusk.xml", new DELunar());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/trap.fire.xml", new DEFireTrap());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/trap.lightning.xml", new DELightning());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/trap.poison.xml", new DEPoisonTrap());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/trap.detonation.xml", new DEBomb());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/trap.entrap.xml", new DECage());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/timelock.xml", new DETimeLock());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/void.xml", new DEVoid());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/wall.xml", new DEWall());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/wisdom.xml", new DEXPStore());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/speed.xml", new DESpeed());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/compression.xml", new DECompression());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/firerain.xml", new DEFireRain());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/eggifier.xml", new DEEggifier());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/resurrection.xml", new DEResurrection());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/power_relay.xml", new DEPowerRelay());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/charge_inscriptions.xml", new DEChargeInscription());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/spawner_collection.xml", new DESpawnerCollector());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/spawner_reassignment.xml", new DESpawnerReprog());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/teleport.xml", new DETeleportation());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/minitele.xml", new DEMiniTele());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/sprite.fire.xml", new DEFireSprite());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/sprite.earth.xml", new DEEarthSprite());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/bounce.xml", new DEBounce());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/spawn_record.xml", new DESpawnRecord());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/spirit_tools.xml", new DESpiritTool());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/ench.firebow.xml", new DEFireBowEnch());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/ench.silktouch.xml", new DESilkTouchEnch());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/ench.fortune.xml", new DEFortuneEnch());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/protection.xml", new DEForcefield());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/level_earth.xml", new DEFlatten());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/lift_terrain.xml", new DELiftTerrain());
		
		XMLDustShapeReader.readAndRegiterShape("/assets/runicdust/runedata/sarlacc.xml", new DEXP());

		// last id used: 46
		// notes for reanimation:
		// all numbers are cut off at the end of the name to preserve lexicon
		// page picture names

	}

	public void registerInscriptions() {
		int N = -1;
		int P = 100;
		int G = 200;
		int L = 300;
		int B = 400;

		InscriptionEvent evt = null;
		int[][] design;


		design = new int[][] {
				{ 0, 0, 0, G, G, 0, 0, 0 }, 
				{ 0, 0, 0, G, G, 0, 0, 0 }, 
				{ 0, 0, G, 0, 0, G, 0, 0 }, 
				{ 0, P, G, G, G, G, P, 0 }, 
				{ 0, 0, P, G, G, P, 0, 0 }, 
				{ 0, P, P, 0, 0, P, P, 0 }, 
				{ 0, P, P, 0, 0, P, P, 0 }, 
				{ P, 0, 0, 0, 0, 0, 0, P } };
		evt = new RocketLaunch(design, "leapI", "Leap I", 0, 1);
		InscriptionManager.registerInscriptionEvent(evt);


		design = new int[][] {
				{ 0, L, 0, 0, L, 0 }, 
				{ 0, L, G, G, L, 0 }, 
				{ L, 0, G, G, 0, L }, 
				{ 0, G, L, L, G, 0 }, 
				{ L, G, 0, 0, G, L }, 
				{ L, L, 0, 0, L, L }, 
				{ L, L, 0, 0, L, L }, 
				{ G, L, 0, 0, L, G } };
		evt = new RocketLaunch(design, "leapII", "Leap II", 1, 2);
		InscriptionManager.registerInscriptionEvent(evt);


		design = new int[][] {

				{ 0, 0, 0, G, L, L, 0, L, 0, 0, 0, 0 },
				{ 0, 0, G, G, L, G, L, L, L, L, 0, 0 },
				{ G, G, G, G, L, G, G, 0, G, 0, L, 0 },
				{ 0, L, 0, G, 0, G, G, L, G, G, G, G },
				{ 0, 0, L, L, L, L, G, L, G, G, 0, 0 },
				{ 0, 0, 0, 0, L, 0, L, L, G, 0, 0, 0 }, };
		evt = new RespawnInscription(design, "respawn", "Return I", 3);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { 
				{0, 0, L, 0, 0, 0, 0, L, 0, 0 },
				{0, G, L, L, 0, 0, L, L, G, 0 },
				{G, G, L, G, L, L, G, L, G, G },
				{0, L, G, 0, G, G, 0, G, L, 0 },
				{0, L, G, 0, G, G, 0, G, L, 0 },
				{G, G, L, G, L, L, G, L, G, G },
				{0, G, L, L, 0, 0, L, L, G, 0 },
				{0, 0, L, 0, 0, 0, 0, L, 0, 0 }
		};
		evt = new VoidInscription(design, "voidinscription",
				"Void I", 4);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { 
				{0, 0, 0, P, P, 0, 0, 0 },
				{0, G, G, G, P, P, P, 0 },
				{0, G, P, G, G, P, P, 0 },
				{P, P, G, P, 0, G, G, G },
				{P, P, P, 0, G, P, G, G },
				{0, G, G, P, P, G, P, 0 },
				{0, G, G, G, P, P, P, 0 },
				{0, 0, 0, G, G, 0, 0, 0 }
		};
		evt = new BounceInscription(design, "bouncy",
				"Bounce I", 5);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { 
				{0, 0, 0, 0, G, 0, 0, 0, G, 0, 0, 0 },
				{0, 0, 0, G, P, P, 0, P, P, G, 0, 0 },
				{0, P, P, P, P, G, G, P, P, P, P, P },
				{G, G, G, G, G, P, P, G, G, G, G, 0 },
				{0, 0, P, G, G, 0, G, G, P, 0, 0, 0 },
				{0, 0, 0, P, 0, 0, 0, P, 0, 0, 0, 0 }
		};
		evt = new EnderInscription(design, "blinkI",
				"Blink I", 6);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] { 
				{0, 0, 0, 0, G, G, 0, G, G, G, 0, 0 },
				{0, 0, L, L, G, G, L, L, 0, G, 0, 0 },
				{0, G, G, G, G, L, G, L, L, L, L, L },
				{L, L, L, L, L, G, L, G, G, G, G, 0 },
				{0, G, 0, 0, L, L, G, G, L, L, 0, 0 },
				{0, G, G, G, 0, 0, G, G, 0, 0, 0, 0 }
		};
		evt = new BlinkerInscription(design, "blinkII",
				"Blink II", 7);
		InscriptionManager.registerInscriptionEvent(evt);

		design = new int[][] {
				{0, 0, G, 0, 0, 0},
				{0, 0, G, L, 0, 0},
				{0, 0, G, G, 0, 0},
				{0, G, 0, G, L, 0},
				{0, G, G, G, L, 0},
				{G, G, L, L, G, G},
				{G, G, L, L, G, G},
				{0, L, G, G, G, 0},
				{0, L, G, 0, G, 0},
				{0, 0, G, G, 0, 0},
				{0, 0, L, G, 0, 0},
				{0, 0, 0, G, 0, 0},
		};
		evt = new ForesightInscription(design, "foresight", "Foresight I", 8);
		evt.setAuthor("billythegoat101");
		InscriptionManager.registerInscriptionEvent(evt);
		
		//Last ID  used: 8
	}

}
