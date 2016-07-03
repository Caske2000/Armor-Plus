package net.caske2000.armorplus.render;

import net.caske2000.armorplus.tileentities.TileEntityArmorCharger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * Created by Caske2000 on 26/03/2016.
 */
public class ArmorChargerRenderer extends TileEntitySpecialRenderer {
    private final EntityItem displayItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D);
    private float angle = 0;

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        TileEntityArmorCharger armorCharger = (TileEntityArmorCharger) te;
        ItemStack armor = armorCharger.getStackInSlot(0);
        if (armor != null) {
            displayItem.setEntityItemStack(armor);

            GlStateManager.pushMatrix();

            //GL11.glDisable(GL11.GL_LIGHTING);
            GlStateManager.disableLighting();
            //GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
            GlStateManager.translate(x + 0.5, y, z + 0.5);
            GL11.glRotatef(angle, 0, 1, 0);
            Minecraft.getMinecraft().getRenderManager().doRenderEntity(displayItem, 0, 0.25F, 0, 0.0F, 0.0F, true);
            //GL11.glEnable(GL11.GL_LIGHTING);
            GlStateManager.enableLighting();

            GlStateManager.popMatrix();
            angle++;
        }
        if (angle > 360) angle = 0;
    }
}
