package calibrage.payzan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.controls.CommonEditText;
import calibrage.payzan.utils.NCBTextInputLayout;

/**
 * Created by Calibrage11 on 9/27/2017.
 */

public class PayElectrictyFragment extends Fragment {

    private View rootView;
    private Context context;
    private NCBTextInputLayout districtTXT, serviceNoTXT, amountTXT;
    private CommonEditText amountEdt, ServiceNEdt;
    private AutoCompleteTextView districtSpn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_pay_electrictybill, container, false);
        context = this.getActivity();

        setViews();
        initViews();

        return rootView;
    }

    private void setViews() {
        districtTXT = (NCBTextInputLayout) rootView.findViewById(R.id.districtTXT);
        serviceNoTXT = (NCBTextInputLayout) rootView.findViewById(R.id.serviceNoTXT);
        amountTXT = (NCBTextInputLayout) rootView.findViewById(R.id.amountTXT);
        amountEdt = (CommonEditText) rootView.findViewById(R.id.amountEdt);
        ServiceNEdt = (CommonEditText) rootView.findViewById(R.id.ServiceNEdt);
        districtSpn = (AutoCompleteTextView) rootView.findViewById(R.id.districtSpn);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(HomeActivity.toolbar);
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
    }

    private void initViews() {
        districtSpn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    districtTXT.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ServiceNEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    serviceNoTXT.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        amountEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    amountTXT.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private boolean isValidateUi() {

        if(TextUtils.isEmpty(districtSpn.getText().toString().trim())){
            districtTXT.setError("select district");
            districtTXT.setErrorEnabled(true);
            return false;
        }else if(TextUtils.isEmpty(ServiceNEdt.getText().toString().trim())){
            serviceNoTXT.setError("enter service no");
            serviceNoTXT.setErrorEnabled(true);
            return false;
        }else if(TextUtils.isEmpty(amountEdt.getText().toString().trim())){
            amountTXT.setError("enter amount");
            amountTXT.setErrorEnabled(true);
            return false;
        }
        return true;
    }

    private void closeTab() {
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