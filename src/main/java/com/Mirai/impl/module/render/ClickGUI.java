package com.Mirai.impl.module.render;

import com.Mirai.MiraiMain;
import com.Mirai.api.module.Category;
import com.Mirai.api.module.Module;
import com.Mirai.api.setting.Setting;
import com.Mirai.api.util.LoggerUtil;
import java.util.Arrays;

public class ClickGUI extends Module {
   public ClickGUI(String name, String description, Category category) {
      super(name, description, category);
      this.setBind(54);
      this.addSetting(new Setting("Color", this, Arrays.asList("Red", "Green", "Blue")));
   }

   public void onEnable() {
      this.mc.displayGuiScreen(MiraiMain.clickGUI);
      LoggerUtil.sendMessage("Click Gui ON");
   }

   public void onDisable() {
      LoggerUtil.sendMessage("Click Gui OFF");
   }
}
