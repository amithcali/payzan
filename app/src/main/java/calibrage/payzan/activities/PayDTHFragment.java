package calibrage.payzan.activities;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import calibrage.payzan.R;

/**
 * Created by Calibrage11 on 9/27/2017.
 */

public class PayDTHFragment extends Fragment {
    private View rootView;
    private Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).setSupportActionBar(HomeActivity.toolbar);
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeTab();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_dth, container, false);
        context = this.getActivity();
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    closeTab();
                    // onCloseFragment();
                    return true;
                } else {
                    return false;
                }
            }
        });
        return  rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeTab(){
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("dthTag");


        if (fragment != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();

        HomeActivity.toolbar.setNavigationIcon(null);
    }
}