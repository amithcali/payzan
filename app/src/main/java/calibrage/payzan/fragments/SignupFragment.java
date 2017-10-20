package calibrage.payzan.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.activities.signup;
import calibrage.payzan.model.RegisterModel;
import calibrage.payzan.model.ResponseModel;
import calibrage.payzan.networkservice.MyServices;
import calibrage.payzan.networkservice.ServiceFactory;
import calibrage.payzan.utils.CommonUtil;
import calibrage.payzan.utils.SmsListener;
import calibrage.payzan.utils.SmsReceiver;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static calibrage.payzan.utils.CommonUtil.isValidEmail;

/**
 * Created by Calibrage19 on 20-10-2017.
 */

public class SignupFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    private EditText reg_mobile,reg_email,reg_password;
    private LoginButton loginButton;
    private Button fbBtn,btnRegister;
    private CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton button;
    private static final int RC_SIGN_IN = 007;
    private AlertDialog alertDialog;
    // private Subscription mRegisterSubscription;
    private TextInputLayout reg_mobile_til;
    private Subscription mRegisterSubscription;
    private View rootView;
    private Context context;
    public  static Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_signup, container, false);
        IntiateGoogleApi();
        context= getActivity();
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) rootView.findViewById(R.id.login);
        fbBtn = (Button) rootView.findViewById(R.id.fbBtn);
        btnRegister = (Button) rootView.findViewById(R.id.btnRegister);
        button = (SignInButton) rootView.findViewById(R.id.btn_sign_in);
        reg_mobile = (EditText) rootView.findViewById(R.id.reg_mobile);
        reg_email = (EditText) rootView.findViewById(R.id.reg_email);
        reg_password = (EditText)rootView.findViewById(R.id.reg_password);
        reg_mobile_til = (TextInputLayout) rootView.findViewById(R.id.reg_mobile_til);

        HomeActivity.toolbar.setNavigationIcon(R.drawable.ic_stat_arrow_back);
        HomeActivity.toolbar.setTitle(getResources().getString(R.string.sign_up));
        HomeActivity.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white_new));
        HomeActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeTab();
            }
        });
        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });

        reg_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reg_mobile_til.setErrorEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity();

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidateUi()){
                    registerUser();
                }

            }
        });






        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("Main", response.toString());
                                setProfileToView(object);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
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
        return rootView;
    }
    private void closeTab() {
        Toast.makeText(context, "ON BACK KEY PRESED", Toast.LENGTH_SHORT).show();
       getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new HomeFragment())
                .commit();
//        toolbar.setNavigationIcon(null);
//        toolbar.setTitle("");
//        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("SignupTag");
//
//
//        if (fragment != null)
//            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
//
//        HomeActivity.toolbar.setNavigationIcon(null);
//        HomeActivity.toolbar.setTitle("");
    }

    private void registerUser() {
        JsonObject object = getRegisterObject();
        MyServices service = ServiceFactory.createRetrofitService(getActivity(), MyServices.class);

        mRegisterSubscription = service.userRegister(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModel>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getActivity(), "check", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof HttpException){
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
                    public void onNext(ResponseModel registerResponseModel) {
                        Toast.makeText(getActivity(), "sucess", Toast.LENGTH_SHORT).show();
                       // finish();
                    }
                });


    }
    private void IntiateGoogleApi() {
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                Log.d("Text", messageText);
                Toast.makeText(getActivity(), "Message: " + messageText, Toast.LENGTH_LONG).show();
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        if (mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {
            try {
                mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                        .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .build();
                loginButton.setReadPermissions("email");
            } catch (Exception e) {
                e.printStackTrace();
            }
//        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
//                .enableAutoManage(getActivity(), this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//        loginButton.setReadPermissions("email");
        }
    }
    private JsonObject getRegisterObject() {
        RegisterModel registerModel = new RegisterModel();
        registerModel.setMobileNumber(reg_mobile.getText().toString());
        registerModel.setPassword(reg_password.getText().toString());
        registerModel.setEmail(reg_email.getText().toString());
        return  new Gson().toJsonTree(registerModel)
                .getAsJsonObject();
    }

    private boolean isValidateUi(){
        if(TextUtils.isEmpty(reg_mobile.getText().toString())){
            // CommonUtil.displayDialogWindow("Please enter mobile no.",alertDialog,signup.this);
            reg_mobile_til.setErrorEnabled(true);
            reg_mobile_til.setError("Please enter mobile no.");
            return false;
        } else if(!TextUtils.isEmpty(reg_mobile.getText().toString())&& (reg_mobile.getText().toString().length()>14||reg_mobile.getText().toString().length()<10)) {
            CommonUtil.displayDialogWindow("Please enter valid mobile no.", alertDialog, getActivity());
            return false;
        }else if   (!isValidEmail(reg_email.getText().toString())) {
            CommonUtil.displayDialogWindow("Please enter valid email ",alertDialog,getActivity());
            return false;
        }else if(TextUtils.isEmpty(reg_password.getText().toString())){
            CommonUtil.displayDialogWindow("Please enter password ",alertDialog,getActivity());
            return false;
        }

        return true;
    }
    private void setProfileToView(JSONObject jsonObject) {
//        try {
//            dummy.setText(jsonObject.getString("email") + "\n" + jsonObject.getString("gender") + "\n" + jsonObject.getString("name"));

//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Toast.makeText(getActivity(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        //updateUI(false);
                        Toast.makeText(getActivity(), "signout", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
