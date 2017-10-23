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

import calibrage.payzan.R;
import calibrage.payzan.adapters.MyTransactionAdapter;

/**
 * Created by Calibrage11 on 10/20/2017.
 */

public class MyTransactions extends Fragment {
    private View rootView;
    private Context context;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_my_transactions,container,false);
        context = this.getActivity();
        recyclerView =(RecyclerView)rootView.findViewById(R.id.recylerview);

        MyTransactionAdapter myTransactionAdapter = new MyTransactionAdapter(context);
        recyclerView.setAdapter(myTransactionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        return rootView;
    }
}
