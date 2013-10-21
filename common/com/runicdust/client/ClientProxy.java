package com.runicdust.client;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.runicdust.DustMod;
import com.runicdust.DustShape;
import com.runicdust.client.gui.GuiTome;
import com.runicdust.client.render.DustBlockRenderers;
import com.runicdust.client.render.RenderDustTable;
import com.runicdust.client.render.RenderEntityBlock;
import com.runicdust.client.render.RenderEntityDust;
import com.runicdust.client.render.RenderLastHandler;
import com.runicdust.core.CommonProxy;
import com.runicdust.entity.EntityBlock;
import com.runicdust.entity.EntityDust;
import com.runicdust.event.InscriptionEvent;
import com.runicdust.tileentity.TileEntityDustTable;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public boolean isClient(){
		return true;
	}
	
	@Override
	public int getBlockModel(Block b) {
		if(b == DustMod.dust) return DustBlockRenderers.dustModelID;
		if(b == DustMod.rutBlock) return DustBlockRenderers.rutModelID;
		return -1;
	}
	
	@Override
	public void resetPlayerTomePage() {
		GuiTome.runePage = 0;
		GuiTome.insPage = 0;
	}
	
	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	@Override
	public void checkRunePage(DustShape shape) {
		PageHelper.instance.checkRuneImage(shape);
	}
	
	@Override
	public void checkInscriptionPage(InscriptionEvent shape) {
		PageHelper.instance.checkInscriptionImage(shape);
	}
	
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
	}
	
	@Override
	public void registerRenderInformation() 
	{
		PageHelper.instance = new PageHelper();
        
//      TextureFXManager.instance().addAnimation(new TextureDustFX(0));
//      TextureFXManager.instance().addAnimation(new TextureGlowFX(2*16));
        
        DustBlockRenderers.dustModelID = RenderingRegistry.getNextAvailableRenderId();
        DustBlockRenderers.rutModelID = RenderingRegistry.getNextAvailableRenderId();
//        DustBlockRenderers.dustModelID = RenderingRegistry.getNextAvailableRenderId();
//        DustBlockRenderers.rutModelID = RenderingRegistry.getNextAvailableRenderId();
        
        RenderingRegistry.registerBlockHandler(new DustBlockRenderers(DustBlockRenderers.dustModelID));
        RenderingRegistry.registerBlockHandler(new DustBlockRenderers(DustBlockRenderers.rutModelID));
        
        RenderingRegistry.registerEntityRenderingHandler(EntityDust.class, new RenderEntityDust());
        RenderingRegistry.registerEntityRenderingHandler(EntityBlock.class, new RenderEntityBlock());
        MinecraftForge.EVENT_BUS.register(new RenderLastHandler());
	}
	
	@Override
	public void registerTileEntityRenderers() 
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDustTable.class, new RenderDustTable());
	}

	
	@Override
	public void openTomeGUI(ItemStack itemstack, EntityPlayer p) {
//		ModLoader.openGUI(p, new GuiTome(itemstack));
		FMLClientHandler.instance().displayGuiScreen(p, new GuiTome(itemstack));
//		p.openGui(DustMod.instance, );
	}
	
	@Override
	public boolean placeDustWithTome(ItemStack itemstack, EntityPlayer p,
			World world, int i, int j, int k, int l) {
        return true;
	}
	
	@Override
	public void tickMouseManager() {
		super.tickMouseManager();
		MouseManager.instance.onTick();
	}
}
