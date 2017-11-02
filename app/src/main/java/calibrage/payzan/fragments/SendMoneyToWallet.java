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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.activities.SendMoney;
import calibrage.payzan.controls.BaseFragment;
import calibrage.payzan.controls.CommonButton;
import calibrage.payzan.controls.CommonEditText;
import calibrage.payzan.interfaces.DrawableClickListener;
import calibrage.payzan.model.LoginResponseModel;
import calibrage.payzan.model.SendMoneyModel;
import calibrage.payzan.model.SendMoneyResponseModel;
import calibrage.payzan.model.UserWalletHistory;
import calibrage.payzan.networkservice.MyServices;
import calibrage.payzan.networkservice.ServiceFactory;
import calibrage.payzan.utils.CommonConstants;
import calibrage.payzan.utils.NCBTextInputLayout;
import calibrage.payzan.utils.SharedPrefsData;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Calibrage11 on 10/20/2017.
 */

public class SendMoneyToWallet extends BaseFragment {
    public static final String TAG = SendMoneyToWallet.class.getSimpleName();
    private View rootView;
    private Context context;
    private NCBTextInputLayout mobileNumberTXT,amountTXT,commentTXT;
    private CommonEditText mobileEdt,amount,commentEdt;
    static final int PICK_CONTACT = 1;
    private CommonButton submitBtn;
    private Subscription sendMoneySubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_send_money,container,false);
        context = this.getActivity();
        setViews();
        initViews();
        return rootView;
    }

    private void initViews() {


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

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMoneyRequest();
            }
        });


    }

    private void sendMoneyRequest() {

        JsonObject object = postJsonObject();

        MyServices service = ServiceFactory.createRetrofitService(getActivity(), MyServices.class);
        sendMoneySubscription = service.sendMoneyRequest(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SendMoneyResponseModel>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getActivity(), "check", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(SendMoneyResponseModel sendMoneyResponseModel) {
                        Toast.makeText(getActivity(), "sucess", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private JsonObject postJsonObject() {
        SendMoneyModel sendMoney = new SendMoneyModel();
        sendMoney.setRecieverUserName(mobileEdt.getText().toString());
        UserWalletHistory userWalletHistory = new UserWalletHistory();
        userWalletHistory.setAmount(Double.valueOf(amount.getText().toString()));
        userWalletHistory.setCreated("");
        userWalletHistory.setCreatedBy(SharedPrefsData.getInstance(context).getUserId(context));
        userWalletHistory.setId(0);
        userWalletHistory.setModified("");
        userWalletHistory.setModifiedBy(SharedPrefsData.getInstance(context).getUserId(context));
        userWalletHistory.setReasonTypeId(20);
        userWalletHistory.setTransactionTypeId(18);
        userWalletHistory.setIsActive(true);
        userWalletHistory.setWalletId(SharedPrefsData.getInstance(context).getWalletId(context));
        sendMoney.setUserWalletHistory(userWalletHistory);
         return new Gson().toJsonTree(sendMoney)
                .getAsJsonObject();
    }

    private void setViews() {

        mobileNumberTXT = (NCBTextInputLayout)rootView.findViewById(R.id.mobileNumberTXT);
        amountTXT = (NCBTextInputLayout)rootView.findViewById(R.id.amountTXT);
        commentTXT = (NCBTextInputLayout)rootView.findViewById(R.id.commentTXT);
        mobileEdt = (CommonEditText) rootView.findViewById(R.id.mobileEdt);
        amount = (CommonEditText) rootView.findViewById(R.id.amount);
        commentEdt = (CommonEditText) rootView.findViewById(R.id.commentEdt);
        submitBtn = (CommonButton) rootView.findViewById(R.id.submit);
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
}
