package com.example.latest_lottery.Util;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.latest_lottery.Home;
import com.example.latest_lottery.R;
import com.example.latest_lottery.show_interface.Parent_Interface;

/**
 * This page is used to generate the utility fragment and display the navigation url to user for using the tool
 *
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class Util_Fragment extends Parent_Interface implements View.OnClickListener {
    private TextView calculator;
    private TextView history_calendar;
    @Override
    public View genview() {

        View v=View.inflate(context, R.layout.util_page,null);
        calculator=v.findViewById(R.id.calculator);
        calculator.setOnClickListener(this);
        return v;
    }

    @Override
    public void loading() {
        super.loading();
        //No data injection in this page so loading will just simply inherent parent loading method in base fragment



    }

    @Override
    public void onClick(View view) {
        if(view==calculator){
            Intent intent=new Intent(getActivity(),calculator_page.class);
            startActivity(intent);
        }
    }
}
