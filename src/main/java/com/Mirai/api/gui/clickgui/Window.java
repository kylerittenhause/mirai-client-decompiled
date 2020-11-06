package com.Mirai.api.gui.clickgui;

import com.Mirai.MiraiMain;
import com.Mirai.api.gui.clickgui.button.ModuleButton;
import com.Mirai.api.gui.clickgui.button.SettingButton;
import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.util.font.FontUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.gui.Gui;

public class Window {
   private final ArrayList buttons = new ArrayList();
   private final Category category;
   private final int W;
   private final int H;
   private final ArrayList buttonsBeforeClosing = new ArrayList();
   private int X;
   private int Y;
   private int dragX;
   private int dragY;
   private boolean open = true;
   private boolean dragging;
   private int showingButtonCount;
   private boolean opening;
   private boolean closing;

   public Window(Category category, int x, int y, int w, int h) {
      this.category = category;
      this.X = x;
      this.Y = y;
      this.W = w;
      this.H = h;
      int yOffset = this.Y + this.H;

      for(Iterator var7 = MiraiMain.moduleManager.getModules(category).iterator(); var7.hasNext(); yOffset += this.H) {
         Module module = (Module)var7.next();
         ModuleButton button = new ModuleButton(module, this.X, yOffset, this.W, this.H);
         this.buttons.add(button);
      }

      this.showingButtonCount = this.buttons.size();
   }

   public void render(int mX, int mY) {
      if (this.dragging) {
         this.X = this.dragX + mX;
         this.Y = this.dragY + mY;
      }

      Gui.drawRect(this.X, this.Y, this.X + this.W, this.Y + this.H, (new Color(203, 203, 203, 255)).getRGB());
      FontUtil.drawString(this.category.getName(), (double)(this.X + 4), (double)(this.Y + 4), (new Color(29, 29, 29, 232)).getRGB());
      if (this.open || this.opening || this.closing) {
         int modY = this.Y + this.H;
         int moduleRenderCount = 0;
         Iterator var5 = this.buttons.iterator();

         label68:
         while(true) {
            ModuleButton moduleButton;
            do {
               do {
                  if (!var5.hasNext()) {
                     break label68;
                  }

                  moduleButton = (ModuleButton)var5.next();
                  ++moduleRenderCount;
               } while(moduleRenderCount >= this.showingButtonCount + 1);

               moduleButton.setX(this.X);
               moduleButton.setY(modY);
               moduleButton.render(mX, mY);
               if (!moduleButton.isOpen() && this.opening && this.buttonsBeforeClosing.contains(moduleButton)) {
                  moduleButton.processRightClick();
               }

               modY += this.H;
            } while(!moduleButton.isOpen() && !moduleButton.isOpening() && !moduleButton.isClosing());

            int settingRenderCount = 0;
            Iterator var8 = moduleButton.getButtons().iterator();

            while(var8.hasNext()) {
               SettingButton settingButton = (SettingButton)var8.next();
               ++settingRenderCount;
               if (settingRenderCount < moduleButton.getShowingModuleCount() + 1) {
                  settingButton.setX(this.X);
                  settingButton.setY(modY);
                  settingButton.render(mX, mY);
                  modY += this.H;
               }
            }
         }
      }

      if (this.opening) {
         ++this.showingButtonCount;
         if (this.showingButtonCount == this.buttons.size()) {
            this.opening = false;
            this.open = true;
            this.buttonsBeforeClosing.clear();
         }
      }

      if (this.closing) {
         --this.showingButtonCount;
         if (this.showingButtonCount == 0 || this.showingButtonCount == 1) {
            this.closing = false;
            this.open = false;
         }
      }

   }

   public void mouseDown(int mX, int mY, int mB) {
      Iterator var4;
      ModuleButton button;
      if (this.isHover(this.X, this.Y, this.W, this.H, mX, mY)) {
         if (mB == 0) {
            this.dragging = true;
            this.dragX = this.X - mX;
            this.dragY = this.Y - mY;
         } else if (mB == 1) {
            if (this.open && !this.opening && !this.closing) {
               this.showingButtonCount = this.buttons.size();
               this.closing = true;
               var4 = this.buttons.iterator();

               while(var4.hasNext()) {
                  button = (ModuleButton)var4.next();
                  if (button.isOpen()) {
                     button.processRightClick();
                     this.buttonsBeforeClosing.add(button);
                  }
               }
            } else if (!this.open && !this.opening && !this.closing) {
               this.showingButtonCount = 1;
               this.opening = true;
            }
         }
      }

      if (this.open) {
         var4 = this.buttons.iterator();

         while(var4.hasNext()) {
            button = (ModuleButton)var4.next();
            button.mouseDown(mX, mY, mB);
         }
      }

   }

   public void mouseUp(int mX, int mY) {
      this.dragging = false;
      if (this.open) {
         Iterator var3 = this.buttons.iterator();

         while(var3.hasNext()) {
            ModuleButton button = (ModuleButton)var3.next();
            button.mouseUp(mX, mY);
         }
      }

   }

   public void keyPress(int key) {
      if (this.open) {
         Iterator var2 = this.buttons.iterator();

         while(var2.hasNext()) {
            ModuleButton button = (ModuleButton)var2.next();
            button.keyPress(key);
         }
      }

   }

   public void close() {
      Iterator var1 = this.buttons.iterator();

      while(var1.hasNext()) {
         ModuleButton button = (ModuleButton)var1.next();
         button.close();
      }

   }

   private boolean isHover(int X, int Y, int W, int H, int mX, int mY) {
      return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
   }

   public int getY() {
      return this.Y;
   }

   public void setY(int y) {
      this.Y = y;
   }
}
