package com.Mirai.api.gui.clickgui;

import com.Mirai.MiraiMain;
import com.Mirai.api.module.Category;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

public class ClickGUI extends GuiScreen {
   private final ArrayList windows = new ArrayList();

   public ClickGUI() {
      int xOffset = 3;
      Category[] var2 = Category.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Category category = var2[var4];
         Window window = new Window(category, xOffset, 3, 105, 15);
         this.windows.add(window);
         xOffset += 110;
      }

   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      this.doScroll();
      Iterator var4 = this.windows.iterator();

      while(var4.hasNext()) {
         Window window = (Window)var4.next();
         window.render(mouseX, mouseY);
      }

   }

   protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
      Iterator var4 = this.windows.iterator();

      while(var4.hasNext()) {
         Window window = (Window)var4.next();
         window.mouseDown(mouseX, mouseY, mouseButton);
      }

   }

   protected void func_146286_b(int mouseX, int mouseY, int state) {
      Iterator var4 = this.windows.iterator();

      while(var4.hasNext()) {
         Window window = (Window)var4.next();
         window.mouseUp(mouseX, mouseY);
      }

   }

   protected void func_73869_a(char typedChar, int keyCode) {
      Iterator var3 = this.windows.iterator();

      while(var3.hasNext()) {
         Window window = (Window)var3.next();
         window.keyPress(keyCode);
      }

      if (keyCode == 1) {
         this.mc.displayGuiScreen((GuiScreen)null);
         if (this.mc.currentScreen == null) {
            this.mc.setIngameFocus();
         }
      }

   }

   public boolean func_73868_f() {
      return false;
   }

   public void drawGradient(int left, int top, int right, int bottom, int startColor, int endColor) {
      this.drawGradientRect(left, top, right, bottom, startColor, endColor);
   }

   public void func_146281_b() {
      Iterator var1 = this.windows.iterator();

      while(var1.hasNext()) {
         Window window = (Window)var1.next();
         window.close();
      }

      MiraiMain.moduleManager.getModule("ClickGUI").disable();
   }

   private void doScroll() {
      int w = Mouse.getDWheel();
      Iterator var2;
      Window window;
      if (w < 0) {
         var2 = this.windows.iterator();

         while(var2.hasNext()) {
            window = (Window)var2.next();
            window.setY(window.getY() - 8);
         }
      } else if (w > 0) {
         var2 = this.windows.iterator();

         while(var2.hasNext()) {
            window = (Window)var2.next();
            window.setY(window.getY() + 8);
         }
      }

   }
}
