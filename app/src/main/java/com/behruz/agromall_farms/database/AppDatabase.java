package com.behruz.agromall_farms.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.model.FarmerFarm;
import com.behruz.agromall_farms.model.User;

/**
 * Created by Gideon Oyediran on 22/05/2020.
 * gideonoyediran@gmail.com
 */
//  RoomDatabase class
//  An array of the Entity classes(the tables)
// The database version which is just an integer.
@Database(entities = {Farmer.class, FarmerFarm.class, User.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

//create database
    public static AppDatabase getDatabase(Context context) {
        //create the database using
        if (INSTANCE == null) {
            INSTANCE =
                Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "borrow_db")
                    .allowMainThreadQueries().build();
                    //.build();
        }
        return INSTANCE;
    }
    public abstract LoginDao loginDao();
    public abstract FarmerDao farmerDao();
    public abstract FarmerFarmDao getFarmerFarmDao();
}
