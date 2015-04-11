package com.twinpeaks.inspectionreport;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class ApplicationController extends Application {
    public static final String TAG = ApplicationController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static ApplicationController mInstance;

    //Sharing Preferences
    public final String  pref_Username = "prefUsername";
    public final String  pref_IsLoggedIn = "prefIsLoggedIn";
    public final boolean pref_IsLoggedIn_defaultValue = false;
    //public final int    Pref_ItemRepitition_defValue = 1;
    //...................

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized ApplicationController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public String ReadSharePreferencesStr(Context c, String key)
    {
        String defaultValue = "";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        return prefs.getString(key, defaultValue);
    }

    public int ReadSharePreferencesInt(Context c, String key)
    {
        int defaultValue = 0;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        return prefs.getInt(key, defaultValue);
    }

    public boolean ReadSharePreferencesBool(Context c, String key)
    {
        boolean defaultValue = false;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        return prefs.getBoolean(key, defaultValue);
    }


}
