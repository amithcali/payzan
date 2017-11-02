package calibrage.payzan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.activities.MyorderActivity;
import calibrage.payzan.controls.BaseFragment;
import calibrage.payzan.interfaces.CommunicateFragments;
import calibrage.payzan.interfaces.OnChildFragmentInteractionListener;
import calibrage.payzan.interfaces.OnChildFragmentToActivityInteractionListener;

/**
 * Created by Calibrage11 on 10/20/2017.
 */

public class TransactionMainFragment extends BaseFragment {
    public static final String TAG = TransactionMainFragment.class.getSimpleName();
    private TabLayout tabs;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private Context context;
    private View rootview;
    private int currentItem;
  //  private CommunicateFragments  communicateFragments;
    private OnChildFragmentToActivityInteractionListener mActivityListener;
    private OnChildFragmentInteractionListener mParentListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            currentItem = bundle.getInt("position", 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_transactions, container, false);
        context = getActivity();
        ((AppCompatActivity)getActivity()).setSupportActionBar(HomeActivity.toolbar);
        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.wallet_sname));
        HomeActivity.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_new));
        HomeActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeTab();
            }
        });
        tabs = (TabLayout)rootview.findViewById(R.id.tabs);
       // tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager)rootview.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
     //   viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(currentItem, true);
        tabs.setupWithViewPager(viewPager);

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
        return rootview;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }


    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        String[] strings = new String[]{"Send Money to wallet","Add Money to wallet","My Transaction"};
//        for (int i = 0; i <strings.length ; i++) {
//            final MyOrderFragment myBottomSheetSort = MyOrderFragment.
//                    newInstance("",0);
//            adapter.addFragment(myBottomSheetSort,strings[i]);
//        }

        adapter.addFragment(new SendMoneyToWallet(),strings[0]);
        adapter.addFragment(new AddMoneyToWallet(),strings[1]);
        adapter.addFragment(new MyTransactions(),strings[2]);
        viewPager.setAdapter(adapter);



    }

//    public void setFragmentCommunication(CommunicateFragments  fragmentCommunication){
//        this.communicateFragments = fragmentCommunication;
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // check if Activity implements listener
        if (context instanceof OnChildFragmentToActivityInteractionListener) {
            mActivityListener = (OnChildFragmentToActivityInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnChildFragmentToActivityInteractionListener");
        }

        // check if parent Fragment implements listener
//        if (getActivity().getSupportFragmentManager().findFragmentByTag("walletTag") instanceof OnChildFragmentInteractionListener) {
//
//            mParentListener = (OnChildFragmentInteractionListener) getParentFragment();
//        } else {
//            throw new RuntimeException("The parent fragment must implement OnChildFragmentInteractionListener");
//        }
    }

    private void closeTab(){


        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("walletTag");
       // communicateFragments.onFragmentInteraction("");

        mActivityListener.messageFromChildFragmentToActivity("handleBottomNavigation");

        if (fragment != null)
        {
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            HomeActivity.toolbar.setNavigationIcon(null);
            HomeActivity.toolbar.setTitle("");

        }

    }
}
