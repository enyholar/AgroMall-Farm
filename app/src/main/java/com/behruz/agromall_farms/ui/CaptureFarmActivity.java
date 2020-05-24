package com.behruz.agromall_farms.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.behruz.agromall_farms.R;
import com.behruz.agromall_farms.adapter.PlacesAutoCompleteAdapter;
import com.behruz.agromall_farms.databinding.ActivityCaptureFarmBinding;
import com.behruz.agromall_farms.model.FarmerFarm;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

public class CaptureFarmActivity extends AppCompatActivity implements PlaceSelectionListener, PlacesAutoCompleteAdapter.ClickListener {
    private int AUTOCOMPLETE_REQUEST_CODE = 100;
    private PlacesAutoCompleteAdapter mAutoCompleteAdapter;
    private ActivityCaptureFarmBinding binding;
    private int farmerId;
    private Double longitude;
    private Double latitude;
    public static final String EXTRA_REPLYS = "farmerName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_capture_farm);
        getData();
        initToolBar();
        String apiKey = getString(R.string.google_map_api_key);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }
        binding.edtPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.placesRecyclerView.getVisibility() == View.GONE) {
                    hideKeyboard(CaptureFarmActivity.this);
                }else {
                  //  binding.lnRecipent.setVisibility(View.GONE);
                    binding.edtPickUp.setFocusable(true);
                    binding.edtPickUp.setCursorVisible(true);
                    binding.edtPickUp.requestFocus();
                    binding.edtPickUp.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            if (inputMethodManager != null) {
                                inputMethodManager.showSoftInput( binding.edtPickUp, InputMethodManager.SHOW_IMPLICIT);
                            }
                            binding.edtPickUp.setFocusable(true);
                            binding.edtPickUp.setCursorVisible(true);
                            binding.edtPickUp.requestFocus();
                        }
                    }, 200);
                }
            }
        });
        binding.edtPickUp.addTextChangedListener(filterTextWatcher);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFarmerInfo();
            }
        });

        initAutoCompleteAdapter();
    }

    private void getData(){
        farmerId = getIntent().getIntExtra("id",0);
    }

    public void addFarmerInfo(){
        String farmName = binding.etfarmName.getText().toString();
        String farmProduct = binding.etFarmProduct.getText().toString();
        String address = binding.edtPickUp.getText().toString();
        if (farmName.isEmpty()){
            binding.etfarmName.setError("Farm name field cannot be empty");
            return;
        }

        if (farmProduct.isEmpty()){
            binding.etFarmProduct.setError("Farm product field cannot be empty");
            return;
        }

        if (address.isEmpty()){
            binding.edtPickUp.setError("Address field cannot be empty");
            return;
        }

        String id = String.valueOf(farmerId);
        FarmerFarm farmer = new FarmerFarm(id,farmName,address,farmProduct,longitude,latitude);
        Intent replyIntent = new Intent();
        if (farmer == null) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            replyIntent.putExtra(EXTRA_REPLYS, farmer);
            setResult(RESULT_OK, replyIntent);
        }
        finish();

    }


    private TextWatcher filterTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {
            if (!s.toString().equals("")) {
                mAutoCompleteAdapter.getFilter().filter(s.toString());
                if ( binding.placesRecyclerView.getVisibility() == View.GONE) {
                    binding.placesRecyclerView.setVisibility(View.VISIBLE);}
            } else {
                if ( binding.placesRecyclerView.getVisibility() == View.VISIBLE) {
                    binding.placesRecyclerView.setVisibility(View.GONE);}
            }
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        public void onTextChanged(CharSequence s, int start, int before, int count) { }
    };

    private void initAutoCompleteAdapter() {
        mAutoCompleteAdapter = new PlacesAutoCompleteAdapter(this);
        binding.placesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAutoCompleteAdapter.setClickListener(this);
        binding.placesRecyclerView.setAdapter(mAutoCompleteAdapter);
        mAutoCompleteAdapter.notifyDataSetChanged();
    }



    public  void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("result", "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddress());
                Toast.makeText(CaptureFarmActivity.this, "ID: " + place.getId() + "address:" + place.getAddress() + "Name:" + place.getName() + " latlong: " + place.getLatLng(), Toast.LENGTH_LONG).show();
                String address = place.getAddress();
                longitude = place.getLatLng().longitude;
                latitude = place.getLatLng().latitude;
                binding.edtPickUp.setText(address);
                // do query with address

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Toast.makeText(CaptureFarmActivity.this, "Error: " + status.getStatusMessage(), Toast.LENGTH_LONG).show();
                Log.i("Result", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public void click(Place place) {
        if (place != null) {
            if (binding.edtPickUp.isFocused()){
                //    originLocation = place.getLatLng();
                binding.edtPickUp.setText(place.getAddress());
                binding.placesRecyclerView.setVisibility(View.GONE);
                longitude = place.getLatLng().longitude;
                latitude = place.getLatLng().latitude;
            //    binding.lnRecipent.setVisibility(View.VISIBLE);
            }

        }

    }


    @Override
    public void onPlaceSelected(@NonNull Place place) {

    }

    @Override
    public void onError(@NonNull Status status) {
        binding.placesRecyclerView.setVisibility(View.GONE);
       // binding.lnRecipent.setVisibility(View.VISIBLE);
    }

    private void initToolBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.capture_farm));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
