package info.acidflow.shartc;

import android.app.Application;
import android.content.Context;

/**
 * Created by acidflow on 27/10/13.
 */
public class ShaRTC extends Application {

    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
    }

    /**
     * Easy access to the application context
     * @return the application context
     */
    public static Context getContext(){
        return mApplicationContext;
    }
}
