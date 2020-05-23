package com.behruz.agromall_farms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.behruz.agromall_farms.databinding.ActivityFarmerDetailBinding;
import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.FarmerFarm;
import com.behruz.agromall_farms.viewModel.FarmerFarmViewModel;

import java.util.List;

public class FarmerDetailActivity extends AppCompatActivity {
    ActivityFarmerDetailBinding binding;
    public static final int NEW_FARM_REQUEST_CODE = 3;
    private FarmerFarmViewModel mViewModel;
    private Farmer farmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_farmer_detail);
        mViewModel =  new ViewModelProvider(this).get(FarmerFarmViewModel.class);
        getData();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FarmerDetailActivity.this, CaptureFarmActivity.class);
                intent.putExtra("id",farmer.getId());
                startActivityForResult(intent, NEW_FARM_REQUEST_CODE);
            }
        });

        mViewModel.getAllFarmOfAFarmer().observe(this, new Observer<List<FarmerFarm>>() {
            @Override
            public void onChanged(@Nullable final List<FarmerFarm> words) {
                // Update the cached copy of the words in the adapter.
               // adapter.setWords(words);
                int a = words.size();

            }
        });
    }

    private void getData(){
        farmer = (Farmer) getIntent().getSerializableExtra("model");
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_FARM_REQUEST_CODE && resultCode == RESULT_OK) {
            FarmerFarm farm = (FarmerFarm) data.getSerializableExtra(CaptureFarmActivity.EXTRA_REPLYS);
            if (farm != null){
                mViewModel.insert(farm);
            }

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
