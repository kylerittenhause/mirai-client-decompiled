package com.Mirai.impl.command;

import com.Mirai.MiraiMain;
import com.Mirai.api.command.Command;
import com.Mirai.api.util.LoggerUtil;
import com.Mirai.api.util.font.CustomFontRenderer;
import com.Mirai.api.util.font.FontUtil;

public class Font extends Command {
   public Font(String name, String[] alias, String usage) {
      super(name, alias, usage);
   }

   public void onTrigger(String arguments) {
      if (arguments.equals("")) {
         this.printUsage();
      } else {
         if (FontUtil.validateFont(arguments)) {
            try {
               MiraiMain.customFontRenderer = new CustomFontRenderer(new java.awt.Font(arguments, 0, 19), true, false);
               LoggerUtil.sendMessage("New font set to " + arguments);
            } catch (Exception var3) {
               LoggerUtil.sendMessage("Failed to set font");
            }
         } else {
            LoggerUtil.sendMessage("Invalid font");
         }

      }
   }
}
