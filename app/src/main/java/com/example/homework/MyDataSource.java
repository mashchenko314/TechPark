package com.example.homework;

import java.util.ArrayList;
import java.util.List;

class MyDataSource {
    static List<MyData> mData;


    private String text;
    private static MyDataSource sInstance;

    private MyDataSource() {
        mData = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            text = Integer.toString(i);
            mData.add(new MyData(text));
        }

    }

    public List<MyData> getData() {
        return mData;
    }

    public synchronized static MyDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MyDataSource();
        }

        return sInstance;

    }

    public static class MyData {
        public String mytext;

        public MyData(String text) {
            mytext = text;
        }
    }
}
