package com.sunmi.javatest;

import java.math.BigDecimal;

public class MyClass {
    public static void main(String[] args) throws Exception {


        BigDecimal bigDecimal1 = new BigDecimal(1.2445);
        System.out.println(bigDecimal1.setScale(1, BigDecimal.ROUND_HALF_UP));//1.234
        BigDecimal bigDecimal2 = new BigDecimal(1.2346);
        System.out.println(bigDecimal2.setScale(3, BigDecimal.ROUND_HALF_UP));//1.235
        BigDecimal bigDecimal3 = new BigDecimal("1.5345");


        System.out.println(bigDecimal3.setScale(0, BigDecimal.ROUND_HALF_UP));//1.235

    }
}
