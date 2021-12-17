package com.example.latest_lottery.History;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.latest_lottery.Processor.History_Process;
import com.example.latest_lottery.Processor.History_Process_Pl3;
import com.example.latest_lottery.Processor.History_Process_Pl5;
import com.example.latest_lottery.Processor.History_Process_Qlc;
import com.example.latest_lottery.Processor.History_Process_Ssq;
import com.example.latest_lottery.Processor.Latest_Process;
import com.example.latest_lottery.R;
import com.example.latest_lottery.Util.LiveDataBus;
import com.example.latest_lottery.show_interface.Parent_Interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import butterknife.BindView;

/**
 * The Intro pages will display 3 slides of welcome pages for the new user
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class History_Fragment extends Parent_Interface implements View.OnClickListener {
    @ViewInject(R.id.grid_view)
    GridView gridView;
    JSONArray data;
    private History_Process history_process;
    private History_Process_Ssq history_process_ssq;
    private History_Process_Qlc history_process_qlc;
    private History_Process_Pl3 history_process_pl3;
    private History_Process_Pl5 history_process_pl5;
    Button b;
    Button c;
    TextView d;



    @Override
    public View genview() {
        View v=View.inflate(context, R.layout.history_page,null);
        x.view().inject(this,v);
    b=v.findViewById(R.id.button1);
        c=v.findViewById(R.id.button2);
    d=v.findViewById(R.id.title);
    b.setOnClickListener(this);
    c.setOnClickListener(this);
        return v;
    }


    @Override
    public void loading() {
        super.loading();

        data_filling();

    }




    private void data_filling() {
        LiveDataBus.getInstance().with("signature",String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String sig) {
                LiveDataBus.getInstance().with("user_info",String.class).observe(getActivity(), new Observer<String>() {
                            @Override
                            public void onChanged(String user) {
                                RequestQueue queue = Volley.newRequestQueue(getActivity());

                                String url ="https://apimobile1z09p6j.epyin.net/query/search.php?user="+user+"&table=dlt&choice=0&arr[0]=100&signature="+sig;

                                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                            @Override
                                            public void onResponse(JSONArray response) {
                                                data=response;
                                                history_process=new History_Process(context,data);
                                                gridView.setAdapter(history_process);
                                                Log.e("err", String.valueOf(response));

                                            }

                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Log.e("err",error+"");

                                            }
                                        });
                                queue.add(jsonObjectRequest);
                            }

                        });



    }



});}

public void submit(String type,int latest){







    LiveDataBus.getInstance().with("signature",String.class).observe(this, new Observer<String>() {

        @Override
        public void onChanged(String sig) {
            LiveDataBus.getInstance().with("user_info",String.class).observe(getActivity(), new Observer<String>() {
                @Override
                public void onChanged(String user) {
                    int tmp=0;
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    if(latest>100&&user.equals("guest")) {
                        tmp = 100;

                    }
                    else
                        tmp=latest;
                    String url ="https://apimobile1z09p6j.epyin.net/query/search.php?user="+user+"&table="+type+"&choice=0&arr[0]="+tmp+"&signature="+sig;

                    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    d.setTextColor(Color.GREEN);
                                    data=response;
                                    if(type.equals("dlt")){
                                    history_process=new History_Process(context,data);
                                    gridView.setAdapter(history_process);}
                                    else if(type.equals("ssq")){
                                        history_process_ssq=new History_Process_Ssq(context,data);
                                        gridView.setAdapter(history_process_ssq);
                                    }
                                    else if(type.equals("qlc")){
                                        history_process_qlc=new History_Process_Qlc(context,data);
                                        gridView.setAdapter(history_process_qlc);
                                    }
                                    else if(type.equals("pl3")){
                                        history_process_pl3=new History_Process_Pl3(context,data);
                                        gridView.setAdapter(history_process_pl3);
                                    }
                                    else if(type.equals("pl5")){
                                        history_process_pl5=new History_Process_Pl5(context,data);
                                        gridView.setAdapter(history_process_pl5);
                                    }


                                }

                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    Log.e("err",error+"");

                                }
                            });
                    queue.add(jsonObjectRequest);
                }

            });



        }



    });
}

    @Override
    public void onClick(View view) {
        /*if(view==b){
            LiveDataBus.getInstance().with("signature",String.class).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String sig) {
                    LiveDataBus.getInstance().with("user_info",String.class).observe(getActivity(), new Observer<String>() {
                        @Override
                        public void onChanged(String user) {
                            RequestQueue queue = Volley.newRequestQueue(getActivity());

                            String url ="https://apimobile1z09p6j.epyin.net/query/search.php?user="+user+"&table=ssq&choice=0&arr[0]=100&signature="+sig;

                            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                                    (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                        @Override
                                        public void onResponse(JSONArray response) {
                                            data=response;
                                            history_process=new History_Process(context,data);
                                            gridView.setAdapter(history_process);
                                            Log.e("err", String.valueOf(response));

                                        }

                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("err",error+"");

                                        }
                                    });
                            queue.add(jsonObjectRequest);
                        }

                    });



                }



            });}*/
        if(view==b){
            final String []items={"Grand Lotto","Chrome Lotto","Seven Lotto","Rank 3","Rank 5"};
            d.setTextColor(Color.RED);
            new AlertDialog.Builder(getActivity()).
                    setTitle("Choose Type").
                    setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            b.setText(items[i]);

                            dialogInterface.dismiss();
                        }
                    }).show();




        }
        if(view==c){
            View v=View.inflate(getActivity(),R.layout.alert_dialog,null);
            final EditText latest=v.findViewById(R.id.period);
            d.setTextColor(Color.RED);
            new AlertDialog.Builder(getActivity()).
                    setView(v).setNegativeButton("cancel",null).
                    setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String l=latest.getText().toString();

                                c.setText(l);
                                String choice=null;
                                switch(b.getText().toString()){
                                    case "Grand Lotto":
                                        choice="dlt";
                                        break;
                                    case "Chrome Lotto":
                                        choice="ssq";
                                        break;
                                    case "Seven Lotto":
                                        choice="qlc";
                                        break;
                                    case "Rank 3":
                                        choice="pl3";
                                        break;
                                    case "Rank 5":
                                        choice="pl5";
                                        break;

                                }

                                submit(choice, Integer.parseInt(l));

                        }
                    }).show();

        }




    }
}

