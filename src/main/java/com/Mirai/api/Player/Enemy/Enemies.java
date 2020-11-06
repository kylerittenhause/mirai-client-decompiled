package com.Mirai.api.Player.Enemy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Enemies {
   public static List enemies;

   public Enemies() {
      enemies = new ArrayList();
   }

   public static List getEnemies() {
      return enemies;
   }

   public static boolean isEnemy(String name) {
      boolean b = false;
      Iterator var2 = getEnemies().iterator();

      while(var2.hasNext()) {
         Enemy e = (Enemy)var2.next();
         if (e.getName().equalsIgnoreCase(name)) {
            b = true;
         }
      }

      return b;
   }

   public static Enemy getEnemyByName(String name) {
      Enemy en = null;
      Iterator var2 = getEnemies().iterator();

      while(var2.hasNext()) {
         Enemy e = (Enemy)var2.next();
         if (e.getName().equalsIgnoreCase(name)) {
            en = e;
         }
      }

      return en;
   }

   public static void addEnemy(String name) {
      enemies.add(new Enemy(name));
   }

   public static void delEnemy(String name) {
      enemies.remove(getEnemyByName(name));
   }
}
