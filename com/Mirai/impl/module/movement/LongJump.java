package com.Mirai.impl.module.movement;

import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.util.LoggerUtil;
import com.Mirai.impl.event.MoveEvent;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class LongJump extends Module {
   private final Setting speed = new Setting("Speed", this, 30, 1, 100);
   private final Setting packet = new Setting("Packet", this, false);
   private boolean jumped = false;
   private boolean boostable = false;

   public LongJump(String name, String description, Category category) {
      super(name, description, category);
      this.addSetting(this.speed);
      this.addSetting(this.packet);
   }

   @SubscribeEvent
   public void onTick(ClientTickEvent event) {
      if (!this.nullCheck()) {
         if (this.jumped) {
            if (this.mc.field_71439_g.field_70122_E || this.mc.field_71439_g.field_71075_bZ.field_75100_b) {
               this.jumped = false;
               this.mc.field_71439_g.field_70159_w = 0.0D;
               this.mc.field_71439_g.field_70179_y = 0.0D;
               if (this.packet.getBooleanValue()) {
                  this.mc.field_71439_g.field_71174_a.func_147297_a(new Position(this.mc.field_71439_g.field_70165_t, this.mc.field_71439_g.field_70163_u, this.mc.field_71439_g.field_70161_v, this.mc.field_71439_g.field_70122_E));
                  this.mc.field_71439_g.field_71174_a.func_147297_a(new Position(this.mc.field_71439_g.field_70165_t + this.mc.field_71439_g.field_70159_w, 0.0D, this.mc.field_71439_g.field_70161_v + this.mc.field_71439_g.field_70179_y, this.mc.field_71439_g.field_70122_E));
               }

               return;
            }

            if (this.mc.field_71439_g.field_71158_b.field_192832_b == 0.0F && this.mc.field_71439_g.field_71158_b.field_78902_a == 0.0F) {
               return;
            }

            double yaw = this.getDirection();
            this.mc.field_71439_g.field_70159_w = -Math.sin(yaw) * (double)((float)Math.sqrt(this.mc.field_71439_g.field_70159_w * this.mc.field_71439_g.field_70159_w + this.mc.field_71439_g.field_70179_y * this.mc.field_71439_g.field_70179_y) * (this.boostable ? (float)this.speed.getIntegerValue() / 10.0F : 1.0F));
            this.mc.field_71439_g.field_70179_y = Math.cos(yaw) * (double)((float)Math.sqrt(this.mc.field_71439_g.field_70159_w * this.mc.field_71439_g.field_70159_w + this.mc.field_71439_g.field_70179_y * this.mc.field_71439_g.field_70179_y) * (this.boostable ? (float)this.speed.getIntegerValue() / 10.0F : 1.0F));
            this.boostable = false;
         }

      }
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      if (!this.nullCheck()) {
         if (this.mc.field_71439_g.field_71158_b.field_192832_b == 0.0F && this.mc.field_71439_g.field_71158_b.field_78902_a == 0.0F && this.jumped) {
            this.mc.field_71439_g.field_70159_w = 0.0D;
            this.mc.field_71439_g.field_70179_y = 0.0D;
            event.setX(0.0D);
            event.setY(0.0D);
         }

      }
   }

   @SubscribeEvent
   public void onJump(LivingJumpEvent event) {
      if (this.mc.field_71439_g != null && this.mc.field_71441_e != null && event.getEntity() == this.mc.field_71439_g && (this.mc.field_71439_g.field_71158_b.field_192832_b != 0.0F || this.mc.field_71439_g.field_71158_b.field_78902_a != 0.0F)) {
         this.jumped = true;
         this.boostable = true;
      }

   }

   private double getDirection() {
      float rotationYaw = this.mc.field_71439_g.field_70177_z;
      if (this.mc.field_71439_g.field_191988_bg < 0.0F) {
         rotationYaw += 180.0F;
      }

      float forward = 1.0F;
      if (this.mc.field_71439_g.field_191988_bg < 0.0F) {
         forward = -0.5F;
      } else if (this.mc.field_71439_g.field_191988_bg > 0.0F) {
         forward = 0.5F;
      }

      if (this.mc.field_71439_g.field_70702_br > 0.0F) {
         rotationYaw -= 90.0F * forward;
      }

      if (this.mc.field_71439_g.field_70702_br < 0.0F) {
         rotationYaw += 90.0F * forward;
      }

      return Math.toRadians((double)rotationYaw);
   }

   public void onEnable() {
      LoggerUtil.sendMessage("LongJump ON");
   }

   public void onDisable() {
      LoggerUtil.sendMessage("LongJump OFF");
   }
}
