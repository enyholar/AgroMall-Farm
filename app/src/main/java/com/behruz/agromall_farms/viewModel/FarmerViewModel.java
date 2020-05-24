package com.behruz.agromall_farms.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.behruz.agromall_farms.database.FarmerDao;
import com.behruz.agromall_farms.database.FarmerFarmDao;
import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.FarmerFarm;
import com.behruz.agromall_farms.repository.FarmRepository;
import com.behruz.agromall_farms.repository.FarmerFarmRepository;

import java.util.List;

/**
 * Created by Gideon Oyediran on 23/05/2020.
 * gideonoyediran@gmail.com
 */

public class FarmerViewModel extends AndroidViewModel {
    private FarmRepository mRepository;
    private LiveData<List<Farmer>> mAllFarmerFarm;

    public FarmerViewModel(Application application) {
        super(application);
        mRepository = new FarmRepository(application);
        mAllFarmerFarm = mRepository.getmAllFarmer();
    }

    public LiveData<List<Farmer>> getAllFarm() {
        return mAllFarmerFarm;
    }

  public void insert(Farmer farmerFarm) {
        mRepository.insert(farmerFarm);
    }

    public LiveData<Farmer> getFarmsById(int farmerId){
        return mRepository.getFarmById(farmerId);
    }
}
