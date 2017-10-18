package calibrage.payzan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import calibrage.payzan.R;
import calibrage.payzan.fragments.AddMoneyToWallet;
import calibrage.payzan.fragments.HomeFragment;

import static calibrage.payzan.utils.CommonUtil.buildCounterDrawable;


/**
 * Created by Calibrage11 on 9/22/2017.
 */

public class HomeActivity extends AppCompatActivity  {


   public  static Toolbar toolbar;

    private FrameLayout content_frame;
    private FragmentManager fragmentManager;
    private Menu Menu;
    private BottomNavigationView bottomNavigationView;

    private TextView textCartItemCount;
    int mCartItemCount = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
        fragmentManager = getSupportFragmentManager();
        content_frame = (FrameLayout) findViewById(R.id.content_frame);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new HomeFragment(),"homeTag")
                .commit();
      //  Toast.makeText(this, "testing in activity", Toast.LENGTH_SHORT).show();
       // CommonUtil.printKeyHash(this);



         bottomNavigationView = (BottomNavigationView)
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
                                toolbar.setNavigationIcon(null);
                                toolbar.setTitle("");
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
                                getSupportFragmentManager().beginTransaction()
                                        .add(R.id.content_frame, new AddMoneyToWallet(),"walletTag")
                                        .commit();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);//Menu Resource, Menu
       final MenuItem menuItem = menu.findItem(R.id.action_cart);
        //menuItem.setIcon(buildCounterDrawable(HomeActivity.this,2,  R.drawable.ic_notification));

       // final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();

        }else if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }else if(item.getItemId()==R.id.action_cart){
            //   item.setIcon(buildCounterDrawable(this,20,  R.drawable.ic_birthday));
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
        }
        // handle arrow click here

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            this.finish();
        }
       // super.onBackPressed();
    }

        void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(ContextCompat.getColor(HomeActivity.this,R.color.new_accent));
            toolbar.setTitle("f");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


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

    @Override
    protected void onResume() {
        super.onResume();
        updateBottomIcons();
    }

    private void updateBottomIcons(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("homeTag");
        if(fragment instanceof HomeFragment){
            bottomNavigationView.setSelectedItemId(R.id.action_home);
        }
    }
}
