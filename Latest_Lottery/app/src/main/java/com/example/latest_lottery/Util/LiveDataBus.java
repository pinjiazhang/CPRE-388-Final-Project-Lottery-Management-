package com.example.latest_lottery.Util;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * This page is used to setup a sender/observer for livedata transmission bus
 *
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */


public final class LiveDataBus {
    //This is an important method implementation for the livedata monitor.
    //It will post/observe any kind of data change throughout the fragment
    private final Map<String, MutableLiveData<Object>> bus;
    private static LiveDataBus liveDataBus=new LiveDataBus();

    private LiveDataBus() {
        bus = new HashMap<>();
    }

    public static LiveDataBus getInstance(){return liveDataBus;}

    public synchronized <T> MutableLiveData<T> with (String key, Class<T> type){
        if(!bus.containsKey(key))
            bus.put(key,new MutableLiveData<Object>());

        return (MutableLiveData<T>) bus.get(key);
    }
}