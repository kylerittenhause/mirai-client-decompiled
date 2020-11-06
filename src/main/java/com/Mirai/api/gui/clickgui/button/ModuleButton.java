package com.Mirai.api.gui.clickgui.button;

import com.Mirai.MiraiMain;
import com.Mirai.api.gui.clickgui.button.settings.BindButton;
import com.Mirai.api.gui.clickgui.button.settings.BoolButton;
import com.Mirai.api.gui.clickgui.button.settings.EnumButton;
import com.Mirai.api.gui.clickgui.button.settings.SliderButton;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.setting.SettingType;
import com.Mirai.api.util.font.FontUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ModuleButton {
   private final Minecraft mc = Minecraft.getMinecraft();
   private final Module module;
   private final ArrayList buttons = new ArrayList();
   private final int W;
   private final int H;
   private int X;
   private int Y;
   private boolean open;
   private int showingModuleCount;
   private boolean opening;
   private boolean closing;

   public ModuleButton(Module module, int x, int y, int w, int h) {
      this.module = module;
      this.X = x;
      this.Y = y;
      this.W = w;
      this.H = h;
      int n = 0;
      Iterator var7 = MiraiMain.settingManager.getSettings(module).iterator();

      while(var7.hasNext()) {
         Setting setting = (Setting)var7.next();
         SettingButton settingButton = null;
         if (setting.getType().equals(SettingType.BOOLEAN)) {
            settingButton = new BoolButton(module, setting, this.X, this.Y + this.H + n, this.W, this.H);
         } else if (setting.getType().equals(SettingType.INTEGER)) {
            settingButton = new SliderButton.IntSlider(module, setting, this.X, this.Y + this.H + n, this.W, this.H);
         } else if (setting.getType().equals(SettingType.ENUM)) {
            settingButton = new EnumButton(module, setting, this.X, this.Y + this.H + n, this.W, this.H);
         }

         if (settingButton != null) {
            this.buttons.add(settingButton);
            n += this.H;
         }
      }

      this.buttons.add(new BindButton(module, this.X, this.Y + this.H + n, this.W, this.H));
   }

   public void render(int mX, int mY) {
      if (this.module.isEnabled()) {
         String var3;
         byte var4;
         if (this.isHover(this.X, this.Y, this.W, this.H - 1, mX, mY)) {
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
               MiraiMain.clickGUI.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(30, 210, 30, 232)).getRGB(), (new Color(30, 206, 30, 232)).getRGB());
               break;
            case 2:
               MiraiMain.clickGUI.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(30, 30, 210, 232)).getRGB(), (new Color(30, 30, 206, 232)).getRGB());
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

         FontUtil.drawStringWithShadow(this.module.getName(), (double)((float)(this.X + 5)), (double)((float)(this.Y + 4)), (new Color(255, 255, 255, 232)).getRGB());
      } else {
         if (this.isHover(this.X, this.Y, this.W, this.H - 1, mX, mY)) {
            MiraiMain.clickGUI.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(220, 220, 220, 232)).getRGB(), (new Color(218, 218, 218, 232)).getRGB());
         } else {
            MiraiMain.clickGUI.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(240, 240, 240, 232)).getRGB(), (new Color(238, 238, 238, 232)).getRGB());
         }

         FontUtil.drawString(this.module.getName(), (double)((float)(this.X + 5)), (double)((float)(this.Y + 4)), (new Color(29, 29, 29, 232)).getRGB());
      }

      if (this.opening) {
         ++this.showingModuleCount;
         if (this.showingModuleCount == this.buttons.size()) {
            this.opening = false;
            this.open = true;
         }
      }

      if (this.closing) {
         --this.showingModuleCount;
         if (this.showingModuleCount == 0) {
            this.closing = false;
            this.open = false;
         }
      }

      if (this.isHover(this.X, this.Y, this.W, this.H - 1, mX, mY) && this.module.getDescription() != null && !this.module.getDescription().equals("")) {
         FontUtil.drawStringWithShadow(this.module.getDescription(), 2.0D, (double)((new ScaledResolution(this.mc)).getScaledHeight() - FontUtil.getFontHeight() - 2), (new Color(-221985596, true)).getRGB());
      }

   }

   public void mouseDown(int mX, int mY, int mB) {
      if (this.isHover(this.X, this.Y, this.W, this.H - 1, mX, mY)) {
         if (mB == 0) {
            this.module.toggle();
            if (this.module.getName().equals("ClickGUI")) {
               this.mc.displayGuiScreen((GuiScreen)null);
            }
         } else if (mB == 1) {
            this.processRightClick();
         }
      }

      if (this.open) {
         Iterator var4 = this.buttons.iterator();

         while(var4.hasNext()) {
            SettingButton settingButton = (SettingButton)var4.next();
            settingButton.mouseDown(mX, mY, mB);
         }
      }

   }

   public void mouseUp(int mX, int mY) {
      Iterator var3 = this.buttons.iterator();

      while(var3.hasNext()) {
         SettingButton settingButton = (SettingButton)var3.next();
         settingButton.mouseUp(mX, mY);
      }

   }

   public void keyPress(int key) {
      Iterator var2 = this.buttons.iterator();

      while(var2.hasNext()) {
         SettingButton settingButton = (SettingButton)var2.next();
         settingButton.keyPress(key);
      }

   }

   public void close() {
      Iterator var1 = this.buttons.iterator();

      while(var1.hasNext()) {
         SettingButton button = (SettingButton)var1.next();
         button.close();
      }

   }

   private boolean isHover(int X, int Y, int W, int H, int mX, int mY) {
      return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
   }

   public void setX(int x) {
      this.X = x;
   }

   public void setY(int y) {
      this.Y = y;
   }

   public boolean isOpen() {
      return this.open;
   }

   public Module getModule() {
      return this.module;
   }

   public ArrayList getButtons() {
      return this.buttons;
   }

   public int getShowingModuleCount() {
      return this.showingModuleCount;
   }

   public boolean isOpening() {
      return this.opening;
   }

   public boolean isClosing() {
      return this.closing;
   }

   public void processRightClick() {
      if (!this.open) {
         this.showingModuleCount = 0;
         this.opening = true;
      } else {
         this.showingModuleCount = this.buttons.size();
         this.closing = true;
      }

   }
}
