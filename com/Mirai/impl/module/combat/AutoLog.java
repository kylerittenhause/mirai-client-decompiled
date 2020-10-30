package com.Mirai.impl.module.combat;

import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.util.LoggerUtil;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AutoLog extends Module {
   private final Setting health = new Setting("Health", this, 10, 1, 30);

   public AutoLog(String name, String description, Category category) {
      super(name, description, category);
      this.addSetting(this.health);
   }

   @SubscribeEvent
   public void onTick(ClientTickEvent event) {
      if (!this.nullCheck()) {
         if (this.mc.field_71439_g.func_110143_aJ() <= (float)this.health.getIntegerValue()) {
            this.disable();
            this.mc.field_71441_e.func_72882_A();
            this.mc.func_71403_a((WorldClient)null);
            this.mc.func_147108_a(new GuiMainMenu());
         }

      }
   }

   public void onEnable() {
      LoggerUtil.sendMessage("AutoLog ON");
   }

   public void onDisable() {
      LoggerUtil.sendMessage("AutoLog OFF");
   }
}
