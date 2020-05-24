package com.behruz.agromall_farms.model;

/**
 * Created by Gideon Oyediran on 22/05/2020.
 * gideonoyediran@gmail.com
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "farmer")
public class Farmer implements Serializable {
    @PrimaryKey(autoGenerate = true) // will serve as a primary key for the table
    public int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "pic",typeAffinity = ColumnInfo.BLOB)
    private byte[] picture;

    public Farmer(String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
                address.equals(farmer.address) &&
                picture.equals(farmer.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phoneNumber, address, picture);
    }
}

