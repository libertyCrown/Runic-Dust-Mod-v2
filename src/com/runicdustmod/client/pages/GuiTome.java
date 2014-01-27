package com.runicdustmod.client.pages;

import com.runicdustmod.RunicDustMod;
import com.runicdustmod.DustShape;
import com.runicdustmod.api.DustManager;
import com.runicdustmod.api.InscriptionManager;
import com.runicdustmod.client.pages.PageHelper;
import com.runicdustmod.client.render.RenderDustTable;
import com.runicdustmod.event.InscriptionEvent;
import com.runicdustmod.util.References;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import java.util.Random;

public class GuiTome extends GuiScreen {

    //Texture locations
    private ResourceLocation texture = new ResourceLocation(References.path + "/guiTomev2.png");
    private ResourceLocation norunes = new ResourceLocation(References.path + "/pages/no_runes.png");
    private ResourceLocation noinscription = new ResourceLocation(References.path + "/pages/no_inscriptions.png");
    private ResourceLocation info = new ResourceLocation(References.path + "/pages/info.png");

    //Page counters
    public static int guidePage = 0;
    public static int runePage = 0;
    public static int inscPage = 0;

    //Current selected section (guide, runes, inscriptions)
    public static int section;

    /** The X size of the inventory window in pixels. */
    protected int xSize;

    /** The Y size of the inventory window in pixels. */
    protected int ySize;

    /** The starting coords of where the gui is drawn */
    protected int xStart, yStart;

    /** Starting X position for the Gui. Inconsistent use for Gui backgrounds. **/
    protected int guiLeft;

    /** Starting Y position for the Gui. Inconsistent use for Gui backgrounds. **/
    protected int guiTop;

    //Button to change section of current entry
    public GuiButton button;

    //X offset for rune text (will be removed/tweaked)
    public int offX;

    public static boolean showSacrifices = true;
    public ItemStack itemstack;

    public GuiTome(ItemStack istack)
    {
        super();
        this.itemstack = istack;
        xSize = 600;
        ySize = 240;
        offX = xSize / 6;
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
        //TODO- Redo button with better look
        /*this.buttonList.add(button = new GuiButton(1, (width + xSize) / 2 + 2
                                - offX, (height - ySize) / 2 + 2 + ySize - 20, (width - xSize)
                                / 2 + offX - 2, 20, "Description >"));*/
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events.
     */
    public void onGuiClosed()
    {
    }

    String[] splashArray = new String[]
    {
            "Now version 2!","Supports Thaumcraft 4!","Much runes.","Like a thauminomicron!",
            "Don't ask for updates!","Like an Arcane Compendium!","Make your own runes!",
            "No dust on walls yet!","Direwolf20 is cool!", "Requires half a brain!"
    };

    int randomCaption = (int) (Math.random() * splashArray.length);

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the
     * items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
        String entryName = "";
        String entryCaption = "";
        String entryText = "";
        boolean doRecolor = false;

        if ((isEntryGuide() && getGuidePage() == 0) || (isEntryRune() && getRunePage() == 0) || isEntryInsc() && getInscPage() == 0)
        {
            //Starting page contents set; default start
            //TODO-tweak information shown here, maybe make it an xml?
            entryName = "Legend: "
                    + (isEntryRune() ? DustManager.namesRemote.size()
                    : InscriptionManager.eventsRemote.size())
                    + " installed";
            entryText = "\n\n"
                    + "Definitions: \n---\n"
                    + "Meat: Pork, Beef, or Chicken raw or cooked.\n---\n"
                    + "Drops: Any item corresponding to a particular mob.\n---\n"
                    + "Variable: The dust is interchangable and allows you to set traits of the rune.\n---\n"
                    + "Powered: If the name is red, then it requires fueling via smeltables.";

            entryCaption = splashArray[randomCaption];
        }
        else
        {
            //loads guide, rune, or inscription data depending on selected tab
            if (isEntryGuide())
            {
                //TODO- establish guide :P
            }

            if (isEntryRune())
            {
                DustShape shape = DustManager.getShape(getRunePage() - 1);
                entryName = shape.getRuneName();
                entryText = showSacrifices ? shape.getNotes() : shape.getDescription();
                entryCaption = "by " + shape.getAuthor();
                if (shape.isPower)
                {
                    doRecolor = true;
                }
                Random rand = new Random();
                randomCaption = (int) (rand.nextInt(splashArray.length));
            }

            if (isEntryInsc())
            {
                //TODO- do whatever needed for xml migration
                InscriptionEvent event = InscriptionManager
                        .getEventInOrder(getInscPage() - 1);
                entryName = event.getInscriptionName();
                entryText = showSacrifices ? event.getNotes() : event
                        .getDescription();
                entryCaption = "by " + event.getAuthor();
                Random rand = new Random();
                randomCaption = (int) (rand.nextInt(splashArray.length));

            }
            GL11.glColor3f(255, 0, 0);
            //TODO- restore text rendering; out of comission during gui work
                /*fontRenderer.drawString(name, (width - xSize) / 2 - offX,
                                (height - ySize) / 2 - fontRenderer.FONT_HEIGHT - 2,
                                recolor ? 0xFF0000 : 0xEEEEEE);
                fontRenderer.drawSplitString(notes, (width + xSize) / 2 + 2 - offX,
                                (height - ySize) / 2 + 2, (width - xSize) / 2 + offX,
                                0xffa0a0a0);*/
            GL11.glPushMatrix();
            float scale = 0.6666F;
            GL11.glTranslated((width - xSize) / 2 - offX, (height - ySize) / 2 + ySize, 0);
            GL11.glScalef(scale, scale, scale);
            fontRenderer.drawString(entryCaption, 0, 0, 0xffa0a0a0);
            GL11.glPopMatrix();
        }
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the
     * items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        TextureObject i = mc.renderEngine.getTexture(texture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        xStart = j;
        yStart = k;
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

        if (isEntryRune())
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
            if (getInscPage() == 0)
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
                        getInscPage() - 1).getIDName());
        }
        drawTexturedModalRect(0, 0, 0, 0, 256, 256);

        GL11.glPopMatrix();

        if (isEntryRune())
        {
            mc.renderEngine.bindTexture(texture);
            drawTexturedModalRect(j - 6, k, 12, 0, 12, ySize);
        } else
        {
            mc.renderEngine.bindTexture(texture);
            drawTexturedModalRect(j - 6, k, 0, 0, 12, ySize);
        }
    }

    @Override
    protected void keyTyped(char par1, int key)
    {
        super.keyTyped(par1, key);

        if (key == 1 || key == this.mc.gameSettings.keyBindInventory.keyCode)
        {
            this.mc.thePlayer.closeScreen();
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
            //HEY! ZOMBIEPIG! HERE IS THAT CODE YOU WERE LOOKING FOR!!
            y -= yStart;
            if (y > 56){
                setSection(1);
            }else if (y > 16){
                setSection(0);
            }
        } else if (y >= (height / 2 - ySize / 2)
                && y <= (height / 2 + ySize / 2))
        {
            if (x >= width / 2 - xSize / 2 - offX
                    && x <= width / 2 + xSize / 2 - offX)
            {
                if (m == 0)
                {
                    advancePage();
                }
                else if (m == 1)
                {
                    retreatPage();
                }
            }
        }
    }

    private void advancePage()
    {
        if (isEntryRune())
        {
            setRunePage(getRunePage() + 1);

            if (getRunePage() >= DustManager.getShapes().size()
                    - RunicDustMod.numSec + 1)
            {
                setRunePage(0);
            }
        } else
        {

            setInscPage(getInscPage() + 1);

            if (getInscPage() >= InscriptionManager.getEvents().size() + 1)
            {
                setInscPage(0);
            }
        }
    }

    private void retreatPage()
    {
        if (isEntryRune())
        {
            setRunePage(getRunePage() - 1);

            if (getRunePage() < 0)
            {
                setRunePage(DustManager.getShapes().size() - RunicDustMod.numSec);
            }
        } else
        {
            setInscPage(getInscPage() - 1);

            if (getInscPage() < 0)
            {
                setInscPage(InscriptionManager.getEvents().size());
            }
        }
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


    //Utility functions that I just threw here

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
    }

    public void setSection(int section)
    {
        this.section = section;
    }

    public boolean isEntryGuide()
    {  return section == 0;  }

    public boolean isEntryRune()
    {  return section == 1;  }

    public boolean isEntryInsc()
    {  return section == 2;  }

    public void setGuidePage(int page)
    {  guidePage = page;  }

    public int getGuidePage()
    {  return guidePage;  }

    public void setRunePage(int page)
    {  runePage = page;  }

    public int getRunePage()
    {  return runePage;  }

    public void setInscPage(int page)
    {  inscPage = page;  }

    public int getInscPage()
    {  return inscPage;  }
}