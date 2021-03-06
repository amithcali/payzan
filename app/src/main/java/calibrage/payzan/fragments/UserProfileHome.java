package calibrage.payzan.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.activities.ProfileActivity;
import calibrage.payzan.activities.RequestForAgent;
import calibrage.payzan.activities.UpdatePasswordActivity;
import calibrage.payzan.controls.BaseFragment;
import calibrage.payzan.interfaces.OnChildFragmentToActivityInteractionListener;
import calibrage.payzan.model.LoginResponseModel;
import calibrage.payzan.utils.CommonConstants;
import calibrage.payzan.utils.CommonUtil;
import calibrage.payzan.utils.SharedPrefsData;


public class UserProfileHome extends BaseFragment {
    public static final String TAG = UserProfileHome.class.getSimpleName();
    private Button btn_logOut, btn_Share,addMoneyBtn;
    private OnChildFragmentToActivityInteractionListener mActivityListener;
    private LinearLayout changePsdLyt;
    private Context context;
    private ImageView profileImage,editProfile;
    private TextView walletBalanceTxt,userMail,userName;
    private View v;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_user_profile_home, container, false);
        context = this.getActivity();

        setViews();
        initViews();



        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
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
        return v;
    }

    private void setViews() {

        btn_logOut = (Button) v.findViewById(R.id.btn_sign_out);
        btn_Share = (Button) v.findViewById(R.id.btn_share);
        addMoneyBtn = (Button) v.findViewById(R.id.addMoneyBtn);
        changePsdLyt = (LinearLayout) v.findViewById(R.id.changePsdLyt);
        walletBalanceTxt = (TextView) v.findViewById(R.id.walletBalanceTxt);
        userName = (TextView) v.findViewById(R.id.userName);
        userMail = (TextView) v.findViewById(R.id.userMail);
        editProfile = (ImageView) v.findViewById(R.id.editProfile);
    }

    private void initViews() {

        LoginResponseModel  loginResponseModel =     new Gson().fromJson(SharedPrefsData.getInstance(context).getUserDetails(context), LoginResponseModel.class);
        if(loginResponseModel!=null){
            walletBalanceTxt.setText(String.valueOf(SharedPrefsData.getInstance(context).getWalletIdMoney(context)));
            userName.setText(SharedPrefsData.getInstance(context).getUserName(context));
            userMail.setText(loginResponseModel.getdata().getUser().getEmail());
        }



        changePsdLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, UpdatePasswordActivity.class));

            }
        });

        addMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(getActivity(), MAIN_CONTAINER, new TransactionMainFragment(), TAG, TransactionMainFragment.TAG);
            }
        });

        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefsData.getInstance(getActivity()).ClearData(getActivity());

                // Toast.makeText(getActivity(), "DATA Cleared", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getContext(), HomeActivity.class);
//                startActivity(i);
                // getActivity().finish();

                closeTab();

            }
        });
        btn_Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Ishare = new Intent(Intent.ACTION_SEND);
                Ishare.setType("text/plain");
                Ishare.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                // Add data to the intent, the receiving app will decide
                // what to do with it.
                Ishare.putExtra(Intent.EXTRA_SUBJECT, "PayZan Android");
                Ishare.putExtra(Intent.EXTRA_TEXT, "http://calibrage.in/");

                startActivity(Intent.createChooser(Ishare, "Share link!"));
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ProfileActivity.class));


            }
        });

    }

    private void closeTab() {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("profileHomeTag");
        mActivityListener.messageFromChildFragmentToActivity("signout");

        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new LoginFragment()).commit();
            HomeActivity.toolbar.setNavigationIcon(null);
            HomeActivity.toolbar.setTitle("");
            CommonUtil.hideSoftKeyboard((AppCompatActivity) getActivity());
        }

    }

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
    }

    // TODO: Rename method, update argument and hook method into UI event

}
