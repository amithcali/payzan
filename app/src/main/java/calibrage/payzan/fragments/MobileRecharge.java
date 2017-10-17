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
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.activities.LoginActivity;
import calibrage.payzan.adapters.GenericAdapter;
import calibrage.payzan.adapters.SingleLineDropDownAdapter;
import calibrage.payzan.model.LoginResponseModel;
import calibrage.payzan.model.OperatorModel;
import calibrage.payzan.networkservice.ApiConstants;
import calibrage.payzan.networkservice.MyServices;
import calibrage.payzan.networkservice.ServiceFactory;
import calibrage.payzan.utils.CommonConstants;
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

public class MobileRecharge extends Fragment {

    private RadioButton prepaidRB, postpaidRB;
    private Button
            talktimeRB, specialRB;
    private ImageView mobileNumber;
    static final int PICK_CONTACT = 1;
    private EditText mobileEdt;
    private Toolbar toolbar;
    private View rootview;
    private Context context;
    private TextView updateOperatorId;
    private AutoCompleteTextView currentOperator;
    private Subscription operatorSubscription;
    private ArrayList<OperatorModel.ListResult> listResults;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.activity_mobile_recharge, container, false);
        context = this.getActivity();
        //  android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        setHasOptionsMenu(true);
        listResults = new ArrayList<OperatorModel.ListResult>();
        ((AppCompatActivity) getActivity()).setSupportActionBar(HomeActivity.toolbar);
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.mobile_recharge_sname));
        HomeActivity.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_new));
        HomeActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeTab();
            }
        });
        mobileNumber = (ImageView) rootview.findViewById(R.id.mobileNumber);
        mobileEdt = (EditText) rootview.findViewById(R.id.mobileEdt);
        currentOperator = (AutoCompleteTextView) rootview.findViewById(R.id.currentOperator);
        talktimeRB = (Button) rootview.findViewById(R.id.talktimeRB);
        specialRB = (Button) rootview.findViewById(R.id.specialRB);
        prepaidRB = (RadioButton) rootview.findViewById(R.id.prepaidRB);
        postpaidRB = (RadioButton) rootview.findViewById(R.id.postpaidRB);
        postpaidRB = (RadioButton) rootview.findViewById(R.id.postpaidRB);
        updateOperatorId = (TextView) rootview.findViewById(R.id.updateOperatorId);
        setHasOptionsMenu(true);

        talktimeRB.setBackgroundResource(R.drawable.roundbutton);
        talktimeRB.setTextColor(ContextCompat.getColor(context, R.color.white_new));
        specialRB.setTextColor(ContextCompat.getColor(context, R.color.accent));

        prepaidRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        postpaidRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        updateOperatorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOperator(CommonConstants.SERVICE_PROVIDER_ID_POSTPAID);
            }
        });


        // currentOperator.setAdapter();


        rootview.setFocusableInTouchMode(true);
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
        });

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
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

        return rootview;
    }

    private void getOperator(String providerType) {

        MyServices service = ServiceFactory.createRetrofitService(context, MyServices.class);
        operatorSubscription = service.getOperator(ApiConstants.MOBILE_SERVICES+providerType)
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

//                        listResults = (ArrayList<OperatorModel.ListResult>) operatorModel.getListResult();
//                        ArrayAdapter<OperatorModel.ListResult> listResultArrayAdapter = new ArrayAdapter<OperatorModel.ListResult>(context,android.R.layout.simple_dropdown_item_1line,listResults);
//                        currentOperator.setAdapter(listResultArrayAdapter);
                        GenericAdapter genericAdapter = new GenericAdapter(context,operatorModel.getListResult(),R.layout.adapter_single_item);
                        currentOperator.setAdapter(genericAdapter);
                    }
                });
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
                        mobileEdt.setText(hasPhone);
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
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("mobileTag");


        if (fragment != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();

        HomeActivity.toolbar.setNavigationIcon(null);
        HomeActivity.toolbar.setTitle("");
    }
}