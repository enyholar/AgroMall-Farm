package com.behruz.agromall_farms.repository;
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.behruz.agromall_farms.database.AppDatabase;
import com.behruz.agromall_farms.database.FarmerDao;
import com.behruz.agromall_farms.database.FarmerFarmDao;
import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.FarmerFarm;

import java.util.List;

public class FarmerFarmRepository {

    private FarmerFarmDao mFarmerFarmDao;
    private LiveData<List<FarmerFarm>> mAllFarmer;

    public FarmerFarmRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mFarmerFarmDao = db.getFarmerFarmDao();

    }

    public LiveData<List<FarmerFarm>> getFarmByFarmerId(String farmerId){
        mAllFarmer = mFarmerFarmDao.getAllFarmOfAFarmer(farmerId);

        return mAllFarmer;
    }

    public void insert(final FarmerFarm farmer) {
        mFarmerFarmDao.insert(farmer);
    }
}
