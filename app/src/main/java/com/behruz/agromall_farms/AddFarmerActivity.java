package com.behruz.agromall_farms;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.behruz.agromall_farms.databinding.ActivityAddFarmerBinding;
import com.behruz.agromall_farms.model.Farmer;
import com.behruz.agromall_farms.viewModel.FarmerViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddFarmerActivity extends AppCompatActivity {
    private ActivityAddFarmerBinding binding;
    FarmerViewModel mFramerViewModel;
    public static final String EXTRA_REPLY = "farmer";
    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;
    private final int MY_PERMISSIONS_REQUEST_READ_MEDIA = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_farmer);
         binding.btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 addFarmerInfo();
             }
         });

         binding.profileImg.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 int permissionCheck = ContextCompat.checkSelfPermission(AddFarmerActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);

                 if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                         requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_MEDIA);
                     }else {
                         chooseImage();
                     }
                 } else {
                     chooseImage();
                 }
             }
         });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            binding.profileImg.setImageURI(filePath);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_MEDIA:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    chooseImage();
                }
                break;

            default:
                break;
        }
    }

    public void addFarmerInfo(){
        String name = binding.etName.getText().toString();
        String email = binding.etEmailAddress.getText().toString();
        String phoneNumber = binding.etphoneNumber.getText().toString();
        String address = binding.etAddress.getText().toString();
        Farmer farmer = new Farmer(name,email,phoneNumber,address);
        byte[] image = convertImageToByte(filePath);
        AppApplication.getInstance().setCapturedPhotoData(image);
 //       farmer.setPicture(image);
        Intent replyIntent = new Intent();
        if (farmer == null) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            replyIntent.putExtra(EXTRA_REPLY, farmer);
            setResult(RESULT_OK, replyIntent);
        }
        finish();

    }

    public byte[] convertImageToByte(Uri uri){
        byte[] data = null;
        try {
            ContentResolver cr = getBaseContext().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

}
