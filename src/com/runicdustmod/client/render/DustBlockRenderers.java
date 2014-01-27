package com.runicdustmod.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import com.runicdustmod.api.DustItemManager;
import com.runicdustmod.block.BlockDust;
import com.runicdustmod.tileentity.TileEntityDust;
import com.runicdustmod.tileentity.TileEntityRut;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

/**
 * 4 3 2 5
 */
public class DustBlockRenderers implements ISimpleBlockRenderingHandler
{

	public static int dustModelID;
	public static int rutModelID;

	public int currentRenderer;

	public DustBlockRenderers(int currentRenderer)
	{
		this.currentRenderer = currentRenderer;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer)
	{

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess iblockaccess, int i, int j,
			int k, Block block, int modelId, RenderBlocks renderblocks)
	{

		if (modelId == dustModelID)
		{
			renderDust(renderblocks, iblockaccess, i, j, k, block);
			return true;
		} else if (modelId == rutModelID)
		{
			renderRut(renderblocks, iblockaccess, i, j, k, block);
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return currentRenderer;
	}

	public boolean renderDust(RenderBlocks renderblocks, IBlockAccess iblock,
			int i, int j, int k, Block block)
	{
		int meta = iblock.getBlockMetadata(i, j, k);
		boolean drawHightlight = (meta == 1 || meta == 3);
		int size = TileEntityDust.size;
		float px = 1F / 16F;
		float cellWidth = 1F / size;
		float h = 0.025F;
		TileEntityDust ted = (TileEntityDust) iblock
				.getBlockTileEntity(i, j, k);
		float t = 0.02F;
		Tessellator tes = Tessellator.instance;
		tes.setBrightness(block.getMixedBrightnessForBlock(iblock, i, j, k));

		int[][][] rendArray = ted.getRendArrays();
		int[][] midArray = rendArray[0]; // Actual points
		int[][] horizArray = rendArray[1]; // horizontal connectors
		int[][] vertArray = rendArray[2]; // vertical connectors
		float bx, bz, bw, bl;
		int[] col;
		float r, g, b;

		float highlightHeight = 0.125f;

		for (int x = 0; x < size + 1; x++)
		{
			for (int z = 0; z < size + 1; z++)
			{
				float ox = x * cellWidth;
				float oz = z * (cellWidth);

				if (midArray[x][z] != 0)
				{

					col = DustItemManager.getFloorColorRGB(midArray[x][z]);
					r = (float) col[0];
					g = (float) col[1];
					b = (float) col[2];

					if (meta == BlockDust.ACTIVE_DUST
							|| meta == BlockDust.ACTIVATING_DUST)
					{
						r = 255f;
						g = 0f;
						b = 0f;
					} else if (meta == BlockDust.DEAD_DUST)
					{
						r = 178f;
						g = 178f;
						b = 178f;
					}

					r = r / 255;
					g = g / 255;
					b = b / 255;

					bx = ox + px;
					bz = oz + px;
					bw = 2 * px;
					bl = 2 * px;
					block.setBlockBounds(bx, t, bz, bx + bw, t + h, bz + bl);

					renderblocks.setRenderBoundsFromBlock(block);
					renderblocks.renderStandardBlockWithColorMultiplier(block,
							i, j, k, r, g, b);

					if (drawHightlight)
					{
						if (meta == BlockDust.ACTIVATING_DUST)
						{
							tes.setColorOpaque_F(1, 1, 1);
							block.setBlockBounds(bx, t, bz, bx + bw,
									highlightHeight, bz + bl);
						} else
						{
							tes.setColorOpaque_F(1, 0.68f, 0.68f);
							block.setBlockBounds(bx, t, bz, bx + bw, t + h, bz
									+ bl);
						}
						tes.setBrightness(15728880);
						this.renderGlowPoint(renderblocks, block, i, j, k, x,
								z, midArray[x][z], horizArray, vertArray);
					}
				}

				if (horizArray[x][z] != 0)
				{

					col = DustItemManager.getFloorColorRGB(horizArray[x][z]);
					r = (float) col[0];
					g = (float) col[1];
					b = (float) col[2];

					if (meta == BlockDust.ACTIVE_DUST
							|| meta == BlockDust.ACTIVATING_DUST)
					{
						r = 255f;
						g = 0f;
						b = 0f;
					} else if (meta == BlockDust.DEAD_DUST)
					{
						r = 178f;
						g = 178f;
						b = 178f;
					}

					r = r / 255;
					g = g / 255;
					b = b / 255;

					bx = ox + px;
					bz = oz - px;
					bw = 2 * px;
					bl = 2 * px;

					if (z == 0)
					{
						bz = 0;
						bl = px;
					}

					if (z == size)
					{
						bl = px;
					}

					block.setBlockBounds(bx, t, bz, bx + bw, t + h, bz + bl);

					renderblocks.setRenderBoundsFromBlock(block);
					renderblocks.renderStandardBlockWithColorMultiplier(block,
							i, j, k, r, g, b);

					if (drawHightlight)
					{
						if (meta == BlockDust.ACTIVATING_DUST)
						{
							tes.setColorOpaque_F(1, 1, 1);
							block.setBlockBounds(bx, t, bz, bx + bw,
									highlightHeight, bz + bl);
						} else
						{
							tes.setColorOpaque_F(1, 0.68f, 0.68f);
							block.setBlockBounds(bx, t, bz, bx + bw, t + h, bz
									+ bl);
						}
						tes.setBrightness(15728880);
						this.renderGlowIgnoreSide(renderblocks, block, i, j, k,
								new boolean[] { true, true, false, false });
					}
				}

				if (vertArray[x][z] != 0)
				{

					col = DustItemManager.getFloorColorRGB(vertArray[x][z]);
					r = (float) col[0];
					g = (float) col[1];
					b = (float) col[2];

					if (meta == BlockDust.ACTIVE_DUST
							|| meta == BlockDust.ACTIVATING_DUST)
					{
						r = 255f;
						g = 0f;
						b = 0f;
					} else if (meta == BlockDust.DEAD_DUST)
					{
						r = 178f;
						g = 178f;
						b = 178f;
					}

					r = r / 255;
					g = g / 255;
					b = b / 255;

					bx = ox - px;
					bz = oz + px;
					bw = 2 * px;
					bl = 2 * px;

					if (x == 0)
					{
						bx = 0;
						bw = px;
					}

					if (x == size)
					{
						bw = px;
					}

					block.setBlockBounds(bx, t, bz, bx + bw, t + h, bz + bl);

					renderblocks.setRenderBoundsFromBlock(block);
					renderblocks.renderStandardBlockWithColorMultiplier(block,
							i, j, k, r, g, b);

					if (drawHightlight)
					{
						if (meta == 3)
						{
							tes.setColorOpaque_F(1, 1, 1);
							block.setBlockBounds(bx, t, bz, bx + bw,
									highlightHeight, bz + bl);
						} else
						{
							tes.setColorOpaque_F(1, 0.68f, 0.68f);
							block.setBlockBounds(bx, t, bz, bx + bw, t + h, bz
									+ bl);
						}
						tes.setBrightness(15728880);
						this.renderGlowIgnoreSide(renderblocks, block, i, j, k,
								new boolean[] { false, false, true, true });
					}
				}
			}
		}

		block.setBlockBounds(0, 0, 0, 0, 0, 0);
		renderblocks.setRenderBoundsFromBlock(block);
		renderblocks.renderStandardBlockWithColorMultiplier(block, i, j, k, 1,
				1, 1);
		renderblocks.overrideBlockTexture = null;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, h, 1.0F);
		return true;
	}

	public boolean renderRut(RenderBlocks rb, IBlockAccess iblock, int i,
			int j, int k, Block block)
	{
		int size = TileEntityDust.size;
		TileEntityRut ter = (TileEntityRut) iblock.getBlockTileEntity(i, j, k);
		int[][][] rut = ter.ruts;
		if (rut == null)
			return false;
		float t = 0.02F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(iblock, i,
				j, k));
		block = Block.blocksList[ter.maskBlock];
		if (block == null)
			return false;
		Block fluid = Block.blocksList[ter.fluid];
		float bi = 2F / 16F; // baseInset
		float fi = 1F / 16F; // fluidInset
		float cw = 6F / 16F; // cornerWidth
		float rw = 4F / 16F; // rutWidth
		int rendered = 0;
		boolean isGrass = block == Block.grass;

		// /the top of stuff
		// y
		if (rut[1][0][1] == 0 && rut[1][2][1] == 0)
		{
			block.setBlockBounds(cw, 0, cw, cw + rw, 1F, cw + rw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		} else if (rut[1][0][1] == 0)
		{
			block.setBlockBounds(cw, 0, cw, cw + rw, bi, cw + rw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		} else if (rut[1][2][1] == 0)
		{
			block.setBlockBounds(cw, 1F - bi, cw, cw + rw, 1F, cw + rw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		block.setBlockBounds(0, 1F - cw, 0, cw, 1F, cw);
		rb.setRenderBoundsFromBlock(block);
		rb.renderStandardBlock(block, i, j, k);
		rendered++;
		block.setBlockBounds(1F - cw, 1F - cw, 0, 1F, 1F, cw);
		rb.setRenderBoundsFromBlock(block);
		rb.renderStandardBlock(block, i, j, k);
		rendered++;
		block.setBlockBounds(0, 1F - cw, 1F - cw, cw, 1F, 1F);
		rb.setRenderBoundsFromBlock(block);
		rb.renderStandardBlock(block, i, j, k);
		rendered++;
		block.setBlockBounds(1F - cw, 1F - cw, 1F - cw, 1F, 1F, 1F);
		rb.setRenderBoundsFromBlock(block);
		rb.renderStandardBlock(block, i, j, k);
		rendered++;

		// Top
		// n
		if (rut[1][2][2] == 0)
		{
			block.setBlockBounds(cw, 1f - cw, 1f - cw, cw + rw, 1f, 1f);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		if (isGrass)
		{
			block.setBlockBounds(0, 0, 0, 1, 1, 1);
			block = Block.grass;
		}

		// s
		if (rut[1][2][0] == 0)
		{
			block.setBlockBounds(cw, 1f - cw, 0F, cw + rw, 1f, cw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		if (isGrass)
		{
			block.setBlockBounds(0, 0, 0, 1, 1, 1);
			block = Block.grass;
		}

		// e
		if (rut[2][2][1] == 0)
		{
			block.setBlockBounds(1f - cw, 1f - cw, cw, 1f, 1f, cw + rw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		if (isGrass)
		{
			block.setBlockBounds(0, 0, 0, 1, 1, 1);
			block = Block.grass;
		}

		// w
		if (rut[0][2][1] == 0)
		{
			block.setBlockBounds(0F, 1f - cw, cw, cw, 1f, cw + rw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		if (isGrass)
		{
			block.setBlockBounds(0, 0, 0, 1, 1, 1);
			block = Block.dirt;
		}

		// corners
		block.setBlockBounds(0, 0, 0, cw, cw, cw);
		rb.setRenderBoundsFromBlock(block);
		rb.renderStandardBlock(block, i, j, k);
		rendered++;
		block.setBlockBounds(1F - cw, 0, 0, 1F, cw, cw);
		rb.setRenderBoundsFromBlock(block);
		rb.renderStandardBlock(block, i, j, k);
		rendered++;
		block.setBlockBounds(0, 0, 1F - cw, cw, cw, 1F);
		rb.setRenderBoundsFromBlock(block);
		rb.renderStandardBlock(block, i, j, k);
		rendered++;
		block.setBlockBounds(1F - cw, 0, 1F - cw, 1F, cw, 1F);
		rb.setRenderBoundsFromBlock(block);
		rb.renderStandardBlock(block, i, j, k);
		rendered++;

		// Base fluid
		if (fluid != null)
		{
			float ix, iy, iz;
			float iw, ih, il;
			ix = iy = iz = fi;
			iw = ih = il = 1F - fi;
			ter.updateNeighbors();
			if (ter.isNeighborSolid(1, 0, 0))
			{
				iw += fi;
			}

			if (ter.isNeighborSolid(-1, 0, 0))
			{
				ix -= fi;
				// iw+=fi;
			}

			if (ter.isNeighborSolid(0, 1, 0))
			{
				ih += fi;
			}

			if (ter.isNeighborSolid(0, -1, 0))
			{
				iy -= fi;
			}

			if (ter.isNeighborSolid(0, 0, 1))
			{
				il += fi;
			}

			if (ter.isNeighborSolid(0, 0, -1))
			{
				iz -= fi;
			}

			fluid.setBlockBounds(ix, iy, iz, iw, ih, il);
			rb.setRenderBoundsFromBlock(fluid);
			rb.renderStandardBlock(fluid, i, j, k);
			rendered++;
		} else
		{
			float ix, iy, iz;
			float iw, ih, il;
			ix = iy = iz = bi;
			iw = ih = il = 1F - bi;
			ter.updateNeighbors();
			if (ter.isNeighborSolid(1, 0, 0))
			{
				iw += bi;
			}

			if (ter.isNeighborSolid(-1, 0, 0))
			{
				ix -= bi;
			}

			if (ter.isNeighborSolid(0, 1, 0))
			{
				ih += bi;
			}

			if (ter.isNeighborSolid(0, -1, 0))
			{
				iy -= bi;
			}

			if (ter.isNeighborSolid(0, 0, 1))
			{
				il += bi;
			}

			if (ter.isNeighborSolid(0, 0, -1))
			{
				iz -= bi;
			}

			// Base middle
			block.setBlockBounds(ix, iy, iz, iw, ih, il);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		// Centers

		// x
		if (rut[0][1][1] == 0 && rut[2][1][1] == 0)
		{
			block.setBlockBounds(0, cw, cw, 1F, cw + rw, cw + rw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		} else if (rut[0][1][1] == 0)
		{
			block.setBlockBounds(0, cw, cw, bi, cw + rw, cw + rw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		} else if (rut[2][1][1] == 0)
		{
			block.setBlockBounds(1F - bi, cw, cw, 1F, cw + rw, cw + rw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		// z
		if (rut[1][1][0] == 0 && rut[1][1][2] == 0)
		{
			block.setBlockBounds(cw, cw, 0F, cw + rw, cw + rw, 1F);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		} else if (rut[1][1][0] == 0)
		{
			block.setBlockBounds(cw, cw, 0F, cw + rw, cw + rw, bi);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		} else if (rut[1][1][2] == 0)
		{
			block.setBlockBounds(cw, cw, 1F - bi, cw + rw, cw + rw, 1F);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		// Edges

		// Bottom
		// n
		if (rut[1][0][2] == 0)
		{
			block.setBlockBounds(cw, 0, 1f - cw, cw + rw, cw, 1f);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		// s
		if (rut[1][0][0] == 0)
		{
			block.setBlockBounds(cw, 0, 0F, cw + rw, cw, cw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		// e
		if (rut[2][0][1] == 0)
		{
			block.setBlockBounds(1f - cw, 0, cw, 1f, cw, cw + rw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		// w
		if (rut[0][0][1] == 0)
		{
			block.setBlockBounds(0F, 0, cw, cw, cw, cw + rw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		// Middle
		// nw
		if (rut[0][1][2] == 0)
		{
			block.setBlockBounds(0F, cw, 1f - cw, cw, cw + rw, 1f);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		// ne
		if (rut[2][1][2] == 0)
		{
			block.setBlockBounds(1f - cw, cw, 1f - cw, 1f, cw + rw, 1f);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		// sw
		if (rut[0][1][0] == 0)
		{
			block.setBlockBounds(0, cw, 0f, cw, cw + rw, cw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		// se
		if (rut[2][1][0] == 0)
		{
			block.setBlockBounds(1f - cw, cw, 0f, 1f, cw + rw, cw);
			rb.setRenderBoundsFromBlock(block);
			rb.renderStandardBlock(block, i, j, k);
			rendered++;
		}

		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);

		if (fluid != null)
		{
			fluid.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		}

		return true;
	}

	// Derped due to TextureFX change
	private void renderGlow(RenderBlocks rb, Block block, int i, int j, int k)
	{
	}

	private void renderGlowIgnoreSide(RenderBlocks rb, Block b, int i, int j,
			int k, boolean[] ignore)
	{
	
	}

	private void renderGlowPoint(RenderBlocks rb, Block b, int i, int j, int k,
			int x, int y, int dust, int[][] horiz, int[][] vert)
	{

	}
}
