package com.behruz.agromall_farms.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.User;
import com.behruz.agromall_farms.repository.FarmRepository;
import com.behruz.agromall_farms.repository.LoginRepository;

import java.util.List;

/**
 * Created by Gideon Oyediran on 23/05/2020.
 * gideonoyediran@gmail.com
 */

public class LoginViewModel extends AndroidViewModel {
    private LoginRepository mRepository;
    private LiveData<List<Farmer>> mAllFarmerFarm;

    public LoginViewModel(Application application) {
        super(application);
        mRepository = new LoginRepository(application);
    }



  public void insert(User user) {
        mRepository.insert(user);
    }

    public User getUser(String email){
        return mRepository.getLoginUser(email);
    }
}
