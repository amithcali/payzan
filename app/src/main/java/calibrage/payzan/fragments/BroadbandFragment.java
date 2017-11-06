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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.controls.BaseFragment;
import calibrage.payzan.controls.CommonEditText;
import calibrage.payzan.utils.CommonUtil;
import calibrage.payzan.utils.NCBTextInputLayout;

/**
 * Created by Calibrage11 on 10/23/2017.
 */

public class BroadbandFragment extends BaseFragment {
    public static final String TAG = BroadbandFragment.class.getSimpleName();
    private View rootView;
    private Context context;
    private CommonEditText ServiceNEdt, amountEdt;
    private NCBTextInputLayout operatorTXT,serviceNoTXT , amountTXT;
    private AutoCompleteTextView operatorSpn;
    private Button submit;
    private String operatorStr,servicesnoStr,amountStr;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_broadband, container, false);
        context = this.getActivity();
        //  android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        setViews();
        initViews();


        return rootView;
    }

    private void setViews() {

        HomeActivity.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_new));
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.broadband_sname));
        /*HomeActivity.toolbar.setTitle(getResources().getString(R.string.broadband_sname));
        HomeActivity.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_new));
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);*/
       /* setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(HomeActivity.toolbar);
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);*/
       /* HomeActivity.toolbar.setTitle(getResources().getString(R.string.broadband_sname));
        HomeActivity.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_new));*/
        /*HomeActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeTab();
            }
        });
*/
        serviceNoTXT = (NCBTextInputLayout)rootView.findViewById(R.id.serviceNoTXT);
        operatorTXT = (NCBTextInputLayout)rootView.findViewById(R.id.operatorTXT);
        amountTXT = (NCBTextInputLayout)rootView.findViewById(R.id.amountTXT);
        operatorSpn = (AutoCompleteTextView) rootView.findViewById(R.id.operatorSpn);
        ServiceNEdt = (CommonEditText) rootView.findViewById(R.id.ServiceNEdt);
        amountEdt = (CommonEditText) rootView.findViewById(R.id.amountEdt);
        submit=(Button)rootView.findViewById(R.id.submit);

        /*rootView.setFocusableInTouchMode(true);
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
*/

    }


    private void initViews(){
        operatorSpn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                operatorSpn.showDropDown();
                return false;
            }
        });
        ServiceNEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    serviceNoTXT.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        amountEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    amountTXT.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidateUi()){

                }
            }
        });


    }

    private boolean isValidateUi(){

        operatorStr=operatorSpn.getText().toString().trim();
        servicesnoStr=ServiceNEdt.getText().toString().trim();
        amountStr=amountEdt.getText().toString().trim();
        if (TextUtils.isEmpty(operatorStr))
        {
            operatorTXT.setErrorEnabled(true);
            operatorTXT.setError("please select operator");
            return false;
        }
        else if (TextUtils.isEmpty(servicesnoStr))
        {
            serviceNoTXT.setErrorEnabled(true);
            serviceNoTXT.setError("please enter serviceno");
            return false;
        }
        else if (TextUtils.isEmpty(amountStr))
        {
            amountTXT.setErrorEnabled(true);
            amountTXT.setError("please enter amount");
            return  false;
        }


        return true;
    }



    /*private void closeTab() {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("broadbandTag");
        if (fragment != null) {
            CommonUtil.hideSoftKeyboard((AppCompatActivity) getActivity());
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            HomeActivity.toolbar.setNavigationIcon(null);
            HomeActivity.toolbar.setTitle("");
        }
    }*/
}
