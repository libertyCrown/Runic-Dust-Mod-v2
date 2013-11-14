package com.dustdefault;

import com.dustcore.XMLDustShapeReader;
import com.dustcore.event.InscriptionEvent;
import com.dustcore.event.InscriptionManager;
import com.dustcore.inscriptions.BlinkerInscription;
import com.dustcore.inscriptions.BounceInscription;
import com.dustcore.inscriptions.EnderInscription;
import com.dustcore.inscriptions.ForesightInscription;
import com.dustcore.inscriptions.RespawnInscription;
import com.dustcore.inscriptions.RocketLaunch;
import com.dustcore.inscriptions.VoidInscription;
import com.dustdefault.runes.DEBomb;
import com.dustdefault.runes.DEBounce;
import com.dustdefault.runes.DECage;
import com.dustdefault.runes.DECampFire;
import com.dustdefault.runes.DEChargeInscription;
import com.dustdefault.runes.DECompression;
import com.dustdefault.runes.DEDawn;
import com.dustdefault.runes.DEEarthSprite;
import com.dustdefault.runes.DEEggifier;
import com.dustdefault.runes.DEFarm;
import com.dustdefault.runes.DEFireBowEnch;
import com.dustdefault.runes.DEFireRain;
import com.dustdefault.runes.DEFireSprite;
import com.dustdefault.runes.DEFireTrap;
import com.dustdefault.runes.DEFlatten;
import com.dustdefault.runes.DEForcefield;
import com.dustdefault.runes.DEFortuneEnch;
import com.dustdefault.runes.DEHeal;
import com.dustdefault.runes.DEHideout;
import com.dustdefault.runes.DELiftTerrain;
import com.dustdefault.runes.DELightning;
import com.dustdefault.runes.DELillyBridge;
import com.dustdefault.runes.DELumberjack;
import com.dustdefault.runes.DELunar;
import com.dustdefault.runes.DEMiniTele;
import com.dustdefault.runes.DEObelisk;
import com.dustdefault.runes.DEPit;
import com.dustdefault.runes.DEPoisonTrap;
import com.dustdefault.runes.DEPowerRelay;
//import com.dustdefault.runes.DEPowerRelay;
import com.dustdefault.runes.DEResurrection;
import com.dustdefault.runes.DESilkTouchEnch;
import com.dustdefault.runes.DESpawnRecord;
import com.dustdefault.runes.DESpawnTorch;
import com.dustdefault.runes.DESpawnerCollector;
import com.dustdefault.runes.DESpawnerReprog;
import com.dustdefault.runes.DESpeed;
import com.dustdefault.runes.DESpiritTool;
import com.dustdefault.runes.DETeleportation;
import com.dustdefault.runes.DETimeLock;
import com.dustdefault.runes.DEVoid;
import com.dustdefault.runes.DEWall;
import com.dustdefault.runes.DEXP;
import com.dustdefault.runes.DEXPStore;

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
@Mod(modid = "DustModDefaults", name = "Runic Dust Mod- Default Pack", version = "v2.0", dependencies = "after:DustMod")
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
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/torch.xml", new DESpawnTorch());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/rabbit.xml", new DEHideout());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/healing.xml", new DEHeal());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/lumber.xml", new DELumberjack());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/campfire.xml", new DECampFire());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/depths.xml", new DEPit());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/heights.xml", new DEObelisk());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/farm.xml", new DEFarm());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/leapfrog.xml", new DELillyBridge());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/dawn.xml", new DEDawn());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/dusk.xml", new DELunar());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/trap.fire.xml", new DEFireTrap());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/trap.lightning.xml", new DELightning());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/trap.poison.xml", new DEPoisonTrap());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/trap.detonation.xml", new DEBomb());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/trap.entrap.xml", new DECage());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/timelock.xml", new DETimeLock());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/void.xml", new DEVoid());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/wall.xml", new DEWall());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/wisdom.xml", new DEXPStore());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/speed.xml", new DESpeed());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/compression.xml", new DECompression());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/firerain.xml", new DEFireRain());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/eggifier.xml", new DEEggifier());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/resurrection.xml", new DEResurrection());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/power_relay.xml", new DEPowerRelay());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/charge_inscriptions.xml", new DEChargeInscription());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/spawner_collection.xml", new DESpawnerCollector());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/spawner_reassignment.xml", new DESpawnerReprog());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/teleport.xml", new DETeleportation());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/minitele.xml", new DEMiniTele());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/sprite.fire.xml", new DEFireSprite());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/sprite.earth.xml", new DEEarthSprite());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/bounce.xml", new DEBounce());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/spawn_record.xml", new DESpawnRecord());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/spirit_tools.xml", new DESpiritTool());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/ench.firebow.xml", new DEFireBowEnch());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/ench.silktouch.xml", new DESilkTouchEnch());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/ench.fortune.xml", new DEFortuneEnch());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/protection.xml", new DEForcefield());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/level_earth.xml", new DEFlatten());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/lift_terrain.xml", new DELiftTerrain());
		
		XMLDustShapeReader.readAndRegisterShape("/assets/dustdefault/runedata/sarlacc.xml", new DEXP());

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
