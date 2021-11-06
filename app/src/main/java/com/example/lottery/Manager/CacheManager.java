package com.example.lottery.Manager;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheManager {
    public static boolean isFirstTime(Context context,String launcher_code){
        SharedPreferences sp=context.getSharedPreferences("new_installation",Context.MODE_PRIVATE);
        boolean s=sp.getBoolean(launcher_code,false);
        return s;
    }
}
