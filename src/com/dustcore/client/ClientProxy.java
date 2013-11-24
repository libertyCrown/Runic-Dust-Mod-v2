package com.dustcore.client;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.dustcore.DustShape;
import com.dustcore.client.gui.GuiTome;
import com.dustcore.client.render.DustBlockRenderers;
import com.dustcore.client.render.RenderDustTable;
import com.dustcore.client.render.RenderEntityBlock;
import com.dustcore.client.render.RenderEntityDust;
import com.dustcore.client.render.RenderLastHandler;
import com.dustcore.config.DustContent;
import com.dustcore.core.CommonProxy;
import com.dustcore.entity.EntityBlock;
import com.dustcore.entity.EntityDust;
import com.dustcore.event.InscriptionEvent;
import com.dustcore.tileentity.TileEntityDustTable;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{

	@Override
	public boolean isClient()
	{
		return true;
	}

	@Override
	public int getBlockModel(Block b)
	{
		if (b == DustContent.dust)
			return DustBlockRenderers.dustModelID;
		if (b == DustContent.rutBlock)
			return DustBlockRenderers.rutModelID;
		return -1;
	}

	@Override
	public void resetPlayerTomePage()
	{
		GuiTome.runePage = 0;
		GuiTome.insPage = 0;
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void checkRunePage(DustShape shape)
	{
		PageHelper.instance.checkRuneImage(shape);
	}

	@Override
	public void checkInscriptionPage(InscriptionEvent shape)
	{
		PageHelper.instance.checkInscriptionImage(shape);
	}

	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
	}

	@Override
	public void registerRenderInformation()
	{
		PageHelper.instance = new PageHelper();

		DustBlockRenderers.dustModelID = RenderingRegistry
				.getNextAvailableRenderId();
		DustBlockRenderers.rutModelID = RenderingRegistry
				.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new DustBlockRenderers(
				DustBlockRenderers.dustModelID));
		RenderingRegistry.registerBlockHandler(new DustBlockRenderers(
				DustBlockRenderers.rutModelID));

		RenderingRegistry.registerEntityRenderingHandler(EntityDust.class,
				new RenderEntityDust());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlock.class,
				new RenderEntityBlock());
		MinecraftForge.EVENT_BUS.register(new RenderLastHandler());
	}

	@Override
	public void registerTileEntityRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDustTable.class,
				new RenderDustTable());
	}

	@Override
	public void openTomeGUI(ItemStack itemstack, EntityPlayer p)
	{
		// ModLoader.openGUI(p, new GuiTome(itemstack));
		FMLClientHandler.instance().displayGuiScreen(p, new GuiTome(itemstack));
		// p.openGui(DustMod.instance, );
	}

	@Override
	public boolean placeDustWithTome(ItemStack itemstack, EntityPlayer p,
			World world, int i, int j, int k, int l)
	{
		return true;
	}

	@Override
	public void tickMouseManager()
	{
		super.tickMouseManager();
		MouseManager.instance.onTick();
	}
}
