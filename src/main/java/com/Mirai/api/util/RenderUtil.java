package com.Mirai.api.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
   public static void drawBox(AxisAlignedBB box, float r, float g, float b, float a) {
      GlStateManager.pushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.disableDepth();
      GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
      GlStateManager.disableTexture2D();
      GlStateManager.depthMask(false);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glLineWidth(1.5F);
      RenderGlobal.renderFilledBox(box, r, g, b, a);
      RenderGlobal.drawSelectionBoundingBox(box, r, g, b, a * 1.5F);
      GL11.glDisable(2848);
      GlStateManager.depthMask(true);
      GlStateManager.enableDepth();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
   }

   public static void drawBoxFromBlockpos(BlockPos blockPos, float r, float g, float b, float a) {
      AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - Minecraft.getMinecraft().getRenderManager().viewerPosX, (double)blockPos.getY() - Minecraft.getMinecraft().getRenderManager().viewerPosY, (double)blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - Minecraft.getMinecraft().getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - Minecraft.getMinecraft().getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - Minecraft.getMinecraft().getRenderManager().viewerPosZ);
      drawBox(axisAlignedBB, r, g, b, a);
   }
}
