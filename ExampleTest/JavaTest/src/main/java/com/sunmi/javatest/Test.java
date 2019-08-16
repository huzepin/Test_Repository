package com.sunmi.javatest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Test {
    public static void main(String[] args) throws Exception {

        testCollect_swap();

    }

    private static void testCollect_swap(){
        ArrayList<String > data = new ArrayList<>();
        for (int i =0 ; i < 8 ; i ++){
            data.add("数据"+i);
        }

        Collections.swap(data,2,5);

        System.out.println(data.toString());
    }


    private void test1() throws IOException {
        BufferedReader df=new BufferedReader(new InputStreamReader(System.in));

        System.out.println("请输入年份:");

        String year1=df.readLine();

        int year=Integer.parseInt(year1);

        System.out.println("请输入月份:");

        String month1=df.readLine();

        int month=Integer.parseInt(month1);

        if(month<1||month>12){
            System.out.println("error您输入的月份范围有误");
        }
        //  730+ 346 = 1076



        System.out.println("请输入日期:");

        String day1=df.readLine();

        int day=Integer.parseInt(day1);

        int twomonth=28;

        int e=0;

        if((year%100!=0&&year%4==0)||(year%400==0)){

            twomonth=29;

            e=1;}

        if(e==1){

            if(month==2){

                if(day>29||day<0){

                    System.out.println("error您输入的日期范围有误");

                }

            }

        }else {

            if(month==2){

                if(day>28||day<0){

                    System.out.println("error您输入的日期范围有误");

                }

            }

        }



        if((month==1||month==3||month==5||month==7||month==8||month==10||month==12)&&day>31||day<0){

            System.out.println("error您输入的日期范围有误");

        }

        if((month==4||month==6||month==9||month==11)&&day>30||day<0){

            System.out.println("error您输入的日期范围有误");

        }





        int [] months={0,31,twomonth,31,30,31,30,31,31,30,31,30,31};

        int totalday=0;

        for( int i=0;i<month;i++)

        {

            totalday+=months[i];

        }

        totalday+=day;

        System.out.println(totalday);
    }

 }
