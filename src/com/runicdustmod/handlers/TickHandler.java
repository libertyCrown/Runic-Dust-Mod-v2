package com.runicdustmod.handlers;

import java.util.EnumSet;

import com.runicdustmod.RunicDustMod;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler
{

	private boolean mouseDown = false;

	private static long lastTick = -1;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		RunicDustMod.proxy.tickMouseManager();
		RunicDustMod.keyHandler.tick();
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT, TickType.SERVER);
	}

	@Override
	public String getLabel()
	{
		return "RunicDustMod";
	}

}
