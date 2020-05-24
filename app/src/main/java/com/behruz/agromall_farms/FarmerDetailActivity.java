package com.behruz.agromall_farms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.behruz.agromall_farms.adapter.FarmAdapter;
import com.behruz.agromall_farms.adapter.FarmerFarmAdapter;
import com.behruz.agromall_farms.databinding.ActivityFarmerDetailBinding;
import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.FarmerFarm;
import com.behruz.agromall_farms.viewModel.FarmerFarmViewModel;
import com.behruz.agromall_farms.viewModel.FarmerViewModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class FarmerDetailActivity extends AppCompatActivity {
    ActivityFarmerDetailBinding binding;
    public static final int NEW_FARM_REQUEST_CODE = 3;
    private FarmerFarmViewModel mViewModel;
    private int farmerId;
    private FarmerFarmAdapter adapter;
    private FarmerViewModel mFarmerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_farmer_detail);
        mFarmerViewModel =  new ViewModelProvider(this).get(FarmerViewModel.class);

        mViewModel =  new ViewModelProvider(this).get(FarmerFarmViewModel.class);
        getData();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FarmerDetailActivity.this, CaptureFarmActivity.class);
                intent.putExtra("id",farmerId);
                startActivityForResult(intent, NEW_FARM_REQUEST_CODE);
            }
        });

        mViewModel.getAllFarmsOfAFarmers(String.valueOf(farmerId)).observe(this, new Observer<List<FarmerFarm>>() {
            @Override
            public void onChanged(@Nullable final List<FarmerFarm> farms) {
                if (farms != null){
                    adapter.clear();
                    adapter.addAll(farms);
                }

            }
        });

        mFarmerViewModel.getFarmsById((farmerId)).observe(this, new Observer<Farmer>() {
            @Override
            public void onChanged(@Nullable final Farmer farms) {
                setValueToView(farms);
            }
        });


        initAdapter();
    }

    private void setValueToView(Farmer farms){
        if (farms != null){
            binding.name.setText(farms.getName());
            binding.parentEmail.setText(farms.getEmail());
            String s = null;
            try {
                s = new String(farms.getPicture(), "UTF-8");
                Uri uri = Uri.parse(s);
                binding.profileImg.setImageURI(uri);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new FarmerFarmAdapter(getApplicationContext(), (model1, position) -> {
            Intent intent = new Intent(FarmerDetailActivity.this, FarmDetailsActivity.class);
            intent.putExtra("model",model1);
            startActivity(intent);
        });
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(adapter);
    }

    private void getData(){
        farmerId = getIntent().getIntExtra("model",0);
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
