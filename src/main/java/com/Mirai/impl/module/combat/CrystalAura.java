package com.Mirai.impl.module.combat;

import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.util.LoggerUtil;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class CrystalAura extends Module {
   private final Setting attackspeed = new Setting("Attack speed", this, 1, 0, 20);
   private final Setting Break = new Setting("Break range", this, 1, 0, 6);
   private final Setting Place = new Setting("", this, Arrays.asList("True", "False"));
   private final Setting AutoSwitch = new Setting("Switch", this, Arrays.asList("AutoSwitch", "No Switch"));
   private final Setting Attack = new Setting("Attack P", this, Arrays.asList("True", "False"));
   private final Setting Attacks = new Setting("Attack M", this, Arrays.asList("True", "False"));
   private final Setting Attackss = new Setting("Attack A", this, Arrays.asList("True", "False"));

   public CrystalAura(String name, String description, Category category) {
      super(name, description, category);
      this.addSetting(this.Place);
      this.addSetting(this.Break);
      this.addSetting(this.attackspeed);
      this.addSetting(this.Attack);
      this.addSetting(this.Attacks);
      this.addSetting(this.Attackss);
      this.addSetting(this.AutoSwitch);
   }

   @SubscribeEvent
   public void onUpdate(ClientTickEvent event) {
      Iterator var2 = this.mc.world.loadedEntityList.iterator();

      while(var2.hasNext()) {
         Entity entity = (Entity)var2.next();
         if (entity instanceof EntityEnderCrystal && !(entity.getDistance(this.mc.player) > (float)this.Break.getIntegerValue())) {
            this.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.mc.playerController.attackEntity(this.mc.player, entity);
         }
      }

   }

   public void onEnable() {
      LoggerUtil.sendMessage("CA ON");
   }

   public void onDisable() {
      LoggerUtil.sendMessage("CA OFF");
   }
}
