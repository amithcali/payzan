package calibrage.payzan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import calibrage.payzan.BuildConfig;
import calibrage.payzan.R;
import calibrage.payzan.activities.RequestForAgent;
import calibrage.payzan.adapters.MyTransactionAdapter;
import calibrage.payzan.adapters.SingleLineDropDownAdapterMandals;
import calibrage.payzan.adapters.TransactionTypeAdapter;
import calibrage.payzan.controls.BaseFragment;
import calibrage.payzan.model.MandalModel;
import calibrage.payzan.model.TransactionResponseModel;
import calibrage.payzan.networkservice.ApiConstants;
import calibrage.payzan.networkservice.MyServices;
import calibrage.payzan.networkservice.ServiceFactory;
import calibrage.payzan.utils.SharedPrefsData;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Calibrage11 on 10/20/2017.
 */

public class MyTransactions extends BaseFragment {
    public static final String TAG = MyOrderFragment.class.getSimpleName();
    private View rootView;
    private Context context;
    private RecyclerView recyclerView, recylerview_transaction;
    private ArrayList<String> transactionStrings = new ArrayList<>();
    private Subscription mGetTransactions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_transactions, container, false);
        context = this.getActivity();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recylerview);
        recylerview_transaction = (RecyclerView) rootView.findViewById(R.id.recylerview_transaction);

        transactionStrings.add("All");
        transactionStrings.add("Paid");
        transactionStrings.add("Received");
        transactionStrings.add("Added");

        TransactionTypeAdapter transactionTypeAdapter = new TransactionTypeAdapter(context, transactionStrings);
        recylerview_transaction.setAdapter(transactionTypeAdapter);
        recylerview_transaction.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        showDialog(getActivity(), "Please Wait Loading");
        getTransactions(SharedPrefsData.getInstance(context).getWalletId(context));


        return rootView;
    }

    private void getTransactions(String walletId) {
        MyServices service = ServiceFactory.createRetrofitService(context, MyServices.class);
        mGetTransactions = service.myTransactions(ApiConstants.PASSBOOK + walletId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TransactionResponseModel>() {
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
                        hideDialog();
                    }

                    @Override
                    public void onNext(TransactionResponseModel transactionResponseModel) {
                        hideDialog();
                        MyTransactionAdapter myTransactionAdapter = new MyTransactionAdapter(context, transactionResponseModel.getListResult());
                        recyclerView.setAdapter(myTransactionAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    }
                });
    }
}
