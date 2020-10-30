package com.Mirai.impl.command;

import com.Mirai.MiraiMain;
import com.Mirai.api.command.Command;
import com.Mirai.api.util.LoggerUtil;
import java.util.Iterator;

public class Help extends Command {
   public Help(String name, String[] alias, String usage) {
      super(name, alias, usage);
   }

   public void onTrigger(String arguments) {
      LoggerUtil.sendMessage("Mirai 0.2");
      Iterator var2 = MiraiMain.commandManager.getCommands().iterator();

      while(var2.hasNext()) {
         Command command = (Command)var2.next();
         LoggerUtil.sendMessage(command.getName() + " - " + command.getUsage());
      }

   }
}
