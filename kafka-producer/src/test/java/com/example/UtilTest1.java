package com.example;

import org.junit.jupiter.api.Test;

public class UtilTest1 {

    public static String stringToHex(String input) {
        StringBuilder hexString = new StringBuilder();
        for (char character : input.toCharArray()) {
            hexString.append(String.format("%02X", (int) character));
        }
        return hexString.toString();
    }

//    Convert String to Hax
    @Test
    public  void test1() {
        String text = "Virendra Yadav";
        String hexText = stringToHex(text);
        System.out.println("Original text: " + text);
        System.out.println("Hexadecimal representation: " + hexText);
    }

//    Hax to string
    @Test
    public void test2(){

        String str="48656C6C6F2C20576F726C6421";

        String result = new String();
        char[] charArray = str.toCharArray();
        for(int i = 0; i < charArray.length; i=i+2) {
            String st = ""+charArray[i]+""+charArray[i+1];
            char ch = (char)Integer.parseInt(st, 16);
            result = result + ch;
        }
        System.out.println(result);
    }
}
