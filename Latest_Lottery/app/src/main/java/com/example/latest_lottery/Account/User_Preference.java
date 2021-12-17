package com.example.latest_lottery.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.latest_lottery.R;
/**
 * User Preferences storage and display(Right now the function is limited but will implement more in future if we have more member level(Right now: guest, member))
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */
public class User_Preference extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference);
    }
}