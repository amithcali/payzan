package calibrage.payzan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import calibrage.payzan.R;
import calibrage.payzan.activities.MyorderActivity;

/**
 * Created by Calibrage11 on 10/20/2017.
 */

public class TransactionMainFragment extends Fragment {

    private TabLayout tabs;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private Context context;
    private View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_transactions, container, false);
        tabs = (TabLayout)rootview.findViewById(R.id.tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager)rootview.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(0, true);
        tabs.setupWithViewPager(viewPager);
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
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        String[] strings = new String[]{"Send Money to wallet","Add Money to wallet","My Transaction"};
        for (int i = 0; i <strings.length ; i++) {
            final MyOrderFragment myBottomSheetSort = MyOrderFragment.
                    newInstance("",0);
            adapter.addFragment(myBottomSheetSort,strings[i]);
        }
        viewPager.setAdapter(adapter);
//        adapter.addFragment(myBottomSheetSort,"Billing");
//        adapter.addFragment(myBottomSheetSort,"All");
//        adapter.addFragment(myBottomSheetSort,"Shopping");



    }
}
