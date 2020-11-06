package com.Mirai.api.gui.clickgui.button.settings;

import com.Mirai.api.gui.clickgui.button.SettingButton;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.util.font.FontUtil;
import java.awt.Color;
import java.util.Iterator;

public class EnumButton extends SettingButton {
   private final Setting setting;

   public EnumButton(Module module, Setting setting, int X, int Y, int W, int H) {
      super(module, X, Y, W, H);
      this.setting = setting;
   }

   public void render(int mX, int mY) {
      this.drawButton(mX, mY);
      FontUtil.drawStringWithShadow(this.setting.getName(), (double)((float)(this.getX() + 6)), (double)((float)(this.getY() + 4)), (new Color(255, 255, 255, 255)).getRGB());
      FontUtil.drawStringWithShadow(this.setting.getEnumValue(), (double)((float)(this.getX() + this.getW() - 6 - FontUtil.getStringWidth(this.setting.getEnumValue()))), (double)((float)(this.getY() + 4)), (new Color(255, 255, 255, 255)).getRGB());
   }

   public void mouseDown(int mX, int mY, int mB) {
      if (this.isHover(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY)) {
         int i;
         int enumIndex;
         Iterator var6;
         String enumName;
         if (mB == 0) {
            i = 0;
            enumIndex = 0;

            for(var6 = this.setting.getEnumValues().iterator(); var6.hasNext(); ++i) {
               enumName = (String)var6.next();
               if (enumName.equals(this.setting.getEnumValue())) {
                  enumIndex = i;
               }
            }

            if (enumIndex == this.setting.getEnumValues().size() - 1) {
               this.setting.setEnumValue((String)this.setting.getEnumValues().get(0));
            } else {
               ++enumIndex;
               i = 0;

               for(var6 = this.setting.getEnumValues().iterator(); var6.hasNext(); ++i) {
                  enumName = (String)var6.next();
                  if (i == enumIndex) {
                     this.setting.setEnumValue(enumName);
                  }
               }
            }
         } else if (mB == 1) {
            i = 0;
            enumIndex = 0;

            for(var6 = this.setting.getEnumValues().iterator(); var6.hasNext(); ++i) {
               enumName = (String)var6.next();
               if (enumName.equals(this.setting.getEnumValue())) {
                  enumIndex = i;
               }
            }

            if (enumIndex == 0) {
               this.setting.setEnumValue((String)this.setting.getEnumValues().get(this.setting.getEnumValues().size() - 1));
            } else {
               --enumIndex;
               i = 0;

               for(var6 = this.setting.getEnumValues().iterator(); var6.hasNext(); ++i) {
                  enumName = (String)var6.next();
                  if (i == enumIndex) {
                     this.setting.setEnumValue(enumName);
                  }
               }
            }
         }
      }

   }
}
