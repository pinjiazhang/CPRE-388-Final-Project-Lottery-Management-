package com.example.latest_lottery;

import static com.android.volley.VolleyLog.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.latest_lottery.Latest.Latest_Fragment;
import com.example.latest_lottery.Processor.Latest_Process;
import com.example.latest_lottery.Util.LiveDataBus;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;

import java.util.concurrent.Executor;

/**
 * This is the login portal for user to verify the username&password.
 * It will push a notification to user if failed retry limit reaches and a google recaptcha will appear
 *
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */


public class Login extends AppCompatActivity implements View.OnClickListener {
    Button b;
    TextView user;
    TextView pass;
    int i=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        b=findViewById(R.id.login_start);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        b.setOnClickListener(this);

    }
    private void verify(String user,String pass){
    //Sending the verification request to the server to verify the username and password
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://apimobile1z09p6j.epyin.net/veri/member.php?user="+user+"&pass="+pass;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String signature=response.getString("signature");
                            String name=response.getString("username");
                            if(signature.length()>0){
                                SharedPreferences sp=Login.this.getSharedPreferences("signature_exists", Context.MODE_PRIVATE);
                                sp.edit().putString("signature_exists",signature).commit();
                                LiveDataBus.getInstance().with("user_info",String.class).postValue(name);
                                LiveDataBus.getInstance().with("signature",String.class).postValue(signature);
                                //Intent intent = new Intent(Login.this, Latest_Fragment.class);
                                //startActivity(intent);
                                finish();

                            }
                            else {
                                Toast.makeText(Login.this, "The password/user combination does not match ", Toast.LENGTH_LONG).show();

                            }
                            } catch (JSONException e) {


                            SafetyNet.getClient(Login.this).verifyWithRecaptcha("6LeH34kdAAAAAMRgyytCYzgqypAZw_lTPdSPDnzM")//Using the official google recaptcha API to determine the behavior of user login
                                    .addOnSuccessListener(new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                                        @Override
                                        public void onSuccess(SafetyNetApi.RecaptchaTokenResponse recaptchaTokenResponse) {
                                            // Indicates communication with reCAPTCHA service was
                                            // successful.
                                            String userResponseToken = recaptchaTokenResponse.getTokenResult();
                                            if (!userResponseToken.isEmpty()) {
                                                // Validate the user response token using the
                                                // reCAPTCHA siteverify API.

                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            if (e instanceof ApiException) {
                                                //API exception occurred and the error message will be directed to the console
                                                ApiException apiException = (ApiException) e;
                                                int statusCode = apiException.getStatusCode();
                                                Log.e(TAG, "Error: " + CommonStatusCodes
                                                        .getStatusCodeString(statusCode));
                                            } else {

                                                Log.e(TAG, "Error: " + e.getMessage());
                                            }
                                        }
                                    });

                            Toast.makeText(Login.this,"The password/user combination does not matched",Toast.LENGTH_LONG).show();//Give a hint to user for reference

                        }
                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,"The password/user combination does not matches",Toast.LENGTH_LONG).show();

                    }
                });
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == b) {
            String username = user.getText().toString().trim();
            String password = pass.getText().toString().trim();
            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                verify(username, password);//The Volley handle the verification process from server
            } else {
                Toast.makeText(this, "Invalid username or password as they cannot be null", Toast.LENGTH_LONG).show();
            }
        }
    }
}
