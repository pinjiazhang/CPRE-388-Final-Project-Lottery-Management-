package com.example.latest_lottery.Account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.example.latest_lottery.Data.Sig_data;
import com.example.latest_lottery.Latest.Latest_Fragment;
import com.example.latest_lottery.Network_Utils.Url_Collection;
import com.example.latest_lottery.R;
import com.example.latest_lottery.User_pref.UserInfo;
import com.example.latest_lottery.Util.LiveDataBus;
import com.example.latest_lottery.show_interface.Parent_Interface;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;
/**
 * Account Fragment used to do modifications to user account settings
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */
public class Account_Fragment extends Parent_Interface implements View.OnClickListener {
    private TextView textView;
    private TextView signoff;
    private TextView preference;
    @Override
    public View genview() {
        View v=View.inflate(context, R.layout.account_page,null);
        textView=v.findViewById(R.id.textView10);
        signoff=v.findViewById(R.id.textView12);
        preference=v.findViewById(R.id.preference);
        signoff.setOnClickListener(this);
        preference.setOnClickListener(this);
        return v;
    }

    @Override
    public void loading() {
        super.loading();
        update_account();
    }

    public void update_account(){
        LiveDataBus.getInstance().with("user_info",String.class).observe(Account_Fragment.this, new Observer<String>() {
            @Override
            public void onChanged(String user) {
                textView.setText(user);

            }
        });}

    @Override
    public void onClick(View view) {
        if(view==signoff){
            SharedPreferences sp=context.getSharedPreferences("signature_exists", Context.MODE_PRIVATE);
            getguest_sig();
            //LiveDataBus.getInstance().with("user_info",String.class).postValue("guest");
            //LiveDataBus.getInstance().with("signature",String.class).postValue("");

        }
        if(view==preference){
            Intent intent=new Intent(getActivity(),User_Preference.class);
            startActivity(intent);
        }
    }


    private void getguest_sig(){
        RequestParams requestParams=new RequestParams(Url_Collection.guest_getsig);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            public void onSuccess(String result){
                Parse_Sig(result);


            }


            private void Parse_Sig(String result) {
                Sig_data sig_data=parsed(result);
                SharedPreferences sp=context.getSharedPreferences("signature_exists", Context.MODE_PRIVATE);
                sp.edit().putString("signature_exists",sig_data.getSignature()).commit();
                LiveDataBus.getInstance().with("user_info",String.class).postValue("guest");
                LiveDataBus.getInstance().with("signature",String.class).postValue(sig_data.getSignature());
                //Parse the new signature if the old one is invalid, it will flush the old one and put the new signature to shared preferences




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
}
