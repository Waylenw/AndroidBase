package com.example.waylenw.crashanler;

import android.app.Application;
import android.text.TextUtils;

import java.io.InputStream;



/**
 * App应用管理类
 * <p/>
 * Created by waylenw on 16/6/3.
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
            ApplicationCrashHandler crashHandler = new ApplicationCrashHandler();
            crashHandler.init(getApplicationContext());
    }
}