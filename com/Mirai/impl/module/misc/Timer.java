package com.Mirai.impl.module.misc;

import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.util.LoggerUtil;
import com.Mirai.mixin.mixins.accessor.IMinecraft;
import com.Mirai.mixin.mixins.accessor.ITimer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class Timer extends Module {
   private final Setting speed = new Setting("Speed", this, 20, 1, 300);

   public Timer(String name, String description, Category category) {
      super(name, description, category);
      this.addSetting(this.speed);
   }

   @SubscribeEvent
   public void onTick(ClientTickEvent event) {
      ((ITimer)((IMinecraft)this.mc).getTimer()).setTickLength(50.0F / ((float)this.speed.getIntegerValue() / 10.0F));
   }

   public void onEnable() {
      LoggerUtil.sendMessage("Timer ON");
   }

   public void onDisable() {
      ((ITimer)((IMinecraft)this.mc).getTimer()).setTickLength(50.0F);
      LoggerUtil.sendMessage("Timer OFF");
   }
}
