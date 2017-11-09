package calibrage.payzan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import calibrage.payzan.R;
import calibrage.payzan.controls.CommonEditText;
import calibrage.payzan.model.ChangePasswordModel;
import calibrage.payzan.model.ChangePasswordResponseModel;
import calibrage.payzan.model.LoginModel;
import calibrage.payzan.model.LoginResponseModel;
import calibrage.payzan.networkservice.MyServices;
import calibrage.payzan.networkservice.ServiceFactory;
import calibrage.payzan.utils.CommonConstants;
import calibrage.payzan.utils.NCBTextInputLayout;
import calibrage.payzan.utils.SharedPrefsData;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Calibrage11 on 11/8/2017.
 */

public class UpdatePasswordActivity extends BaseActivity {
    private CommonEditText oldPsdEdt, newPsdEdt, confirmPsdEdt;
    private NCBTextInputLayout oldPsdTIL, newPsdTIL, confirmPsdTIL;
    private Button saveBtn;
    private Subscription passwordSubscription;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        setView();
        initView();
    }

    private void initView() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidateUi()) {
                    showProgressDialog();
                    changePassword();

                }

            }
        });

    }

    private void setView() {
        oldPsdEdt = (CommonEditText) findViewById(R.id.oldPsdEdt);
        newPsdEdt = (CommonEditText) findViewById(R.id.newPsdEdt);
        confirmPsdEdt = (CommonEditText) findViewById(R.id.confirmPsdEdt);
        oldPsdTIL = (NCBTextInputLayout) findViewById(R.id.oldPsdTIL);
        newPsdTIL = (NCBTextInputLayout) findViewById(R.id.newPsdTIL);
        confirmPsdTIL = (NCBTextInputLayout) findViewById(R.id.confirmPsdTIL);
        saveBtn = (Button) findViewById(R.id.saveBtn);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(getResources().getString(R.string.Update_Password));

    }

    private boolean isValidateUi() {
        return true;
    }

    private void changePassword() {
        JsonObject object = getPassword();
        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
        passwordSubscription = service.changePassword(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ChangePasswordResponseModel>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(UpdatePasswordActivity.this, "check", Toast.LENGTH_SHORT).show();
                        hideProgressDialog();
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
                        hideProgressDialog();

                    }

                    @Override
                    public void onNext(ChangePasswordResponseModel changePasswordResponseModel) {

                        Toast.makeText(UpdatePasswordActivity.this, "sucess", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private JsonObject getPassword() {
        ChangePasswordModel changePasswordModel = new ChangePasswordModel();
        changePasswordModel.setUserName(SharedPrefsData.getInstance(this).getUserName(this));
        changePasswordModel.setOldPassword(oldPsdEdt.getText().toString().trim());
        changePasswordModel.setNewPassword(newPsdEdt.getText().toString().trim());
        changePasswordModel.setConfirmPassword(confirmPsdEdt.getText().toString().trim());
        return new Gson().toJsonTree(changePasswordModel)
                .getAsJsonObject();
    }
}
