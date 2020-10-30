package com.Mirai.api.gui.clickgui.button.settings;

import com.Mirai.MiraiMain;
import com.Mirai.api.gui.clickgui.button.SettingButton;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.util.font.FontUtil;
import java.awt.Color;
import java.text.DecimalFormat;

public class SliderButton extends SettingButton {
   private final Setting setting;
   protected boolean dragging = false;
   protected int sliderWidth = 0;

   SliderButton(Module module, Setting setting, int X, int Y, int W, int H) {
      super(module, X, Y, W, H);
      this.setting = setting;
   }

   protected void updateSlider(int mouseX) {
   }

   public void render(int mX, int mY) {
      this.updateSlider(mX);
      String var3;
      byte var4;
      if (this.isHover(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY)) {
         MiraiMain.clickGUI.drawGradient(this.getX() + this.sliderWidth + 6, this.getY(), this.getX() + this.getW(), this.getY() + this.getH(), (new Color(220, 220, 220, 232)).getRGB(), (new Color(218, 218, 218, 232)).getRGB());
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
            MiraiMain.clickGUI.drawGradient(this.getX(), this.getY(), this.getX() + this.sliderWidth + 6, this.getY() + this.getH(), (new Color(210, 30, 30, 232)).getRGB(), (new Color(206, 30, 30, 232)).getRGB());
            break;
         case 1:
            MiraiMain.clickGUI.drawGradient(this.getX(), this.getY(), this.getX() + this.sliderWidth + 6, this.getY() + this.getH(), (new Color(20, 210, 20, 232)).getRGB(), (new Color(20, 206, 20, 232)).getRGB());
            break;
         case 2:
            MiraiMain.clickGUI.drawGradient(this.getX(), this.getY(), this.getX() + this.sliderWidth + 6, this.getY() + this.getH(), (new Color(20, 20, 210, 232)).getRGB(), (new Color(20, 20, 206, 232)).getRGB());
         }
      } else {
         MiraiMain.clickGUI.drawGradient(this.getX() + this.sliderWidth + 6, this.getY(), this.getX() + this.getW(), this.getY() + this.getH(), (new Color(240, 240, 240, 232)).getRGB(), (new Color(238, 238, 238, 232)).getRGB());
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
            MiraiMain.clickGUI.drawGradient(this.getX(), this.getY(), this.getX() + this.sliderWidth + 6, this.getY() + this.getH(), (new Color(220, 30, 30, 232)).getRGB(), (new Color(216, 30, 30, 232)).getRGB());
            break;
         case 1:
            MiraiMain.clickGUI.drawGradient(this.getX(), this.getY(), this.getX() + this.sliderWidth + 6, this.getY() + this.getH(), (new Color(30, 220, 30, 232)).getRGB(), (new Color(30, 216, 30, 232)).getRGB());
            break;
         case 2:
            MiraiMain.clickGUI.drawGradient(this.getX(), this.getY(), this.getX() + this.sliderWidth + 6, this.getY() + this.getH(), (new Color(30, 30, 220, 232)).getRGB(), (new Color(30, 30, 216, 232)).getRGB());
         }
      }

      FontUtil.drawStringWithShadow(this.setting.getName(), (double)((float)(this.getX() + 6)), (double)((float)(this.getY() + 4)), (new Color(255, 255, 255, 255)).getRGB());
      FontUtil.drawStringWithShadow(String.valueOf(this.setting.getIntegerValue()), (double)((float)(this.getX() + this.getW() - 6 - FontUtil.getStringWidth(String.valueOf(this.setting.getIntegerValue())))), (double)((float)(this.getY() + 4)), (new Color(255, 255, 255, 255)).getRGB());
   }

   public void mouseDown(int mX, int mY, int mB) {
      if (this.isHover(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY)) {
         this.dragging = true;
      }

   }

   public void mouseUp(int mouseX, int mouseY) {
      this.dragging = false;
   }

   public void close() {
      this.dragging = false;
   }

   public static class IntSlider extends SliderButton {
      private final Setting intSetting;

      public IntSlider(Module module, Setting setting, int X, int Y, int W, int H) {
         super(module, setting, X, Y, W, H);
         this.intSetting = setting;
      }

      protected void updateSlider(int mouseX) {
         double diff = (double)Math.min(this.getW(), Math.max(0, mouseX - this.getX()));
         double min = (double)this.intSetting.getMinIntegerValue();
         double max = (double)this.intSetting.getMaxIntegerValue();
         this.sliderWidth = (int)((double)(this.getW() - 6) * ((double)this.intSetting.getIntegerValue() - min) / (max - min));
         if (this.dragging) {
            if (diff == 0.0D) {
               this.intSetting.setIntegerValue(this.intSetting.getIntegerValue());
            } else {
               DecimalFormat format = new DecimalFormat("##");
               String newValue = format.format(diff / (double)this.getW() * (max - min) + min);
               this.intSetting.setIntegerValue(Integer.parseInt(newValue));
            }
         }

      }
   }
}
