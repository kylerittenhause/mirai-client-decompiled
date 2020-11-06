package com.Mirai.api.util.font;

import java.awt.Font;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL11;

public class CustomFontRenderer extends CustomFont {
   protected CustomFont.CharData[] boldChars = new CustomFont.CharData[256];
   protected CustomFont.CharData[] italicChars = new CustomFont.CharData[256];
   protected CustomFont.CharData[] boldItalicChars = new CustomFont.CharData[256];
   private final int[] colorCode = new int[32];
   protected DynamicTexture texBold;
   protected DynamicTexture texItalic;
   protected DynamicTexture texItalicBold;

   public CustomFontRenderer(Font font, boolean antiAlias, boolean fractionalMetrics) {
      super(font, antiAlias, fractionalMetrics);
      this.setupMinecraftColorcodes();
      this.setupBoldItalicIDs();
   }

   public void drawString(String text, float x, float y, int color) {
      this.drawString(text, (double)x, (double)y, color, false);
   }

   public void drawStringWithShadow(String text, double x, double y, int color) {
      this.drawString(text, x + 1.0D, y + 1.0D, color, true);
      this.drawString(text, x, y, color, false);
   }

   public void drawCenteredStringWithShadow(String text, float x, float y, int color) {
      this.drawStringWithShadow(text, (double)(x - (float)this.getStringWidth(text) / 2.0F), (double)y, color);
   }

   public void drawCenteredString(String text, float x, float y, int color) {
      this.drawString(text, x - (float)this.getStringWidth(text) / 2.0F, y, color);
   }

   public void drawString(String text, double x, double y, int c, boolean shadow) {
      int color = c;
      --x;
      y -= 2.0D;
      if (text != null) {
         if (c == 553648127) {
            color = 16777215;
         }

         if ((color & -67108864) == 0) {
            color |= -16777216;
         }

         if (shadow) {
            color = (color & 16579836) >> 2 | color & -16777216;
         }

         CustomFont.CharData[] currentData = this.charData;
         float alpha = (float)(color >> 24 & 255) / 255.0F;
         boolean bold = false;
         boolean italic = false;
         boolean strikethrough = false;
         boolean underline = false;
         x *= 2.0D;
         y *= 2.0D;
         GL11.glPushMatrix();
         GlStateManager.scale(0.5D, 0.5D, 0.5D);
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.color((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, alpha);
         int size = text.length();
         GlStateManager.enableTexture2D();
         GlStateManager.bindTexture(this.tex.getGlTextureId());
         GL11.glBindTexture(3553, this.tex.getGlTextureId());

         for(int i = 0; i < size; ++i) {
            char character = text.charAt(i);
            if (character == 167) {
               int colorIndex = 21;

               try {
                  colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
               } catch (Exception var20) {
               }

               if (colorIndex < 16) {
                  bold = false;
                  italic = false;
                  underline = false;
                  strikethrough = false;
                  GlStateManager.bindTexture(this.tex.getGlTextureId());
                  currentData = this.charData;
                  if (colorIndex < 0) {
                     colorIndex = 15;
                  }

                  if (shadow) {
                     colorIndex += 16;
                  }

                  int cCode = this.colorCode[colorIndex];
                  GlStateManager.color((float)(cCode >> 16 & 255) / 255.0F, (float)(cCode >> 8 & 255) / 255.0F, (float)(cCode & 255) / 255.0F, alpha);
               } else if (colorIndex == 17) {
                  bold = true;
                  if (italic) {
                     GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                     currentData = this.boldItalicChars;
                  } else {
                     GlStateManager.bindTexture(this.texBold.getGlTextureId());
                     currentData = this.boldChars;
                  }
               } else if (colorIndex == 18) {
                  strikethrough = true;
               } else if (colorIndex == 19) {
                  underline = true;
               } else if (colorIndex == 20) {
                  italic = true;
                  if (bold) {
                     GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                     currentData = this.boldItalicChars;
                  } else {
                     GlStateManager.bindTexture(this.texItalic.getGlTextureId());
                     currentData = this.italicChars;
                  }
               } else if (colorIndex == 21) {
                  bold = false;
                  italic = false;
                  underline = false;
                  strikethrough = false;
                  GlStateManager.color((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, alpha);
                  GlStateManager.bindTexture(this.tex.getGlTextureId());
                  currentData = this.charData;
               }

               ++i;
            } else if (character < currentData.length) {
               GL11.glBegin(4);
               this.drawChar(currentData, character, (float)x, (float)y);
               GL11.glEnd();
               if (strikethrough) {
                  this.drawLine(x, y + (double)((float)currentData[character].height / 2.0F), x + (double)currentData[character].width - 8.0D, y + (double)((float)currentData[character].height / 2.0F));
               }

               if (underline) {
                  this.drawLine(x, y + (double)currentData[character].height - 2.0D, x + (double)currentData[character].width - 8.0D, y + (double)currentData[character].height - 2.0D);
               }

               x += (double)(currentData[character].width - 8 + this.charOffset);
            }
         }

         GL11.glHint(3155, 4352);
         GL11.glPopMatrix();
      }
   }

   public int getStringWidth(String text) {
      if (text == null) {
         return 0;
      } else {
         int width = 0;
         CustomFont.CharData[] currentData = this.charData;
         int size = text.length();

         for(int i = 0; i < size; ++i) {
            char character = text.charAt(i);
            if (character == 167) {
               ++i;
            } else if (character < currentData.length) {
               width += currentData[character].width - 8 + this.charOffset;
            }
         }

         return width / 2;
      }
   }

   public void setFont(Font font) {
      super.setFont(font);
      this.setupBoldItalicIDs();
   }

   public void setAntiAlias(boolean antiAlias) {
      super.setAntiAlias(antiAlias);
      this.setupBoldItalicIDs();
   }

   public void setFractionalMetrics(boolean fractionalMetrics) {
      super.setFractionalMetrics(fractionalMetrics);
      this.setupBoldItalicIDs();
   }

   private void setupBoldItalicIDs() {
      this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
      this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
      this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
   }

   private void drawLine(double x, double y, double x1, double y1) {
      GL11.glDisable(3553);
      GL11.glLineWidth(1.0F);
      GL11.glBegin(1);
      GL11.glVertex2d(x, y);
      GL11.glVertex2d(x1, y1);
      GL11.glEnd();
      GL11.glEnable(3553);
   }

   private void setupMinecraftColorcodes() {
      for(int index = 0; index < 32; ++index) {
         int noClue = (index >> 3 & 1) * 85;
         int red = (index >> 2 & 1) * 170 + noClue;
         int green = (index >> 1 & 1) * 170 + noClue;
         int blue = (index & 1) * 170 + noClue;
         if (index == 6) {
            red += 85;
         }

         if (index >= 16) {
            red /= 4;
            green /= 4;
            blue /= 4;
         }

         this.colorCode[index] = (red & 255) << 16 | (green & 255) << 8 | blue & 255;
      }

   }
}
