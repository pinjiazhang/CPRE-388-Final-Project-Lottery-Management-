package com.example.latest_lottery.Manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * The cache manager setup a checking point according to message carried by welcome page intent
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class CacheManager extends android.app.Activity {
    //To check if user is first time, if it is the first time s returns 0, otherwise 1 so the 3 welcome slide won't pop up if user have used this app before
    public static boolean isFirstTime(Context context,String launcher_code){
        SharedPreferences sp=context.getSharedPreferences("new_install",Context.MODE_PRIVATE);
        boolean s=sp.getBoolean(launcher_code,false);
        return s;
    }
    //Add the cache into the shared preferences
    public static void add_cache(Context context,String code,boolean v){
        SharedPreferences sp=context.getSharedPreferences("new_install",Context.MODE_PRIVATE);
        sp.edit().putBoolean(code,v).commit();
    }

}
