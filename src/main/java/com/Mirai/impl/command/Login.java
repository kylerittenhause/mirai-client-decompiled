package com.Mirai.impl.command;

import com.Mirai.api.command.Command;
import com.Mirai.api.util.LoggerUtil;
import com.Mirai.api.util.SessionUtil;

public class Login extends Command {
   public Login(String name, String[] alias, String usage) {
      super(name, alias, usage);
   }

   public void onTrigger(String arguments) {
      String[] split = arguments.split(" ");

      try {
         if (split[0].equals("") || split[1].equals("")) {
            this.printUsage();
            return;
         }
      } catch (Exception var4) {
         this.printUsage();
         return;
      }

      if (SessionUtil.login(split[0], split[1])) {
         LoggerUtil.sendMessage("Logged in to " + SessionUtil.getSession().getUsername());
      } else {
         LoggerUtil.sendMessage("Failed to log in");
      }

   }
}
