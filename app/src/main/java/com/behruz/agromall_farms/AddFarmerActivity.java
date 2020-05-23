package com.behruz.agromall_farms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.behruz.agromall_farms.databinding.ActivityAddFarmerBinding;
import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.viewModel.FarmerViewModel;

public class AddFarmerActivity extends AppCompatActivity {
    private ActivityAddFarmerBinding binding;
    FarmerViewModel mFramerViewModel;
    public static final String EXTRA_REPLY = "farmer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_farmer);
         binding.btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 addFarmerInfo();
             }
         });
    }

    public void addFarmerInfo(){
        String name = binding.etName.getText().toString();
        String email = binding.etEmailAddress.getText().toString();
        String phoneNumber = binding.etphoneNumber.getText().toString();
        String address = binding.etAddress.getText().toString();
        Farmer farmer = new Farmer(name,email,phoneNumber,address);
        Intent replyIntent = new Intent();
        if (farmer == null) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            replyIntent.putExtra(EXTRA_REPLY, farmer);
            setResult(RESULT_OK, replyIntent);
        }
        finish();

    }
}
