package com.example.latest_lottery.Util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.latest_lottery.Analysis.Latest_Analysis_Nav;
import com.example.latest_lottery.Processor.Latest_Process;
import com.example.latest_lottery.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This page is used to deploy the calculator page for user to use and transmit data throughout the server and client
 *
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class calculator_page extends AppCompatActivity {
    RadioGroup r;
    EditText r1;
    EditText r2;
    EditText r3;
    EditText r4;
    EditText r5;
    EditText r6;
    EditText r7;
    EditText b1;
    EditText b2;
    EditText period;
    TextView textView;
    TextView t;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_page);
        textView=findViewById(R.id.Lotto_Type);
        r=findViewById(R.id.rg1);
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        r3=findViewById(R.id.r3);
        r4=findViewById(R.id.r4);
        r5=findViewById(R.id.r5);
        r6=findViewById(R.id.r6);
        r7=findViewById(R.id.r7);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        period=findViewById(R.id.period_calculate);
        t=findViewById(R.id.display);
        b=findViewById(R.id.prize);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type="";

                if(textView.getText().toString().equals("Grand")){
                    type="dlt";
                }
                else if(textView.getText().toString().equals("Chrome")){
                    type="ssq";
                }
                else if(textView.getText().toString().equals("Seven")){
                    type="qlc";
                }
                else if(textView.getText().toString().equals("Rank3")){
                    type="pl3";
                }
                else{
                    type="pl5";
                }
                calculate(type,period.getText().toString());
            }
        });
        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=(RadioButton)findViewById(i);
                String s=radioButton.getText().toString();
                if(s.equals("Grand")){
                    textView.setText("Grand");
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.VISIBLE);
                    r5.setVisibility(View.VISIBLE);
                    r6.setVisibility(View.GONE);
                    r7.setVisibility(View.GONE);
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.VISIBLE);
                }
                else if(s.equals("Chrome")){
                    textView.setText("Chrome");
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.VISIBLE);
                    r5.setVisibility(View.VISIBLE);
                    r6.setVisibility(View.VISIBLE);
                    r7.setVisibility(View.GONE);
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.GONE);
                }
                else if(s.equals("Seven")){
                    textView.setText("Seven");
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.VISIBLE);
                    r5.setVisibility(View.VISIBLE);
                    r6.setVisibility(View.VISIBLE);
                    r7.setVisibility(View.GONE);
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.GONE);
                }
                else if(s.equals("R3")){
                    textView.setText("Rank3");
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.GONE);
                    r5.setVisibility(View.GONE);
                    r6.setVisibility(View.GONE);
                    r7.setVisibility(View.GONE);
                    b1.setVisibility(View.GONE);
                    b2.setVisibility(View.GONE);
                }
                else if(s.equals("R5")){
                    textView.setText("Rank5");
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.VISIBLE);
                    r5.setVisibility(View.VISIBLE);
                    r6.setVisibility(View.GONE);
                    r7.setVisibility(View.GONE);
                    b1.setVisibility(View.GONE);
                    b2.setVisibility(View.GONE);
                }
            }
        });
    }
    public void calculate(String type,String period){
        //Setup different cases for calculating lottery type
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://apimobile1z09p6j.epyin.net/query/prize.php?type="+type+"&period="+period;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(type.equals("dlt")) {
                                String red1 = response.getString("red1");
                                String red2 = response.getString("red2");
                                String red3 = response.getString("red3");
                                String red4 = response.getString("red4");
                                String red5 = response.getString("red5");
                                String blue1 = response.getString("blue1");
                                String blue2 = response.getString("blue2");
                                String date = response.getString("date");
                                int wrong=(red1.equals(r1.getText().toString())?0:1)+(red2.equals(r2.getText().toString())?0:1)+(red3.equals(r3.getText().toString())?0:1)+(red4.equals(r4.getText().toString())?0:1)+(red5.equals(r5.getText().toString())?0:1)+(blue1.equals(b1.getText().toString())?0:1)+(blue2.equals(b2.getText().toString())?0:1);
                                double prize=(wrong==7)?0:1000/Math.pow(2,wrong);
                                t.setText("The correct number for that period\n "+red1+" , "+red2+" , "+red3+" , "+red4+" , "+red5+" , "+blue1+" , "+blue2+"//date "+date+"\nYou got "+wrong+" wrong so the prize is "+prize);

                            }
                            else if(type.equals("qlc")) {
                                String red1 = response.getString("red1");
                                String red2 = response.getString("red2");
                                String red3 = response.getString("red3");
                                String red4 = response.getString("red4");
                                String red5 = response.getString("red5");
                                String red6 = response.getString("red6");
                                String blue1 = response.getString("blue1");
                                String date = response.getString("date");
                                int wrong=(red1.equals(r1.getText().toString())?0:1)+(red2.equals(r2.getText().toString())?0:1)+(red3.equals(r3.getText().toString())?0:1)+(red4.equals(r4.getText().toString())?0:1)+(red5.equals(r5.getText().toString())?0:1)+(red6.equals(r6.getText().toString())?0:1)+(blue1.equals(b1.getText().toString())?0:1);
                                double prize=(wrong==7)?0:1000/Math.pow(2,wrong);
                                t.setText("The correct number for that period\n "+red1+" , "+red2+" , "+red3+" , "+red4+" , "+red5+" , "+red6+" , "+blue1+"//date "+date+"\nYou got "+wrong+" wrong so the prize is "+prize);
                            }
                            else if(type.equals("ssq")) {
                                String red1 = response.getString("red1");
                                String red2 = response.getString("red2");
                                String red3 = response.getString("red3");
                                String red4 = response.getString("red4");
                                String red5 = response.getString("red5");
                                String red6 = response.getString("red6");
                                String blue1 = response.getString("blue1");
                                String date = response.getString("date");
                                int wrong=(red1.equals(r1.getText().toString())?0:1)+(red2.equals(r2.getText().toString())?0:1)+(red3.equals(r3.getText().toString())?0:1)+(red4.equals(r4.getText().toString())?0:1)+(red5.equals(r5.getText().toString())?0:1)+(red6.equals(r6.getText().toString())?0:1)+(blue1.equals(b1.getText().toString())?0:1);
                                double prize=(wrong==7)?0:1000/Math.pow(2,wrong);
                                t.setText("The correct number for that period\n "+red1+" , "+red2+" , "+red3+" , "+red4+" , "+red5+" , "+red6+" , "+blue1+"//date "+date+"\nYou got "+wrong+" wrong so the prize is "+prize);

                            }
                            else if(type.equals("pl3")) {
                                String red1 = response.getString("red1");
                                String red2 = response.getString("red2");
                                String red3 = response.getString("red3");
                                String date = response.getString("date");
                                int wrong=(red1.equals(r1.getText().toString())?0:1)+(red2.equals(r2.getText().toString())?0:1)+(red3.equals(r3.getText().toString())?0:1);
                                double prize=(wrong==3)?0:500/Math.pow(2,wrong);
                                t.setText("The correct number for that period\n "+red1+" , "+red2+" , "+red3+"//date "+date+"\nYou got "+wrong+" wrong so the prize is "+prize);
                            }
                            else if(type.equals("pl5")) {
                                String red1 = response.getString("red1");
                                String red2 = response.getString("red2");
                                String red3 = response.getString("red3");
                                String red4 = response.getString("red3");
                                String red5 = response.getString("red3");
                                String date = response.getString("date");
                                int wrong=(red1.equals(r1.getText().toString())?0:1)+(red2.equals(r2.getText().toString())?0:1)+(red3.equals(r3.getText().toString())?0:1)+(red4.equals(r4.getText().toString())?0:1)+(red5.equals(r5.getText().toString())?0:1);
                                double prize=(wrong==5)?0:1000/Math.pow(2,wrong);
                                t.setText("The correct number for that period\n "+red1+" , "+red2+" , "+red3+" , "+red4+" , "+red5+"//date "+date+"\nYou got "+wrong+" wrong so the prize is "+prize);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("err",error+"");
                    }







});
        queue.add(jsonObjectRequest);
    }}