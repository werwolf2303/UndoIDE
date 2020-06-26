package com.ide.window.stringcache;

public class Cache {

    private static int data1;
    private static int data2;
    private static int data3;
    private static int data4;
    private static int data5;
    private static int data6;
    private static int data7;
    private static int data8;
    private static int data9;
    private static String stringdat1;
    private static String stringdat2;
    private static String stringdat3;
    private static String stringdat4;
    private static String stringdat5;
    private static String stringdat6;
    private static String stringdat7;
    private static String stringdat8;
    private static String stringdat9;

    public static void cache1(String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
                stringdat1 = string1;
                stringdat2 = string2;
                stringdat3 = string3;
                stringdat4 = string4;
                stringdat5 = string5;
                stringdat6 = string6;
                stringdat7 = string7;
                stringdat8 = string8;
                stringdat9 = string9;
    }

    public static void cache1(int string1, int string2, int string3, int string4, int string5, int string6, int string7, int string8, int string9) {
        data1 = string1;
        data2 = string2;
        data3 = string3;
        data4 = string4;
        data5 = string5;
        data6 = string6;
        data7 = string7;
        data8 = string8;
        data9 = string9;
    }

    public static class getCache {
        public static String dat1() {
           return stringdat1;
        }
        public static String dat2() {
            return stringdat2;
        }
        public static String dat3() {
            return stringdat3;
        }
        public static String dat4() {
            return stringdat4;
        }
        public static String dat5() {
            return stringdat5;
        }
        public static String dat6() {
            return stringdat6;
        }
        public static String dat7() {
            return stringdat7;
        }
        public static String dat8() {
            return stringdat8;
        }
        public static String dat9() {
            return stringdat9;
        }
    }

    public static void write(String VALUE, Object DATA, long TIME) {
        System.out.print("Wrote to cache");
    }
}
