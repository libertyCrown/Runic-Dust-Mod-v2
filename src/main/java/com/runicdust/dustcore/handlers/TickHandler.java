package com.runicdust.dustcore.handlers;

import java.util.EnumSet;

import com.runicdust.dustcore.DustModCore;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler
{

	private boolean mouseDown = false;

	private static long lastTick = -1;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		DustModCore.proxy.tickMouseManager();
		DustModCore.keyHandler.tick();
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
		return "DustModCore";
	}

}
