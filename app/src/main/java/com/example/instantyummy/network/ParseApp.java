package com.example.instantyummy.network;
import android.app.Application;

import com.example.instantyummy.R;
import com.example.instantyummy.models.YummyUser;
import com.parse.Parse;
import com.parse.ParseUser;

public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseUser.registerSubclass(YummyUser.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());
    }

}
