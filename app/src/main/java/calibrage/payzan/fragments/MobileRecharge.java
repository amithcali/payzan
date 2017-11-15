package calibrage.payzan.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.activities.LoginActivity;
import calibrage.payzan.adapters.GenericAdapter;
import calibrage.payzan.adapters.SingleLineDropDownAdapter;
import calibrage.payzan.controls.BaseFragment;
import calibrage.payzan.controls.CommonEditText;
import calibrage.payzan.interfaces.DrawableClickListener;
import calibrage.payzan.model.LoginResponseModel;
import calibrage.payzan.model.OperatorModel;
import calibrage.payzan.networkservice.ApiConstants;
import calibrage.payzan.networkservice.MyServices;
import calibrage.payzan.networkservice.ServiceFactory;
import calibrage.payzan.utils.CommonConstants;
import calibrage.payzan.utils.CommonUtil;
import calibrage.payzan.utils.NCBTextInputLayout;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.R.attr.fragment;
import static calibrage.payzan.utils.CommonUtil.buildCounterDrawable;

/**
 * Created by Calibrage11 on 10/2/2017.
 */

public class MobileRecharge extends BaseFragment implements GenericAdapter.AdapterOnClick {
    public static final String TAG = MobileRecharge.class.getSimpleName();
    private RadioButton prepaidRB, postpaidRB;
    private Button
            talktimeRB, specialRB, submit;
    private ImageView mobileNumber;
    static final int PICK_CONTACT = 1;
    private calibrage.payzan.controls.CommonEditText mobileEdt, amount;
    private Toolbar toolbar;
    private View rootview;
    private Context context;
    private TextView updateOperatorId;
    private AutoCompleteTextView currentOperator;
    private Subscription operatorSubscription;
    private ArrayList<OperatorModel.ListResult> listResults;
    private String serviceProviderType;
    private NCBTextInputLayout mobileNumberTXT, operatorTXT, amountTXT;
    private Boolean isProvider = false;
    private String mobileStr,currentOperatorStr,amountStr;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.activity_mobile_recharge, container, false);
        context = this.getActivity();
        //  android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        setViews();
        initViews();


        return rootview;
    }

    private void initViews() {

        prepaidRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceProviderType = CommonConstants.SERVICE_PROVIDER_ID_PREPAID;
                getOperator(serviceProviderType);
            }
        });

        postpaidRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceProviderType = CommonConstants.SERVICE_PROVIDER_ID_POSTPAID;
                getOperator(serviceProviderType);
            }
        });

        updateOperatorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentOperator.showDropDown();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUI();

            }
        });
        currentOperator.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                currentOperator.showDropDown();



                return false;
            }
        });
        mobileEdt.setDrawableClickListener(new DrawableClickListener() {


            public void onClick(DrawablePosition target) {
                switch (target) {
                    case RIGHT:
                        //Do something here
                        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                        startActivityForResult(intent, PICK_CONTACT);

                        break;

                    default:
                        break;
                }
            }

        });

        mobileEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    mobileNumberTXT.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        currentOperator.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    operatorTXT.setErrorEnabled(false);
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

        // currentOperator.setAdapter();


       /* rootview.setFocusableInTouchMode(true);
        rootview.requestFocus();
        rootview.setOnKeyListener(new View.OnKeyListener() {
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
        });*/

        talktimeRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                talktimeRB.setBackgroundResource(R.drawable.roundbutton);
                talktimeRB.setTextColor(ContextCompat.getColor(context, R.color.white_new));
                specialRB.setBackgroundResource(R.color.white_new);
                specialRB.setTextColor(ContextCompat.getColor(context, R.color.accent));

            }
        });

        specialRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                specialRB.setBackgroundResource(R.drawable.roundbutton);
                specialRB.setTextColor(ContextCompat.getColor(context, R.color.white_new));
                // talktimeRB.setBackgroundResource(R.drawable.border_accent);
                talktimeRB.setBackgroundColor(ContextCompat.getColor(context, R.color.white_new));
                talktimeRB.setTextColor(ContextCompat.getColor(context, R.color.accent));

            }
        });

        mobileNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setViews() {
        setHasOptionsMenu(true);
        listResults = new ArrayList<OperatorModel.ListResult>();
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.mobile_recharge_sname));
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        /*((AppCompatActivity) getActivity()).setSupportActionBar(HomeActivity.toolbar);
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.mobile_recharge_sname));
        HomeActivity.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_new));*/
       /* HomeActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeTab(TAG);
               *//* closeTab();*//*
            }
        });*/
        mobileNumber = (ImageView) rootview.findViewById(R.id.mobileNumber);
        mobileEdt = (CommonEditText) rootview.findViewById(R.id.mobileEdt);
        amount = (CommonEditText) rootview.findViewById(R.id.amount);
        currentOperator = (AutoCompleteTextView) rootview.findViewById(R.id.currentOperator);
        talktimeRB = (Button) rootview.findViewById(R.id.talktimeRB);
        specialRB = (Button) rootview.findViewById(R.id.specialRB);
        prepaidRB = (RadioButton) rootview.findViewById(R.id.prepaidRB);
        postpaidRB = (RadioButton) rootview.findViewById(R.id.postpaidRB);
        postpaidRB = (RadioButton) rootview.findViewById(R.id.postpaidRB);
        updateOperatorId = (TextView) rootview.findViewById(R.id.updateOperatorId);
        submit = (Button) rootview.findViewById(R.id.submit);
        mobileNumberTXT = (NCBTextInputLayout) rootview.findViewById(R.id.mobileNumberTXT);
        operatorTXT = (NCBTextInputLayout) rootview.findViewById(R.id.operatorTXT);
        amountTXT = (NCBTextInputLayout) rootview.findViewById(R.id.amountTXT);
        setHasOptionsMenu(true);
        currentOperator.setThreshold(1);

        talktimeRB.setBackgroundResource(R.drawable.roundbutton);
        talktimeRB.setTextColor(ContextCompat.getColor(context, R.color.white_new));
        specialRB.setTextColor(ContextCompat.getColor(context, R.color.accent));
        serviceProviderType = CommonConstants.SERVICE_PROVIDER_ID_PREPAID;
        prepaidRB.setChecked(true);
        getOperator(CommonConstants.SERVICE_PROVIDER_ID_PREPAID);
    }

    private void getOperator(String providerType) {
        if (CommonUtil.isNetworkAvailable(context)) {
            MyServices service = ServiceFactory.createRetrofitService(context, MyServices.class);
            operatorSubscription = service.getOperator(ApiConstants.MOBILE_SERVICES + providerType)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<OperatorModel>() {
                        @Override
                        public void onCompleted() {
                            Toast.makeText(context, "check", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof HttpException) {
                                ((HttpException) e).code();
                                ((HttpException) e).message();
                                ((HttpException) e).response().errorBody();
                                try {
                                    ((HttpException) e).response().errorBody().string();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                e.printStackTrace();
                            }
                            Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(OperatorModel operatorModel) {

                            listResults = (ArrayList<OperatorModel.ListResult>) operatorModel.getListResult();
//                        ArrayAdapter<OperatorModel.ListResult> listResultArrayAdapter = new ArrayAdapter<OperatorModel.ListResult>(context,android.R.layout.simple_dropdown_item_1line,listResults);
//                        currentOperator.setAdapter(listResultArrayAdapter);


                            GenericAdapter genericAdapter = new GenericAdapter(context, operatorModel.getListResult(), R.layout.adapter_single_item);
                            genericAdapter.setAdapterOnClick(MobileRecharge.this);
                            currentOperator.setAdapter(genericAdapter);
                        }
                    });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = context.getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        mobileEdt.setText(hasPhone.replaceAll("\\s",""));
                    }

                }
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void closeTab() {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(TAG);


        if (fragment != null){
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            HomeActivity.toolbar.setNavigationIcon(null);
            HomeActivity.toolbar.setTitle("");
            CommonUtil.hideSoftKeyboard((AppCompatActivity)getActivity());
        }
    }

    private boolean validateUI() {
        isProviderExists();
        mobileStr = mobileEdt.getText().toString().trim();
        currentOperatorStr= currentOperator.getText().toString().trim();
        amountStr=amount.getText().toString().trim();

        if (TextUtils.isEmpty(mobileStr)) {
            mobileNumberTXT.setErrorEnabled(true);
            mobileNumberTXT.setError("Enter mobile number");

        }
        else if (!isValidPhone())
        {
            mobileNumberTXT.setErrorEnabled(true);
            mobileNumberTXT.setError("Enter valid mobile no");
            return false;
        }
        else if (TextUtils.isEmpty(currentOperatorStr)) {
            operatorTXT.setErrorEnabled(true);
            operatorTXT.setError("Select operator ");
        } else if (!isProvider) {
            operatorTXT.setErrorEnabled(true);
            operatorTXT.setError("your  operator is not valid ");
        } else if (amountStr.equalsIgnoreCase("")) {
            amountTXT.setErrorEnabled(true);
            amountTXT.setError("Enter amount");
        }
        return true;

    }
    private boolean isValidPhone()
    {
        String target=mobileEdt.getText().toString().trim();
        if (target.length()!=10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }

    private void isProviderExists() {
        ArrayList<String> operator = new ArrayList<>();
        for (int i = 0; i < listResults.size(); i++) {
            operator.add(listResults.get(i).getName());
        }
        for (int i = 0; i < operator.size(); i++) {
            if (operator.get(i).matches(currentOperator.getText().toString().trim())) ;
            {
                isProvider = true;
                break;
            }
        }


    }

    @Override
    public void adapterOnClick(int position) {
        currentOperator.setText(listResults.get(position).getName());

    }
}