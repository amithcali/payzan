package calibrage.payzan.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calibrage.payzan.R;
import calibrage.payzan.controls.BaseFragment;

/**
 * Created by Calibrage11 on 10/23/2017.
 */

public class ProfileHomeFragment extends BaseFragment {
    public static final String TAG = ProfileHomeFragment.class.getSimpleName();
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_home, container, false);

return view;
    }
}
