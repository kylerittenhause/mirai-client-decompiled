package com.Mirai.api.Player.Friends;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Friends {
   public static List friends;

   public Friends() {
      friends = new ArrayList();
   }

   public static List getFriends() {
      return friends;
   }

   public static boolean isFriend(String name) {
      boolean b = false;
      Iterator var2 = getFriends().iterator();

      while(var2.hasNext()) {
         Friend f = (Friend)var2.next();
         if (f.getName().equalsIgnoreCase(name)) {
            b = true;
         }
      }

      return b;
   }

   public Friend getFriendByName(String name) {
      Friend fr = null;
      Iterator var3 = getFriends().iterator();

      while(var3.hasNext()) {
         Friend f = (Friend)var3.next();
         if (f.getName().equalsIgnoreCase(name)) {
            fr = f;
         }
      }

      return fr;
   }

   public void addFriend(String name) {
      friends.add(new Friend(name));
   }

   public void delFriend(String name) {
      friends.remove(this.getFriendByName(name));
   }
}
