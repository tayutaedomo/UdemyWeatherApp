package net.tayutaedomo.udemyweatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by tayutaedomo on 2017/06/21.
 */

class MySingleton {
    private static MySingleton ourInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    public static synchronized MySingleton getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new MySingleton(context);
        }
        return ourInstance;
    }

    private MySingleton(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}

