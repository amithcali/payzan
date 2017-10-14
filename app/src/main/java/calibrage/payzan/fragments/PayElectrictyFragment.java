package calibrage.payzan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;

/**
 * Created by Calibrage11 on 9/27/2017.
 */

public class PayElectrictyFragment extends Fragment {

    private View rootView;
    private Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_pay_electrictybill, container, false);
        context = this.getActivity();
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(HomeActivity.toolbar);
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.electricity_sname));
        HomeActivity.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_new));
        HomeActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeTab();
            }
        });

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

    private void closeTab(){
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("elctricityTag");


        if (fragment != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        HomeActivity.toolbar.setTitle("");
        HomeActivity.toolbar.setNavigationIcon(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

}