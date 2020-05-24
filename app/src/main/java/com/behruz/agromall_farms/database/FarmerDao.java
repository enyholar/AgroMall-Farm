package com.behruz.agromall_farms.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.FarmerFarm;

import java.util.List;

@Dao
public interface FarmerDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from farmer ORDER BY name ASC")
    LiveData<List<Farmer>> getAllFarmers();

    @Query("SELECT * FROM farmer WHERE id=:farmerId")
    LiveData<Farmer> getAFarmer(int farmerId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Farmer farmer);

    @Query("DELETE FROM farmer")
    void deleteAll();
}
