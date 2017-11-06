package calibrage.payzan.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.adapters.GenericAdapter;
import calibrage.payzan.controls.BaseFragment;
import calibrage.payzan.controls.CommonEditText;
import calibrage.payzan.interfaces.DrawableClickListener;
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

/**
 * Created by Calibrage11 on 9/28/2017.
 */

public class PayLandLineBill extends BaseFragment implements GenericAdapter.AdapterOnClick {
    public static final String TAG = PayLandLineBill.class.getSimpleName();
    private View rootView;
    private Context context;
    private NCBTextInputLayout operatorTXT, numberTXT, circleTXT, amountTXT;
    private calibrage.payzan.controls.CommonEditText mobilenoEdt, amount;
    private AutoCompleteTextView circleEdt, operatorEdt;
    static final int PICK_CONTACT = 1;
    private Subscription operatorSubscription;
    private ArrayList<OperatorModel.ListResult> listResults;
    private Button submit;
    private String operatorStr,mobilenoStr,circleStr,amountStr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CommonUtil.adjustSoftKeyboard(getActivity().getWindow());
        ;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_paylandline_bill, container, false);
        context = this.getActivity();
        setViews();
        initViews();
        getOperator(CommonConstants.SERVICE_PROVIDER_ID_LANDLINE);
        return rootView;
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
                        mobilenoEdt.setText(hasPhone);
                    }

                }
                break;
        }
    }

    private void initViews() {
        operatorEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                operatorEdt.showDropDown();
                return false;
            }
        });
        operatorEdt.addTextChangedListener(new TextWatcher() {
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
        mobilenoEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    numberTXT.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        circleEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    circleTXT.setErrorEnabled(false);
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

        mobilenoEdt.setDrawableClickListener(new DrawableClickListener() {


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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidateUi()){

                }
            }
        });
    }

    private void setViews() {

        setHasOptionsMenu(true);

        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.landline_sname));
        /*((AppCompatActivity) getActivity()).setSupportActionBar(HomeActivity.toolbar);
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.landline_sname));
        HomeActivity.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_new));*/
       /* HomeActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeTab();
            }
        });*/
        operatorTXT = (NCBTextInputLayout) rootView.findViewById(R.id.operatorTXT);
        numberTXT = (NCBTextInputLayout) rootView.findViewById(R.id.numberTXT);
        circleTXT = (NCBTextInputLayout) rootView.findViewById(R.id.circleTXT);
        amountTXT = (NCBTextInputLayout) rootView.findViewById(R.id.amountTXT);

        operatorEdt = (AutoCompleteTextView) rootView.findViewById(R.id.operatorEdt);
        mobilenoEdt = (CommonEditText) rootView.findViewById(R.id.mobilenoEdt);
        circleEdt = (AutoCompleteTextView) rootView.findViewById(R.id.circleEdt);
        amount = (CommonEditText) rootView.findViewById(R.id.amount);
        submit = (Button) rootView.findViewById(R.id.submit);
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
        });*/
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
                            genericAdapter.setAdapterOnClick(PayLandLineBill.this);
                            operatorEdt.setAdapter(genericAdapter);
                        }
                    });
        }
    }
    private void closeTab() {
        /*Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("landlineTag");


        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            HomeActivity.toolbar.setNavigationIcon(null);
            HomeActivity.toolbar.setTitle("");
            CommonUtil.hideSoftKeyboard((AppCompatActivity) getActivity());
        }*/
    }

    private boolean isValidateUi() {
        operatorStr=operatorEdt.getText().toString().trim();
        mobilenoStr=mobilenoEdt.getText().toString().trim();
        circleStr =circleEdt.getText().toString().trim();
        amountStr=  amount.getText().toString().trim();
        if (TextUtils.isEmpty(operatorStr)) {
            operatorTXT.setErrorEnabled(true);
            operatorTXT.setError("please select operator");
            return false;
        } else if (TextUtils.isEmpty(mobilenoStr)) {
            numberTXT.setError("enter number");
            numberTXT.setErrorEnabled(true);
            return false;
        } else if (TextUtils.isEmpty(circleStr)) {
            circleTXT.setError("select circle");
            circleTXT.setErrorEnabled(true);
            return false;
        } else if (TextUtils.isEmpty(amountStr)) {
            amountTXT.setError("enter amount");
            amountTXT.setErrorEnabled(true);
            return false;
        }
        return true;
    }

    @Override
    public void adapterOnClick(int position) {
        operatorEdt.setText(listResults.get(position).getName());
    }
}

