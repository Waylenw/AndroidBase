package com.servicedemo.project;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Service的定义和使用
 */
public class MainActivity extends Activity {
    Intent intent = null;
    ServiceConnection conn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        intent.setClass(this, MyService.class);
        conn = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName arg0) {
                System.out.println("服务断开");


            }

            @Override
            public void onServiceConnected(ComponentName arg0, IBinder ibinder) {

                System.out.println((MyService.Mybind)ibinder);
                System.out.println("服务连接");

            }
        };
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_0:

                startService(intent);

                break;
            case KeyEvent.KEYCODE_1:

                stopService(intent);

                break;
            case KeyEvent.KEYCODE_2:

                bindService(intent, conn, Service.BIND_AUTO_CREATE);

                break;
            case KeyEvent.KEYCODE_3:

                unbindService(conn);

                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}