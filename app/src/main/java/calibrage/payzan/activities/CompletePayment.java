package calibrage.payzan.activities;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import calibrage.payzan.R;
import calibrage.payzan.adapters.PaymentMethodsAdapter;
import calibrage.payzan.fragments.HomeFragment;
import calibrage.payzan.fragments.MobileRecharge;
import calibrage.payzan.fragments.MyOrderFragment;
import calibrage.payzan.interfaces.PaymentMethodClickListiner;

import android.support.v4.app.FragmentManager;


public class CompletePayment extends AppCompatActivity implements PaymentMethodClickListiner {
    private FrameLayout content_frame;
    private FragmentManager fragmentManager;
    private ArrayList<Pair<Integer, String>> menuPairList = new ArrayList<>();
    private RecyclerView lst_payment;
    private LinearLayoutManager mLayoutManager;
    PaymentMethodClickListiner methodClickListiner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_payment);
        fragmentManager = getSupportFragmentManager();

        mLayoutManager = new LinearLayoutManager(this);
        menuPairList.add(Pair.create(R.drawable.ic_mobile, "Saved Cards"));
        menuPairList.add(Pair.create(R.drawable.ic_electricity, "Credit Card"));
        menuPairList.add(Pair.create(R.drawable.ic_event, "Debit Card"));
        menuPairList.add(Pair.create(R.drawable.ic_sport, "Net Banking"));

        PaymentMethodsAdapter paymentMethodsAdapter = new PaymentMethodsAdapter(menuPairList, getBaseContext());
        paymentMethodsAdapter.setOnAdapterListener(this);
        lst_payment = (RecyclerView) findViewById(R.id.Lst_PaymentMethods);
        lst_payment.setLayoutManager(new GridLayoutManager(this, 4));
        lst_payment.setAdapter(paymentMethodsAdapter);


    }


    @Override
    public void onPaymentMethodClickListiner(int pos) {
        MyOrderFragment Mobile=new MyOrderFragment();
        ReplcaFragment(Mobile);

    }

    public void ReplcaFragment(android.support.v4.app.Fragment fragment) {
        fragmentManager.beginTransaction().add(R.id.content_frame, fragment).commit();
    }


}
