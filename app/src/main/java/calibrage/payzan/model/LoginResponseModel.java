package calibrage.payzan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Calibrage11 on 10/3/2017.
 */



    public class LoginResponseModel {

    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("User")
        @Expose
        private User user;
        @SerializedName("Roles")
        @Expose
        private List<Role> roles = null;
        @SerializedName("ActivityRights")
        @Expose
        private List<Object> activityRights = null;
        @SerializedName("UserWallet")
        @Expose
        private UserWallet userWallet;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

        public List<Object> getActivityRights() {
            return activityRights;
        }

        public void setActivityRights(List<Object> activityRights) {
            this.activityRights = activityRights;
        }

        public UserWallet getUserWallet() {
            return userWallet;
        }

        public void setUserWallet(UserWallet userWallet) {
            this.userWallet = userWallet;
        }

        public class User {

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
            private String phoneNumber;

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

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

        }

        public class Role {

            @SerializedName("UserId")
            @Expose
            private String userId;
            @SerializedName("RoleId")
            @Expose
            private String roleId;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }


        }

        public class UserWallet {

            @SerializedName("UserId")
            @Expose
            private String userId;
            @SerializedName("WalletId")
            @Expose
            private String walletId;
            @SerializedName("Balance")
            @Expose
            private Integer balance;
            @SerializedName("Id")
            @Expose
            private Integer id;
            @SerializedName("IsActive")
            @Expose
            private Boolean isActive;
            @SerializedName("CreatedByUserId")
            @Expose
            private String createdByUserId;
            @SerializedName("CreatedDate")
            @Expose
            private String createdDate;
            @SerializedName("UpdatedByUserId")
            @Expose
            private String updatedByUserId;
            @SerializedName("UpdatedDate")
            @Expose
            private String updatedDate;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getWalletId() {
                return walletId;
            }

            public void setWalletId(String walletId) {
                this.walletId = walletId;
            }

            public Integer getBalance() {
                return balance;
            }

            public void setBalance(Integer balance) {
                this.balance = balance;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Boolean getIsActive() {
                return isActive;
            }

            public void setIsActive(Boolean isActive) {
                this.isActive = isActive;
            }

            public String getCreatedByUserId() {
                return createdByUserId;
            }

            public void setCreatedByUserId(String createdByUserId) {
                this.createdByUserId = createdByUserId;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getUpdatedByUserId() {
                return updatedByUserId;
            }

            public void setUpdatedByUserId(String updatedByUserId) {
                this.updatedByUserId = updatedByUserId;
            }

            public String getUpdatedDate() {
                return updatedDate;
            }

            public void setUpdatedDate(String updatedDate) {
                this.updatedDate = updatedDate;
            }

        }

    }
}