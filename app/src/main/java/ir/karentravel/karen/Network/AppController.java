package ir.karentravel.karen.Network;

import android.app.Application;
import android.text.TextUtils;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


import ir.karentravel.karen.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppController  extends Application {

    public final static String TAG = AppController.class.getSimpleName();


    public final static String BASE_URL = "http://karentravel.ir/karen/index.php/api/";
    public final static String ACTORS_URL = BASE_URL + "Auth/signin/";
    public final static String ACTORS_URL = BASE_URL + "Auth/signup/";

    public final static String ACTORS_URL = BASE_URL + "Users/";
    public final static String ACTORS_URL = BASE_URL + "Users/detail/";
    public final static String ACTORS_URL = BASE_URL + "Users/update/";
    public final static String ACTORS_URL = BASE_URL + "Users/updatepass/";


    public final static String SAVE_LOGIN = "SAVE_LOGIN";


    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/irsans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
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
}