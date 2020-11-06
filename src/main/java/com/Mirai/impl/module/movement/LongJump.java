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
            if (this.mc.player.onGround || this.mc.player.capabilities.isFlying) {
               this.jumped = false;
               this.mc.player.motionX = 0.0D;
               this.mc.player.motionZ = 0.0D;
               if (this.packet.getBooleanValue()) {
                  this.mc.player.connection.sendPacket(new Position(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ, this.mc.player.onGround));
                  this.mc.player.connection.sendPacket(new Position(this.mc.player.posX + this.mc.player.motionX, 0.0D, this.mc.player.posZ + this.mc.player.motionZ, this.mc.player.onGround));
               }

               return;
            }

            if (this.mc.player.movementInput.moveForward == 0.0F && this.mc.player.movementInput.moveStrafe == 0.0F) {
               return;
            }

            double yaw = this.getDirection();
            this.mc.player.motionX = -Math.sin(yaw) * (double)((float)Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ) * (this.boostable ? (float)this.speed.getIntegerValue() / 10.0F : 1.0F));
            this.mc.player.motionZ = Math.cos(yaw) * (double)((float)Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ) * (this.boostable ? (float)this.speed.getIntegerValue() / 10.0F : 1.0F));
            this.boostable = false;
         }

      }
   }

   @SubscribeEvent
   public void onMove(MoveEvent event) {
      if (!this.nullCheck()) {
         if (this.mc.player.movementInput.moveForward == 0.0F && this.mc.player.movementInput.moveStrafe == 0.0F && this.jumped) {
            this.mc.player.motionX = 0.0D;
            this.mc.player.motionZ = 0.0D;
            event.setX(0.0D);
            event.setY(0.0D);
         }

      }
   }

   @SubscribeEvent
   public void onJump(LivingJumpEvent event) {
      if (this.mc.player != null && this.mc.world != null && event.getEntity() == this.mc.player && (this.mc.player.movementInput.moveForward != 0.0F || this.mc.player.movementInput.moveStrafe != 0.0F)) {
         this.jumped = true;
         this.boostable = true;
      }

   }

   private double getDirection() {
      float rotationYaw = this.mc.player.rotationYaw;
      if (this.mc.player.moveForward < 0.0F) {
         rotationYaw += 180.0F;
      }

      float forward = 1.0F;
      if (this.mc.player.moveForward < 0.0F) {
         forward = -0.5F;
      } else if (this.mc.player.moveForward > 0.0F) {
         forward = 0.5F;
      }

      if (this.mc.player.moveStrafing > 0.0F) {
         rotationYaw -= 90.0F * forward;
      }

      if (this.mc.player.moveStrafing < 0.0F) {
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
