package com.example.latest_lottery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.latest_lottery.Account.Account_Fragment;
import com.example.latest_lottery.Analysis.Analysis_Fragment;
import com.example.latest_lottery.History.History_Fragment;
import com.example.latest_lottery.Latest.Latest_Fragment;
import com.example.latest_lottery.Util.Util_Fragment;
import com.example.latest_lottery.show_interface.Parent_Interface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This is the launcher of setting up 4 fragments extracted from the base fragment setup
 * It will formalize the initialization step and make coding style easier to read
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */


public class Home extends FragmentActivity {
    private ArrayList<Parent_Interface> fragment_unit;
    private RadioGroup radioGroup;
    private int select_index = 0;
    private Fragment temp_page;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        radioGroup=findViewById(R.id.home_radio_group);//setup radiogroup listener for monitoring the fragment change

        Initialization();
        listen_setup();
        radioGroup.check(R.id.latest);


    }


    public void listen_setup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.latest) {
                    select_index = 0;
                } else if (i == R.id.history) {
                    select_index = 1;
                } else if (i == R.id.analysis) {
                    select_index = 2;
                } else if (i == R.id.util) {
                    select_index = 3;
                } else if (i == R.id.account) {
                    select_index = 4;
                } else
                    select_index = 0;
                //once setup the listener we will return back the index page that user focused on and store this index for the use of switching pages
                Parent_Interface parent_interface = get_page(select_index);
                switch_page(temp_page,parent_interface);
                //Toast.makeText(Home.this,"da",0).show();
            }



        });
    }


    private void switch_page(Fragment fragment, Parent_Interface parent_interface) {
        if (temp_page != parent_interface) {
            temp_page = parent_interface;
            if (parent_interface != null) {
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                if (!parent_interface.isAdded()) {
                    if (fragment != null) {
                        t.hide(fragment);
                    }
                    t.add(R.id.home_frame, parent_interface).commit();
                } else {
                    if (fragment != null) {
                        t.hide(fragment);
                    }
                    t.show(parent_interface).commit();
                }

            }


        }


    }
/*
    private String auth_checker(Context context){
        SharedPreferences sp=context.getSharedPreferences("signature_exists", Context.MODE_PRIVATE);
        String s=sp.getString("signature_exists",null);
        if(s==null)
            return "";
        else
            return s;

    }

    private void guest_auth(RequestQueue requestQueue){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest("https://apimobile1z09p6j.epyin.net:8931/veri/guest", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Toast.makeText(Home.this, response.getString("signature").toString(), 0).show();
                    SharedPreferences sp=Home.this.getSharedPreferences("signature_exists",Context.MODE_PRIVATE);
                    sp.edit().putString("signature_exists",response.getString("signature").toString()).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            public void onErrorResponse(VolleyError volleyerror){
                Toast.makeText(Home.this, volleyerror.toString(), 0).show();
            }

        });
        requestQueue.add(jsonObjectRequest);
    }

    public void authentication_verify(RequestQueue requestQueue,String signature){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest("https://apimobile1z09p6j.epyin.net:8931/veri/validity?signature="+signature, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String code=response.getString("authenticate");
                    if(code=="1"){
                        user=response.getString("user").toString();
                    }
                    else{
                        user="guest";
                        guest_auth(requestQueue);
                    }
                    Toast.makeText(Home.this,user,0).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            public void onErrorResponse(VolleyError volleyerror){
                Toast.makeText(Home.this, volleyerror.toString(), 0).show();
            }

        });
        requestQueue.add(jsonObjectRequest);
    }
*/

    public void Initialization() {
        fragment_unit = new ArrayList<>();
        fragment_unit.add(new Latest_Fragment());
        fragment_unit.add(new History_Fragment());
        fragment_unit.add(new Analysis_Fragment());
        fragment_unit.add(new Util_Fragment());
        fragment_unit.add(new Account_Fragment());//Inject different fragements into the fragment_unit manager
    }

    private Parent_Interface get_page(int i) {
        if (fragment_unit != null && fragment_unit.size() > 0) {
            Parent_Interface parent_interface = fragment_unit.get(i);
            return parent_interface;
        } else
            return null;
    }
}