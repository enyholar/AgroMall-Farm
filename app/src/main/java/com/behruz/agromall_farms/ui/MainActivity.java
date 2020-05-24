package com.behruz.agromall_farms.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.behruz.agromall_farms.AppApplication;
import com.behruz.agromall_farms.R;
import com.behruz.agromall_farms.adapter.FarmAdapter;
import com.behruz.agromall_farms.databinding.ActivityMainBinding;
import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.viewModel.FarmerViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FarmerViewModel mFramerViewModel;
    public static final int NEW_FARMER_REQUEST_CODE = 1;
    private ActivityMainBinding binding;
    private FarmAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
       // mFramerViewModel =  new ViewModelProvider(this).get(FarmerViewModel.class);
        mFramerViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(FarmerViewModel.class);
        initToolBar();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddFarmerActivity.class);
                startActivityForResult(intent, NEW_FARMER_REQUEST_CODE);
            }
        });

        mFramerViewModel.getAllFarm().observe(this, new Observer<List<Farmer>>() {
            @Override
            public void onChanged(@Nullable final List<Farmer> farmerList) {
                // Update the cached copy of the words in the adapter.
                if (farmerList != null) {
                    adapter.clear();
                    adapter.addAll(farmerList);
                }
            }
        });

        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new FarmAdapter(getApplicationContext(), (model1, position) -> {
            Intent intent = new Intent(MainActivity.this, FarmerDetailActivity.class);
            intent.putExtra("model",model1.getId());
            startActivity(intent);
        });
        binding.recyclerview.setLayoutManager(layoutManager);
        //       binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setAdapter(adapter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_FARMER_REQUEST_CODE && resultCode == RESULT_OK) {
            Farmer farmer = (Farmer) data.getSerializableExtra(AddFarmerActivity.EXTRA_REPLY);
        //    farmer.setPicture(AppApplication.getInstance().getCapturedPhotoData());
            mFramerViewModel.insert(farmer);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }

        AppApplication.getInstance().setCapturedPhotoData(null);

    }

    private void initToolBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.farmer_list));
    }
}
