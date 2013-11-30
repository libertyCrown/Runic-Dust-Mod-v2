package com.dustcore.client.gui;

import java.util.Random;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.dustcore.DustMod;
import com.dustcore.DustShape;
import com.dustcore.api.DustManager;
import com.dustcore.api.InscriptionManager;
import com.dustcore.client.PageHelper;
import com.dustcore.client.render.RenderDustTable;
import com.dustcore.event.InscriptionEvent;
import com.dustcore.util.References;

public class GuiTome extends GuiScreen
{
        private ResourceLocation texture = new ResourceLocation(References.path
                        + "/tomeGui.png");
        private ResourceLocation norunes = new ResourceLocation(References.path
                        + "/pages/no_runes.png");
        private ResourceLocation noinscription = new ResourceLocation(
                        References.path + "/pages/no_inscriptions.png");
        private ResourceLocation info = new ResourceLocation(References.path
                        + "/pages/info.png");

        public static int runePage = 0;
        public static int insPage = 0;

        public static int type = 0;

        /** The X size of the inventory window in pixels. */
        protected int xSize;

        /** The Y size of the inventory window in pixels. */
        protected int ySize;

        /** The starting coords of where the gui is drawn */
        protected int xStart, yStart;

        /**
         * Starting X position for the Gui. Inconsistent use for Gui backgrounds.
         */
        protected int guiLeft;

        /**
         * Starting Y position for the Gui. Inconsistent use for Gui backgrounds.
         */
        protected int guiTop;

        public GuiButton button;

        public int offX;

        public static boolean showSacrifices = true;
        public ItemStack itemstack;

        public GuiTome(ItemStack itemstack)
        {
                super();
                this.itemstack = itemstack;
                xSize = 206;
                ySize = 166;
                offX = xSize / 4;
        }

        @Override
        public void drawScreen(int par1, int par2, float par3)
        {
                super.drawScreen(par1, par2, par3);
                drawDefaultBackground();
                drawGuiContainerBackgroundLayer(par3, par1, par2);
                drawGuiContainerForegroundLayer();

                for (int i = 0; i < buttonList.size(); i++)
                {
                        GuiButton guibutton = (GuiButton) buttonList.get(i);
                        guibutton.drawButton(mc, par1, par2);
                }
        }

        @Override
        public void initGui()
        {
                super.initGui();
                guiLeft = (width - xSize) / 2;
                guiTop = (height - ySize) / 2;
                this.buttonList.add(button = new GuiButton(1, (width + xSize) / 2 + 2
                                - offX, (height - ySize) / 2 + 2 + ySize - 20, (width - xSize)
                                / 2 + offX - 2, 20, "Description >"));
        }

        /**
         * Called when the screen is unloaded. Used to disable keyboard repeat
         * events
         */
        public void onGuiClosed()
        {
        }

        String[] derp = new String[] { "Hi", "I should get rid of this page...",
                        "Hope you enjoy!", "Make some runes!", "Space for rent.",
                        "Modders: Make custom runes!", "Insert joke here.",
                        "Direwolf20 is cool!", "Notch is cool!", "Jeb_ is cool!",
                        "Stop annoying LexManos!", "Go play Thaumcraft.",
                        "The QubeTubers are cool!", "Try Minecraft: Ars Magica!",
                        "Play outside!", "Now version 2!", "zombiepig333 says hi!" };
        int randAuthor = (int) (Math.random() * derp.length);

        /**
         * Draw the foreground layer for the GuiContainer (everything in front of the
         * items)
         */
        protected void drawGuiContainerForegroundLayer()
        {
                String name = "";
                String author = "";
                String notes = "";
                boolean recolor = false;

                if ((isRunes() && getRunePage() == 0)
                                || (!isRunes() && getInsPage() == 0))
                {
                        name = "Legend: "
                                        + (isRunes() ? DustManager.namesRemote.size()
                                                        : InscriptionManager.eventsRemote.size())
                                        + " installed";
                        notes = "\n\n\n"
                                        + "Meat: Pork, Beef, or Chicken raw or cooked.\n---\n"
                                        + "Drops: Any item corresponding to a particular mob.\n---\n"
                                        + "Variable: The dust is interchangable and allows you to set traits of the rune.\n---\n"
                                        + "Powered: If the name is red, then it requires fueling via smeltables.";

                        author = derp[randAuthor];
                } else
                {
                        if (isRunes())
                        {
                                DustShape shape = DustManager.getShape(getRunePage() - 1);
                                name = shape.getRuneName();
                                notes = showSacrifices ? shape.getNotes() : shape
                                                .getDescription();
                                author = "by " + shape.getAuthor();
                                if (shape.isPower)
                                {
                                        recolor = true;
                                }
                                Random rand = new Random();
                                randAuthor = (int) (rand.nextInt(derp.length));
                        } else
                        {
                                InscriptionEvent event = InscriptionManager
                                                .getEventInOrder(getInsPage() - 1);
                                name = event.getInscriptionName();
                                notes = showSacrifices ? event.getNotes() : event
                                                .getDescription();
                                author = "by " + event.getAuthor();
                                Random rand = new Random();
                                randAuthor = (int) (rand.nextInt(derp.length));
                        }
                }

                GL11.glColor3f(255, 0, 0);
                fontRenderer.drawString(name, (width - xSize) / 2 - offX,
                                (height - ySize) / 2 - fontRenderer.FONT_HEIGHT - 2,
                                recolor ? 0xFF0000 : 0xEEEEEE);
                fontRenderer.drawSplitString(notes, (width + xSize) / 2 + 2 - offX,
                                (height - ySize) / 2 + 2, (width - xSize) / 2 + offX,
                                0xffa0a0a0);
                GL11.glPushMatrix();
                float scale = 0.6666F;
                GL11.glTranslated((width - xSize) / 2 - offX, (height - ySize) / 2
                                + ySize, 0);
                GL11.glScalef(scale, scale, scale);
                fontRenderer.drawString(author, 0, 0, 0xffa0a0a0);
                GL11.glPopMatrix();
        }

        /**
         * Called from the main game loop to update the screen.
         */
        public void updateScreen()
        {
                super.updateScreen();
        }

        public void setRunePage(int p)
        {
                runePage = p;
        }

        public int getRunePage()
        {
                return runePage;
        }

        public void setInsPage(int p)
        {
                insPage = p;
        }

        public int getInsPage()
        {
                return insPage;
        }

        @Override
        protected void keyTyped(char par1, int key)
        {
                super.keyTyped(par1, key);

                if (key == 1 || key == this.mc.gameSettings.keyBindInventory.keyCode)
                {
                        this.mc.thePlayer.closeScreen();
                        // this.mc.displayGuiScreen((GuiScreen)null);
                }

                if (key == mc.gameSettings.keyBindLeft.keyCode)
                {
                        retreatPage();
                } else if (key == mc.gameSettings.keyBindRight.keyCode)
                {
                        advancePage();
                }
        }

        /**
         * Called when the mouse is clicked.
         */
        protected void mouseClicked(int x, int y, int m)
        {
                super.mouseClicked(x, y, m);

                if (x < xStart + 6 && y - yStart < 100 && y - yStart > 16)
                {
                        y -= yStart;
                        if (y > 56)
                                setType(1);
                        else if (y > 16)
                                setType(0);
                } else if (y >= (height / 2 - ySize / 2)
                                && y <= (height / 2 + ySize / 2))
                {
                        if (x >= width / 2 - xSize / 2 - offX
                                        && x <= width / 2 + xSize / 2 - offX)
                        {
                                if (m == 0)
                                {
                                        advancePage();
                                } else if (m == 1)
                                {
                                        retreatPage();
                                }
                        }
                }

                // System.out.println("Click " + par1 + " " + par2 + " " + par3 + " " +
                // width + " " + height);
        }

        private void advancePage()
        {
                if (isRunes())
                {
                        // itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                        setRunePage(getRunePage() + 1);

                        if (getRunePage() >= DustManager.getShapes().size()
                                        - DustMod.numSec + 1)
                        {
                                setRunePage(0);
                        }
                } else
                {

                        setInsPage(getInsPage() + 1);

                        if (getInsPage() >= InscriptionManager.getEvents().size() + 1)
                        {
                                setInsPage(0);
                        }
                }
        }

        private void retreatPage()
        {
                if (isRunes())
                {
                        setRunePage(getRunePage() - 1);

                        if (getRunePage() < 0)
                        {
                                setRunePage(DustManager.getShapes().size() - DustMod.numSec);
                        }
                } else
                {
                        setInsPage(getInsPage() - 1);

                        if (getInsPage() < 0)
                        {
                                setInsPage(InscriptionManager.getEvents().size());
                        }
                }
        }

        /**
         * Draw the background layer for the GuiContainer (everything behind the
         * items)
         */
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3)
        {
                TextureObject i = mc.renderEngine.getTexture(texture);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.renderEngine.bindTexture(texture);
                int j = (width - xSize) / 2 - offX;
                int k = (height - ySize) / 2;
                xStart = j;
                yStart = k;
                int pageWidth = 70;
                int pageHeight = 56;
                int ox = 4;
                int oy = 4;
                float scalex = (float) (xSize - ox * 2) / 256F;
                float scaley = (float) (ySize - oy * 2) / 256F;
                float res = xSize / ySize;
                drawTexturedModalRect(j, k, 24, 0, xSize, ySize);
                GL11.glPushMatrix();
                GL11.glScalef(1 / res, res, 1);
                GL11.glTranslatef(j + ox, k + oy, 0);
                GL11.glScalef(scalex, scaley, 1f);

                if (isRunes())
                {
                        if (getRunePage() == 0)
                        {
                                if (DustManager.isEmpty())
                                {
                                        mc.renderEngine.bindTexture(norunes);
                                } else
                                {
                                        mc.renderEngine.bindTexture(info);
                                }
                        } else
                                PageHelper.bindPage(RenderDustTable
                                                .getRunePageName(getRunePage()));
                } else
                {
                        if (getInsPage() == 0)
                        {
                                if (InscriptionManager.isEmpty())
                                {
                                        mc.renderEngine.bindTexture(noinscription);
                                } else
                                {
                                        mc.renderEngine.bindTexture(info);
                                }
                        } else
                                PageHelper.bindPage(InscriptionManager.getEventInOrder(
                                                getInsPage() - 1).getIDName());
                }
                drawTexturedModalRect(0, 0, 0, 0, 256, 256);

                GL11.glPopMatrix();

                if (isRunes())
                {
                        mc.renderEngine.bindTexture(texture);
                        drawTexturedModalRect(j - 6, k, 12, 0, 12, ySize);
                } else
                {
                        mc.renderEngine.bindTexture(texture);
                        drawTexturedModalRect(j - 6, k, 0, 0, 12, ySize);
                }
        }

        public boolean isRunes()
        {
                return type == 0;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        @Override
        protected void actionPerformed(GuiButton but)
        {
                if (but == button)
                {
                        showSacrifices = !showSacrifices;

                        if (showSacrifices)
                        {
                                but.displayString = "Description >";
                        } else
                        {
                                but.displayString = "< Information";
                        }
                } else
                {
                        super.actionPerformed(but);
                }
        }
}