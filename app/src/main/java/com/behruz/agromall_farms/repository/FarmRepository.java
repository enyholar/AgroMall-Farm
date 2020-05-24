package com.behruz.agromall_farms.repository;
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.behruz.agromall_farms.database.AppDatabase;
import com.behruz.agromall_farms.database.FarmerDao;
import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.FarmerFarm;

import java.util.List;

public class FarmRepository {

    private FarmerDao mFarmerDao;
    private LiveData<List<Farmer>> mAllFarmer;
    private LiveData<Farmer> mFarmer;
    public FarmRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mFarmerDao = db.farmerDao();
        mAllFarmer = mFarmerDao.getAllFarmers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Farmer>> getmAllFarmer() {
        return mAllFarmer;
    }

    public void insert(final Farmer farmer) {
        mFarmerDao.insert(farmer);
    }

    public LiveData<Farmer> getFarmById(int farmerId){
        mFarmer = mFarmerDao.getAFarmer(farmerId);

        return mFarmer;
    }

}
