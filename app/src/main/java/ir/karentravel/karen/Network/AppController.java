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

    public static final String TAG = AppController.class.getSimpleName();


    public static final String BASE_URL = "http://iava.in/mes/app/index.php/api/";
    public static final String ACTORS_URL = BASE_URL + "actor";
    public static final String DIRECTORS_URL = BASE_URL + "director";
    public static final String DECATE_URL = BASE_URL + "decade";

    public static final String MOVIE_ACTORS_URL = BASE_URL + "tag/actor/";
    public static final String MOVIE_DIRECTORS_URL = BASE_URL + "tag/director/";
    public static final String MOVIE_DECATE_URL = BASE_URL + "tag/decade/";


    public static String ID_ACTORS = "";
    public static String ID_DIRECTORS = "";


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