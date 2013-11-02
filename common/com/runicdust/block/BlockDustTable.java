package com.runicdust.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.runicdust.DustManager;
import com.runicdust.DustMod;
import com.runicdust.tileentity.TileEntityDustTable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDustTable extends BlockContainer 
{
	/*@SideOnly(Side.CLIENT)
	private Icon sideTex;*/
	@SideOnly(Side.CLIENT)
	private Icon topTex;
	@SideOnly(Side.CLIENT)
	private Icon botTex;
	
    public BlockDustTable(int i)
    {
        super(i, Material.wood);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        setLightOpacity(0);
        this.setHardness(3F);
        this.setHardness(2.5F);
        this.setStepSound(Block.soundWoodFootstep);
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack item)
    {
    	int l = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 3, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 0, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 1, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 2, 2);
        }

        if (item.hasDisplayName())
        {
            ((TileEntityFurnace)world.getBlockTileEntity(i, j, k)).setGuiDisplayName(item.getDisplayName());
        }
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public Icon getIcon(int i, int meta)
    {
        if (i == 1)
        {
            return topTex;
        }

        if (i == 0)
        {
            return botTex;
        }

        return blockIcon;
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player,int dir, float x, float y, float z)
    {
        if (/*world.multiplayerWorld*/false)
        {
            return true;
        }
        else if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == DustMod.runicPaper.itemID)
        {
            int page = (((TileEntityDustTable)world.getBlockTileEntity(i, j, k)).page - 1);

            if (page == -1)
            {
                return true;
            }

            page = DustManager.getShape(page).id;
            ItemStack to = new ItemStack(DustMod.dustScroll, 1, page);
            ItemStack cur = player.getCurrentEquippedItem() ;

            if (cur.stackSize == 1)
            {
                cur.itemID = DustMod.dustScroll.itemID;
                cur.setItemDamage(to.getItemDamage());
            }
            else
            {
                player.inventory.addItemStackToInventory(to);
                cur.stackSize--;
            }
            return true;
        }
        else
        {
            if (player.isSneaking())
            {
                onBlockClicked(world, i, j, k, player);
                return true;
            }

            TileEntityDustTable tedt = (TileEntityDustTable)world.getBlockTileEntity(i, j, k);
            tedt.page --;

            if (tedt.page < 0)
            {
                tedt.page = DustManager.getNames().size() - DustMod.numSec;
            }

            return true;
        }
    }

    @Override
    public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if (/*world.multiplayerWorld*/false)
        {
            return;
        }
        else
        {
            TileEntityDustTable tedt = (TileEntityDustTable)world.getBlockTileEntity(i, j, k);
            tedt.page++;

            if (tedt.page >= DustManager.getNames().size() - DustMod.numSec + 1)
            {
                tedt.page = 0;
            }
        }
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
        return new TileEntityDustTable();
	}

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(DustMod.spritePath + "table_side");
        this.topTex = par1IconRegister.registerIcon(DustMod.spritePath + "table_top");
        this.botTex = par1IconRegister.registerIcon(DustMod.spritePath + "table_bottom");
    }
}
