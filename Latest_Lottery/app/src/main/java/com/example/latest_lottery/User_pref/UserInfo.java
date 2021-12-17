package com.example.latest_lottery.User_pref;

import android.app.Application;
/**
 * A very simple class to be used for object storage and communicate throughout the fragment
 *
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */
public class UserInfo extends Application {
    public String signature;
    public String authenticate;
    public String user;

    public void onCreate(){
    super.onCreate();
    }


}
