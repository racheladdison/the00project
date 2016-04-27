package com.project.cfood;

/**
 * Created by Austin on 4/26/2016.
 */
import android.app.Application;
import android.content.Context;

public class DataContext extends Application {
    private static Context context;
    private static DBCreator dbCreator;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbCreator = new DBCreator();
        DatabaseManager.initializeInstance(dbCreator);

    }


    public static Context getContext(){
        return context;
    }

}