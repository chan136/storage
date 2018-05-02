package com.example.storage.Utils;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jerry on 2017/12/12.
 */
public class ShowTime {

    public static void show(final TextView date) {
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                date.setText((String) msg.obj);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM月dd日   HH:mm");
                        String str = sdf.format(new Date());
                        handler.sendMessage(handler.obtainMessage(100, str));
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void staticShow(final TextView date, final SimpleDateFormat sdf) {
        String str = sdf.format(new Date());
        date.setText(str);
    }

    public static void staticShow(final TextView date, final SimpleDateFormat sdf,int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - count); // 设置为上一个月
        date.setText(sdf.format(calendar.getTime()));
    }

}
