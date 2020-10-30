package com.Mirai.impl.module.movement;

import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.util.LoggerUtil;
import com.Mirai.api.util.PlayerUtil;
import com.Mirai.impl.event.MoveEvent;
import com.Mirai.impl.event.WalkEvent;
import com.Mirai.mixin.mixins.accessor.IMinecraft;
import com.Mirai.mixin.mixins.accessor.ITimer;
import java.util.Arrays;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class Speed extends Module {
   private final Setting mode = new Setting("Mode", this, Arrays.asList("Strafe", "TP"));
   private final Setting speed = new Setting("Speed", this, 9, 1, 100);
   private final Setting useTimer = new Setting("UseTimer", this, false);
   private final Setting timerSpeed = new Setting("TimerSpeed", this, 7, 1, 20);
   private int currentStage;
   private double currentSpeed;
   private double distance;
   private int cooldown;

   public Speed(String name, String description, Category category) {
      super(name, description, category);
      this.addSetting(this.mode);
      this.addSetting(this.speed);
      this.addSetting(this.useTimer);
      this.addSetting(this.timerSpeed);
   }

   public void onEnable() {
      this.currentSpeed = PlayerUtil.vanillaSpeed();
      if (!this.mc.field_71439_g.field_70122_E) {
         this.currentStage = 3;
      }

      LoggerUtil.sendMessage("Speed ON");
   }

   public void onDisable() {
      this.currentSpeed = 0.0D;
      this.currentStage = 2;
      ((ITimer)((IMinecraft)this.mc).getTimer()).setTickLength(50.0F);
      LoggerUtil.sendMessage("Speed OFF");
   }

   @SubscribeEvent
   public void onTick(ClientTickEvent event) {
      if (!this.nullCheck()) {
         if (this.useTimer.getBooleanValue()) {
            ((ITimer)((IMinecraft)this.mc).getTimer()).setTickLength(50.0F / ((float)(this.timerSpeed.getIntegerValue() + 100) / 100.0F));
         } else {
            ((ITimer)((IMinecraft)this.mc).getTimer()).setTickLength(50.0F);
         }

      }
   }

   @SubscribeEvent
   public void onUpdateWalkingPlayer(WalkEvent event) {
      this.distance = Math.sqrt((this.mc.field_71439_g.field_70165_t - this.mc.field_71439_g.field_70169_q) * (this.mc.field_71439_g.field_70165_t - this.mc.field_71439_g.field_70169_q) + (this.mc.field_71439_g.field_70161_v - this.mc.field_71439_g.field_70166_s) * (this.mc.field_71439_g.field_70161_v - this.mc.field_71439_g.field_70166_s));
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      float yaw;
      double motionX;
      double motionZ;
      if (this.mode.getEnumValue().equalsIgnoreCase("Strafe")) {
         float forward = this.mc.field_71439_g.field_71158_b.field_192832_b;
         float strafe = this.mc.field_71439_g.field_71158_b.field_78902_a;
         yaw = this.mc.field_71439_g.field_70177_z;
         if (this.currentStage == 1 && PlayerUtil.isMoving()) {
            this.currentStage = 2;
            this.currentSpeed = 1.1799999475479126D * PlayerUtil.vanillaSpeed() - 0.01D;
         } else if (this.currentStage == 2) {
            this.currentStage = 3;
            if (PlayerUtil.isMoving()) {
               event.setY(this.mc.field_71439_g.field_70181_x = 0.4D);
               if (this.cooldown > 0) {
                  --this.cooldown;
               }

               this.currentSpeed *= (double)((float)this.speed.getIntegerValue() / 5.0F);
            }
         } else if (this.currentStage == 3) {
            this.currentStage = 4;
            this.currentSpeed = this.distance - 0.66D * (this.distance - PlayerUtil.vanillaSpeed());
         } else {
            if (this.mc.field_71441_e.func_184144_a(this.mc.field_71439_g, this.mc.field_71439_g.func_174813_aQ().func_72317_d(0.0D, this.mc.field_71439_g.field_70181_x, 0.0D)).size() > 0 || this.mc.field_71439_g.field_70124_G) {
               this.currentStage = 1;
            }

            this.currentSpeed = this.distance - this.distance / 159.0D;
         }

         this.currentSpeed = Math.max(this.currentSpeed, PlayerUtil.vanillaSpeed());
         if (forward == 0.0F && strafe == 0.0F) {
            event.setX(0.0D);
            event.setZ(0.0D);
            this.currentSpeed = 0.0D;
         } else if (forward != 0.0F) {
            if (strafe >= 1.0F) {
               yaw += forward > 0.0F ? -45.0F : 45.0F;
               strafe = 0.0F;
            } else if (strafe <= -1.0F) {
               yaw += forward > 0.0F ? 45.0F : -45.0F;
               strafe = 0.0F;
            }

            if (forward > 0.0F) {
               forward = 1.0F;
            } else if (forward < 0.0F) {
               forward = -1.0F;
            }
         }

         motionX = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
         motionZ = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
         if (this.cooldown == 0) {
            event.setX((double)forward * this.currentSpeed * motionX + (double)strafe * this.currentSpeed * motionZ);
            event.setZ((double)forward * this.currentSpeed * motionZ - (double)strafe * this.currentSpeed * motionX);
         }

         if (forward == 0.0F && strafe == 0.0F) {
            event.setX(0.0D);
            event.setZ(0.0D);
         }
      } else if (PlayerUtil.isMoving() && this.mc.field_71439_g.field_70122_E) {
         for(double d = 0.0625D; d < (double)((float)this.speed.getIntegerValue() / 10.0F); d += 0.262D) {
            yaw = this.mc.field_71439_g.field_70126_B + (this.mc.field_71439_g.field_70177_z - this.mc.field_71439_g.field_70126_B) * this.mc.func_184121_ak();
            if (this.mc.field_71439_g.field_71158_b.field_192832_b != 0.0F) {
               if (this.mc.field_71439_g.field_71158_b.field_78902_a > 0.0F) {
                  yaw += (float)(this.mc.field_71439_g.field_71158_b.field_192832_b > 0.0F ? -45 : 45);
               } else if (this.mc.field_71439_g.field_71158_b.field_78902_a < 0.0F) {
                  yaw += (float)(this.mc.field_71439_g.field_71158_b.field_192832_b > 0.0F ? 45 : -45);
               }

               this.mc.field_71439_g.field_71158_b.field_78902_a = 0.0F;
               if (this.mc.field_71439_g.field_71158_b.field_192832_b > 0.0F) {
                  this.mc.field_71439_g.field_71158_b.field_192832_b = 1.0F;
               } else if (this.mc.field_71439_g.field_71158_b.field_192832_b < 0.0F) {
                  this.mc.field_71439_g.field_71158_b.field_192832_b = -1.0F;
               }
            }

            motionX = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
            motionZ = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
            this.mc.field_71439_g.field_71174_a.func_147297_a(new Position(this.mc.field_71439_g.field_70165_t + (double)this.mc.field_71439_g.field_71158_b.field_192832_b * d * motionX + (double)this.mc.field_71439_g.field_71158_b.field_78902_a * d * motionZ, this.mc.field_71439_g.field_70163_u, this.mc.field_71439_g.field_70161_v + ((double)this.mc.field_71439_g.field_71158_b.field_192832_b * d * motionZ - (double)this.mc.field_71439_g.field_71158_b.field_78902_a * d * motionX), this.mc.field_71439_g.field_70122_E));
         }

         this.mc.field_71439_g.field_71174_a.func_147297_a(new Position(this.mc.field_71439_g.field_70165_t + this.mc.field_71439_g.field_70159_w, 0.0D, this.mc.field_71439_g.field_70161_v + this.mc.field_71439_g.field_70179_y, this.mc.field_71439_g.field_70122_E));
      }

   }
}
