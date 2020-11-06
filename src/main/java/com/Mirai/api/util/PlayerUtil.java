package com.Mirai.api.util;

import java.util.Iterator;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction.Action;
import net.minecraft.network.play.client.CPacketPlayer.Rotation;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class PlayerUtil {
   private static final Minecraft mc = Minecraft.getMinecraft();

   public static double vanillaSpeed() {
      double baseSpeed = 0.272D;
      if (Minecraft.getMinecraft().player.isPotionActive(MobEffects.SPEED)) {
         int amplifier = ((PotionEffect)Objects.requireNonNull(Minecraft.getMinecraft().player.getActivePotionEffect(MobEffects.SPEED))).getAmplifier();
         baseSpeed *= 1.0D + 0.2D * (double)amplifier;
      }

      return baseSpeed;
   }

   public static boolean isMoving() {
      return (double)Minecraft.getMinecraft().player.moveForward != 0.0D || (double)Minecraft.getMinecraft().player.moveStrafing != 0.0D;
   }

   public static int getSlot(Item item) {
      for(int i = 0; i < 9; ++i) {
         Item item1 = Minecraft.getMinecraft().player.inventory.getStackInSlot(i).getItem();
         if (item.equals(item1)) {
            return i;
         }
      }

      return -1;
   }

   public static int getSlot(Block block) {
      for(int i = 0; i < 9; ++i) {
         Item item = Minecraft.getMinecraft().player.inventory.getStackInSlot(i).getItem();
         if (item instanceof ItemBlock && ((ItemBlock)item).getBlock().equals(block)) {
            return i;
         }
      }

      return -1;
   }

   public static void placeBlock(BlockPos pos) {
      EnumFacing[] var1 = EnumFacing.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         EnumFacing enumFacing = var1[var3];
         if (!mc.world.getBlockState(pos.offset(enumFacing)).getBlock().equals(Blocks.AIR) && !isIntercepted(pos)) {
            Vec3d vec = new Vec3d((double)pos.getX() + 0.5D + (double)enumFacing.getXOffset() * 0.5D, (double)pos.getY() + 0.5D + (double)enumFacing.getYOffset() * 0.5D, (double)pos.getZ() + 0.5D + (double)enumFacing.getZOffset() * 0.5D);
            float[] old = new float[]{mc.player.rotationYaw, mc.player.rotationPitch};
            mc.player.connection.sendPacket(new Rotation((float)Math.toDegrees(Math.atan2(vec.z - mc.player.posZ, vec.x - mc.player.posX)) - 90.0F, (float)(-Math.toDegrees(Math.atan2(vec.y - (mc.player.posY + (double)mc.player.getEyeHeight()), Math.sqrt((vec.x - mc.player.posX) * (vec.x - mc.player.posX) + (vec.z - mc.player.posZ) * (vec.z - mc.player.posZ))))), mc.player.onGround));
            mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, Action.START_SNEAKING));
            mc.playerController.processRightClickBlock(mc.player, mc.world, pos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d(pos), EnumHand.MAIN_HAND);
            mc.player.swingArm(EnumHand.MAIN_HAND);
            mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, Action.STOP_SNEAKING));
            mc.player.connection.sendPacket(new Rotation(old[0], old[1], mc.player.onGround));
            return;
         }
      }

   }

   public static boolean isIntercepted(BlockPos pos) {
      Iterator var1 = mc.world.loadedEntityList.iterator();

      Entity entity;
      do {
         if (!var1.hasNext()) {
            return false;
         }

         entity = (Entity)var1.next();
      } while(!(new AxisAlignedBB(pos)).intersects(entity.getEntityBoundingBox()));

      return true;
   }
}
