package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils extends Random {

    public static void main(String[] args) {
//        System.out.println(randomString(50));
//        System.out.println(randomEmail(10));
//        System.out.println(randomNumber(100000L, 999999L));
//        System.out.println(randomPhone("+003", 100000L, 999999L));

        System.out.println(RandomStringUtils.random(20, false, false));
        System.out.println(RandomStringUtils.random(20, true, false));
        System.out.println(RandomStringUtils.random(20, false, true));
        System.out.println(RandomStringUtils.random(20, true, true));

        String [] names = {"a", "b", "c", "d", "e"};
        System.out.println(randomListItem(names));

    }
    public static String randomString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static String randomEmail(int len) {
        return randomString(len).toLowerCase() + "@gmail.com";
    }

    public static String randomListItem(String [] values) {
        int index = randInt(0, values.length);
        return values[index];
    }
    
    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
//
//    static Long randomNumber(Long min, Long max) {
//        return ThreadLocalRandom().current().nextLong(min, max);
//    }
//
//    static String randomPhone(String prefix, Long min, Long max) {
//        return prefix + randomNumber(min, max);
//    }
}
