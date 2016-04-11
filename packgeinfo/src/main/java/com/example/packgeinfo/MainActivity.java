package com.example.packgeinfo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PackageManager pm = this.getPackageManager();
        ArrayList<PackageInfo> listInfo = new ArrayList<PackageInfo>();
        listInfo = (ArrayList<PackageInfo>) pm.getInstalledPackages(PackageManager.GET_ACTIVITIES
                | PackageManager.GET_UNINSTALLED_PACKAGES);
    }
}
