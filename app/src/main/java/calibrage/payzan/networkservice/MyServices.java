package calibrage.payzan.networkservice;

import com.google.gson.JsonObject;


import calibrage.payzan.model.DistrictModel;
import calibrage.payzan.model.LoginResponseModel;
import calibrage.payzan.model.MandalModel;
import calibrage.payzan.model.OperatorModel;
import calibrage.payzan.model.SendMoneyResponseModel;
import calibrage.payzan.model.StatesModel;
import calibrage.payzan.model.VillageModel;
import calibrage.payzan.model.WalletResponse;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;


import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Calibrage11 on 9/8/2017.
 */

public interface MyServices {
    // @Headers("Accept: application/json")
    @POST(ApiConstants.REGISTER)
    Observable<calibrage.payzan.model.ResponseModel> userRegister(@Body JsonObject data);

    @POST(ApiConstants.LOGIN)
    Observable<LoginResponseModel> UserLogin(@Body JsonObject data);

    @POST(ApiConstants.WALLET)
    Observable<WalletResponse> UserAddWallet(@Body JsonObject data);

    @GET
    Observable<StatesModel> getStates(@Url String url);

    @GET
    Observable<DistrictModel> getDistricts(@Url String url);

    @GET
    Observable<MandalModel> getMandals(@Url String url);

    @GET
    Observable<VillageModel> getVillages(@Url String url);

    @GET
    Observable<OperatorModel> getOperator(@Url String url);

    @POST(ApiConstants.AGENT_REQUEST)
    Observable<calibrage.payzan.model.AgentResponseModel> agentRequest(@Body JsonObject data);

    @POST(ApiConstants.SEND_MONEY_WALLET)
    Observable<SendMoneyResponseModel> sendMoneyRequest(@Body JsonObject data);


}
