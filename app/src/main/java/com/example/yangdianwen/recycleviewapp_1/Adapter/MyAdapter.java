package com.example.yangdianwen.recycleviewapp_1.Adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yangdianwen.recycleviewapp_1.Bean.JsonBean;
import com.example.yangdianwen.recycleviewapp_1.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by yangdianwen on 16-6-19.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener
{
    private static final String TAG = "MyAdapter";
    private List<JsonBean.ResultsBean> mData;
    private LayoutInflater mInflater;
    private MyItemClickListener mListener;
  private Context context;
    //空参构造器
    public MyAdapter(){

    }
    //constructor适配器的构造器
    public  MyAdapter(Context context, List<JsonBean.ResultsBean> list){
        this.context=context;
        mInflater=LayoutInflater.from(context);
        this.mData=list;
    };
    //添加数据
    public void addAllData(List<JsonBean.ResultsBean> list){
        mData.addAll(list);
    }
    //设置item点击事件的方法
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mListener = listener;
    }
    //初始化viewholder，初始化item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                mInflater.inflate(R.layout.item_cardview, parent,
                        false),mListener);
        return holder;
    }
    //绑定viewholder时 则去设置Cardview中子控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JsonBean.ResultsBean jsonBean=mData.get(position);
        holder.textView.setText(jsonBean.getWho());
        holder.textView_creatTime.setText("创建时间："+jsonBean.getCreatedAt());
        holder.textView_publishTmie.setText("发布时间："+jsonBean.getPublishedAt());
        String url=jsonBean.getUrl();
        String desc = jsonBean.getDesc();
        String time = jsonBean.getPublishedAt();
        String createdAt = jsonBean.getCreatedAt();
        String type = jsonBean.getType();
        Log.d(TAG, "onBindViewHolder: "+desc+"\n"+time+"\n"+createdAt+"\n"+type);
        Picasso.with(context)
                .load(url)
                .into(holder.imageView);
        ViewCompat.setTransitionName(holder.imageView,url);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 创建一个自己的viewholder类继承自RecyclerView.ViewHolder
     * 初始化Cardview中子控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        TextView textView_publishTmie;
        TextView textView_creatTime;
        ImageView imageView;
        //MyViewHolder的构造器，对cardview中的控件进行初始化，初始化listener
        public MyViewHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.tv);
            textView_creatTime=(TextView)itemView.findViewById(R.id.tv_creatTime);
            textView_publishTmie=(TextView)itemView.findViewById(R.id.tv_publistTime);
            imageView  = (ImageView) itemView.findViewById(R.id.iv);
            mListener=listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getPosition());
            }
        }
    }
    //item的点击事件接口
    public interface MyItemClickListener {
        public void onItemClick(View view,int postion);
    }
}

