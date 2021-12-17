package com.example.latest_lottery.Processor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.latest_lottery.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Similar as in latest process. We need to bind the itemview to the data also.
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */


public class Item_Process extends BaseAdapter {
    private Context context;
    private JSONArray data;

    public Item_Process(Context context, JSONArray j){
        this.context=context;
        this.data=j;


    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null)
        {
            view=View.inflate(context,R.layout.item_list,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView=view.findViewById(R.id.iv_channel);
            viewHolder.textView=view.findViewById(R.id.tv_channel);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        try {
            JSONObject j=data.getJSONObject(i);
            Glide.with(context).load(j.getString("img_url")).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(viewHolder.imageView);
            viewHolder.textView.setText(j.getString("name"));//Skip the memory and disk cache so that program can fetch the latest data.
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;
    }
    static class ViewHolder{
        public ImageView imageView;
        public TextView textView;
    }
}
