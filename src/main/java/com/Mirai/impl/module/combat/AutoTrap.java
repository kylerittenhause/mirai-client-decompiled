package com.Mirai.impl.module.combat;

import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.util.LoggerUtil;
import com.Mirai.api.util.PlayerUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AutoTrap extends Module {
   private final Setting blocksPerTick = new Setting("BPT", this, 1, 1, 10);
   private final Setting disable = new Setting("Disable", this, true);
   private final List positions = new ArrayList(Arrays.asList(new Vec3d(0.0D, -1.0D, -1.0D), new Vec3d(1.0D, -1.0D, 0.0D), new Vec3d(0.0D, -1.0D, 1.0D), new Vec3d(-1.0D, -1.0D, 0.0D), new Vec3d(0.0D, 0.0D, -1.0D), new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(-1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 1.0D, -1.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(-1.0D, 1.0D, 0.0D), new Vec3d(0.0D, 2.0D, -1.0D), new Vec3d(0.0D, 2.0D, 1.0D), new Vec3d(0.0D, 2.0D, 0.0D)));
   private boolean finished;

   public AutoTrap(String name, String description, Category category) {
      super(name, description, category);
      this.addSetting(this.blocksPerTick);
      this.addSetting(this.disable);
   }

   public void onEnable() {
      this.finished = false;
      LoggerUtil.sendMessage("AutoTrap ON");
   }

   @SubscribeEvent
   public void onTick(ClientTickEvent event) {
      if (!this.nullCheck()) {
         if (this.finished && this.disable.getBooleanValue()) {
            this.disable();
         }

         int blocksPlaced = 0;
         Iterator var3 = this.positions.iterator();

         while(var3.hasNext()) {
            Vec3d position = (Vec3d)var3.next();
            EntityPlayer closestPlayer = this.getClosestPlayer();
            if (closestPlayer != null) {
               BlockPos pos = new BlockPos(position.add(this.getClosestPlayer().getPositionVector()));
               if (this.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
                  int oldSlot = this.mc.player.inventory.currentItem;
                  this.mc.player.inventory.currentItem = PlayerUtil.getSlot(Blocks.OBSIDIAN);
                  PlayerUtil.placeBlock(pos);
                  this.mc.player.inventory.currentItem = oldSlot;
                  ++blocksPlaced;
                  if (blocksPlaced == this.blocksPerTick.getIntegerValue()) {
                     return;
                  }
               }
            }
         }

         if (blocksPlaced == 0) {
            this.finished = true;
         }

      }
   }

   private EntityPlayer getClosestPlayer() {
      EntityPlayer closestPlayer = null;
      double range = 1000.0D;
      Iterator var4 = this.mc.world.playerEntities.iterator();

      while(var4.hasNext()) {
         EntityPlayer playerEntity = (EntityPlayer)var4.next();
         if (!playerEntity.equals(this.mc.player)) {
            double distance = (double)this.mc.player.getDistance(playerEntity);
            if (distance < range) {
               closestPlayer = playerEntity;
               range = distance;
            }
         }
      }

      return closestPlayer;
   }

   public void onDisable() {
      LoggerUtil.sendMessage("AutoTrap OFF");
   }
}
