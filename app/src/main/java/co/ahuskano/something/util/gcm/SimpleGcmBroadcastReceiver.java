package co.ahuskano.something.util.gcm;

import android.content.Intent;

import com.dmacan.lightandroidgcm.GcmBroadcastReceiver;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class SimpleGcmBroadcastReceiver extends GcmBroadcastReceiver {
    @Override
    protected String getGcmIntentServiceClassName(Intent intent) {
        return SimpleGcmIntentService.class.getName();
    }
}
