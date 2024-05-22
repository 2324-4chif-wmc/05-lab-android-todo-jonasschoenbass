package at.htl.additionaltask;

import android.app.Application;
import android.util.Log;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class TodoApplication extends Application {

    public final String Tag = TodoApplication.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Tag, "App started...");
    }
}
