package com.runicdustmod.runes;

import com.runicdustmod.handlers.XMLDustShapeReader;
import com.runicdustmod.runes.standard.RuneBarrier;
import com.runicdustmod.runes.standard.RuneCampfire;
import com.runicdustmod.runes.standard.RuneChargeInscription;
import com.runicdustmod.runes.standard.RuneCompression;
import com.runicdustmod.runes.standard.RuneDawn;
import com.runicdustmod.runes.standard.RuneDepths;
import com.runicdustmod.runes.standard.RuneDetonation;
import com.runicdustmod.runes.standard.RuneDusk;
import com.runicdustmod.runes.standard.RuneEnchFireBow;
import com.runicdustmod.runes.standard.RuneEnchFortune;
import com.runicdustmod.runes.standard.RuneEnchSilkTouch;
import com.runicdustmod.runes.standard.RuneFarm;
import com.runicdustmod.runes.standard.RuneFireSprite;
import com.runicdustmod.runes.standard.RuneForcefield;
import com.runicdustmod.runes.standard.RuneHealing;
import com.runicdustmod.runes.standard.RuneHeights;
import com.runicdustmod.runes.standard.RuneHellstorm;
import com.runicdustmod.runes.standard.RuneLevelEarth;
import com.runicdustmod.runes.standard.RuneLillyBridge;
import com.runicdustmod.runes.standard.RuneMiniTeleport;
import com.runicdustmod.runes.standard.RuneMountain;
import com.runicdustmod.runes.standard.RunePowerRelay;
import com.runicdustmod.runes.standard.RuneRabbitHole;
import com.runicdustmod.runes.standard.RuneRebirth;
import com.runicdustmod.runes.standard.RuneRecord;
import com.runicdustmod.runes.standard.RuneResurrection;
import com.runicdustmod.runes.standard.RuneSarlacc;
import com.runicdustmod.runes.standard.RuneSpawnerCollector;
import com.runicdustmod.runes.standard.RuneSpawnerReprog;
import com.runicdustmod.runes.standard.RuneSpeed;
import com.runicdustmod.runes.standard.RuneSpiritTool;
import com.runicdustmod.runes.standard.RuneTeleport;
import com.runicdustmod.runes.standard.RuneTimeLock;
import com.runicdustmod.runes.standard.RuneTorch;
import com.runicdustmod.runes.standard.RuneTrapCage;
import com.runicdustmod.runes.standard.RuneTrapFire;
import com.runicdustmod.runes.standard.RuneTrapLightning;
import com.runicdustmod.runes.standard.RuneTrapPoison;
import com.runicdustmod.runes.standard.RuneVoidStorage;
import com.runicdustmod.runes.standard.RuneWisdom;
import com.runicdustmod.runes.testing.RuneBounce;
import com.runicdustmod.runes.testing.RuneEarthSprite;
import com.runicdustmod.runes.testing.RuneLumberjack;

public class RuneRegistry {
	
	public static void registerDefaultRunes()
	{
		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/torch.xml", new RuneTorch());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/rabbithole.xml", new RuneRabbitHole());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/healing.xml", new RuneHealing());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/campfire.xml", new RuneCampfire());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/depths.xml", new RuneDepths());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/heights.xml", new RuneHeights());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/farm.xml", new RuneFarm());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/lillybridge.xml", new RuneLillyBridge());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/dawn.xml", new RuneDawn());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/dusk.xml", new RuneDusk());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/trap.fire.xml", new RuneTrapFire());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/trap.lightning.xml", new RuneTrapLightning());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/trap.poison.xml", new RuneTrapPoison());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/trap.detonation.xml", new RuneDetonation());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/trap.entrap.xml", new RuneTrapCage());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/timelock.xml", new RuneTimeLock());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/void.xml", new RuneVoidStorage());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/barrier.xml", new RuneBarrier());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/wisdom.xml", new RuneWisdom());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/speed.xml", new RuneSpeed());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/compression.xml", new RuneCompression());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/firerain.xml", new RuneHellstorm());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/rebirth.xml", new RuneRebirth());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/resurrection.xml", new RuneResurrection());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/power_relay.xml", new RunePowerRelay());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/charge_inscriptions.xml", new RuneChargeInscription());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/spawner_collection.xml", new RuneSpawnerCollector());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/spawner_reassignment.xml", new RuneSpawnerReprog());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/teleport.xml", new RuneTeleport());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/minitele.xml", new RuneMiniTeleport());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/sprite.fire.xml", new RuneFireSprite());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/spawn_record.xml", new RuneRecord());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/spirit_tools.xml", new RuneSpiritTool());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/ench.firebow.xml", new RuneEnchFireBow());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/ench.silktouch.xml", new RuneEnchSilkTouch());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/ench.fortune.xml", new RuneEnchFortune());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/forcefield.xml", new RuneForcefield());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/level_earth.xml", new RuneLevelEarth());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/mountain.xml", new RuneMountain());

		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/sarlacc.xml", new RuneSarlacc());
		
		// Last id used: 46
		// notes for reanimation:
		// all numbers are cut off at the end of the name to preserve lexicon
		// page picture names
	}
	
	public static void registerTestingRunes()
	{
		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/bounce.xml", new RuneBounce());
		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/lumber.xml", new RuneLumberjack());
		XMLDustShapeReader.readAndRegisterShape(
				"/assets/runicdustmod/runedata/sprite.earth.xml", new RuneEarthSprite());
		
		//Last id used: 49
	}
	
	public static void registerThaumcraftRunes()
	{
		//nothing here yet
	}

}
