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
   private static final Minecraft mc = Minecraft.func_71410_x();

   public static double vanillaSpeed() {
      double baseSpeed = 0.272D;
      if (Minecraft.func_71410_x().field_71439_g.func_70644_a(MobEffects.field_76424_c)) {
         int amplifier = ((PotionEffect)Objects.requireNonNull(Minecraft.func_71410_x().field_71439_g.func_70660_b(MobEffects.field_76424_c))).func_76458_c();
         baseSpeed *= 1.0D + 0.2D * (double)amplifier;
      }

      return baseSpeed;
   }

   public static boolean isMoving() {
      return (double)Minecraft.func_71410_x().field_71439_g.field_191988_bg != 0.0D || (double)Minecraft.func_71410_x().field_71439_g.field_70702_br != 0.0D;
   }

   public static int getSlot(Item item) {
      for(int i = 0; i < 9; ++i) {
         Item item1 = Minecraft.func_71410_x().field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
         if (item.equals(item1)) {
            return i;
         }
      }

      return -1;
   }

   public static int getSlot(Block block) {
      for(int i = 0; i < 9; ++i) {
         Item item = Minecraft.func_71410_x().field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
         if (item instanceof ItemBlock && ((ItemBlock)item).func_179223_d().equals(block)) {
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
         if (!mc.field_71441_e.func_180495_p(pos.func_177972_a(enumFacing)).func_177230_c().equals(Blocks.field_150350_a) && !isIntercepted(pos)) {
            Vec3d vec = new Vec3d((double)pos.func_177958_n() + 0.5D + (double)enumFacing.func_82601_c() * 0.5D, (double)pos.func_177956_o() + 0.5D + (double)enumFacing.func_96559_d() * 0.5D, (double)pos.func_177952_p() + 0.5D + (double)enumFacing.func_82599_e() * 0.5D);
            float[] old = new float[]{mc.field_71439_g.field_70177_z, mc.field_71439_g.field_70125_A};
            mc.field_71439_g.field_71174_a.func_147297_a(new Rotation((float)Math.toDegrees(Math.atan2(vec.field_72449_c - mc.field_71439_g.field_70161_v, vec.field_72450_a - mc.field_71439_g.field_70165_t)) - 90.0F, (float)(-Math.toDegrees(Math.atan2(vec.field_72448_b - (mc.field_71439_g.field_70163_u + (double)mc.field_71439_g.func_70047_e()), Math.sqrt((vec.field_72450_a - mc.field_71439_g.field_70165_t) * (vec.field_72450_a - mc.field_71439_g.field_70165_t) + (vec.field_72449_c - mc.field_71439_g.field_70161_v) * (vec.field_72449_c - mc.field_71439_g.field_70161_v))))), mc.field_71439_g.field_70122_E));
            mc.field_71439_g.field_71174_a.func_147297_a(new CPacketEntityAction(mc.field_71439_g, Action.START_SNEAKING));
            mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, pos.func_177972_a(enumFacing), enumFacing.func_176734_d(), new Vec3d(pos), EnumHand.MAIN_HAND);
            mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
            mc.field_71439_g.field_71174_a.func_147297_a(new CPacketEntityAction(mc.field_71439_g, Action.STOP_SNEAKING));
            mc.field_71439_g.field_71174_a.func_147297_a(new Rotation(old[0], old[1], mc.field_71439_g.field_70122_E));
            return;
         }
      }

   }

   public static boolean isIntercepted(BlockPos pos) {
      Iterator var1 = mc.field_71441_e.field_72996_f.iterator();

      Entity entity;
      do {
         if (!var1.hasNext()) {
            return false;
         }

         entity = (Entity)var1.next();
      } while(!(new AxisAlignedBB(pos)).func_72326_a(entity.func_174813_aQ()));

      return true;
   }
}
