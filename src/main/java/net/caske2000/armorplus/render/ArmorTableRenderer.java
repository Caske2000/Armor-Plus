package net.caske2000.armorplus.render;

import net.caske2000.armorplus.tileentities.TileEntityArmorTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * Created by Caske2000 on 6-3-2016.
 */
public class ArmorTableRenderer extends TileEntitySpecialRenderer
{
    private final EntityItem displayItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D);
    private float angle = 0;

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        TileEntityArmorTable armorTable = (TileEntityArmorTable) te;
        ItemStack armor = armorTable.getStackInSlot(1);
        if (armor != null)
        {
            displayItem.setEntityItemStack(armor);

            GlStateManager.pushMatrix();

            GL11.glDisable(GL11.GL_LIGHTING);
            GlStateManager.translate(x + 0.5, y, z + 0.5);
            GL11.glRotatef(angle, 0, 1, 0);
            Minecraft.getMinecraft().getRenderManager().doRenderEntity(displayItem, 0, 0.2F, 0, 0.0F, 0.0F, true);
            GL11.glEnable(GL11.GL_LIGHTING);

            GlStateManager.popMatrix();
            angle++;
        }
        if (angle > 360) angle = 0;
    }
}
