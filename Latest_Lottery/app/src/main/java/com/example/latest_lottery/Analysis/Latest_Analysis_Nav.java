package com.example.latest_lottery.Analysis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.latest_lottery.Processor.Latest_Process;
import com.example.latest_lottery.R;
import com.example.latest_lottery.Util.LiveDataBus;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Latest Analyzer to analyze the latest lottery appearance based on frequency
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class Latest_Analysis_Nav extends AppCompatActivity implements View.OnClickListener {
    Button b;
    RadioGroup r1;
    RadioGroup r2;
    RadioGroup r3;
    RadioButton rb;
    TextView t;
    BarChart barchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_analysis_nav);
        b=findViewById(R.id.button);
        r1=findViewById(R.id.rg1);
        r2=findViewById(R.id.rg2);
        r3=findViewById(R.id.rg3);
        t=findViewById(R.id.textView9);
        b.setOnClickListener(this);


    }


    public void get_data(String a,int b)
    {
        LiveDataBus.getInstance().with("signature",String.class).observe(this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                LiveDataBus.getInstance().with("user_info",String.class).observe(Latest_Analysis_Nav.this, new Observer<String>() {

                            @Override
                            public void onChanged(String u) {
                                RequestQueue queue = Volley.newRequestQueue(Latest_Analysis_Nav.this);
                                String url ="https://apimobile1z09p6j.epyin.net/query/search.php?user="+u+"&table="+a+"&choice=0&arr[0]="+b+"&signature="+s;
                                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                            @Override
                                            public void onResponse(JSONArray response) {
                                                try {
                                                    ArrayList<Integer> in = new ArrayList<Integer>();

                                                    JSONObject j;
                                                    if(a.equals("dlt")) {
                                                        for (int i = 0; i < response.length(); i++) {
                                                            j = response.getJSONObject(i);
                                                            in.add(Integer.parseInt(j.getString("red1")));
                                                            in.add(Integer.parseInt(j.getString("red2")));
                                                            in.add(Integer.parseInt(j.getString("red3")));
                                                            in.add(Integer.parseInt(j.getString("red4")));
                                                            in.add(Integer.parseInt(j.getString("red5")));
                                                        }
                                                        int []num=new int[35];
                                                        int sum=0;
                                                        for(int i=0;i<35;i++){
                                                            num[i]=Collections.frequency(in,(i+1));
                                                            sum+=num[i];
                                                        }
                                                        draw(num,a);

                                                        t.setText("value 1 has frequency of "+num[0]+"\nvalue 2 has frequency of "+num[1]+"\nvalue 3 has frequency of "+num[2]+"\nvalue 4 has frequency of "+num[3]+"\nvalue 5 has frequency of "+num[4]+"\nvalue 6 has frequency of "+num[5]+"\nvalue 7 has frequency of "+num[6]+"\nvalue 8 has frequency of "+num[7]+"\nvalue 9 has frequency of "+num[8]+"\nvalue 10 has frequency of "+num[9]+"\nvalue 11 has frequency of "+num[10]+"\nvalue 12 has frequency of "+num[11]+"\nvalue 13 has frequency of "+num[12]+"\nvalue 14 has frequency of "+num[13]+"\nvalue 15 has frequency of "+num[14]+"\nvalue 16 has frequency of "+num[15]+"\nvalue 17 has frequency of "+num[16]+"\nvalue 18 has frequency of "+num[17]+"\nvalue 19 has frequency of "+num[18]+"\nvalue 20 has frequency of "+num[19]+"\nvalue 21 has frequency of "+num[20]+"\nvalue 22 has frequency of "+num[21]+"\nvalue 23 has frequency of "+num[22]+"\nvalue 24 has frequency of "+num[23]+"\nvalue 25 has frequency of "+num[24]+"\nvalue 26 has frequency of "+num[25]+"\nvalue 27 has frequency of "+num[26]+"\nvalue 28 has frequency of "+num[27]+"\nvalue 29 has frequency of "+num[28]+"\nvalue 30 has frequency of "+num[29]+"\nvalue 31 has frequency of "+num[30]+"\nvalue 32 has frequency of "+num[31]+"\nvalue 33 has frequency of "+num[32]+"\nvalue 34 has frequency of "+num[33]+"\nvalue 35 has frequency of "+num[34]);

                                                    }
                                                    else if(a.equals("ssq")) {
                                                        for (int i = 0; i < response.length(); i++) {
                                                            j = response.getJSONObject(i);
                                                            in.add(Integer.parseInt(j.getString("red1")));
                                                            in.add(Integer.parseInt(j.getString("red2")));
                                                            in.add(Integer.parseInt(j.getString("red3")));
                                                            in.add(Integer.parseInt(j.getString("red4")));
                                                            in.add(Integer.parseInt(j.getString("red5")));
                                                            in.add(Integer.parseInt(j.getString("red6")));
                                                        }
                                                        int []num=new int[33];
                                                        int sum=0;
                                                        for(int i=0;i<33;i++){
                                                            num[i]=Collections.frequency(in,(i+1));
                                                            sum+=num[i];
                                                        }
                                                        draw(num,a);
                                                        t.setText("value 1 has frequency of "+num[0]+"\nvalue 2 has frequency of "+num[1]+"\nvalue 3 has frequency of "+num[2]+"\nvalue 4 has frequency of "+num[3]+"\nvalue 5 has frequency of "+num[4]+"\nvalue 6 has frequency of "+num[5]+"\nvalue 7 has frequency of "+num[6]+"\nvalue 8 has frequency of "+num[7]+"\nvalue 9 has frequency of "+num[8]+"\nvalue 10 has frequency of "+num[9]+"\nvalue 11 has frequency of "+num[10]+"\nvalue 12 has frequency of "+num[11]+"\nvalue 13 has frequency of "+num[12]+"\nvalue 14 has frequency of "+num[13]+"\nvalue 15 has frequency of "+num[14]+"\nvalue 16 has frequency of "+num[15]+"\nvalue 17 has frequency of "+num[16]+"\nvalue 18 has frequency of "+num[17]+"\nvalue 19 has frequency of "+num[18]+"\nvalue 20 has frequency of "+num[19]+"\nvalue 21 has frequency of "+num[20]+"\nvalue 22 has frequency of "+num[21]+"\nvalue 23 has frequency of "+num[22]+"\nvalue 24 has frequency of "+num[23]+"\nvalue 25 has frequency of "+num[24]+"\nvalue 26 has frequency of "+num[25]+"\nvalue 27 has frequency of "+num[26]+"\nvalue 28 has frequency of "+num[27]+"\nvalue 29 has frequency of "+num[28]+"\nvalue 30 has frequency of "+num[29]+"\nvalue 31 has frequency of "+num[30]+"\nvalue 32 has frequency of "+num[31]+"\nvalue 33 has frequency of "+num[32]);
                                                    }
                                                    else if(a.equals("qlc")) {
                                                        for (int i = 0; i < response.length(); i++) {
                                                            j = response.getJSONObject(i);
                                                            in.add(Integer.parseInt(j.getString("red1")));
                                                            in.add(Integer.parseInt(j.getString("red2")));
                                                            in.add(Integer.parseInt(j.getString("red3")));
                                                            in.add(Integer.parseInt(j.getString("red4")));
                                                            in.add(Integer.parseInt(j.getString("red5")));
                                                            in.add(Integer.parseInt(j.getString("red6")));
                                                        }
                                                        int []num=new int[30];
                                                        int sum=0;
                                                        for(int i=0;i<30;i++){
                                                            num[i]=Collections.frequency(in,(i+1));
                                                            sum+=num[i];
                                                        }
                                                        draw(num,a);
                                                        t.setText("value 1 has frequency of "+num[0]+"\nvalue 2 has frequency of "+num[1]+"\nvalue 3 has frequency of "+num[2]+"\nvalue 4 has frequency of "+num[3]+"\nvalue 5 has frequency of "+num[4]+"\nvalue 6 has frequency of "+num[5]+"\nvalue 7 has frequency of "+num[6]+"\nvalue 8 has frequency of "+num[7]+"\nvalue 9 has frequency of "+num[8]+"\nvalue 10 has frequency of "+num[9]+"\nvalue 11 has frequency of "+num[10]+"\nvalue 12 has frequency of "+num[11]+"\nvalue 13 has frequency of "+num[12]+"\nvalue 14 has frequency of "+num[13]+"\nvalue 15 has frequency of "+num[14]+"\nvalue 16 has frequency of "+num[15]+"\nvalue 17 has frequency of "+num[16]+"\nvalue 18 has frequency of "+num[17]+"\nvalue 19 has frequency of "+num[18]+"\nvalue 20 has frequency of "+num[19]+"\nvalue 21 has frequency of "+num[20]+"\nvalue 22 has frequency of "+num[21]+"\nvalue 23 has frequency of "+num[22]+"\nvalue 24 has frequency of "+num[23]+"\nvalue 25 has frequency of "+num[24]+"\nvalue 26 has frequency of "+num[25]+"\nvalue 27 has frequency of "+num[26]+"\nvalue 28 has frequency of "+num[27]+"\nvalue 29 has frequency of "+num[28]+"\nvalue 30 has frequency of "+num[29]);
                                                    }
                                                    else if(a.equals("pl3")) {
                                                        for (int i = 0; i < response.length(); i++) {
                                                            j = response.getJSONObject(i);
                                                            in.add(Integer.parseInt(j.getString("red1")));
                                                            in.add(Integer.parseInt(j.getString("red2")));
                                                            in.add(Integer.parseInt(j.getString("red3")));
                                                        }
                                                        int []num=new int[10];
                                                        int sum=0;
                                                        for(int i=0;i<10;i++){
                                                            num[i]=Collections.frequency(in,(i));
                                                            sum+=num[i];
                                                        }
                                                        draw(num,a);
                                                        t.setText("value 1 has frequency of "+num[0]+"\nvalue 2 has frequency of "+num[1]+"\nvalue 3 has frequency of "+num[2]+"\nvalue 4 has frequency of "+num[3]+"\nvalue 5 has frequency of "+num[4]+"\nvalue 6 has frequency of "+num[5]+"\nvalue 7 has frequency of "+num[6]+"\nvalue 8 has frequency of "+num[7]+"\nvalue 9 has frequency of "+num[8]);
                                                    }
                                                    else{
                                                        for (int i = 0; i < response.length(); i++) {
                                                            j = response.getJSONObject(i);
                                                            in.add(Integer.parseInt(j.getString("red1")));
                                                            in.add(Integer.parseInt(j.getString("red2")));
                                                            in.add(Integer.parseInt(j.getString("red3")));
                                                            in.add(Integer.parseInt(j.getString("red4")));
                                                            in.add(Integer.parseInt(j.getString("red5")));
                                                        }
                                                        int []num=new int[10];
                                                        int sum=0;
                                                        for(int i=0;i<10;i++){
                                                            num[i]=Collections.frequency(in,(i));
                                                            sum+=num[i];
                                                        }
                                                        draw(num,a);
                                                        t.setText("value 1 has frequency of "+num[0]+"\nvalue 2 has frequency of "+num[1]+"\nvalue 3 has frequency of "+num[2]+"\nvalue 4 has frequency of "+num[3]+"\nvalue 5 has frequency of "+num[4]+"\nvalue 6 has frequency of "+num[5]+"\nvalue 7 has frequency of "+num[6]+"\nvalue 8 has frequency of "+num[7]+"\nvalue 9 has frequency of "+num[8]);
                                                    }


                                                    Log.e("err", String.valueOf(response.getJSONObject(0)));


                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }


                                            }



                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                //Log.e("err",error+"");
                                            }
                                        });
                                queue.add(jsonObjectRequest);
                            }});}});}

public void draw(int[] a,String name){
    barchart=findViewById(R.id.latest_analysis);
    ArrayList<BarEntry> arrayList=new ArrayList<>();
    arrayList.add(new BarEntry(1,2));

    for(int i=0;i<a.length;i++)
        arrayList.add(new BarEntry(i,a[i]));
    BarDataSet barDataSet=new BarDataSet(arrayList,"frequency");

    BarData b=new BarData(barDataSet);
    barchart.setFitBars(true);
    barchart.setData(b);
    if(name.equals("dlt"))
    barchart.getDescription().setText("Grand Lotto Distribution in" +a.length+" count");
    else if(name.equals("ssq"))
        barchart.getDescription().setText("Chrome Lotto Distribution in"+a.length+" count");
    else if(name.equals("qlc"))
        barchart.getDescription().setText("Chrome Lotto Distribution in"+a.length+" count");
    else if(name.equals("qlc"))
        barchart.getDescription().setText("Seven Lotto Distribution in"+a.length+" count");
    else if(name.equals("pl3"))
        barchart.getDescription().setText("Rank3 Distribution in"+a.length+" count");
    else if(name.equals("pl5"))
        barchart.getDescription().setText("Rank5 Distribution in"+a.length+" count");
    else{}
    barchart.notifyDataSetChanged();

}





    @Override
    public void onClick(View view) {
        if(view==b){
            int a=0;
            String type=null;
            int range=0;
            String graph=null;

            a=r1.getCheckedRadioButtonId();
            rb=findViewById(a);
            type= rb.getText().toString();

            a=r2.getCheckedRadioButtonId();
            rb=findViewById(a);
            range= Integer.parseInt(rb.getText().toString());

            a=r3.getCheckedRadioButtonId();
            rb=findViewById(a);
            graph= rb.getText().toString();


            if(type.equals("Grand"))
                type="dlt";
            else if(type.equals("Chrome"))
                type="ssq";
            else if(type.equals("Seven"))
                type="qlc";
            else if(type.equals("Rank3"))
                type="pl3";
            else
                type="pl5";

            get_data(type,range);


        }
    }
}