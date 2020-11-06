package com.Mirai.mixin.loader;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

@MCVersion("1.12.2")
public class MixinLoader implements IFMLLoadingPlugin {
   public MixinLoader() {
      MixinBootstrap.init();
      Mixins.addConfiguration("mixins.Mirai.json");
      MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
   }

   public String[] getASMTransformerClass() {
      return new String[0];
   }

   public String getModContainerClass() {
      return null;
   }

   public String getSetupClass() {
      return null;
   }

   public void injectData(Map data) {
   }

   public String getAccessTransformerClass() {
      return null;
   }
}
