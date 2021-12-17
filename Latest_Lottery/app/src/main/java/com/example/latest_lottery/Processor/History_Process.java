package com.example.latest_lottery.Processor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.latest_lottery.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;



import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Setup different cases for different types of lottery seperately
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class History_Process extends BaseAdapter {
    private Context context;
    private JSONArray data;
    private String value[]=new String[7];
    private String x[]=new String[7];
    private String y[]=new String[7];



    public History_Process(Context context, JSONArray j) {
        this.context = context;
        this.data = j;

    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return data.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        History_Process.ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.history_data, null);
            holder = new History_Process.ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (History_Process.ViewHolder) view.getTag();
        }

        try {
            JSONObject j=data.getJSONObject(i);

            holder.period.setText(j.getString("period"));
            holder.ball1.setText(j.getString("red1"));
            holder.ball2.setText(j.getString("red2"));
            holder.ball3.setText(j.getString("red3"));
            holder.ball4.setText(j.getString("red4"));
            holder.ball5.setText(j.getString("red5"));
            holder.blue1.setText(j.getString("blue1"));
            holder.blue2.setText(j.getString("blue2"));
            holder.date.setText(j.getString("date"));

            holder.ball1.setTextColor(0xfff00000);
            holder.ball2.setTextColor(0xfff00000);
            holder.ball3.setTextColor(0xfff00000);
            holder.ball4.setTextColor(0xfff00000);
            holder.ball5.setTextColor(0xfff00000);
            holder.blue1.setTextColor(0xff0000ff);
            holder.blue2.setTextColor(0xff0000ff);









        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
    static class ViewHolder {
        @BindView(R.id.period)
        TextView period;
        @BindView(R.id.ball1)
        TextView ball1;
        @BindView(R.id.ball2)
        TextView ball2;
        @BindView(R.id.ball3)
        TextView ball3;
        @BindView(R.id.ball4)
        TextView ball4;
        @BindView(R.id.ball5)
        TextView ball5;
        @BindView(R.id.blue1)
        TextView blue1;
        @BindView(R.id.blue2)
        TextView blue2;
        @BindView(R.id.date)
        TextView date;




        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
