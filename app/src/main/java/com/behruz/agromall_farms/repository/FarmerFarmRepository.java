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

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<FarmerFarm>> getmAllFarmerFarm() {
        return mAllFarmer;
    }

    public void getFarmByFarmerId(String farmerId){
        mAllFarmer = mFarmerFarmDao.getAllFarmOfAFarmer(farmerId);
    }

    public void insert(final FarmerFarm farmer) {
        mFarmerFarmDao.insert(farmer);
    }
}
