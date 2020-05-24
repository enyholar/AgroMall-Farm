package com.behruz.agromall_farms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.behruz.agromall_farms.databinding.ActivityFarmDetailsBinding;
import com.behruz.agromall_farms.model.FarmerFarm;

public class FarmDetailsActivity extends AppCompatActivity {
    private ActivityFarmDetailsBinding binding;
    private FarmerFarm farm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_farm_details);
        getData();
    }

    private void getData(){
        farm = (FarmerFarm) getIntent().getSerializableExtra("model");
    }
}
