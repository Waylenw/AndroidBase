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
     * AsynTask�Ķ���
     */
    public class AsyncTaskDemo extends AsyncTask<Integer, Integer, String> {
        //�÷����ں�̨����-------��������Ϊ����������  ��������Ϊ��һ����������   ��Ӧ�������
        protected String doInBackground(Integer... params) {
            System.out.println("doInBackground"+Thread.currentThread().getId());
            return null;
        }
        //�����û����� �����߳���ִ��
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onPreExecute"+Thread.currentThread().getId());
        }

        //���߳�ִ�� onPreExecute֮��ִ��   �������� ����������
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("onPostExecute"+Thread.currentThread().getId());
        }

        //������ ��ӦΪ�ڶ�������
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            System.out.println("onProgressUpdate");
        }
    }

}
