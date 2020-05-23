package com.behruz.agromall_farms.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.behruz.agromall_farms.database.FarmerFarmDao;
import com.behruz.agromall_farms.model.FarmerFarm;
import com.behruz.agromall_farms.repository.FarmerFarmRepository;

import java.util.List;

/**
 * Created by Gideon Oyediran on 23/05/2020.
 * gideonoyediran@gmail.com
 */

public class FarmerFarmViewModel extends AndroidViewModel {
    FarmerFarmDao farmerFarmDao;
    private FarmerFarmRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<FarmerFarm>> mAllFarmerFarm;

    public FarmerFarmViewModel(Application application, FarmerFarmDao dao) {
        super(application);
        this.farmerFarmDao = dao;
        mRepository = new FarmerFarmRepository(application,farmerFarmDao);
        mAllFarmerFarm = mRepository.getmAllFarmerFarm();
    }

    LiveData<List<FarmerFarm>> getAllFarmOfAFarmer() {
        return mAllFarmerFarm;
    }

    void insert(FarmerFarm farmerFarm) {
        mRepository.insert(farmerFarm);
    }
}
