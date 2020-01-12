package com.zhaohm.messagenotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class MainActivity extends Activity {
    final int NOTIFYID = 0x123;            //通知的ID
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取通知管理器，用于发送通知
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notification = null; // 创建一个Notification对象

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){        //Android 8.0适配
            NotificationChannel channel = new NotificationChannel(String.valueOf(1),
                    "channel_name",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            notification = new NotificationCompat.Builder(this,String.valueOf(1)).setDefaults(Notification.DEFAULT_ALL);
            notification.setPriority(NotificationCompat.PRIORITY_MAX);
        }else{
            notification = new NotificationCompat.Builder(this,"haha").setDefaults(Notification.DEFAULT_ALL);
            notification.setPriority(NotificationCompat.PRIORITY_MAX);
        }
        // 设置打开该通知，该通知自动消失
        notification.setAutoCancel(true);
        // 设置显示在状态栏的通知提示信息
        notification.setTicker("安卓课程第一季上线啦！");
        // 设置通知的小图标
        notification.setSmallIcon(R.mipmap.ic_launcher);
        //设置下拉列表中的大图标
        notification.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        // 设置通知内容的标题
        notification.setContentTitle("Android入门第一季！");
        // 设置通知内容
        notification.setContentText("点击查看详情！");
        //设置发送时间
        notification.setWhen(System.currentTimeMillis());
        // 创建一个启动其他Activity的Intent
        Intent intent = new Intent(MainActivity.this
                , MessageActivity.class);
        PendingIntent pi = PendingIntent.getActivity(
                MainActivity.this, 0, intent, 0);
        //设置通知栏点击跳转
        notification.setContentIntent(pi);
        Notification C =notification.build();
        //发送通知
//        C.defaults = Notification.DEFAULT_SOUND
//                | Notification.DEFAULT_VIBRATE
//                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        notificationManager.notify(NOTIFYID, C);
    }
}
