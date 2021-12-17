package com.example.latest_lottery.Analysis;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.latest_lottery.R;
import com.example.latest_lottery.show_interface.Parent_Interface;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment used to implement analyzer by setting up correct view and data loading
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class Analysis_Fragment extends Parent_Interface implements View.OnClickListener {
    private TextView analysis1;
    private TextView analysis2;
    private TextView analysis3;
    private TextView analysis4;
    @Override
    public View genview() {
        View v=View.inflate(context, R.layout.analysis_page,null);
        analysis1=v.findViewById(R.id.textView1);
        analysis2=v.findViewById(R.id.textView2);
        analysis3=v.findViewById(R.id.textView3);
        analysis4=v.findViewById(R.id.textView4);
        analysis1.setOnClickListener(this);
        analysis2.setOnClickListener(this);
        analysis3.setOnClickListener(this);
        analysis4.setOnClickListener(this);
        return v;
    }

    @Override
    public void loading() {
        super.loading();


    }

    @Override
    public void onClick(View view) {
        if(view==analysis1)
        {
            Intent intent = new Intent(getActivity(),Latest_Analysis_Nav.class);
            startActivity(intent);
        }
    }
}
