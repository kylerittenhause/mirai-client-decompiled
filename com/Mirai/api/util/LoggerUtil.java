package com.Mirai.api.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class LoggerUtil {
   public static void sendMessage(String message) {
      sendMessage(message, true);
   }

   public static void sendMessage(String message, boolean waterMark) {
      StringBuilder messageBuilder = new StringBuilder();
      if (waterMark) {
         messageBuilder.append("&7[&9未来&7] ");
      }

      messageBuilder.append("&7").append(message);
      Minecraft.func_71410_x().field_71456_v.func_146158_b().func_146227_a(new TextComponentString(messageBuilder.toString().replace("&", "§")));
   }
}
