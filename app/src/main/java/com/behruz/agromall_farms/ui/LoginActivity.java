package com.behruz.agromall_farms.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.behruz.agromall_farms.R;
import com.behruz.agromall_farms.databinding.ActivityLoginBinding;
import com.behruz.agromall_farms.model.User;
import com.behruz.agromall_farms.viewModel.FarmerFarmViewModel;
import com.behruz.agromall_farms.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mViewModel =  new ViewModelProvider(this).get(LoginViewModel.class);
        insertDeFaultUser();
        binding.btnServerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();
                if (email.isEmpty()){
                    binding.etEmail.setError("Email field cannot be empty");
                    return;
                }

                if (password.isEmpty()){
                    binding.etPassword.setError("Password field cannot be empty");
                    return;
                }

                User user = mViewModel.getUser(email);
                if (user != null){
                    if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void insertDeFaultUser(){
        User user = new User("test@theagromall.com","password");
        mViewModel.insert(user);
    }
}
