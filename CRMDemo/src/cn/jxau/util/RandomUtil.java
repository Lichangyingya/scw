package cn.jxau.util;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class RandomUtil {
   public static int getInt() {
       UUID uuid = UUID.randomUUID();
       String[] uuids = uuid.toString().split("-");
       BigInteger bigInteger = new BigInteger(uuids[1], 16);
       String s = bigInteger.toString();
       if (s.length()<5) {
           s = 0+s;
       }
       int id = Integer.parseInt(2019+s);
       return id;
   }


}
