package com.asyncTask.project;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            new AsyncTaskDemo().execute(1,1,null);
    }

    /**
     * AsynTask的定义
     */
    public class AsyncTaskDemo extends AsyncTask<Integer, Integer, String> {
        //该方法在后台运行-------返回类型为第三个参数  输入类型为第一个参数类型   对应上面参数
        protected String doInBackground(Integer... params) {
            System.out.println("doInBackground"+Thread.currentThread().getId());
            return null;
        }
        //设置用户界面 在主线程中执行
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onPreExecute"+Thread.currentThread().getId());
        }

        //主线程执行 onPreExecute之后执行   输入类型 第三个参数
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("onPostExecute"+Thread.currentThread().getId());
        }

        //参数中 对应为第二个参数
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            System.out.println("onProgressUpdate");
        }
    }

}
