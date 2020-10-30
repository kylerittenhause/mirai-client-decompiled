package com.Mirai.api.module;

import com.Mirai.impl.module.combat.AutoLog;
import com.Mirai.impl.module.combat.AutoTrap;
import com.Mirai.impl.module.combat.Criticals;
import com.Mirai.impl.module.combat.CrystalAura;
import com.Mirai.impl.module.combat.Surround;
import com.Mirai.impl.module.exploit.Blink;
import com.Mirai.impl.module.exploit.PacketMine;
import com.Mirai.impl.module.misc.ChatSuffix;
import com.Mirai.impl.module.misc.Timer;
import com.Mirai.impl.module.movement.LongJump;
import com.Mirai.impl.module.movement.Speed;
import com.Mirai.impl.module.movement.Sprint;
import com.Mirai.impl.module.render.ClickGUI;
import com.Mirai.impl.module.render.CustomFont;
import java.util.ArrayList;
import java.util.Iterator;

public class ModuleManager {
   private final ArrayList modules = new ArrayList();

   public ModuleManager() {
      this.modules.add(new AutoLog("AutoLog", "Automatically logs out when your health is low", Category.COMBAT));
      this.modules.add(new AutoTrap("AutoTrap", "Traps players", Category.COMBAT));
      this.modules.add(new Surround("Surround", "Places blocks around you", Category.COMBAT));
      this.modules.add(new Criticals("Criticals", "Deal critical hits without jumping", Category.COMBAT));
      this.modules.add(new CrystalAura("CrystalAura", "Places and brakes crystals", Category.COMBAT));
      this.modules.add(new Blink("Blink", "Fake lag", Category.EXPLOIT));
      this.modules.add(new PacketMine("PacketMine", "Mine blocks with packets", Category.EXPLOIT));
      this.modules.add(new Timer("Timer", "Speeds up your game", Category.MISC));
      this.modules.add(new ChatSuffix("ChatSuffix", "Adds a suffix to your chat messages", Category.MISC));
      this.modules.add(new LongJump("LongJump", "Jumps far", Category.MOVEMENT));
      this.modules.add(new Sprint("Sprint", "Sprints, Obviously", Category.MOVEMENT));
      this.modules.add(new Speed("Strafe", "Allows you to move faster", Category.MOVEMENT));
      this.modules.add(new CustomFont("CustomFont", "Use a custom font render instead of Minecraft's default", Category.RENDER));
      this.modules.add(new ClickGUI("ClickGUI", "Toggle modules by clicking on them", Category.RENDER));
   }

   public ArrayList getModules() {
      return this.modules;
   }

   public Module getModule(String name) {
      Iterator var2 = this.modules.iterator();

      Module module;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         module = (Module)var2.next();
      } while(!module.getName().equalsIgnoreCase(name));

      return module;
   }

   public ArrayList getModules(Category category) {
      ArrayList mods = new ArrayList();
      Iterator var3 = this.modules.iterator();

      while(var3.hasNext()) {
         Module module = (Module)var3.next();
         if (module.getCategory().equals(category)) {
            mods.add(module);
         }
      }

      return mods;
   }
}
