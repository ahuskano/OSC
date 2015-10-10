package co.ahuskano.something.util.gcm;

import android.content.Context;
import android.util.Log;

import com.dmacan.lightandroidgcm.GcmRegistar;
import com.dmacan.lightandroidgcm.listener.OnErrorListener;
import com.dmacan.lightandroidgcm.listener.OnGcmRegisteredListener;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class SimpleGcmRegistar extends GcmRegistar {
    private Context context;
    private OnGcmRegisteredListener onGcmRegisteredListener;
    private OnErrorListener onErrorListener;

    /**
     * @param context application's context.
     */
    public SimpleGcmRegistar(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onRegister(String registrationId) {
      /*
        Store regId within preferences, like:
        new Preferences(context).saveRegistrationId(registrationId);
        */
        if (this.onGcmRegisteredListener != null)
            this.onGcmRegisteredListener.onGcmRegistered(true, registrationId);
    }

    @Override
    protected void onError(Exception exception) {
        Log.e("SimpleGCM", "Exception: " + exception.getMessage());
        if (this.onErrorListener != null)
            this.onErrorListener.onError(exception.getMessage());
    }

    public void setOnGcmRegisteredListener(OnGcmRegisteredListener onGcmRegisteredListener) {
        this.onGcmRegisteredListener = onGcmRegisteredListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }
}
