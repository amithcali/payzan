package calibrage.payzan.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import calibrage.payzan.R;
import calibrage.payzan.utils.CommonUtil;


/**
 * Created by Calibrage11 on 9/11/2017.
 */

public class SplashActivity extends AppCompatActivity {
    private static int TIMEOUT = 5000;
    private ImageView _img_splash;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !CommonUtil.areAllPermissionsAllowed(this, CommonUtil.PERMISSIONS_REQUIRED)) {
            ActivityCompat.requestPermissions(this, CommonUtil.PERMISSIONS_REQUIRED, CommonUtil.PERMISSION_CODE);
        }
        _img_splash = (ImageView) findViewById(R.id.img_splash);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        _img_splash.setAnimation(animation);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, TIMEOUT);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CommonUtil.PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //  Log.v(LOG_TAG, "permission granted");

                }
                break;
        }
    }

}
