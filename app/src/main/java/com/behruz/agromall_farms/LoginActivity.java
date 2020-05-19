package com.behruz.agromall_farms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        setContentView(R.layout.activity_login);
    }
}
