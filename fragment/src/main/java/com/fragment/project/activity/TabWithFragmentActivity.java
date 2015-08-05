package com.fragment.project.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.fragment.project.R;
import com.fragment.project.fragment.OneFragment;

/**
 * tab+Frament的Demo
 */
public class TabWithFragmentActivity extends FragmentActivity {
    private FrameLayout fl_frament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_with_fragment);
        initView();
    }

    private void initView() {
        fl_frament=(FrameLayout)findViewById(R.id.fl_fragment);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_fragment, OneFragment.newInstance());
        fragmentTransaction.commit();

    }

}
