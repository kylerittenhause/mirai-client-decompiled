package com.Mirai.api.setting;

import com.Mirai.api.module.Module;
import java.util.ArrayList;
import java.util.Iterator;

public class SettingManager {
   private final ArrayList settings = new ArrayList();

   public void addSetting(Setting setting) {
      this.settings.add(setting);
   }

   public ArrayList getSettings(Module module) {
      ArrayList sets = new ArrayList();
      Iterator var3 = this.settings.iterator();

      while(var3.hasNext()) {
         Setting setting = (Setting)var3.next();
         if (setting.getModule().equals(module)) {
            sets.add(setting);
         }
      }

      return sets;
   }

   public Setting getSetting(String moduleName, String name) {
      Iterator var3 = this.settings.iterator();

      Setting setting;
      do {
         if (!var3.hasNext()) {
            return null;
         }

         setting = (Setting)var3.next();
      } while(!setting.getModule().getName().equalsIgnoreCase(moduleName) || !setting.getName().equalsIgnoreCase(name));

      return setting;
   }
}
