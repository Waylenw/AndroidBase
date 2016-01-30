package com.example.handlerthread;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private HandlerThread handlerThread;
    private ImageView imageView,imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        imageView= (ImageView) findViewById(R.id.imageView);
        imageView1= (ImageView) findViewById(R.id.imageView1);

        handlerThread = new HandlerThread("MainActivity");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper());

        //点击download开始进行下载
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(new MyRunable(1));
                handler.post(new MyRunable(2));
            }
        });
    }


    class MyRunable implements Runnable {
        int pos;

        public MyRunable(int pos) {
            this.pos = pos;
        }


        @Override
        public void run() {
            //模拟耗时
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (pos == 1) {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setBackgroundResource(R.mipmap.ic_launcher);
                    }
                });
            } else {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView1.setBackgroundResource(R.mipmap.ic_launcher);
                    }
                });
            }


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();//停止Looper的循环
    }
}
