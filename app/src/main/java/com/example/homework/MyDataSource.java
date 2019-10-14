package com.example.homework;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MyDataSource {
    public static List<MyData> mData;


    String text;
    private static MyDataSource sInstance;
    public MyDataSource(){
        mData=new ArrayList<>();
        for (Integer i=1;i<101;i++){
            text=i.toString();
            mData.add(new MyData(text));
        }

    }

    public List<MyData> getData(){
        return mData;
    }
    public synchronized static MyDataSource getInstance(){
        if(sInstance==null){
            sInstance=new MyDataSource();
        }

        return sInstance;

    }
    public static class MyData{
        public String mytext;
       public MyData(String text){
            mytext=text;
        }
        public MyData(){}
        public void putdate(Integer integer){
           mytext=integer.toString();
        }
    }
}
