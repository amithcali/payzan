package calibrage.payzan.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

import calibrage.easypay.AddMoneyToWallet;
import calibrage.easypay.R;
import calibrage.easypay.login.LoginActivity;
import calibrage.easypay.user.MyorderActivity;
import calibrage.easypay.user.ProfileActivity;

/**
 * Created by Calibrage11 on 9/22/2017.
 */

public class HomeActivity extends AppCompatActivity  {


    Toolbar toolbar;

    private FrameLayout content_frame;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      //  setupToolbar();
        fragmentManager = getSupportFragmentManager();
        content_frame = (FrameLayout) findViewById(R.id.content_frame);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_frame, new HomeFragment())
                .commit();




        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper bottomNavigationViewHelper = new BottomNavigationViewHelper();
        bottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.action_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:

                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.content_frame, new HomeFragment())
                                        .commit();
//                                SpannableString s = new SpannableString(item.getTitle());
//                                s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.accent,null)), 0, s.length(), 0);
//                                item.setTitle(s);
//                                item.setIcon(R.drawable.ic_cart);
                                    break;
                            case R.id.action_login:
                                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                                startActivity(intent);
//                                Intent intent1 = new Intent(HomeActivity.this,ProfileActivity.class);
//                                startActivity(intent1);
                                break;

                            case R.id.action_wallet:
                                Intent wintent = new Intent(HomeActivity.this,AddMoneyToWallet.class);
                                startActivity(wintent);
                                break;
                            case R.id.action_offers:
                                break;
//                            case R.id.action_doctor:
//                                break;

                        }
                        return true;
                    }
                });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //    void setupToolbar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        toolbar.setTitleTextColor(Color.WHITE);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//    }


    public class BottomNavigationViewHelper {
        public void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }

}
