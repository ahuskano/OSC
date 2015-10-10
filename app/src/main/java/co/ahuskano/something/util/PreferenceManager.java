package co.ahuskano.something.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class PreferenceManager {

    public static String GCM_KEY="gcm_key";

    private static SharedPreferences getPreference(Context context){
        return context.getSharedPreferences("userdetails", Context.MODE_PRIVATE);
    }

    public static void setGCMkey(Context context,String gcm){
        SharedPreferences.Editor edit = getPreference(context).edit();
        edit.putString(PreferenceManager.GCM_KEY,gcm.trim());
        edit.commit();
    }

    public static String getGCMkey(Context context){
        return getPreference(context).getString(PreferenceManager.GCM_KEY,"default");

    }
}
