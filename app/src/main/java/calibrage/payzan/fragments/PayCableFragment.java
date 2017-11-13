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
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.adapters.GenericAdapter;
import calibrage.payzan.controls.BaseFragment;
import calibrage.payzan.controls.CommonEditText;
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
 * Created by Calibrage11 on 10/13/2017.
 */

public class PayCableFragment extends BaseFragment implements GenericAdapter.AdapterOnClick {
    public static final String TAG = PayCableFragment.class.getSimpleName();
    private View rootView;
    private Context context;
    private NCBTextInputLayout operatorTXT, amountTXT, accontNoTXT;
    private AutoCompleteTextView operatorSpn;
    private CommonEditText accontNoEdt, amountEdt;
    private Subscription operatorSubscription;
    private ArrayList<OperatorModel.ListResult> listResults;
    private Button submit;
    private  String operatorStr,accontNoStr,amountStr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cable, container, false);
        context = this.getActivity();
        setViews();
        initViews();
        getOperator(CommonConstants.SERVICE_PROVIDER_ID_CABLEBILL);

        return rootView;
    }

    private void setViews() {
        operatorTXT = (NCBTextInputLayout) rootView.findViewById(R.id.operatorTXT);
        accontNoTXT = (NCBTextInputLayout) rootView.findViewById(R.id.accontNoTXT);
        accontNoEdt = (CommonEditText) rootView.findViewById(R.id.accontNoEdt);
        amountEdt = (CommonEditText) rootView.findViewById(R.id.amountEdt);
        amountTXT = (NCBTextInputLayout) rootView.findViewById(R.id.amountTXT);
        operatorSpn = (AutoCompleteTextView) rootView.findViewById(R.id.operatorSpn);
        submit=(Button)rootView.findViewById(R.id.submit);
       /* setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(HomeActivity.toolbar);*/
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.cabletv_sname));
        /*HomeActivity.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_new));
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
        });*/
    }


    private void initViews() {

        accontNoEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    accontNoTXT.setErrorEnabled(false);

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

        operatorSpn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    operatorTXT.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        operatorSpn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                operatorSpn.showDropDown();


                return false;
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidateUi())
                {

                }
            }
        });
    }

    private boolean isValidateUi() {
        operatorStr=operatorSpn.getText().toString().trim();
        accontNoStr =accontNoEdt.getText().toString().trim();
        amountStr=amountEdt.getText().toString().trim();
        if (TextUtils.isEmpty(operatorStr)) {
            operatorTXT.setError("select operator");
            operatorTXT.setErrorEnabled(true);
            return true;
        } else if (TextUtils.isEmpty(accontNoStr)) {
            accontNoTXT.setError("Enter account no");
            accontNoTXT.setErrorEnabled(true);
            return true;
        } else if (TextUtils.isEmpty(amountStr)) {
            amountTXT.setError("Enter amount");
            amountTXT.setErrorEnabled(true);
            return true;
        }
        return true;
    }

    private void getOperator(String providerType) {
if(CommonUtil.isNetworkAvailable(context)){


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
                        genericAdapter.setAdapterOnClick(PayCableFragment.this);
                        operatorSpn.setAdapter(genericAdapter);;
                    }
                });
    }}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeTab() {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("cableTag");


        if (fragment != null){
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            HomeActivity.toolbar.setNavigationIcon(null);
            HomeActivity.toolbar.setTitle("");
            CommonUtil.hideSoftKeyboard((AppCompatActivity)getActivity());
        }
    }

    @Override
    public void adapterOnClick(int position) {
        operatorSpn.setText(listResults.get(position).getName());

    }
}

