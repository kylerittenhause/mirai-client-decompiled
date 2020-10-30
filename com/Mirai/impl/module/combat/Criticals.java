package com.Mirai.impl.module.combat;

import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.util.LoggerUtil;
import com.Mirai.impl.event.PacketSendEvent;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.minecraft.network.play.client.CPacketUseEntity.Action;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Criticals extends Module {
   public Criticals(String name, String description, Category category) {
      super(name, description, category);
   }

   @SubscribeEvent
   public void onPacketSend(PacketSendEvent event) {
      if (!this.nullCheck()) {
         if (event.getPacket() instanceof CPacketUseEntity && ((CPacketUseEntity)event.getPacket()).func_149565_c() == Action.ATTACK && this.mc.field_71439_g.field_70122_E) {
            this.mc.field_71439_g.field_71174_a.func_147297_a(new Position(this.mc.field_71439_g.field_70165_t, this.mc.field_71439_g.field_70163_u + 0.1D, this.mc.field_71439_g.field_70161_v, false));
            this.mc.field_71439_g.field_71174_a.func_147297_a(new Position(this.mc.field_71439_g.field_70165_t, this.mc.field_71439_g.field_70163_u, this.mc.field_71439_g.field_70161_v, false));
         }

      }
   }

   public void onEnable() {
      LoggerUtil.sendMessage("Criticals ON");
   }

   public void onDisable() {
      LoggerUtil.sendMessage("Criticals OFF");
   }
}
