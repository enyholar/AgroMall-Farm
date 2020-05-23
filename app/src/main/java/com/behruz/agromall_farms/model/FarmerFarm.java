package com.behruz.agromall_farms.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * Created by Gideon Oyediran on 22/05/2020.
 * gideonoyediran@gmail.com
 */

@Entity(tableName = "farmer_farm")
public class FarmerFarm {
    @PrimaryKey(autoGenerate = true) // will serve as a primary key for the table
    public int id;

    @ColumnInfo(name = "farmer_id")
    private String farmerId;

    @ColumnInfo(name = "farm_name")
    private String farmName;

    @ColumnInfo(name = "farm_address")
    private String farmAddress;

    @ColumnInfo(name = "farm_product")
    private String farmProduct;

    @ColumnInfo(name = "farm_lon")
    private Double farmLon;

    @ColumnInfo(name = "farm_lat")
    private Double farmLat;

    public FarmerFarm(String farmerId, String farmName, String farmAddress, String farmProduct, Double farmLon, Double farmLat) {
        this.farmerId = farmerId;
        this.farmName = farmName;
        this.farmAddress = farmAddress;
        this.farmProduct = farmProduct;
        this.farmLon = farmLon;
        this.farmLat = farmLat;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmAddress() {
        return farmAddress;
    }

    public void setFarmAddress(String farmAddress) {
        this.farmAddress = farmAddress;
    }

    public String getFarmProduct() {
        return farmProduct;
    }

    public void setFarmProduct(String farmProduct) {
        this.farmProduct = farmProduct;
    }

    public Double getFarmLon() {
        return farmLon;
    }

    public void setFarmLon(Double farmLon) {
        this.farmLon = farmLon;
    }

    public Double getFarmLat() {
        return farmLat;
    }

    public void setFarmLat(Double farmLat) {
        this.farmLat = farmLat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FarmerFarm that = (FarmerFarm) o;
        return id == that.id &&
                farmerId.equals(that.farmerId) &&
                farmName.equals(that.farmName) &&
                farmAddress.equals(that.farmAddress) &&
                farmProduct.equals(that.farmProduct) &&
                farmLon.equals(that.farmLon) &&
                farmLat.equals(that.farmLat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, farmerId, farmName, farmAddress, farmProduct, farmLon, farmLat);
    }
}
