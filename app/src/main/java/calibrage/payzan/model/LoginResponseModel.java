package calibrage.payzan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Calibrage11 on 10/3/2017.
 */


public class LoginResponseModel {

    @SerializedName("Result")
    @Expose
    private Data data;
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("AffectedRecords")
    @Expose
    private Integer affectedRecords;
    @SerializedName("EndUserMessage")
    @Expose
    private String endUserMessage;
    @SerializedName("ValidationErrors")
    @Expose
    private List<Object> validationErrors = null;
    @SerializedName("Exception")
    @Expose
    private Object exception;

    public Data getdata() {
        return data;
    }

    public void setResult(Data result) {
        this.data = result;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getAffectedRecords() {
        return affectedRecords;
    }

    public void setAffectedRecords(Integer affectedRecords) {
        this.affectedRecords = affectedRecords;
    }

    public String getEndUserMessage() {
        return endUserMessage;
    }

    public void setEndUserMessage(String endUserMessage) {
        this.endUserMessage = endUserMessage;
    }

    public List<Object> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<Object> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

   public class Data {

        @SerializedName("User")
        @Expose
        private User user;
        @SerializedName("Roles")
        @Expose
        private List<Object> roles = null;
        @SerializedName("ActivityRights")
        @Expose
        private List<Object> activityRights = null;
        @SerializedName("UserWallet")
        @Expose
        private Object userWallet;
        @SerializedName("AccessToken")
        @Expose
        private String accessToken;
        @SerializedName("ExpiresIn")
        @Expose
        private Integer expiresIn;
        @SerializedName("TokenType")
        @Expose
        private String tokenType;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public List<Object> getRoles() {
            return roles;
        }

        public void setRoles(List<Object> roles) {
            this.roles = roles;
        }

        public List<Object> getActivityRights() {
            return activityRights;
        }

        public void setActivityRights(List<Object> activityRights) {
            this.activityRights = activityRights;
        }

        public Object getUserWallet() {
            return userWallet;
        }

        public void setUserWallet(Object userWallet) {
            this.userWallet = userWallet;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Integer getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(Integer expiresIn) {
            this.expiresIn = expiresIn;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }
        class User {

            @SerializedName("Id")
            @Expose
            private String id;
            @SerializedName("Email")
            @Expose
            private String email;
            @SerializedName("UserName")
            @Expose
            private String userName;
            @SerializedName("PhoneNumber")
            @Expose
            private Object phoneNumber;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public Object getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(Object phoneNumber) {
                this.phoneNumber = phoneNumber;
            }
        }
    }



}

