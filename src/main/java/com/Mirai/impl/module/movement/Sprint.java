package com.Mirai.impl.module.movement;

import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.util.LoggerUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class Sprint extends Module {
   public Sprint(String name, String description, Category category) {
      super(name, description, category);
   }

   @SubscribeEvent
   public void onUpdate(ClientTickEvent event) {
      if (!this.nullCheck()) {
         if (!this.mc.player.isSprinting()) {
            this.mc.player.setSprinting(true);
         }

      }
   }

   public void onEnable() {
      LoggerUtil.sendMessage("Sprint ON");
   }

   public void onDisable() {
      LoggerUtil.sendMessage("Sprint OFF");
   }
}
