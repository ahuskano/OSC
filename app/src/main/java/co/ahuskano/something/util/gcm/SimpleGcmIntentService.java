package co.ahuskano.something.util.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.dmacan.lightandroidgcm.GcmIntentService;
import com.dmacan.lightandroidgcm.GcmObserver;
import com.dmacan.lightandroidgcm.listener.OnGcmMessageReceivedListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import co.ahuskano.something.R;
import co.ahuskano.something.activitys.SpaceDetailActivity;
import co.ahuskano.something.util.Constants;

/**
 * Created by ahuskano on 10.10.2015..
 */

public class SimpleGcmIntentService extends GcmIntentService {

    private static final String TAG = "DAM_SERV_GCM";

    public SimpleGcmIntentService() {
        this("MOJGCMINTENTSERVICE_BRE");
        Log.i(TAG, "GCMI start");
    }

    /**
     * @param name Used to name the worker thread, important only for debugging.
     */
    protected SimpleGcmIntentService(String name) {
        super(name);
    }

    @Override
    protected void onSendError() {
        OnGcmMessageReceivedListener observer = GcmObserver.getInstance().getObserver();
        if (observer != null)
            observer.onGcmMessageReceived(false, null);
    }

    @Override
    protected void onMessageDeleted(int total) {
        OnGcmMessageReceivedListener observer = GcmObserver.getInstance().getObserver();
        if (observer != null)
            observer.onGcmMessageReceived(true, null);
    }

    @Override
    protected void onMessageReceived(Intent intent) {
        Log.i(TAG, "Message received");
        OnGcmMessageReceivedListener observer = GcmObserver.getInstance().getObserver();
        if (observer != null)
            observer.onGcmMessageReceived(true, intent);
        notification(intent);
    }

    private void notification(Intent data) {
        Log.d("test", "notification " + data.getStringExtra("interests"));
        Gson gson=new Gson();
        JsonArray array=gson.fromJson(data.getStringExtra("interest"), JsonArray.class);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icone_notification) // notification icon
                .setContentTitle("User with similar interests near you!") // title for notification
                .setAutoCancel(true); // clear notification after click
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getBaseContext(), SpaceDetailActivity.class);
        intent.putExtra(Constants.KEY_SPACE_ID, data.getStringExtra("space"));
        intent.putExtra(Constants.KEY_GCM, true);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());

    }

}
