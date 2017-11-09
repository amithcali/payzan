package calibrage.payzan.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Calibrage11 on 11/9/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    private void initProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }


    public void showProgressDialog() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mProgressDialog == null)
                        initProgressDialog();
                    if (!mProgressDialog.isShowing())
                        mProgressDialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void hideProgressDialog() {
        runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            mProgressDialog = null;
                        }
                    }
                });
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
