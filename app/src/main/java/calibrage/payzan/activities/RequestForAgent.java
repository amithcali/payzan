package calibrage.payzan.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import calibrage.payzan.R;
import calibrage.payzan.adapters.SingleLineDropDownAdapter;
import calibrage.payzan.model.DistrictModel;
import calibrage.payzan.model.MandalModel;
import calibrage.payzan.model.StatesModel;
import calibrage.payzan.model.VillageModel;


/**
 * Created by Calibrage11 on 10/5/2017.
 */

public class RequestForAgent extends AppCompatActivity implements SingleLineDropDownAdapter.AdapterOnClick {

    private TextInputLayout stateTIl, districtTIl, mandalTIl, villageTIl, firstNameTIl, middleNameTIL, lastNameTIL, mobileTIL, emailTIL, address1TIL, address2TIL, landmarkTIL, commentTIL;
    private EditText commentsEdt, landmarkEdt, address2Edt, address1Edt, emailEdt, mobileEdt, lastNameEdt, middleNameEdt, firstNameEdt;
    private AutoCompleteTextView villageSpn, mandalSpn, districtSpn, stateSpn;
//    private Subscription mGetStatesSubscription, mGetDistrictSubscription,getmGetDistrictSubscription;
    private MandalModel mandalModellist;
    private StatesModel statesModellist;
    private VillageModel villageModellist;
    private DistrictModel districtModellist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_as_agent);
        setViews();
       // getStates();

    }

    private void setViews() {
        stateTIl = (TextInputLayout) findViewById(R.id.stateTIl);
        districtTIl = (TextInputLayout) findViewById(R.id.districtTIl);
        villageTIl = (TextInputLayout) findViewById(R.id.villageTIl);
        firstNameTIl = (TextInputLayout) findViewById(R.id.firstNameTIl);
        middleNameTIL = (TextInputLayout) findViewById(R.id.middleNameTIL);
        lastNameTIL = (TextInputLayout) findViewById(R.id.lastNameTIL);
        mobileTIL = (TextInputLayout) findViewById(R.id.mobileTIL);
        emailTIL = (TextInputLayout) findViewById(R.id.emailTIL);
        address1TIL = (TextInputLayout) findViewById(R.id.address1TIL);
        address2TIL = (TextInputLayout) findViewById(R.id.address2TIL);
        landmarkTIL = (TextInputLayout) findViewById(R.id.landmarkTIL);
        commentTIL = (TextInputLayout) findViewById(R.id.commentTIL);
        commentTIL = (TextInputLayout) findViewById(R.id.commentTIL);
        commentsEdt = (EditText) findViewById(R.id.commentsEdt);
        landmarkEdt = (EditText) findViewById(R.id.landmarkEdt);
        address2Edt = (EditText) findViewById(R.id.address2Edt);
        address1Edt = (EditText) findViewById(R.id.address1Edt);
        emailEdt = (EditText) findViewById(R.id.emailEdt);
        mobileEdt = (EditText) findViewById(R.id.mobileEdt);
        lastNameEdt = (EditText) findViewById(R.id.lastNameEdt);
        middleNameEdt = (EditText) findViewById(R.id.middleNameEdt);
        firstNameEdt = (EditText) findViewById(R.id.firstNameEdt);
        villageSpn = (AutoCompleteTextView) findViewById(R.id.villageSpn);
        mandalSpn = (AutoCompleteTextView) findViewById(R.id.mandalSpn);
        districtSpn = (AutoCompleteTextView) findViewById(R.id.districtSpn);
        stateSpn = (AutoCompleteTextView) findViewById(R.id.stateSpn);

        stateSpn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                stateSpn.showDropDown();
                return false;
            }
        });



    }
//
//    private void getStates() {
//        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
//        mGetStatesSubscription = service.getStates(ApiConstants.STATES + "1")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<StatesModel>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(RequestForAgent.this, "check", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof HttpException) {
//                            ((HttpException) e).code();
//                            ((HttpException) e).message();
//                            ((HttpException) e).response().errorBody();
//                            try {
//                                ((HttpException) e).response().errorBody().string();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            }
//                            e.printStackTrace();
//                        }
//                        Toast.makeText(RequestForAgent.this, "fail", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(StatesModel statesModel) {
//                        statesModellist = statesModel;
//                        Toast.makeText(RequestForAgent.this, "sucess", Toast.LENGTH_SHORT).show();
//
//                        SingleLineDropDownAdapter singleLineDropDownAdapter = new SingleLineDropDownAdapter(RequestForAgent.this, R.layout.adapter_single_item, (List<StatesModel.Data>) statesModel.getData());
//                        singleLineDropDownAdapter.setAdapterOnClick(RequestForAgent.this);
//                        stateSpn.setAdapter(singleLineDropDownAdapter);
//                    }
//                });
//    }
//
//    private void getDistricts() {
//        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
//        mGetDistrictSubscription = service.getDistricts(ApiConstants.DISTRICTS + "1")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<DistrictModel>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(RequestForAgent.this, "check", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof HttpException) {
//                            ((HttpException) e).code();
//                            ((HttpException) e).message();
//                            ((HttpException) e).response().errorBody();
//                            try {
//                                ((HttpException) e).response().errorBody().string();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            }
//                            e.printStackTrace();
//                        }
//                        Toast.makeText(RequestForAgent.this, "fail", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(DistrictModel districtModel) {
//                        districtModellist = districtModel;
//                        Toast.makeText(RequestForAgent.this, "sucess", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                });
//    }
//
//
//    private void getMandals() {
//        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
//        mGetDistrictSubscription = service.getMandals(ApiConstants.MANDALS + "1")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MandalModel>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(RequestForAgent.this, "check", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof HttpException) {
//                            ((HttpException) e).code();
//                            ((HttpException) e).message();
//                            ((HttpException) e).response().errorBody();
//                            try {
//                                ((HttpException) e).response().errorBody().string();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            }
//                            e.printStackTrace();
//                        }
//                        Toast.makeText(RequestForAgent.this, "fail", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(MandalModel mandalModel) {
//                        mandalModellist = mandalModel;
//                        Toast.makeText(RequestForAgent.this, "sucess", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                });
//    }
//    private void getVillages() {
//        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
//        mGetDistrictSubscription = service.getVillages(ApiConstants.VILLAGE + "1")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<VillageModel>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(RequestForAgent.this, "check", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof HttpException) {
//                            ((HttpException) e).code();
//                            ((HttpException) e).message();
//                            ((HttpException) e).response().errorBody();
//                            try {
//                                ((HttpException) e).response().errorBody().string();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            }
//                            e.printStackTrace();
//                        }
//                        Toast.makeText(RequestForAgent.this, "fail", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(VillageModel villageModel) {
//                        villageModellist = villageModel;
//                        Toast.makeText(RequestForAgent.this, "sucess", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                });
//    }

    private void initViews() {

    }


    @Override
    public void adapterOnClick(int position) {

    }
}


