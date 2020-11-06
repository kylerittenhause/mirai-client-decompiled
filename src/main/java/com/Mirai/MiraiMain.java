package com.Mirai;

import com.Mirai.api.command.CommandManager;
import com.Mirai.api.gui.clickgui.ClickGUI;
import com.Mirai.api.module.ModuleManager;
import com.Mirai.api.setting.SettingManager;
import com.Mirai.api.util.font.CustomFontRenderer;
import java.awt.Font;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.lwjgl.opengl.Display;

@Mod(
   modid = "mirai",
   name = "Mirai",
   version = "0.2 TB-0003"
)
public class MiraiMain {
   public static final String MOD_ID = "mirai";
   public static final String NAME = "Mirai";
   public static final String VERSION = "0.2 TB-0003";
   public static final String MIRAI_JAP = "未来";
   public static ModuleManager moduleManager;
   public static SettingManager settingManager;
   public static CustomFontRenderer customFontRenderer;
   public static ClickGUI clickGUI;
   public static CommandManager commandManager;

   @EventHandler
   public void initialize(FMLInitializationEvent event) {
      commandManager = new CommandManager();
      settingManager = new SettingManager();
      moduleManager = new ModuleManager();
      customFontRenderer = new CustomFontRenderer(new Font("Verdana", 0, 19), true, false);
      clickGUI = new ClickGUI();
      MinecraftForge.EVENT_BUS.register(new com.Mirai.api.EventHandler());
   }

   @EventHandler
   public void initialize(FMLPostInitializationEvent event) {
      Display.setTitle("未来Mirai 0.2 TB-0003");
   }
}
