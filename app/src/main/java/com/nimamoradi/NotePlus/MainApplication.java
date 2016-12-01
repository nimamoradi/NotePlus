package com.nimamoradi.NotePlus;

import android.app.Application;

import com.backtory.androidsdk.Storage;
import com.backtory.androidsdk.internal.Backtory;
import com.backtory.androidsdk.internal.Config;

/**
 * Created by nima on 11/24/2016.
 */

public class MainApplication extends Application {
    private static final String backtory_auth_instance_id="583699f8e4b00df14addd2d6";
    private static final String backtory_auth_key="583699f8e4b05d702457bca3";
    private static final String cloud_function_key="583699f8e4b00df14addd2d8";
    @Override
    public void onCreate() {
        super.onCreate();
                // Initializing backtory
        Backtory.init(this, Config.newBuilder().
                // Setting shared preferences as default storage for backtory
                        storage(new Storage.SharedPreferencesStorage(this)).
                // Enabling User Services
                        initAuth(backtory_auth_instance_id, backtory_auth_key).
                //Enabling cloud function
                        initCloudCode(cloud_function_key).
                 // Finilizing sdk
                        build());
    }


}
