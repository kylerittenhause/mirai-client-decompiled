package com.Mirai.api.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
   public static void drawBox(AxisAlignedBB box, float r, float g, float b, float a) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179147_l();
      GlStateManager.func_179097_i();
      GlStateManager.func_179120_a(770, 771, 0, 1);
      GlStateManager.func_179090_x();
      GlStateManager.func_179132_a(false);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glLineWidth(1.5F);
      RenderGlobal.func_189696_b(box, r, g, b, a);
      RenderGlobal.func_189697_a(box, r, g, b, a * 1.5F);
      GL11.glDisable(2848);
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179126_j();
      GlStateManager.func_179098_w();
      GlStateManager.func_179084_k();
      GlStateManager.func_179121_F();
   }

   public static void drawBoxFromBlockpos(BlockPos blockPos, float r, float g, float b, float a) {
      AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.func_177958_n() - Minecraft.func_71410_x().func_175598_ae().field_78730_l, (double)blockPos.func_177956_o() - Minecraft.func_71410_x().func_175598_ae().field_78731_m, (double)blockPos.func_177952_p() - Minecraft.func_71410_x().func_175598_ae().field_78728_n, (double)(blockPos.func_177958_n() + 1) - Minecraft.func_71410_x().func_175598_ae().field_78730_l, (double)(blockPos.func_177956_o() + 1) - Minecraft.func_71410_x().func_175598_ae().field_78731_m, (double)(blockPos.func_177952_p() + 1) - Minecraft.func_71410_x().func_175598_ae().field_78728_n);
      drawBox(axisAlignedBB, r, g, b, a);
   }
}
