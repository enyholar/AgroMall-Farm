package com.behruz.agromall_farms;

import android.app.Application;

/**
 * Created by Gideon Oyediran on 24/05/2020.
 * gideonoyediran@gmail.com
 */
public class AppApplication extends Application {
    private static AppApplication sInstance;
    private byte[] mCapturedPhotoData;

    // Getters & Setters
    public byte[] getCapturedPhotoData() {
        return mCapturedPhotoData;
    }

    public void setCapturedPhotoData(byte[] capturedPhotoData) {
        mCapturedPhotoData = capturedPhotoData;
    }

    // Singleton code
    public static AppApplication getInstance() { return sInstance; }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
