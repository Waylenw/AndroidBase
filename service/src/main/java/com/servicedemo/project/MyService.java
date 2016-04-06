package com.servicedemo.project;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

/**
 * 自定义Service
 *
 *
 * 定义完后需在AndroidManifest.xml配置
 *
 */
public class MyService extends Service {
    MediaPlayer media = null;
    Mybind mybind = new Mybind();

    /**
     * 该BInder用于Activity共享数据
     */
    public class Mybind extends Binder {

        int i = 10;
    }

    public IBinder onBind(Intent arg0) {
        System.out.println("onBind=====");

        return mybind;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        System.out.println("取消绑定");
        super.unbindService(conn);
    }

    @Override
    public void onCreate() {

        System.out.println("onCreate=====");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        System.out.println("onStart=====");
        media = new MediaPlayer();
        // media.create(this,R.raw.test);
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand=====");
        media = new MediaPlayer();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy=====");
        media.stop();
        super.onDestroy();
    }
}