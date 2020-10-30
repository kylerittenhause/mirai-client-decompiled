package com.Mirai.api.gui.clickgui.button.settings;

import com.Mirai.MiraiMain;
import com.Mirai.api.gui.clickgui.button.SettingButton;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.util.font.FontUtil;
import java.awt.Color;

public class BoolButton extends SettingButton {
   private final Setting setting;

   public BoolButton(Module module, Setting setting, int X, int Y, int W, int H) {
      super(module, X, Y, W, H);
      this.setting = setting;
   }

   public void render(int mX, int mY) {
      if (this.setting.getBooleanValue()) {
         this.drawButton(mX, mY);
         FontUtil.drawStringWithShadow(this.setting.getName(), (double)((float)(this.getX() + 6)), (double)((float)(this.getY() + 4)), (new Color(255, 255, 255, 232)).getRGB());
      } else {
         if (this.isHover(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY)) {
            MiraiMain.clickGUI.drawGradient(this.getX(), this.getY(), this.getX() + this.getW(), this.getY() + this.getH(), (new Color(220, 220, 220, 232)).getRGB(), (new Color(218, 218, 218, 232)).getRGB());
         } else {
            MiraiMain.clickGUI.drawGradient(this.getX(), this.getY(), this.getX() + this.getW(), this.getY() + this.getH(), (new Color(240, 240, 240, 232)).getRGB(), (new Color(238, 238, 238, 232)).getRGB());
         }

         FontUtil.drawString(this.setting.getName(), (double)((float)(this.getX() + 6)), (double)((float)(this.getY() + 4)), (new Color(29, 29, 29, 232)).getRGB());
      }

   }

   public void mouseDown(int mX, int mY, int mB) {
      if (this.isHover(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY) && (mB == 0 || mB == 1)) {
         this.setting.setBooleanValue(!this.setting.getBooleanValue());
      }

   }
}
