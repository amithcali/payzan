package calibrage.payzan.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.activities.LoginActivity;
import calibrage.payzan.controls.BaseFragment;
import calibrage.payzan.model.LoginModel;
import calibrage.payzan.model.LoginResponseModel;
import calibrage.payzan.model.PostWalletModel;
import calibrage.payzan.model.WalletResponse;
import calibrage.payzan.networkservice.MyServices;
import calibrage.payzan.networkservice.ServiceFactory;
import calibrage.payzan.utils.CommonConstants;
import calibrage.payzan.utils.CommonUtil;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static calibrage.payzan.fragments.HomeFragment.walletTxt;

/**
 * Created by Calibrage11 on 9/25/2017.
 */

public class AddMoneyToWallet extends BaseFragment {
    public static final String TAG = AddMoneyToWallet.class.getSimpleName();
    private EditText enterMoneyEdt, enterpromocodeEdt;
    private Button submit;
    private Toolbar toolbar;
    private View rootView;
    private Context context;
    private AlertDialog alertDialog;
    private Subscription mRegisterSubscription;
    private TextView addHundTxt, addfiveTxt, addthouTxt;
    private int addMoney;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_add_money_to_wallet, container, false);
        context = this.getActivity();

        setHasOptionsMenu(true);

        setViews();
        initViews();


        return rootView;
    }

    private void setViews() {

        enterMoneyEdt = (EditText) rootView.findViewById(R.id.amount);
        addHundTxt = (TextView) rootView.findViewById(R.id.addHundTxt);

        addfiveTxt = (TextView) rootView.findViewById(R.id.addfiveTxt);
        addthouTxt = (TextView) rootView.findViewById(R.id.addthouTxt);
        enterpromocodeEdt = (EditText) rootView.findViewById(R.id.promocode);
        submit = (Button) rootView.findViewById(R.id.submit);

    }

    private void initViews() {

        addHundTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addMoney(100);
            }
        });
        addfiveTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoney(500);
            }
        });
        addthouTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoney(1000);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (enterMoneyEdt.getText().toString().equalsIgnoreCase("")) {
                    CommonUtil.displayDialogWindow("please enter amount", alertDialog, context);
                } else {
                    addWallet();
                }

            }
        });

    }

    private void addMoney(int amount) {

        try {
            int value = Integer.parseInt("0"+enterMoneyEdt.getText().toString());
            addMoney = value + amount;
        }catch (Exception e){

        }
        enterMoneyEdt.setText(String.valueOf(addMoney));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    private void addWallet() {
        JsonObject object = postWalletObject();
        MyServices service = ServiceFactory.createRetrofitService(context, MyServices.class);
        mRegisterSubscription = service.UserAddWallet(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WalletResponse>() {
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
                    public void onNext(WalletResponse walletResponse) {
                        Toast.makeText(context, "sucess", Toast.LENGTH_SHORT).show();
                        // finish();
                        CommonConstants.WALLETMONEY = String.valueOf(walletResponse.getResult().getBalance());
                        CommonUtil.displayDialogWindow("Wallet is Updated Sucessfully", alertDialog, context);
                        walletTxt.setText(CommonConstants.WALLETMONEY);
                        //  closeTab();
                    }
                });
    }

    private JsonObject postWalletObject() {
        PostWalletModel postWalletModel = new PostWalletModel();
        postWalletModel.setAmount(Integer.parseInt(enterMoneyEdt.getText().toString()));
        postWalletModel.setUpdatedByUserId(CommonConstants.USERID);
        postWalletModel.setCreatedByUserId(CommonConstants.USERID);
        postWalletModel.setWalletId(CommonConstants.WALLETID);
        postWalletModel.setReasonTypeId(20);
        postWalletModel.setTransactionTypeId(18);

        return new Gson().toJsonTree(postWalletModel)
                .getAsJsonObject();
    }


}
