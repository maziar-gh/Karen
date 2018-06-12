package ir.karentravel.karen.Activity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;

import at.blogc.android.views.ExpandableTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.karentravel.karen.Network.AppController;
import ir.karentravel.karen.R;
import ir.karentravel.karen.Utils.AboutDialog;
import ir.karentravel.karen.Utils.ContactDialog;
import ir.karentravel.karen.Utils.PassDialog;
import ir.karentravel.karen.Utils.SavePref;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_bar)
    BottomNavigationBar bottomBar;
    @BindView(R.id.expandableTextView)
    ExpandableTextView expandableTextView;
    @BindView(R.id.imgexpand)
    ImageView imgexpand;
    @BindView(R.id.frame_nottour)
    FrameLayout frameNottour;
    @BindView(R.id.frame_profile)
    FrameLayout frameProfile;
    @BindView(R.id.frame_tour)
    FrameLayout frameTour;
    @BindView(R.id.frame_contact)
    FrameLayout frameContact;
    @BindView(R.id.img_instagram)
    ImageView imgInstagram;
    @BindView(R.id.img_telegram)
    ImageView imgTelegram;
    @BindView(R.id.img_website)
    ImageView imgWebsite;
    @BindView(R.id.tv_contact1)
    TextView tvContact;
    @BindView(R.id.tv_about1)
    TextView tvAbout;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.tv_editpass)
    TextView tvEditpass;
    @BindView(R.id.tv_editprofile)
    TextView tvEditprofile;


    private SavePref save;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        save = new SavePref(this);

        bottomBarSetup();
        expenTetview();

        onClick();


    }

    private void onClick() {
        imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/touregardeshgari/");

                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");
                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            }
        });

        imgTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Gardeshgarie_karen"));
                startActivity(telegram);
            }
        });

        imgWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://karentravel.ir/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactDialog dialog = new ContactDialog(MainActivity.this);
                dialog.show();
            }
        });


        tvAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutDialog dialog = new AboutDialog(MainActivity.this);
                dialog.show();
            }
        });


        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.save(AppController.SAVE_LOGIN, "0");
                finish();
            }
        });


        tvEditpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassDialog dialog = new PassDialog(MainActivity.this);
                dialog.show();
            }
        });
    }

    private void expenTetview() {
        expandableTextView.setAnimationDuration(750L);
        expandableTextView.setInterpolator(new OvershootInterpolator());
        expandableTextView.setExpandInterpolator(new OvershootInterpolator());
        expandableTextView.setCollapseInterpolator(new OvershootInterpolator());
        imgexpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (expandableTextView.isExpanded())
                    imgexpand.setImageResource(R.drawable.ic_expand_more);
                else
                    imgexpand.setImageResource(R.drawable.ic_expand_less);
                expandableTextView.toggle();
            }
        });
        expandableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableTextView.isExpanded())
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
                switch (position) {
                    case 0:
                        showView(frameProfile);
                        break;
                    case 1:
                        showView(frameTour);
                        break;
                    case 2:
                        showView(frameContact);
                        break;
                }
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


    private void hideAll() {
        frameContact.setVisibility(View.GONE);
        frameContact.animate().alpha(0.0f);
        frameNottour.setVisibility(View.GONE);
        frameNottour.animate().alpha(0.0f);
        frameProfile.setVisibility(View.GONE);
        frameProfile.animate().alpha(0.0f);
        frameTour.setVisibility(View.GONE);
        frameTour.animate().alpha(0.0f);
    }

    private void showView(View v) {
        hideAll();
        v.setVisibility(View.VISIBLE);
        //frameTour.animate().alpha(1f);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
