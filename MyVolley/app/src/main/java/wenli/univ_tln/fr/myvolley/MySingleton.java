package wenli.univ_tln.fr.myvolley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by wenlixing on 07/12/2016.
 */

public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    public MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static synchronized MySingleton getInstance(Context context){
        if(mInstance == null)
            mInstance = new MySingleton(context);
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public<T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

}
