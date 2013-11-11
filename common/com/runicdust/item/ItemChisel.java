/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runicdust.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.runicdust.DustMod;
import com.runicdust.DustModItem;
import com.runicdust.config.DustConfig;
import com.runicdust.config.DustContent;
import com.runicdust.tileentity.TileEntityRut;
import com.runicdust.util.References;

/**
 *
 * @author billythegoat101
 */
public class ItemChisel extends DustModItem
{
    private int tex;

    public ItemChisel(int i)
    {
        super(i);
        
        setMaxStackSize(1);
        setMaxDamage(238);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer p, World world, int i, int j, int k, int face,float x,float y,float z)
    {

		if(!world.canMineBlock(p, i, j, k)) return false;
    	
        int blockId = world.getBlockId(i, j, k);
        int meta = world.getBlockMetadata(i, j, k);
        Block b = Block.blocksList[blockId];
        if(b == DustContent.dust){
        	j--;
            blockId = world.getBlockId(i, j, k);
            meta = world.getBlockMetadata(i, j, k);
            b = Block.blocksList[blockId];
        }
        
        if (b == DustContent.rutBlock)
        {
            itemstack.damageItem(1, p);
        }

        if (b == null)
        {
            return false;
        }

        if ((b.getBlockHardness(world,i,j,k) > Block.wood.getBlockHardness(world,i,j,k) && !References.Enable_Decorative_Ruts) || b == Block.bedrock)
        {
            return false;
        }
        else if (!b.isOpaqueCube() || b.getRenderType() != 0 || !b.renderAsNormalBlock())
        {
            return false;
        }

        itemstack.damageItem(1, p);

//        if (!world.isRemote)
//        {
            world.setBlock(i, j, k, DustContent.rutBlock.blockID, meta,3);
            TileEntityRut ter = (TileEntityRut)world.getBlockTileEntity(i, j, k);
            ter.maskBlock = blockId;
            ter.maskMeta = meta;
            DustContent.rutBlock.onBlockActivated(world, i, j, k, p,face,x,y,z);
//            System.out.println("Set");
//        }

//        System.out.println("Setting to " + blockID + " " + meta);
        return true;
    }
//    @Override
//    public String getItemName() {
//        return "dustchisel";
//    }
}