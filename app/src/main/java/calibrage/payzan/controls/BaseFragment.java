package calibrage.payzan.controls;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.utils.CommonUtil;

/*   this frgamenbt usefull for adding and back caling fragemnt*/

public class BaseFragment extends Fragment {
    private ProgressDialog mProgressDialog;

    public static final int MAIN_CONTAINER = R.id.content_frame;


    protected void popUpFromBackStack(FragmentActivity activity) {
        activity.getSupportFragmentManager().popBackStack();
    }

    public static void addFragment(FragmentActivity activity, int container, Fragment fragment,
                                   String cuurentFragmentTag, String newFragmentTag) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(cuurentFragmentTag)
                .add(container, fragment, newFragmentTag)
                .commit();
    }

    public void replaceFragment(final FragmentActivity activity, final int container, final Fragment
            fragment, final String cuurentFragmentTag, final String newFragmentTag) {
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments

              /*  closeTab(cuurentFragmentTag);*/
                FragmentTransaction fragmentTransaction = activity
                        .getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction
                        .addToBackStack(cuurentFragmentTag)
                        .add(container, fragment, newFragmentTag);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            new Handler().post(mPendingRunnable);
        }
    }
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
    public void showDialog(FragmentActivity activity, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(activity);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage(message);
        }
        if (mProgressDialog != null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
    public void closeTab(String TAG) {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(TAG);


        if (fragment != null){
            HomeActivity.toolbar.setNavigationIcon(null);
            HomeActivity.toolbar.setTitle("");
            if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                dialog.setContentView(R.layout.alert_dialouge_home);

                Button ok_btn = (Button) dialog.findViewById(R.id.ok_btn);
                Button cancel_btn = (Button) dialog.findViewById(R.id.cancel_btn);


                ok_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                //dialog.setCancelable(false);
                dialog.show();

            }
          /* getActivity().getSupportFragmentManager().popBackStackImmediate();*/
           /* getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            HomeActivity.toolbar.setNavigationIcon(null);
            HomeActivity.toolbar.setTitle("");
            CommonUtil.hideSoftKeyboard((AppCompatActivity)getActivity());*/
        }
    }
}
