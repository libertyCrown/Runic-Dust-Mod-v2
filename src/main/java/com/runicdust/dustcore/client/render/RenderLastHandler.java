package com.runicdust.dustcore.client.render;

import java.util.HashMap;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class RenderLastHandler
{
	public static HashMap<Object[], IRenderLast> map = new HashMap<Object[], IRenderLast>();

	@ForgeSubscribe
	public void onRenderWorldLast(RenderWorldLastEvent evt)
	{
		RenderGlobal renderer;
		float partialTicks;
		renderer = evt.context;
		partialTicks = evt.partialTicks;
		for (Object[] o : map.keySet())
		{
			map.get(o).renderLast(o, partialTicks);
		}

		map = new HashMap<Object[], IRenderLast>();
	}

	public static void registerLastRender(IRenderLast rend, Object[] o)
	{
		map.put(o, rend);
	}
}
