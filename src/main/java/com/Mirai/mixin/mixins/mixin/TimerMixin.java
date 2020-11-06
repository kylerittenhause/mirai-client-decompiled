package com.Mirai.mixin.mixins.mixin;

import com.Mirai.mixin.mixins.accessor.ITimer;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(
   value = {Timer.class},
   priority = 634756347
)
public class TimerMixin implements ITimer {
   @Shadow
   private float field_194149_e;

   public float getTickLength() {
      return this.field_194149_e;
   }

   public void setTickLength(float tickLength) {
      this.field_194149_e = tickLength;
   }
}
