package co.ahuskano.something.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class PreferenceManager {

    public static String GCM_KEY="gcm_key";
    public static String USERNAME="username";
    public static String token="token";
    public static String REMEMBER_ME="remember_me";

    private static SharedPreferences getPreference(Context context){
        return context.getSharedPreferences("userdetails", Context.MODE_PRIVATE);
    }

    public static void setGCMkey(Context context,String gcm){
        SharedPreferences.Editor edit = getPreference(context).edit();
        edit.putString(PreferenceManager.GCM_KEY,gcm.trim());
        edit.commit();
    }

    public static String getGCMkey(Context context){
        return getPreference(context).getString(PreferenceManager.GCM_KEY, "default");

    }

    public static void setUsername(Context context,String gcm){
        SharedPreferences.Editor edit = getPreference(context).edit();
        edit.putString(PreferenceManager.USERNAME,gcm.trim());
        edit.commit();
    }

    public static String getUsername(Context context){
        return getPreference(context).getString(PreferenceManager.USERNAME,"default");
    }
    public static void setToken(Context context,String gcm){
        SharedPreferences.Editor edit = getPreference(context).edit();
        edit.putString(PreferenceManager.token,gcm.trim());
        edit.commit();
    }

    public static String getToken(Context context){
        return getPreference(context).getString(PreferenceManager.token,"default");
    }

    public static void setRememberMe(Context context,boolean gcm){
        SharedPreferences.Editor edit = getPreference(context).edit();
        edit.putBoolean(PreferenceManager.REMEMBER_ME, gcm);
        edit.commit();
    }

    public static boolean getRememberMe(Context context){
        return getPreference(context).getBoolean(PreferenceManager.REMEMBER_ME,false);
    }

}
