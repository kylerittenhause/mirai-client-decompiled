package com.Mirai.api.gui.clickgui.button.settings;

import com.Mirai.api.gui.clickgui.button.SettingButton;
import com.Mirai.api.module.Module;
import com.Mirai.api.util.font.FontUtil;
import java.awt.Color;
import org.lwjgl.input.Keyboard;

public class BindButton extends SettingButton {
   private final Module module;
   private boolean binding;

   public BindButton(Module module, int x, int y, int w, int h) {
      super(module, x, y, w, h);
      this.module = module;
   }

   public void render(int mX, int mY) {
      this.drawButton(mX, mY);
      FontUtil.drawStringWithShadow("Bind", (double)((float)(this.getX() + 6)), (double)((float)(this.getY() + 4)), (new Color(255, 255, 255, 255)).getRGB());
      if (this.binding) {
         FontUtil.drawStringWithShadow("...", (double)((float)(this.getX() + this.getW() - 6 - FontUtil.getStringWidth("..."))), (double)((float)(this.getY() + 4)), (new Color(255, 255, 255, 255)).getRGB());
      } else {
         try {
            FontUtil.drawStringWithShadow(Keyboard.getKeyName(this.module.getBind()), (double)((float)(this.getX() + this.getW() - 6 - FontUtil.getStringWidth(Keyboard.getKeyName(this.module.getBind())))), (double)((float)(this.getY() + 4)), (new Color(255, 255, 255, 255)).getRGB());
         } catch (Exception var4) {
            FontUtil.drawStringWithShadow("NONE", (double)((float)(this.getX() + this.getW() - 6 - FontUtil.getStringWidth("NONE"))), (double)((float)(this.getY() + 4)), (new Color(255, 255, 255, 255)).getRGB());
         }
      }

   }

   public void mouseDown(int mX, int mY, int mB) {
      if (this.isHover(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY)) {
         this.binding = !this.binding;
      }

   }

   public void keyPress(int key) {
      if (this.binding) {
         if (key != 211 && key != 14 && key != 0) {
            this.getModule().setBind(key);
         } else {
            this.getModule().setBind(256);
         }

         this.binding = false;
      }

   }
}
