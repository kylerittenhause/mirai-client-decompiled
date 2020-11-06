package com.Mirai.api.gui.clickgui.button;

import com.Mirai.MiraiMain;
import com.Mirai.api.module.Module;
import java.awt.Color;
import net.minecraft.client.Minecraft;

public class SettingButton {
   public final Minecraft mc = Minecraft.getMinecraft();
   private final int H;
   private Module module;
   private int X;
   private int Y;
   private int W;

   public SettingButton(Module module, int x, int y, int w, int h) {
      this.module = module;
      this.X = x;
      this.Y = y;
      this.W = w;
      this.H = h;
   }

   public void render(int mX, int mY) {
   }

   public void mouseDown(int mX, int mY, int mB) {
   }

   public void mouseUp(int mX, int mY) {
   }

   public void keyPress(int key) {
   }

   public void close() {
   }

   public void drawButton(int mX, int mY) {
      String var3;
      byte var4;
      if (this.isHover(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY)) {
         var3 = MiraiMain.settingManager.getSetting("ClickGUI", "Color").getEnumValue();
         var4 = -1;
         switch(var3.hashCode()) {
         case 82033:
            if (var3.equals("Red")) {
               var4 = 0;
            }
            break;
         case 2073722:
            if (var3.equals("Blue")) {
               var4 = 2;
            }
            break;
         case 69066467:
            if (var3.equals("Green")) {
               var4 = 1;
            }
         }

         switch(var4) {
         case 0:
            MiraiMain.clickGUI.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(210, 30, 30, 232)).getRGB(), (new Color(206, 30, 30, 232)).getRGB());
            break;
         case 1:
            MiraiMain.clickGUI.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(20, 210, 20, 232)).getRGB(), (new Color(20, 206, 20, 232)).getRGB());
            break;
         case 2:
            MiraiMain.clickGUI.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(20, 20, 210, 232)).getRGB(), (new Color(20, 20, 206, 232)).getRGB());
         }
      } else {
         var3 = MiraiMain.settingManager.getSetting("ClickGUI", "Color").getEnumValue();
         var4 = -1;
         switch(var3.hashCode()) {
         case 82033:
            if (var3.equals("Red")) {
               var4 = 0;
            }
            break;
         case 2073722:
            if (var3.equals("Blue")) {
               var4 = 2;
            }
            break;
         case 69066467:
            if (var3.equals("Green")) {
               var4 = 1;
            }
         }

         switch(var4) {
         case 0:
            MiraiMain.clickGUI.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(220, 30, 30, 232)).getRGB(), (new Color(216, 30, 30, 232)).getRGB());
            break;
         case 1:
            MiraiMain.clickGUI.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(30, 220, 30, 232)).getRGB(), (new Color(30, 216, 30, 232)).getRGB());
            break;
         case 2:
            MiraiMain.clickGUI.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(30, 30, 220, 232)).getRGB(), (new Color(30, 30, 216, 232)).getRGB());
         }
      }

   }

   public Module getModule() {
      return this.module;
   }

   public void setModule(Module module) {
      this.module = module;
   }

   public int getX() {
      return this.X;
   }

   public void setX(int x) {
      this.X = x;
   }

   public int getY() {
      return this.Y;
   }

   public void setY(int y) {
      this.Y = y;
   }

   public int getW() {
      return this.W;
   }

   public int getH() {
      return this.H;
   }

   public boolean isHover(int X, int Y, int W, int H, int mX, int mY) {
      return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
   }
}
