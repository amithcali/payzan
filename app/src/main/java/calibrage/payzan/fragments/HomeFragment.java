package calibrage.payzan.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import calibrage.payzan.R;
import calibrage.payzan.activities.RequestForAgent;
import calibrage.payzan.activities.SendMoney;
import calibrage.payzan.adapters.BannerAdapter;
import calibrage.payzan.adapters.RechargeAdapter;
import calibrage.payzan.interfaces.RechargeClickListiner;
import calibrage.payzan.utils.CommonUtil;


/**
 * Created by Calibrage11 on 9/23/2017.
 */

public class HomeFragment extends Fragment implements RechargeClickListiner{
    private View  view;
    private RecyclerView recharge_recylerview,recylerviewbanner,recylerviewbook;
    private ArrayList<Pair<Integer,String>> menuPairList = new ArrayList<>();
    private Context context;
    public static TextView AgentRequestTxt,walletTxt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_screen,container,false);
        context =this.getActivity();

      //  Toast.makeText(context, "testing in fragment", Toast.LENGTH_SHORT).show();


//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.setTitle("f");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recharge_recylerview = (RecyclerView) view.findViewById(R.id.recylerview);
        recylerviewbanner = (RecyclerView) view.findViewById(R.id.recylerviewbanner);
        recylerviewbook = (RecyclerView) view.findViewById(R.id.recylerviewbook);
        AgentRequestTxt = (TextView) view.findViewById(R.id.AgentRequestTxt);
        walletTxt = (TextView) view.findViewById(R.id.walletTxt);
        //  pay_recylerview = (RecyclerView)findViewById(R.id.pay_recylerview);
        menuPairList.add(Pair.create(R.drawable.ic_mobile, "Mobile"));
        menuPairList.add(Pair.create(R.drawable.ic_landline, "Landline"));
        menuPairList.add(Pair.create(R.drawable.ic_dth, "DTH"));
        menuPairList.add(Pair.create(R.drawable.ic_internet, "Internet"));
        menuPairList.add(Pair.create(R.drawable.ic_television, "Cable TV"));
        menuPairList.add(Pair.create(R.drawable.ic_electricity, "Electricity"));
        menuPairList.add(Pair.create(R.drawable.ic_water_tap, "Water"));
        menuPairList.add(Pair.create(R.drawable.ic_data_card, "Data Card"));




        RechargeAdapter rechargeAdapter = new RechargeAdapter(context,menuPairList);
         rechargeAdapter.setOnAdapterListener(HomeFragment.this);
        recharge_recylerview.setAdapter(rechargeAdapter);
        recharge_recylerview.setLayoutManager(new GridLayoutManager(context,4));

        recylerviewbook.setAdapter(rechargeAdapter);
        recylerviewbook.setLayoutManager(new GridLayoutManager(context,4));
         rechargeAdapter.setOnAdapterListener(HomeFragment.this);
        ArrayList<Integer> bannerArrayList = new ArrayList<>();
        bannerArrayList.add(R.drawable.bant);
        bannerArrayList.add(R.drawable.bans);
        bannerArrayList.add(R.drawable.ban);
        bannerArrayList.add(R.drawable.banf);
        BannerAdapter bannerAdapter = new BannerAdapter(context,bannerArrayList);
        recylerviewbanner.setAdapter(bannerAdapter);
        recylerviewbanner.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        AgentRequestTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, RequestForAgent.class));
            }
        });

        return view;
    }


    @Override
    public void onAdapterClickListiner(int pos) {
        switch (pos){
            case 0:{

                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.content_frame, new MobileRecharge(), "mobileTag");
                //ft.addToBackStack("mobileTag");
                ft.commit();
                break;
            }case 1:{
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.content_frame, new PayLandLineBill(), "landlineTag");
                //ft.addToBackStack("mobileTag");
                ft.commit();
                break;
            }case 2:{
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.content_frame, new PayDTHFragment(), "dthTag");
                //ft.addToBackStack("mobileTag");
                ft.commit();
                break;
            }case 3:{
               // startActivity(new Intent(context, PayLandLineBill.class));
                break;
            }case 4:{
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.content_frame, new PayCableFragment(), "cableTag");
                //ft.addToBackStack("mobileTag");
                ft.commit();

                break;
            }case 5:{
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.content_frame, new PayElectrictyFragment(), "elctricityTag");
                //ft.addToBackStack("mobileTag");
                ft.commit();
                break;
            }case 6:{
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.content_frame, new PayElectrictyFragment(), "elctricityTag");
                //ft.addToBackStack("mobileTag");
                ft.commit();
                break;
            }case 7:{
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.content_frame, new DataCardFragment(), "datacardTag");
                //ft.addToBackStack("mobileTag");
                ft.commit();
                break;
            }
            default:
        }

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
//        // Do something that differs the Activity's menu here
//        super.onCreateOptionsMenu(menu, inflater);
//
//
//        inflater.inflate(R.menu.search, menu);//Menu Resource, Menu
//       // this.menu = menu;
//        MenuItem menuItem = menu.findItem(R.id.action_cart);
//        menuItem.setIcon(buildCounterDrawable(context,2,  R.drawable.ic_notification));
//        MenuItem item = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setQueryHint("search");
//        searchView.setIconifiedByDefault(false);
//        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//        searchEditText.setTextColor(ContextCompat.getColor(context,R.color.white));
//        searchEditText.setHintTextColor((ContextCompat.getColor(context,R.color.white)));
//        try {
//            // https://github.com/android/platform_frameworks_base/blob/kitkat-release/core/java/android/widget/TextView.java#L562-564
//            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
//            f.setAccessible(true);
//            f.set(searchEditText, R.drawable.cursor);
//        } catch (Exception ignored) {
//        }
////        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
////        TextView textView = (TextView) searchView.findViewById(id);
////        textView.setTextColor(Color.WHITE);
//
////        int magId = getResources().getIdentifier("android:id/search_mag_icon", null, null);
////        ImageView magImage = (ImageView) searchView.findViewById(magId);
////        magImage.setLayoutParams(new LinearLayoutCompat.LayoutParams(0,0));
//       // searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) context);
//
//        MenuItemCompat.setOnActionExpandListener(item,
//                new MenuItemCompat.OnActionExpandListener() {
//                    @Override
//                    public boolean onMenuItemActionCollapse(MenuItem item) {
//// Do something when collapsed
//
//                        return true; // Return true to collapse action view
//                    }
//
//                    @Override
//                    public boolean onMenuItemActionExpand(MenuItem item) {
//// Do something when expanded
//                        return true; // Return true to expand action view
//                    }
//                });
//
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_search) {
//
//        }else if (item.getItemId() == android.R.id.home) {
//            //finish(); // close this activity and return to preview activity (if there is any)
//        }else if(item.getItemId()==R.id.action_cart){
//            //   item.setIcon(buildCounterDrawable(this,20,  R.drawable.ic_birthday));
//            Toast.makeText(context
//
//                    , "clicked", Toast.LENGTH_SHORT).show();
//        }
//        // handle arrow click here
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onResume() {
        super.onResume();
        walletTxt.setText(CommonUtil.WALLETMONEY);
    }

    @Override
    public void onPause() {
        super.onPause();
        walletTxt.setText(CommonUtil.WALLETMONEY);
    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
