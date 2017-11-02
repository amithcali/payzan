package calibrage.payzan.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calibrage.payzan.R;
import calibrage.payzan.controls.BaseFragment;

/**
 * Created by Calibrage11 on 10/16/2017.
 */

public class SelectOperatorFragment extends BaseFragment {
    public static final String TAG = SelectOperatorFragment.class.getSimpleName();
    private View rootView;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.select_operator_fragment,container,false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recylerview);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
