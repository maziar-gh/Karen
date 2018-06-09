package ir.karentravel.karen.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.karentravel.karen.R;
import ir.karentravel.karen.Utils.ExpandableTextView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bottom_bar)
    BottomNavigationBar bottomBar;
    @BindView(R.id.expandableTextView)
    at.blogc.android.views.ExpandableTextView expandableTextView;
    @BindView(R.id.imgexpand)
    ImageView imgexpand;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        bottomBarSetup();
        expenTetview();


    }

    private void expenTetview() {
        expandableTextView.setAnimationDuration(750L);
        expandableTextView.setInterpolator(new OvershootInterpolator());
        expandableTextView.setExpandInterpolator(new OvershootInterpolator());
        expandableTextView.setCollapseInterpolator(new OvershootInterpolator());
        imgexpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(expandableTextView.isExpanded())
                    imgexpand.setImageResource(R.drawable.ic_expand_more);
                else
                    imgexpand.setImageResource(R.drawable.ic_expand_less);
                expandableTextView.toggle();
            }
        });
        expandableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expandableTextView.isExpanded())
                    imgexpand.setImageResource(R.drawable.ic_expand_more);
                else
                    imgexpand.setImageResource(R.drawable.ic_expand_less);
                expandableTextView.toggle();
            }
        });
    }

    private void bottomBarSetup() {
        BottomBarItem profile = new BottomBarItem(R.drawable.ic_profile, R.string.bottom_profile);
        BottomBarItem tour = new BottomBarItem(R.drawable.ic_tour, R.string.bottom_tour);
        BottomBarItem contact = new BottomBarItem(R.drawable.ic_contact, R.string.bottom_contact);

        bottomBar
                .addTab(profile)
                .addTab(tour)
                .addTab(contact);

        bottomBar.selectTab(1, true);

        bottomBar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                //Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //only for translucent system navbar
        if (shouldAddNavigationBarPadding()) {
            bottomBar.setPadding(0, 0, 0, getSystemNavigationBarHeight());
        }
    }


    private boolean shouldAddNavigationBarPadding() {
        return atLeastKitkat() && isInPortrait() && hasSystemNavigationBar();
    }

    private boolean isInPortrait() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    private boolean atLeastKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }


    private int getSystemNavigationBarHeight() {
        Resources res = getResources();

        int id = res.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = 0;

        if (id > 0) {
            height = res.getDimensionPixelSize(id);
        }

        return height;
    }

    private boolean hasSystemNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display d = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            d.getRealMetrics(realDisplayMetrics);

            int realHeight = realDisplayMetrics.heightPixels;
            int realWidth = realDisplayMetrics.widthPixels;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            d.getMetrics(displayMetrics);

            int displayHeight = displayMetrics.heightPixels;
            int displayWidth = displayMetrics.widthPixels;

            return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
        } else {
            boolean hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            return !hasMenuKey && !hasBackKey;
        }
    }



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
