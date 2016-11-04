package com.example.waylenw.crashanler;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by waylenw on 16/11/2.
 */
public class ApplicationCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "ApplicationCrashHandler";

    private static ApplicationCrashHandler instance; // 单例模式


    private Context context; // 程序Context对象
    private Thread.UncaughtExceptionHandler defalutHandler; // 系统默认的UncaughtException处理类
    private DateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd_HH-mm-ss.SSS", Locale.CHINA);


    /**
     * 异常处理初始化
     *
     * @param context
     */
    public void init(Context context) {
        this.context = context;
        // 获取系统默认的UncaughtException处理器
        defalutHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        // 自定义错误处理
        boolean res = handleException(ex);
        if (!res && defalutHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            defalutHandler.uncaughtException(thread, ex);

        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            ex.printStackTrace();
            Log.e(TAG, "error : " + Log.getStackTraceString(ex));


//            new RuntimeException(ex.getMessage()).printStackTrace();
//            new RuntimeException(ex.getMessage()).printStackTrace();

            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }








    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }

        new Thread() {

            @Override
            public void run() {
                Looper.prepare();

                String err = "[" + ex.getMessage() + "]";
                Toast.makeText(context, "程序出现异常." + err, Toast.LENGTH_LONG)
                        .show();
//                Log.e(TAG, "error : " +err);

                Looper.loop();
            }

        }.start();

//        // 收集设备参数信息 \日志信息
//        String errInfo = collectDeviceInfo(context, ex);
//        // 保存日志文件
//        saveCrashInfo2File(errInfo);
        return true;
    }
}
