package com.Mirai.impl.module.render;

import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.util.LoggerUtil;

public class CustomFont extends Module {
   public CustomFont(String name, String description, Category category) {
      super(name, description, category);
   }

   public void onEnable() {
      LoggerUtil.sendMessage("Custom Font ON");
   }

   public void onDisable() {
      LoggerUtil.sendMessage("Custom Font OFF");
   }
}
