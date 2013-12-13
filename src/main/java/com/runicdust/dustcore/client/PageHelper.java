package com.runicdust.dustcore.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.runicdust.dustcore.DustMod;
import com.runicdust.dustcore.DustShape;
import com.runicdust.dustcore.api.DustItemManager;
import com.runicdust.dustcore.event.InscriptionEvent;

public class PageHelper
{
	public static String folder = "/assets/dustcore/";
	public static String runeFolder = "/assets/dustcore/runedata/image/";
	public static String insFolder = "/assets/dustcore/inscriptiondata/image/";
	public static BufferedImage background;
	public static BufferedImage backgroundIns;
	public static BufferedImage shade;
	public static BufferedImage colors;
	public static int bgw, bgh;
	public static PageHelper instance;
	private static BufferedImage missingExternalTextureImage;

	private static HashMap<String, BufferedImage> images;

	public PageHelper()
	{
		String minecraftPath = DustMod.suggestedConfig.getParent();
		File file = new File(minecraftPath);
		minecraftPath = file.getParent();

		folder = minecraftPath + folder;
		runeFolder = minecraftPath + runeFolder;
		insFolder = minecraftPath + insFolder;

		missingExternalTextureImage = new BufferedImage(64, 64, 2);
		Graphics var3 = missingExternalTextureImage.getGraphics();
		var3.setColor(Color.WHITE);
		var3.fillRect(0, 0, 64, 64);
		var3.setColor(Color.BLACK);
		var3.drawString("missingEXTtex", 1, 10);
		var3.dispose();
		images = new HashMap<String, BufferedImage>();
		try
		{
			background = getImage("textures/pages/background.png");
			backgroundIns = getImage("textures/pages/backgroundIns.png");
			shade = getImage("textures/pages/shade.png");
			colors = getImage("textures/pages/colors.png");
			bgw = background.getWidth();
			bgh = background.getHeight();

			boolean success = new File(folder).mkdir();
			if (success)
			{
				DustMod.log(Level.INFO,
						"Lexicon Folder " + new File(folder).getAbsolutePath()
								+ " created.");
				System.out.println("[DustMod] Lexicon Folder "
						+ new File(folder).getAbsolutePath() + " created.");
			}
			new File(runeFolder).mkdirs();
			new File(insFolder).mkdirs();
		} catch (IOException ex)
		{
			Logger.getLogger(PageHelper.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public static void checkInscriptionImage(InscriptionEvent event)
	{
		BufferedImage dust = new BufferedImage(bgw, bgh,
				BufferedImage.TYPE_INT_ARGB);
		BufferedImage result = new BufferedImage(bgw, bgh,
				BufferedImage.TYPE_INT_ARGB);

		String name = "" + event.idName;
		while (Character.isDigit(name.charAt(name.length() - 1)))
		{
			name = name.substring(0, name.length() - 1);
		}

		File file = new File(insFolder + name + ".png");

		int[][] values = event.referenceDesign;
		int width = values[0].length;
		int height = values.length;

		int pxwMax = bgw - 6;
		int pxhMax = bgh - 6;

		int pxWidth = 0;
		int pxHeight = 0;

		int dW = 1; // dotWidth
		int sW = 1; // spaceWidth

		pxWidth = 34;
		pxHeight = 34;

		// Dust
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				int value = values[y][x];
				if (value != 0)
				{
					int[] loc = getPosition(x, y, dW, sW, bgw, bgh, width,
							height);
					int colorCheck = value;
					if (colorCheck == -1)
					{
						colorCheck = 0;
					}
					colorCheck *= 2;
					colorCheck += 2;

					if (x < width - 1 && values[y][x + 1] == value)
					{
						int[] nextLoc = getPosition(x + 1, y, dW, sW, bgw, bgh,
								width, height);
						for (int i = Math.min(loc[0], nextLoc[0]); i < Math
								.max(loc[0], nextLoc[0]); i++)
						{
							for (int j = 0; j < dW; j++)
							{
								dust.setRGB(i, loc[1] + j,
										getRandomDustColor(value, false));
							}
						}
					}
					if (y < height - 1 && values[y + 1][x] == value)
					{
						int[] nextLoc = getPosition(x, y + 1, dW, sW, bgw, bgh,
								width, height);
						for (int i = 0; i < dW; i++)
						{
							for (int j = Math.min(loc[1], nextLoc[1]); j < Math
									.max(loc[1], nextLoc[1]); j++)
							{
								dust.setRGB(loc[0] + i, j,
										getRandomDustColor(value, false));
							}
						}
					}
					for (int i = 0; i < dW; i++)
					{
						for (int j = 0; j < dW; j++)
						{
							dust.setRGB(loc[0] + i, loc[1] + j,
									getRandomDustColor(value, true));
						}
					}
				}
			}
		}

		// shade and finalization
		result.getGraphics().drawImage(backgroundIns, 0, 0, null);
		for (int x = 0; x < result.getWidth(); x++)
		{
			for (int y = 0; y < result.getHeight(); y++)
			{
				if (dust.getRGB(x, y) != 0)
				{
					result.setRGB(x, y, dust.getRGB(x, y));
				}
				int color = result.getRGB(x, y);
				Color c = new Color(color);
				int r, g, b;
				r = c.getRed();
				g = c.getGreen();
				b = c.getBlue();

				c = new Color(r, g, b);
				int resultColor = c.getRGB();
				result.setRGB(x, y, resultColor);
			}
		}
		try
		{
			new File(insFolder).mkdirs();
			ImageIO.write(result, "PNG", new File(insFolder + name + ".png"));
		} catch (IOException ex)
		{
			Logger.getLogger(PageHelper.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		images.put(name, result);
	}

	public static void checkRuneImage(DustShape shape)
	{
		BufferedImage dust = new BufferedImage(bgw, bgh,
				BufferedImage.TYPE_INT_ARGB);
		BufferedImage result = new BufferedImage(bgw, bgh,
				BufferedImage.TYPE_INT_ARGB);
		String name = "" + shape.name;
		while (Character.isDigit(name.charAt(name.length() - 1)))
		{
			name = name.substring(0, name.length() - 1);
		}

		File file = new File(runeFolder + name + ".png");

		int[][][] values = shape.data;
		int width = shape.data[0][0].length;
		int height = shape.data[0].length;

		int pxwMax = bgw - 6;
		int pxhMax = bgh - 6;

		int pxWidth = 0;
		int pxHeight = 0;

		int dW = 3; // dotWidth
		int sW = 2; // spaceWidth

		pxWidth = width * dW + (width - 1) * sW + dW;
		pxHeight = height * dW + (height - 1) * sW + dW;
		if (pxWidth > pxwMax || pxHeight > pxhMax)
		{
			dW = 2;
			sW = 2;
			pxWidth = width * dW + (width - 1) * sW;
			pxHeight = height * dW + (height - 1) * sW;

			if (pxWidth > pxwMax || pxHeight > pxhMax)
			{
				dW = 2;
				sW = 1;
				pxWidth = width * dW + (width - 1) * sW;
				pxHeight = height * dW + (height - 1) * sW;

				if (pxWidth > pxwMax || pxHeight > pxhMax)
				{
					dW = 1;
					sW = 1;
					pxWidth = width * dW + (width - 1) * sW;
					pxHeight = height * dW + (height - 1) * sW;
				}
			}
		}

		// gold
		int tx = pxWidth / 2 + 2;
		int ty = pxHeight / 2 + 2;
		for (int x = -tx; x < tx; x++)
		{
			for (int y = -ty; y < ty; y++)
			{
				if (x == -tx || y == -ty || x == tx - 1 || y == ty - 1)
				{
					dust.setRGB(bgw / 2 + x, bgh / 2 + y, getColor(colors, 0));
				} else
				{
					dust.setRGB(bgw / 2 + x, bgh / 2 + y, getColor(colors, 1));
				}
			}
		}

		// corners
		for (int x = 0; x <= 1; x++)
		{
			for (int y = 0; y <= 1; y++)
			{
				if (x != 0 || y != 0 || true)
				{
					dust.setRGB(bgw / 2 - tx + x - 1, bgh / 2 - ty + y - 1,
							getColor(colors, 0));
					dust.setRGB(bgw / 2 + tx + x - 1, bgh / 2 - ty + y - 1,
							getColor(colors, 0));
					dust.setRGB(bgw / 2 + tx + x - 1, bgh / 2 + ty + y - 1,
							getColor(colors, 0));
					dust.setRGB(bgw / 2 - tx + x - 1, bgh / 2 + ty + y - 1,
							getColor(colors, 0));
				}
			}
		}

		// Dust
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				int value = values[0][y][x];
				if (value != 0)
				{
					int[] loc = getPosition(x, y, dW, sW, bgw, bgh, width,
							height);
					int colorCheck = value;
					if (colorCheck == -1)
					{
						colorCheck = 0;
					}
					colorCheck *= 2;
					colorCheck += 2;

					if (x < width - 1 && values[0][y][x + 1] == value)
					{
						int[] nextLoc = getPosition(x + 1, y, dW, sW, bgw, bgh,
								width, height);
						for (int i = Math.min(loc[0], nextLoc[0]); i < Math
								.max(loc[0], nextLoc[0]); i++)
						{
							for (int j = 0; j < dW; j++)
							{
								dust.setRGB(i, loc[1] + j,
										getRandomDustColor(value, false));
							}
						}
					}
					if (y < height - 1 && values[0][y + 1][x] == value)
					{
						int[] nextLoc = getPosition(x, y + 1, dW, sW, bgw, bgh,
								width, height);
						for (int i = 0; i < dW; i++)
						{
							for (int j = Math.min(loc[1], nextLoc[1]); j < Math
									.max(loc[1], nextLoc[1]); j++)
							{
								dust.setRGB(loc[0] + i, j,
										getRandomDustColor(value, false));
							}
						}
					}
					for (int i = 0; i < dW; i++)
					{
						for (int j = 0; j < dW; j++)
						{
							dust.setRGB(loc[0] + i, loc[1] + j,
									getRandomDustColor(value, true));
						}
					}
				}
			}
		}

		// shade and finalization
		result.getGraphics().drawImage(background, 0, 0, null);
		for (int x = 0; x < result.getWidth(); x++)
		{
			for (int y = 0; y < result.getHeight(); y++)
			{
				if (dust.getRGB(x, y) != 0)
				{
					result.setRGB(x, y, dust.getRGB(x, y));
				}
				int color = result.getRGB(x, y);
				Color c = new Color(color);
				int r, g, b;
				r = c.getRed();
				g = c.getGreen();
				b = c.getBlue();
				c = new Color(r, g, b);
				int resultColor = c.getRGB();
				result.setRGB(x, y, resultColor);
			}
		}
		try
		{
			new File(runeFolder).mkdirs();
			ImageIO.write(result, "PNG", new File(runeFolder + name + ".png"));
		} catch (IOException ex)
		{
			Logger.getLogger(PageHelper.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		images.put(name, result);
	}

	public static int linearColorBurn(int v1, int v2)
	{
		return ((v1 + v2) < 255) ? 0 : (v1 + v2 - 255);
	}

	public static int[] getPosition(int x, int y, int dW, int sW, int bgW,
			int bgH, int w, int h)
	{
		int[] rtn = new int[] { 0, 0 };

		x -= w / 2;
		y -= h / 2;

		if (x >= 0 && sW < 2)
		{
			rtn[0]++;
		}
		if (y >= 0 && sW < 2)
		{
			rtn[1]++;
		}
		if (dW == 2 && sW == 2)
		{
			rtn[0]++;
			rtn[1]++;
		}

		rtn[0] += x * dW;
		rtn[0] += (x - 1) * sW;

		rtn[1] += y * dW;
		rtn[1] += (y - 1) * sW;

		rtn[0] += bgW / 2;
		rtn[1] += bgH / 2;

		rtn[0] += dW;
		rtn[1] += dW;

		return rtn;
	}

	public static int getColor(BufferedImage colors, int color)
	{
		int rand = new Random().nextInt(16);
		return colors.getRGB(rand, color);
	}

	public static int getRandomDustColor(int dust, boolean primary)
	{
		int color = DustItemManager.getPrimaryColor(dust);
		if (!primary)
		{
			color = new Color(color).brighter().getRGB();
		}

		int r = (color & 0xFF0000) >> 16;
		int g = (color & 0xFF00) >> 8;
		int b = (color & 0xFF);
		Color temp = new Color(DustItemManager.getFloorColorRGB(dust)[0],
				DustItemManager.getFloorColorRGB(dust)[0],
				DustItemManager.getFloorColorRGB(dust)[2]);
		Color c = primary ? temp : temp;

		if (primary)
		{
			r += 10;
			g += 10;
			b += 10;
		} else
		{
			r -= 10;
			g -= 10;
			b -= 10;
		}

		int tol = 30;
		Random rand = new Random();
		int random = rand.nextInt(tol);

		c = stain(c, (float) rand.nextGaussian() * 0.05f
				+ (primary ? 0.02F : 0));
		if (primary)
			random *= -1;

		r = r + random;
		g = g + random;
		b = b + random;

		if (r < 0)
			r = 0;
		if (r > 255)
			r = 255;
		if (g < 0)
			g = 0;
		if (g > 255)
			g = 255;
		if (b < 0)
			b = 0;
		if (b > 255)
			b = 255;

		color = (r << 16) | (g << 8) | (b);
		return color;
	}

	public static Color stain(Color color, float amount)
	{
		int r = (int) ((color.getRed() * (1 - amount) / 255) * 255);
		int g = (int) ((color.getGreen() * (1 - amount) / 255) * 255);
		int b = (int) ((color.getBlue() * (1 - amount) / 255) * 255);

		if (r < 0)
			r = 0;
		if (r > 255)
			r = 255;
		if (g < 0)
			g = 0;
		if (g > 255)
			g = 255;
		if (b < 0)
			b = 0;
		if (b > 255)
			b = 255;

		return new Color(r, g, b);
	}

	public static BufferedImage getImage(String file) throws IOException
	{
		if (images.containsKey(file))
		{
			return images.get(file);
		}
		BufferedImage rtn = null;
		Minecraft mc = Minecraft.getMinecraft();
		ResourceLocation location = new ResourceLocation("dustcore", file);
		ResourceManager rp = mc.getMinecraft().getResourceManager();
		InputStream stream = rp.getResource(location).getInputStream();
		if (stream == null)
		{
			throw new IllegalArgumentException(
					"[DustMod] Image file not found! " + file
							+ ". Perhaps you installed it wrong?");
		}
		rtn = ImageIO.read(stream);
		images.put(file, rtn);
		return rtn;
	}

	public static void bindPage(String name)
	{
		Minecraft mc = Minecraft.getMinecraft();
		TextureManager re = mc.renderEngine;
		int tex = GL11.glGenTextures();
		try
		{
			BufferedImage image = getImage(name);
			DynamicTexture testing = new DynamicTexture(image);
			tex = testing.getGlTextureId();
		} catch (IOException ex)
		{
			Logger.getLogger(PageHelper.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex);
	}
}
