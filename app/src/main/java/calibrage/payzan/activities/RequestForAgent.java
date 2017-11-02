package calibrage.payzan.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import calibrage.payzan.R;
import calibrage.payzan.adapters.GenericAdapter;
import calibrage.payzan.adapters.SingleLineDropDownAdapter;
import calibrage.payzan.adapters.SingleLineDropDownAdapterMandals;
import calibrage.payzan.adapters.SingleLineDropDownAdapterVillages;
import calibrage.payzan.adapters.SingleLineDropDownAdapterdistrict;
import calibrage.payzan.fragments.PayDTHFragment;
import calibrage.payzan.model.AgentModel;
import calibrage.payzan.model.AgentResponseModel;
import calibrage.payzan.model.DistrictModel;
import calibrage.payzan.model.LoginModel;
import calibrage.payzan.model.LoginResponseModel;
import calibrage.payzan.model.MandalModel;
import calibrage.payzan.model.OperatorModel;
import calibrage.payzan.model.StatesModel;;
import calibrage.payzan.model.VillageModel;
import calibrage.payzan.networkservice.ApiConstants;
import calibrage.payzan.networkservice.MyServices;
import calibrage.payzan.networkservice.ServiceFactory;
import calibrage.payzan.utils.CommonConstants;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static calibrage.payzan.R.id.operatorSpn;


/**
 * Created by Calibrage11 on 10/5/2017.
 */

public class RequestForAgent extends AppCompatActivity implements SingleLineDropDownAdapter.AdapterOnClick, SingleLineDropDownAdapterdistrict.AdapterDistOnClick, SingleLineDropDownAdapterMandals.AdapterMandalOnClick, SingleLineDropDownAdapterVillages.AdapterVillageOnClick {

    private TextInputLayout stateTIl, districtTIl, mandalTIl, villageTIl, firstNameTIl, middleNameTIL, lastNameTIL, mobileTIL, emailTIL, address1TIL, address2TIL, landmarkTIL, commentTIL;
    private EditText commentsEdt, landmarkEdt, address2Edt, address1Edt, emailEdt, mobileEdt, lastNameEdt, middleNameEdt, firstNameEdt;
    private AutoCompleteTextView villageSpn, mandalSpn, districtSpn, stateSpn;
    private Subscription mGetStatesSubscription, mGetDistrictSubscription, getmGetDistrictSubscription;
    private MandalModel mandalModellist;
    private StatesModel statesModellist;
    private VillageModel villageModellist;
    private DistrictModel districtModellist;
    private Button btn_submit;
    private Subscription mRegisterSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_as_agent);

        setViews();
        getStates();
        PostAgentRequest();
      /*  getDistricts();*/


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
        btn_submit = (Button) findViewById(R.id.submit);

        stateSpn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                stateSpn.showDropDown();
                getStates();
                return false;
            }
        });
        districtSpn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                districtSpn.showDropDown();

                return false;
            }
        });
        mandalSpn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mandalSpn.showDropDown();

                return false;
            }
        });
        villageSpn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                villageSpn.showDropDown();

                return false;
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void getStates() {


        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);

        String URL = "http://192.168.1.147/PayZanAPI/api/States/GetStateInfo/1";/*ApiConstants.STATES +"1" ;*/
        mGetStatesSubscription = service.getStates(URL)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StatesModel>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RequestForAgent.this, "check", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RequestForAgent.this, "fail", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(StatesModel statesModel) {
                        statesModellist = statesModel;
                        Toast.makeText(RequestForAgent.this, "sucess", Toast.LENGTH_SHORT).show();

                        SingleLineDropDownAdapter singleLineDropDownAdapter = new SingleLineDropDownAdapter(RequestForAgent.this, R.layout.adapter_single_item, (List<StatesModel.Data>) statesModel.getListResult());
                        singleLineDropDownAdapter.setAdapterOnClick(RequestForAgent.this);
                        stateSpn.setAdapter(singleLineDropDownAdapter);
                    }
                });
    }


    private void getDistricts(int id) {
        String URL = "http://192.168.1.147/PayZanAPI/api/Districts/GetDistrictsInfo/" + id;  /*ApiConstants.DISTRICTS + "1"*/
        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
        mGetDistrictSubscription = service.getDistricts(URL)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DistrictModel>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RequestForAgent.this, "check", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RequestForAgent.this, "fail", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(DistrictModel districtModel) {
                        districtModellist = districtModel;
                        Toast.makeText(RequestForAgent.this, "sucess", Toast.LENGTH_SHORT).show();

                        SingleLineDropDownAdapterdistrict singleLineDropDownAdapter = new SingleLineDropDownAdapterdistrict(RequestForAgent.this, R.layout.adapter_single_item, (List<DistrictModel.Data>) districtModel.getListResult());
                        singleLineDropDownAdapter.setAdapterDistOnClick(RequestForAgent.this);

                        districtSpn.setAdapter(singleLineDropDownAdapter);
                    }
                });
    }


    private void getMandals(int Id) {
        String Url = "http://192.168.1.147/PayZanAPI/api/Mandals/GetMandalInfo/" + Id;  /*ApiConstants.MANDALS + "1"*/
        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
        mGetDistrictSubscription = service.getMandals(Url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MandalModel>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RequestForAgent.this, "check", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RequestForAgent.this, "fail", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(MandalModel mandalModel) {
                        mandalModellist = mandalModel;
                        Toast.makeText(RequestForAgent.this, "sucess", Toast.LENGTH_SHORT).show();


                        SingleLineDropDownAdapterMandals singleLineDropDownAdapter = new SingleLineDropDownAdapterMandals(RequestForAgent.this, R.layout.adapter_single_item, (List<MandalModel.data>) mandalModel.getListResult());
                        singleLineDropDownAdapter.setAdapterMandalOnClick(RequestForAgent.this);

                        mandalSpn.setAdapter(singleLineDropDownAdapter);

                    }
                });
    }

    private void getVillages(int id) {
        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
        String Url = "http://192.168.1.147/PayZanAPI/api/Villages/GetVillageInfo/" + id; /* ApiConstants.VILLAGE + "1"*/
        mGetDistrictSubscription = service.getVillages(Url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VillageModel>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RequestForAgent.this, "check", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RequestForAgent.this, "fail", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(VillageModel villageModel) {
                        villageModellist = villageModel;

                        Toast.makeText(RequestForAgent.this, "sucess", Toast.LENGTH_SHORT).show();


                        SingleLineDropDownAdapterVillages singleLineDropDownAdapter = new SingleLineDropDownAdapterVillages(RequestForAgent.this, R.layout.adapter_single_item, (List<VillageModel.Data>) villageModel.getListResult());
                        singleLineDropDownAdapter.setAdapterVillageOnClick(RequestForAgent.this);

                        villageSpn.setAdapter(singleLineDropDownAdapter);

                    }
                });
    }


    private void initViews() {

    }


    private void PostAgentRequest() {
        String URL = "http://192.168.1.147/PayZanAPI/api/AgentRequestInfo/AddUpdateAgentRequestInfo";
        JsonObject object = getAgentObject();
        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
        mRegisterSubscription = (Subscription) service.agentRequest(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AgentResponseModel>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RequestForAgent.this, "check", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RequestForAgent.this, "fail", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(AgentResponseModel AgentResponseModel) {

                        Toast.makeText(RequestForAgent.this, "sucess", Toast.LENGTH_SHORT).show();
                      /*  CommonConstants.USERID = loginResponseModel.getData().getUser().getId();
                        CommonConstants.WALLETID = String.valueOf(loginResponseModel.getData().getUserWallet().getWalletId());*/
                        finish();
                    }
                });

    }

    private JsonObject getAgentObject() {

        AgentModel agentModel = new AgentModel();
        agentModel.setId(0);
        agentModel.setAgentRequestCategoryId(38);
        agentModel.setTitleTypeId(4);
        agentModel.setFirstName("mahesh");
        agentModel.setMiddleName("mashi");
        agentModel.setLastName("mnah");
        agentModel.setMobileNumber("7032214460");
        agentModel.setEmail("mall@m.com");
        agentModel.setAddressLine1("hyd");
        agentModel.setAddressLine2("hyd1");
        agentModel.setLandmark("kjufgjkhkjfg");
        agentModel.setVillageId(1);
        agentModel.setComments("jhjhdhh");
        agentModel.setCreated("2017-10-31T05:15:57.983Z");


        return new Gson().toJsonTree(agentModel)
                .getAsJsonObject();

    }


    @Override
    public void adapterOnClick(int position) {
        stateSpn.setText(statesModellist.getListResult().get(position).getName());
        stateSpn.dismissDropDown();


        getDistricts(statesModellist.getListResult().get(position).getId());
    }

    @Override
    public void adapterDistOnClick(int position) {
        districtSpn.setText(districtModellist.getListResult().get(position).getName());
        districtSpn.dismissDropDown();

        getMandals(districtModellist.getListResult().get(position).getId());
    }

    @Override
    public void AdapterMandalOnClick(int position) {
        mandalSpn.setText(mandalModellist.getListResult().get(position).getName());
        mandalSpn.dismissDropDown();

        getVillages(mandalModellist.getListResult().get(position).getId());
    }


    @Override
    public void adapterVillageOnClick(int position) {
        villageSpn.setText(villageModellist.getListResult().get(position).getName());
        villageSpn.dismissDropDown();
    }

}


