package com.example.waylenw.demobackground;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBroadcastReciver();
    }

    public void initBroadcastReciver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(new ScreenBroadcastReceiver(), filter);


    }

    /**
     * screen状态广播接收者
     */
    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        private String action = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) { // 开屏
                Toast.makeText(MainActivity.this, "开屏了", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "开屏了");
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 锁屏
                Toast.makeText(MainActivity.this, "锁屏了", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "锁屏了");
            } else if (Intent.ACTION_USER_PRESENT.equals(action)) { // 解锁
                Toast.makeText(MainActivity.this, "解锁了", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "解锁了");
            }
        }
    }


}
