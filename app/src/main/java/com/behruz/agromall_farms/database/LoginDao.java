package com.behruz.agromall_farms.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.FarmerFarm;
import com.behruz.agromall_farms.model.User;

import java.util.List;

@Dao
public interface LoginDao {

    @Query("SELECT * FROM user WHERE email=:email")
    User getUser(String email);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

}
