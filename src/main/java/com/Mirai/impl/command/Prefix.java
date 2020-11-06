package com.Mirai.impl.command;

import com.Mirai.MiraiMain;
import com.Mirai.api.command.Command;
import com.Mirai.api.util.LoggerUtil;

public class Prefix extends Command {
   public Prefix(String name, String[] alias, String usage) {
      super(name, alias, usage);
   }

   public void onTrigger(String arguments) {
      if (arguments.equals("")) {
         this.printUsage();
      } else {
         MiraiMain.commandManager.setPrefix(arguments);
         LoggerUtil.sendMessage("Prefix set to " + arguments);
      }
   }
}
