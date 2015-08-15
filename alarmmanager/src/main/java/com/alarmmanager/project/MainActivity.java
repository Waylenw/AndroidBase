package com.alarmmanager.project;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(), MessageActivity.class);
        switch (v.getId()){
            case  R.id.btn_gd://固定时间执行任务
                PendingIntent pa=PendingIntent.getActivity(this, 2, intent, 0);

                AlarmManager alarm=	(AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
                alarm.set(AlarmManager.RTC_WAKEUP, (System.currentTimeMillis() + 2000 * 5), pa);
                Toast.makeText(getApplicationContext(),"消息列表将在5s后打开",Toast.LENGTH_LONG).show();
                break;
            case  R.id.btn_round://固定周期执行任务

                PendingIntent sender=PendingIntent.getActivity(MainActivity.this, 2, intent, 0);


                AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);
                //5秒一个周期，不停的发送广播
                am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP
                        , SystemClock.elapsedRealtime(), 5 * 1000, sender);
                Toast.makeText(getApplicationContext(),"消息列表将在5s后打开",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
