package com.behruz.agromall_farms.model;

/**
 * Created by Gideon Oyediran on 22/05/2020.
 * gideonoyediran@gmail.com
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "farmer")
public class Farmer {
    @PrimaryKey(autoGenerate = true) // will serve as a primary key for the table
    public int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    @ColumnInfo(name = "pic")
    private String picture;

    public Farmer(String name, String email, String phoneNumber, String picture) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmer farmer = (Farmer) o;
        return id == farmer.id &&
                name.equals(farmer.name) &&
                email.equals(farmer.email) &&
                phoneNumber.equals(farmer.phoneNumber) &&
                picture.equals(farmer.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phoneNumber, picture);
    }
}

