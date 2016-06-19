package com.example.yangdianwen.recycleviewapp_1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;

import com.example.yangdianwen.recycleviewapp_1.Adapter.MyAdapter;

import com.example.yangdianwen.recycleviewapp_1.Bean.JsonBean;
import com.example.yangdianwen.recycleviewapp_1.HttpUtis.HttpOnline;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView rc_view;
    private List<JsonBean.ResultsBean> mArrayList;
    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc_view = (RecyclerView) findViewById(R.id.rv);
        //recycle设置布局管理器
        rc_view.setLayoutManager(new GridLayoutManager(this,2));
        //执行异步任务加载数据
        new MyAysncTask().execute();
    }

    //执行异步任务的内部类，继承AsyncTask类
    private class MyAysncTask extends AsyncTask<String, Integer, String> implements MyAdapter.MyItemClickListener {
        //执行获取数据方法
        @Override
        protected String doInBackground(String... params) {
            HttpOnline httpOnline = new HttpOnline();
            String results = httpOnline.getData();
            Log.d(TAG, "doInBackground: " + results);
            return results;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        //解析数据
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Parsegson(json);
            MyAdapter dataAapter=new MyAdapter(MainActivity.this,mArrayList);
            rc_view.setAdapter(dataAapter);
            //recycle中的item的点击事件是在其adapter中绑定的,调用adapter类中的setOnItemClickListener方法
            // 实现OnItemClickListener接口
            dataAapter.setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(View view, int postion) {
//
//            for (int i = 0; i <mArrayList.size() ; i++) {
//                if (postion==i){
////                    url = mArrayList.get(i).getContent();
//                    Intent intent=new Intent(MainActivity.this,Base_WebView.class);
//                    startActivity(intent);
//                }
//            }
//            Log.d(TAG, "onItemClick: 。。当前点击了第 "+postion+"个item");
//
        }
    }

    private List<JsonBean.ResultsBean> Parsegson(String json) {
        mArrayList = new ArrayList<>();
        Gson gson = new Gson();
        //调用fromJson方法解析数据
        JsonBean bean = gson.fromJson(json, JsonBean.class);
        List<JsonBean.ResultsBean> data = bean.getResults();
        // 往数据集合中添加数据
        for (int i = 0; i < data.size(); i++) {
            mArrayList.add(data.get(i));
        }

        return mArrayList;
    }

}