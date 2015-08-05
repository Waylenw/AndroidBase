package com.fragment.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.fragment.project.activity.TabWithFragmentActivity;


public class MainActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_framentBase:
                startActivity(TabWithFragmentActivity.class);
                break;
            default:
                break;
        }
    }

    public void startActivity(Class className) {
        Intent intent = new Intent(getApplicationContext(), className);
        startActivity(intent);
    }

}
