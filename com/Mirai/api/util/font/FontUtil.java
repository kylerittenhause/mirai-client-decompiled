package com.Mirai.api.util.font;

import com.Mirai.MiraiMain;
import java.awt.GraphicsEnvironment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class FontUtil {
   private static final FontRenderer fontRenderer;

   public static int getStringWidth(String text) {
      return customFont() ? MiraiMain.customFontRenderer.getStringWidth(text) + 3 : fontRenderer.func_78256_a(text);
   }

   public static void drawString(String text, double x, double y, int color) {
      if (customFont()) {
         MiraiMain.customFontRenderer.drawString(text, x, y - 1.0D, color, false);
      } else {
         fontRenderer.func_78276_b(text, (int)x, (int)y, color);
      }

   }

   public static void drawStringWithShadow(String text, double x, double y, int color) {
      if (customFont()) {
         MiraiMain.customFontRenderer.drawStringWithShadow(text, x, y - 1.0D, color);
      } else {
         fontRenderer.func_175063_a(text, (float)x, (float)y, color);
      }

   }

   public static void drawCenteredStringWithShadow(String text, float x, float y, int color) {
      if (customFont()) {
         MiraiMain.customFontRenderer.drawCenteredStringWithShadow(text, x, y - 1.0F, color);
      } else {
         fontRenderer.func_175063_a(text, x - (float)fontRenderer.func_78256_a(text) / 2.0F, y, color);
      }

   }

   public static void drawCenteredString(String text, float x, float y, int color) {
      if (customFont()) {
         MiraiMain.customFontRenderer.drawCenteredString(text, x, y - 1.0F, color);
      } else {
         fontRenderer.func_78276_b(text, (int)(x - (float)(getStringWidth(text) / 2)), (int)y, color);
      }

   }

   public static int getFontHeight() {
      return customFont() ? MiraiMain.customFontRenderer.fontHeight / 2 - 1 : fontRenderer.field_78288_b;
   }

   public static boolean validateFont(String font) {
      String[] var1 = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         String s = var1[var3];
         if (s.equals(font)) {
            return true;
         }
      }

      return false;
   }

   private static boolean customFont() {
      return MiraiMain.moduleManager.getModule("CustomFont").isEnabled();
   }

   static {
      fontRenderer = Minecraft.func_71410_x().field_71466_p;
   }
}
