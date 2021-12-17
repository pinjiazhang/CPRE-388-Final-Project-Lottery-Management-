package com.example.latest_lottery.Latest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Entity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.latest_lottery.Data.Sig_data;
import com.example.latest_lottery.Data.Sig_verify;
import com.example.latest_lottery.Login;
import com.example.latest_lottery.Network_Utils.Url_Collection;
import com.example.latest_lottery.Processor.Item_Process;
import com.example.latest_lottery.Processor.Latest_Process;
import com.example.latest_lottery.R;
import com.example.latest_lottery.User_pref.UserInfo;
import com.example.latest_lottery.Util.LiveDataBus;
import com.example.latest_lottery.show_interface.Parent_Interface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * The latest fragement will display the main page in home page of the app
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class Latest_Fragment extends Parent_Interface implements View.OnClickListener {



    @ViewInject(R.id.index_view)
    private RecyclerView rv;
    @ViewInject(R.id.index_userlevel)
    private TextView textView;
    @ViewInject(R.id.progressBar)//By using the viewinject from xutils we can simply replace the findviewbyid statement
    private ProgressBar progressBar;
    private UserInfo userInfo=new UserInfo();
    private Latest_Process latest_process;
    private Item_Process item_process;
    private JSONArray data=null;//Initialize the JSONArray for future data fetching where the array will return sets of lottery data from each type
    @ViewInject(R.id.index_user)
    private TextView t;


    @Override
    public View genview() {
        //view generation, seperate from the loading method that load the data
        View v=View.inflate(context, R.layout.index_page,null);
        //rv=v.findViewById(R.id.index_view);
        x.view().inject(this,v);
        t.setOnClickListener(this);
        return v;
    }




    @Override
    public void loading() {
        super.loading();
        userInfo.signature=auth_checker(context);
        if(userInfo.signature=="") {
            getguest_sig(userInfo);
        }
        else {
            sig_verify(userInfo.signature);
        }
        //If the signature stored inside of the shared preferences returns null, we will regain the signature from the server as guest. Otherwise we
        //will proceed the verification of the existing signature
        //The payload of the signature is combined with username+user_agent+ip. So if any one of these elements changed and does not match the data inside
        //of the database it will prompt user to reenter the password and username in order to guarantee the safety


        load_latest();
        LiveDataBus.getInstance().with("signature",String.class).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        LiveDataBus.getInstance().with("user_info",String.class).observe(Latest_Fragment.this, new Observer<String>() {
                            @Override
                            public void onChanged(String u) {
                                textView.setText(u);
                            }
                        });
                }});


            //getlatest();
        //latest_process=new Latest_Process(context,);
        //rv.setAdapter(latest_process);









    }



    private void getguest_sig(UserInfo userInfo) {
        RequestParams requestParams=new RequestParams(Url_Collection.guest_getsig);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            public void onSuccess(String result){
                Parse_Sig(result);
                sig_verify(userInfo.signature);

            }


            private void Parse_Sig(String result) {
                Sig_data sig_data=parsed(result);
                userInfo.signature=sig_data.getSignature();
                SharedPreferences sp=context.getSharedPreferences("signature_exists", Context.MODE_PRIVATE);
                sp.edit().putString("signature_exists",userInfo.signature).commit();

                //return sig;




            }

            private Sig_data parsed(String result) {
                Gson gson=new Gson();
                Sig_data sig=gson.fromJson(result,Sig_data.class);
                return sig;

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("err",ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

        });

    }


    private void sig_verify(String param) {
        RequestParams requestParams=new RequestParams(Url_Collection.sig_verify+"?signature="+param);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            public void onSuccess(String result){
                Parse_Res(result);
            }

            private void Parse_Res(String result) {
                Sig_verify sig_verify=parsed(result);
                String auth_code=sig_verify.getAuthenticate().toString();
                String user_group= sig_verify.getUser();
                if(auth_code.equals("1")) {
                    textView.setText(user_group);
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    userInfo.authenticate="verified";
                    userInfo.user=user_group;
                    LiveDataBus.getInstance().with("user_info",String.class).postValue(userInfo.user);
                    LiveDataBus.getInstance().with("signature",String.class).postValue(userInfo.signature);

                }
                else{
                    SharedPreferences sp=context.getSharedPreferences("signature_exists", Context.MODE_PRIVATE);
                    sp.edit().clear().commit();
                    userInfo.authenticate="";
                    userInfo.user="guest";
                    getguest_sig(userInfo);

                }



                // Request a string response from the provided URL.



            }



            private Sig_verify parsed(String result) {
                Gson gson=new Gson();
                Sig_verify code=gson.fromJson(result,Sig_verify.class);
                return code;

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("err",ex);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

        });

    }


    private String auth_checker(Context context){

        SharedPreferences sp=context.getSharedPreferences("signature_exists", Context.MODE_PRIVATE);
        String s=sp.getString("signature_exists",null);
        if(s==null)
            return "";
        else
            return s;
    }




    private void load_latest(){
        //After passing the verification process we can load the latest from the server using the current signature
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="https://apimobile1z09p6j.epyin.net/layout.json";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            data=response;
                            latest_process=new Latest_Process(context,data);
                            rv.setAdapter(latest_process);
                            rv.setLayoutManager(new LinearLayoutManager(context));

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
    }


    @Override
    public void onClick(View view) {
        if(view==t){
            if(!textView.getText().equals("guest")){
                new AlertDialog.Builder(getActivity()).setTitle("Login Confirmation")
                        .setMessage("Seems You have logged in,want to switch acount?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getActivity(), Login.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
            else{
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        }

    }
}
