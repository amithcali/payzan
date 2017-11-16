package calibrage.payzan.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import java.util.regex.Pattern;

import calibrage.payzan.BuildConfig;
import calibrage.payzan.R;
import calibrage.payzan.adapters.GenericAdapter;
import calibrage.payzan.adapters.SingleLineDropDownAdapter;
import calibrage.payzan.adapters.SingleLineDropDownAdapterMandals;
import calibrage.payzan.adapters.SingleLineDropDownAdapterVillages;
import calibrage.payzan.adapters.SingleLineDropDownAdapterdistrict;
import calibrage.payzan.controls.CommonEditText;
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
import calibrage.payzan.utils.NCBTextInputLayout;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static calibrage.payzan.R.id.operatorSpn;


/**
 * Created by Calibrage11 on 10/5/2017.
 */

public class  RequestForAgent extends AppCompatActivity implements SingleLineDropDownAdapter.AdapterOnClick, SingleLineDropDownAdapterdistrict.AdapterDistOnClick, SingleLineDropDownAdapterMandals.AdapterMandalOnClick, SingleLineDropDownAdapterVillages.AdapterVillageOnClick {

    private NCBTextInputLayout stateTIl, districtTIl, mandalTIl, villageTIl, firstNameTIl, middleNameTIL, lastNameTIL, mobileTIL, emailTIL, address1TIL, address2TIL, landmarkTIL, commentTIL;
    private CommonEditText commentsEdt, landmarkEdt, address2Edt, address1Edt, emailEdt, mobileEdt, lastNameEdt, middleNameEdt, firstNameEdt;
    private AutoCompleteTextView villageSpn, mandalSpn, districtSpn, stateSpn;
    private Subscription mGetStatesSubscription, mGetDistrictSubscription, getmGetDistrictSubscription;
    private MandalModel mandalModellist;
    private StatesModel statesModellist;
    private VillageModel villageModellist;
    private DistrictModel districtModellist;
    private Button btn_submit;
    private Subscription mRegisterSubscription;

    private String firstNameStr,middleNameStr,lastNameStr,mobileStr,emailStr,stateStr,districtStr,mandalStr,villageStr,address1Str,
            address2Str,landmarkStr,commentsStr ;

    public final Pattern EMAIL_ADDRESS_PATTERN=Pattern.compile
            ("[a-zA-Z0-9+._%-+]{1,256}"+"@"+"[a-zA-Z0-9][a-zA-Z0-9-]{0,64}"+"("+"."+"[a-zA-Z0-9][a-zA-Z0-9-]{0,25}"+")+");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_as_agent);

        setViews();
        getStates();
        initViews();
     //   PostAgentRequest();
      /*  getDistricts();*/


    }

    private void setViews() {
        stateTIl = (NCBTextInputLayout) findViewById(R.id.stateTIl);
        districtTIl = (NCBTextInputLayout) findViewById(R.id.districtTIl);
        villageTIl = (NCBTextInputLayout) findViewById(R.id.villageTIl);
        firstNameTIl = (NCBTextInputLayout) findViewById(R.id.firstNameTIl);
        middleNameTIL = (NCBTextInputLayout) findViewById(R.id.middleNameTIL);
        lastNameTIL = (NCBTextInputLayout) findViewById(R.id.lastNameTIL);
        mobileTIL = (NCBTextInputLayout) findViewById(R.id.mobileTIL);
        emailTIL = (NCBTextInputLayout) findViewById(R.id.emailTIL);
        address1TIL = (NCBTextInputLayout) findViewById(R.id.address1TIL);
        address2TIL = (NCBTextInputLayout) findViewById(R.id.address2TIL);
        landmarkTIL = (NCBTextInputLayout) findViewById(R.id.landmarkTIL);
        commentTIL = (NCBTextInputLayout) findViewById(R.id.commentTIL);
        commentTIL = (NCBTextInputLayout) findViewById(R.id.commentTIL);
        commentsEdt = (CommonEditText) findViewById(R.id.commentsEdt);
        landmarkEdt = (CommonEditText) findViewById(R.id.landmarkEdt);
        address2Edt = (CommonEditText) findViewById(R.id.address2Edt);
        address1Edt = (CommonEditText) findViewById(R.id.address1Edt);
        emailEdt = (CommonEditText) findViewById(R.id.emailEdt);
        mobileEdt = (CommonEditText) findViewById(R.id.mobileEdt);
        lastNameEdt = (CommonEditText) findViewById(R.id.lastNameEdt);
        middleNameEdt = (CommonEditText) findViewById(R.id.middleNameEdt);
        firstNameEdt = (CommonEditText) findViewById(R.id.firstNameEdt);
        villageSpn = (AutoCompleteTextView) findViewById(R.id.villageSpn);
        mandalSpn = (AutoCompleteTextView) findViewById(R.id.mandalSpn);
        districtSpn = (AutoCompleteTextView) findViewById(R.id.districtSpn);
        stateSpn = (AutoCompleteTextView) findViewById(R.id.stateSpn);
        btn_submit = (Button) findViewById(R.id.submit);
        mandalTIl=(NCBTextInputLayout) findViewById(R.id.mandalTIl);

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

       String BaseUrl= BuildConfig.AZURE_URL+"api/States/GetStateInfo/1";
       /* String URL = "http://192.168.1.147/PayZanAPI/api/States/GetStateInfo/1";*//*ApiConstants.STATES +"1" ;*/
        mGetStatesSubscription = service.getStates(BaseUrl)
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
        String BaseUrl= BuildConfig.AZURE_URL+"api/Districts/GetDistrictsInfo/"+id;
       /* String URL = "http://192.168.1.147/PayZanAPI/api/Districts/GetDistrictsInfo/" + id;  *//*ApiConstants.DISTRICTS + "1"*/
        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
        mGetDistrictSubscription = service.getDistricts(BaseUrl)
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
        String BaseUrl= BuildConfig.AZURE_URL+"api/Mandals/GetMandalInfo/"+Id;
       /* String Url = "http://192.168.1.147/PayZanAPI/api/Mandals/GetMandalInfo/" + Id;  *//*ApiConstants.MANDALS + "1"*/
        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
        mGetDistrictSubscription = service.getMandals(BaseUrl)
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

        /*String Url = "http://192.168.1.147/PayZanAPI/api/Villages/GetVillageInfo/" + id;*/
        String BaseUrl= BuildConfig.AZURE_URL+"api/Villages/GetVillageInfo/"+id;
        MyServices service = ServiceFactory.createRetrofitService(this, MyServices.class);
       /* ApiConstants.VILLAGE + "1"*/
        mGetDistrictSubscription = service.getVillages(BaseUrl)
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
        firstNameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    firstNameTIl.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        middleNameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    middleNameTIL.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lastNameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    lastNameTIL.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mobileEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    mobileTIL.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        emailEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    emailTIL.setErrorEnabled(false);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        stateSpn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0)
                    stateTIl.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        districtSpn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    districtTIl.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mandalSpn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    mandalTIl.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        villageSpn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                  villageTIl.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        address1Edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                  address1TIL.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        address2Edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                   address2TIL.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        landmarkEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    landmarkTIL.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        commentsEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    commentTIL.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUi()) {
                  //  Toast.makeText(RequestForAgent.this, "Valid Email Addresss", Toast.LENGTH_SHORT).show();
                }
                else{
                 //   Toast.makeText(RequestForAgent.this,"Invalid Email Addresss", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private boolean validateUi()
    {

        firstNameStr=firstNameEdt.getText().toString().trim();
        middleNameStr= middleNameEdt.getText().toString().trim();
        lastNameStr= lastNameEdt.getText().toString().trim();
        mobileStr=mobileEdt.getText().toString().trim();
        emailStr=emailEdt.getText().toString().trim();
        stateStr=stateSpn.getText().toString().trim();
        districtStr=districtSpn.getText().toString().trim();
        mandalStr=mandalSpn.getText().toString().trim();
        villageStr=villageSpn.getText().toString().trim();
        address1Str=address1Edt.getText().toString().trim();
        address2Str=address2Edt.getText().toString().trim();
        landmarkStr=landmarkEdt.getText().toString().trim();
        commentsStr=commentsEdt.getText().toString().trim();

        if (TextUtils.isEmpty(firstNameStr)) {
            firstNameTIl.setError("Enter first name");
            firstNameTIl.setErrorEnabled(true);
            return false;
        }
        else if (!(firstNameStr.length() > 3 && firstNameStr.length() <= 30 )) {
            firstNameTIl.setErrorEnabled(true);
            firstNameTIl.setError("Please enter min 3 && max 30 character");
            return false;
        }
        else if (TextUtils.isEmpty(middleNameStr)){
            middleNameTIL.setError("Enter middle name");
            middleNameTIL.setErrorEnabled(true);
            return false;
        }
        else if (!(middleNameStr.length() > 3 && middleNameStr.length() <= 30 )) {
            middleNameTIL.setErrorEnabled(true);
            middleNameTIL.setError("Please enter min 3 && max 30 character");
            return false;
        }
        else if (TextUtils.isEmpty(lastNameStr)){
            lastNameTIL.setError("Enter last name");
            lastNameTIL.setErrorEnabled(true);
            return false;
        }
        else if (!(lastNameStr.length() > 3 && lastNameStr.length() <= 30 )) {
            lastNameTIL.setErrorEnabled(true);
            lastNameTIL.setError("Please enter min 3 && max 30 character");
            return false;
        }

        else if (TextUtils.isEmpty(mobileStr))
        {
            mobileTIL.setErrorEnabled(true);
            mobileTIL.setError("Enter  mobile no");
            return false;
        }
        else if (!isValidPhone())
        {
            mobileTIL.setErrorEnabled(true);
            mobileTIL.setError("Enter valid mobile no");
            return false;
        }
        else if (TextUtils.isEmpty(emailStr))
        {
            emailTIL.setError("Enter email id");
            emailTIL.setErrorEnabled(true);
           // return EMAIL_ADDRESS_PATTERN.matcher(emailStr).matches();
            return false;
        }
        else if(!checkEmail()){
            emailTIL.setError("Enter valid email ");
            emailTIL.setErrorEnabled(true);
            return false;
        }
        else if (TextUtils.isEmpty(stateStr))
        {
            stateTIl.setErrorEnabled(true);
            stateTIl.setError("Enter state name");
            return false;
        }
        else  if (TextUtils.isEmpty(districtStr))
        {
            districtTIl.setErrorEnabled(true);
            districtTIl.setError("Enter district name");
            return false;
        }
        else  if (TextUtils.isEmpty(mandalStr))
        {
            mandalTIl.setError("Enter mandal name");
            mandalTIl.setErrorEnabled(true);
            return  false;
        }
        else if (TextUtils.isEmpty(villageStr))
        {
            villageTIl.setErrorEnabled(true);
            villageTIl.setError("Enter village name");
        }
        else if (TextUtils.isEmpty(address1Str))
        {
            address1TIL.setError("Enter address1 ");
            address1TIL.setErrorEnabled(true);
            return false;
        }
        else if (!(address1Str.length() > 2 && address1Str.length() <= 10 )) {
            address1TIL.setErrorEnabled(true);
            address1TIL.setError("Please enter min 2 && max 10 character");
            return false;
        }
        else if (TextUtils.isEmpty(address2Str))
        {
            address2TIL.setError("Enter address2 ");
            address2TIL.setErrorEnabled(true);
            return false;
        }
        else if (!(address2Str.length() > 2 && address2Str.length() <= 10 )) {
            address2TIL.setErrorEnabled(true);
            address2TIL.setError("Please enter min 2 && max 10 character");
            return false;
        }
        else if (TextUtils.isEmpty(landmarkStr))
        {
            landmarkTIL.setErrorEnabled(true);
            landmarkTIL.setError("Enter landmark");
            return false;
        }
        else if (!(landmarkStr.length() > 2 && landmarkStr.length() <= 10 )) {
            landmarkTIL.setErrorEnabled(true);
            landmarkTIL.setError("Please enter min 2 && max 10 character");
            return false;
        }
        else if (TextUtils.isEmpty(commentsStr))
        {
            commentTIL.setErrorEnabled(true);
            commentTIL.setError("Enter comments");
            return false;
        }
        else if (!(commentsStr.length() > 2 && landmarkStr.length() <= 10 )) {
            commentTIL.setErrorEnabled(true);
            commentTIL.setError("Please enter min 2 && max 10 character");
            return false;
        }
        return true;
    }

    private boolean checkEmail() {
        String email=emailEdt.getText().toString().trim();
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
    private boolean isValidPhone()
    {
        String target=mobileEdt.getText().toString().trim();
        if (target.length()!=10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }


    private void PostAgentRequest() {

      /*  String BaseUrl= BuildConfig.AZURE_URL+"api/AgentRequestInfo/AddUpdateAgentRequestInfo";
        String URL = "http://192.168.1.147/PayZanAPI/api/AgentRequestInfo/AddUpdateAgentRequestInfo";*/
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


