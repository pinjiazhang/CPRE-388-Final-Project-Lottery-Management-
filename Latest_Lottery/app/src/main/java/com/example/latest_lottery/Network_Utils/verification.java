package com.example.latest_lottery.Network_Utils;

import android.app.Application;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xutils.x;

/**
 * Dependency Injection for xutils tool
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class verification extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
