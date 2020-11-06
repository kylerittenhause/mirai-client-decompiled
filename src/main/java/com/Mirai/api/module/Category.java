package com.Mirai.api.module;

public enum Category {
   COMBAT("Combat"),
   EXPLOIT("Exploit"),
   RENDER("Visuals"),
   MOVEMENT("Movement"),
   MISC("Miscellaneous");

   private String name;

   private Category(String name) {
      this.setName(name);
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
