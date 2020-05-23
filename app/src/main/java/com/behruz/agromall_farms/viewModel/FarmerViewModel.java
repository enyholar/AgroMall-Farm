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
    FarmerDao farmerDao;
    private FarmRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Farmer>> mAllFarmerFarm;

    public FarmerViewModel(Application application, FarmerDao dao) {
        super(application);
        this.farmerDao = dao;
        mRepository = new FarmRepository(application,farmerDao);
        mAllFarmerFarm = mRepository.getmAllFarmer();
    }

    LiveData<List<Farmer>> getAllFarmOfAFarmer() {
        return mAllFarmerFarm;
    }

    void insert(Farmer farmerFarm) {
        mRepository.insert(farmerFarm);
    }
}
