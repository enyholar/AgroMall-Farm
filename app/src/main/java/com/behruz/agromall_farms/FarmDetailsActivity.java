package com.behruz.agromall_farms;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.behruz.agromall_farms.databinding.ActivityFarmDetailsBinding;
import com.behruz.agromall_farms.model.FarmerFarm;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FarmDetailsActivity extends AppCompatActivity  implements OnMapReadyCallback {
    private ActivityFarmDetailsBinding binding;
    private FarmerFarm farm;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_farm_details);
        initMap();
        setUpView();

    }

    private void getData(){
        farm = (FarmerFarm) getIntent().getSerializableExtra("model");

    }

    @SuppressLint("SetTextI18n")
    private void setUpView(){
        if (farm != null){
            binding.name.setText("Farm name : " + farm.getFarmName());
            binding.farmProduct.setText("Farm product : " + farm.getFarmProduct());
        }
    }

    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        displayLocation(farm.getFarmAddress(),farm.getFarmLat(),farm.getFarmLon());
    }

    private void displayLocation(String address,double latitude, double longitude) {
         mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude))
                .title(address)
                .icon(BitmapDescriptorFactory.defaultMarker()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),17.0f));

    }


}
