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

import java.util.ArrayList;

import calibrage.payzan.R;
import calibrage.payzan.adapters.MyTransactionAdapter;
import calibrage.payzan.adapters.TransactionTypeAdapter;
import calibrage.payzan.controls.BaseFragment;

/**
 * Created by Calibrage11 on 10/20/2017.
 */

public class MyTransactions extends BaseFragment {
    public static final String TAG = MyOrderFragment.class.getSimpleName();
    private View rootView;
    private Context context;
    private RecyclerView recyclerView,recylerview_transaction;
    private ArrayList<String> transactionStrings = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_my_transactions,container,false);
        context = this.getActivity();
        recyclerView =(RecyclerView)rootView.findViewById(R.id.recylerview);
        recylerview_transaction =(RecyclerView)rootView.findViewById(R.id.recylerview_transaction);

        transactionStrings.add("All");
        transactionStrings.add("Paid");
        transactionStrings.add("Received");
        transactionStrings.add("Added");

        TransactionTypeAdapter transactionTypeAdapter = new TransactionTypeAdapter(context,transactionStrings);
        recylerview_transaction.setAdapter(transactionTypeAdapter);
        recylerview_transaction.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        MyTransactionAdapter myTransactionAdapter = new MyTransactionAdapter(context);
        recyclerView.setAdapter(myTransactionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        return rootView;
    }
}
