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
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(messageBuilder.toString().replace("&", "§")));
   }
}
