package calibrage.payzan.fragments;

import android.app.ActionBar;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.controls.CommonEditText;
import calibrage.payzan.utils.NCBTextInputLayout;

/**
 * Created by Calibrage11 on 9/27/2017.
 */

public class PayDTHFragment extends Fragment {
    private View rootView;
    private Context context;
    private NCBTextInputLayout subscriberTXT, operatorTXT, amountTXT;
    private CommonEditText subscriberEdt, amount;
    private AutoCompleteTextView operatorSpn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_dth, container, false);
        context = this.getActivity();
        // getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setViews();
        initViews();

        return rootView;
    }

    private void initViews() {
        subscriberEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    subscriberTXT.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    amountTXT.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setViews() {
        subscriberTXT = (NCBTextInputLayout) rootView.findViewById(R.id.SubscriberTXT);
        operatorTXT = (NCBTextInputLayout) rootView.findViewById(R.id.operatorTXT);
        amountTXT = (NCBTextInputLayout) rootView.findViewById(R.id.amountTXT);

        subscriberEdt = (CommonEditText) rootView.findViewById(R.id.subscriberEdt);
        amount = (CommonEditText) rootView.findViewById(R.id.amount);
        operatorSpn = (AutoCompleteTextView) rootView.findViewById(R.id.operatorSpn);

        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(HomeActivity.toolbar);
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.dth_sname));
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

    private boolean isValidateUI() {

        if(TextUtils.isEmpty(subscriberEdt.getText().toString().trim())){
            subscriberTXT.setError("enter subscriber id");
            subscriberTXT.setErrorEnabled(true);
            return true;
        }else if(TextUtils.isEmpty(operatorSpn.getText().toString().trim())){
            operatorTXT.setError("select operator");
            operatorTXT.setErrorEnabled(true);
            return true;
        }else if(TextUtils.isEmpty(amount.getText().toString().trim())){
            amountTXT.setError("enter amount");
            amountTXT.setErrorEnabled(true);
            return true;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeTab() {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("dthTag");


        if (fragment != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();

        HomeActivity.toolbar.setNavigationIcon(null);
        HomeActivity.toolbar.setTitle("");
    }
}
