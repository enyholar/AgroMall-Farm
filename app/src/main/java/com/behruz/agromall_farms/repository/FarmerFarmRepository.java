package com.behruz.agromall_farms.repository;
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.behruz.agromall_farms.database.FarmerDao;
import com.behruz.agromall_farms.database.FarmerFarmDao;
import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.FarmerFarm;

import java.util.List;

public class FarmerFarmRepository {

    private FarmerFarmDao mFarmerFarmDao;
    private LiveData<List<FarmerFarm>> mAllFarmer;

    public FarmerFarmRepository(Application application, FarmerFarmDao dao) {
        mFarmerFarmDao = dao;
        mAllFarmer = mFarmerFarmDao.getAllFarmOfAFarmer();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<FarmerFarm>> getmAllFarmerFarm() {
        return mAllFarmer;
    }

    public void insert(final FarmerFarm farmer) {
        mFarmerFarmDao.insert(farmer);
    }
}
