package com.sunmi.javatest;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.math.BigDecimal;

import sun.rmi.runtime.Log;

public class MyClass {


    private static int index = 100;
    static int i =0;
    public static void main(String[] args) throws Exception {
        final int[] index = {100};
       new Thread(new Runnable() {
           @Override
           public void run() {
               while (index[0] > 0) {
                   index[0]--;
                   i++;
                   System.out.println(i+"==============" + index[0]);
               }
           }
       }).start();
       new Thread(runnable2).start();
       new Thread(runnable3).start();

    }


    public static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (index > 0) {
                index --;
                i++;
                System.out.println(i+"==============" + index);
            }
        }
    };

    public static Runnable runnable2 = new Runnable() {
        @Override
        public void run() {

            while (index > 0) {
                index--;
                i++;
                System.out.println(i+"2==============" + index);
            }
        }
    };

    public static Runnable runnable3 = new Runnable() {
        @Override
        public void run() {

            while (index > 0){
                index --;
                i++;
                System.out.println(i+"3=============="+index);
            }

        }
    };

    private void show_begin() {


    BigDecimal bigDecimal1 = new BigDecimal(1.2445);
        System.out.println(bigDecimal1.setScale(1,BigDecimal.ROUND_HALF_UP));//1.234
    BigDecimal bigDecimal2 = new BigDecimal(1.2346);
        System.out.println(bigDecimal2.setScale(3,BigDecimal.ROUND_HALF_UP));//1.235
    BigDecimal bigDecimal3 = new BigDecimal("1.5345");


        System.out.println(bigDecimal3.setScale(0,BigDecimal.ROUND_HALF_UP));//1.235
    }
}
