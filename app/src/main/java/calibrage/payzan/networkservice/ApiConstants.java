package calibrage.payzan.networkservice;

/**
 * Created by Calibrage11 on 9/8/2017.
 */

public interface ApiConstants {
    String REGISTER = "api/Register/Register";
    String LOGIN = "api/Register/Login";
    String STATES ="api/States/GetStates/";
    String DISTRICTS ="api/Districts/GetDistricts/";
    String MANDALS ="api/Mandals/GetMandals/";
    String VILLAGE ="api/Villages/GetVillages/";
    String WALLET ="api/UserWallet/AddMoneyToUserWallet";
    String MOBILE_SERVICES = "api/ServiceProvider/GetServiceProvidersByServiceType/";
    String AGENT_REQUEST = "api/AgentRequestInfo/AddUpdateAgentRequestInfo";
    String SEND_MONEY_WALLET = "api/UserWallet/SendMoneyToUserWallet";
    String PASSBOOK = "/api/UserWallet/GetPassbookDetails/";
    String CHANGE_PASSWORD = "/api/Register/ChangePassword";
  //  String LOGIN = "API
}
