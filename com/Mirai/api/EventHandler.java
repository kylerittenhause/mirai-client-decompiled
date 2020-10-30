package com.Mirai.api;

import com.Mirai.MiraiMain;
import com.Mirai.api.module.Module;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;

public class EventHandler {
   @SubscribeEvent
   public void onKeyInput(KeyInputEvent event) {
      if (Keyboard.getEventKeyState()) {
         Iterator var2 = MiraiMain.moduleManager.getModules().iterator();

         while(var2.hasNext()) {
            Module module = (Module)var2.next();
            if (module.getBind() == Keyboard.getEventKey()) {
               module.toggle();
            }
         }

      }
   }

   @SubscribeEvent
   public void onChatSend(ClientChatEvent event) {
      if (Minecraft.func_71410_x().field_71439_g != null && Minecraft.func_71410_x().field_71441_e != null) {
         if (event.getMessage().startsWith(MiraiMain.commandManager.getPrefix())) {
            event.setCanceled(true);
            Minecraft.func_71410_x().field_71456_v.func_146158_b().func_146239_a(event.getMessage());
            MiraiMain.commandManager.runCommand(event.getMessage());
         }

      }
   }
}
