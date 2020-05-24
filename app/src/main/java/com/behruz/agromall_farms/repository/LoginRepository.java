package com.behruz.agromall_farms.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.behruz.agromall_farms.database.AppDatabase;
import com.behruz.agromall_farms.database.LoginDao;
import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.User;

import java.util.List;

public class LoginRepository {

    private LoginDao mLoginDao;
    private LiveData<List<Farmer>> mAllFarmer;
    private LiveData<Farmer> mFarmer;
    public LoginRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mLoginDao = db.loginDao();
    }


    public void insert(final User user) {
        mLoginDao.insert(user);
    }

    public User getLoginUser(String email){
        return mLoginDao.getUser(email);
    }

}
