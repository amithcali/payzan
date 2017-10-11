package calibrage.payzan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Calibrage11 on 10/3/2017.
 */



    public class LoginResponseModel {

    @SerializedName("statusCode")
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

        @SerializedName("firstName")
        @Expose
        private Object firstName;
        @SerializedName("middleName")
        @Expose
        private Object middleName;
        @SerializedName("lastName")
        @Expose
        private Object lastName;
        @SerializedName("contactNumber")
        @Expose
        private String contactNumber;
        @SerializedName("mobileNumber")
        @Expose
        private Object mobileNumber;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("active")
        @Expose
        private Boolean active;
        @SerializedName("createdBy")
        @Expose
        private Object createdBy;
        @SerializedName("createdDate")
        @Expose
        private Object createdDate;
        @SerializedName("lastLoginDate")
        @Expose
        private Object lastLoginDate;
        @SerializedName("lastLogoutDate")
        @Expose
        private Object lastLogoutDate;
        @SerializedName("countryCreatedByUserId")
        @Expose
        private Object countryCreatedByUserId;
        @SerializedName("countryUpdatedByUserId")
        @Expose
        private Object countryUpdatedByUserId;
        @SerializedName("stateCreatedByUserId")
        @Expose
        private Object stateCreatedByUserId;
        @SerializedName("stateUpdatedByUserId")
        @Expose
        private Object stateUpdatedByUserId;
        @SerializedName("districtCreatedByUserId")
        @Expose
        private Object districtCreatedByUserId;
        @SerializedName("districtUpdatedByUserId")
        @Expose
        private Object districtUpdatedByUserId;
        @SerializedName("mandalCreatedByUserId")
        @Expose
        private Object mandalCreatedByUserId;
        @SerializedName("mandalUpdatedByUserId")
        @Expose
        private Object mandalUpdatedByUserId;
        @SerializedName("villageCreatedByUserId")
        @Expose
        private Object villageCreatedByUserId;
        @SerializedName("villageUpdatedByUserId")
        @Expose
        private Object villageUpdatedByUserId;
        @SerializedName("classTypeCreatedByUserId")
        @Expose
        private Object classTypeCreatedByUserId;
        @SerializedName("classTypeUpdatedByUserId")
        @Expose
        private Object classTypeUpdatedByUserId;
        @SerializedName("typeCdDmtCreatedByUserId")
        @Expose
        private Object typeCdDmtCreatedByUserId;
        @SerializedName("typeCdDmtUpdatedByUserId")
        @Expose
        private Object typeCdDmtUpdatedByUserId;
        @SerializedName("bankCreatedByUserId")
        @Expose
        private Object bankCreatedByUserId;
        @SerializedName("bankUpdatedByUserId")
        @Expose
        private Object bankUpdatedByUserId;
        @SerializedName("regionCreatedByUserId")
        @Expose
        private Object regionCreatedByUserId;
        @SerializedName("regionUpdatedByUserId")
        @Expose
        private Object regionUpdatedByUserId;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("normalizedUserName")
        @Expose
        private String normalizedUserName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("normalizedEmail")
        @Expose
        private String normalizedEmail;
        @SerializedName("emailConfirmed")
        @Expose
        private Boolean emailConfirmed;
        @SerializedName("passwordHash")
        @Expose
        private String passwordHash;
        @SerializedName("securityStamp")
        @Expose
        private String securityStamp;
        @SerializedName("concurrencyStamp")
        @Expose
        private String concurrencyStamp;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("phoneNumberConfirmed")
        @Expose
        private Boolean phoneNumberConfirmed;
        @SerializedName("twoFactorEnabled")
        @Expose
        private Boolean twoFactorEnabled;
        @SerializedName("lockoutEnd")
        @Expose
        private Object lockoutEnd;
        @SerializedName("lockoutEnabled")
        @Expose
        private Boolean lockoutEnabled;
        @SerializedName("accessFailedCount")
        @Expose
        private Integer accessFailedCount;

        public Object getFirstName() {
            return firstName;
        }

        public void setFirstName(Object firstName) {
            this.firstName = firstName;
        }

        public Object getMiddleName() {
            return middleName;
        }

        public void setMiddleName(Object middleName) {
            this.middleName = middleName;
        }

        public Object getLastName() {
            return lastName;
        }

        public void setLastName(Object lastName) {
            this.lastName = lastName;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public Object getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(Object mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
        }

        public Object getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Object createdDate) {
            this.createdDate = createdDate;
        }

        public Object getLastLoginDate() {
            return lastLoginDate;
        }

        public void setLastLoginDate(Object lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
        }

        public Object getLastLogoutDate() {
            return lastLogoutDate;
        }

        public void setLastLogoutDate(Object lastLogoutDate) {
            this.lastLogoutDate = lastLogoutDate;
        }

        public Object getCountryCreatedByUserId() {
            return countryCreatedByUserId;
        }

        public void setCountryCreatedByUserId(Object countryCreatedByUserId) {
            this.countryCreatedByUserId = countryCreatedByUserId;
        }

        public Object getCountryUpdatedByUserId() {
            return countryUpdatedByUserId;
        }

        public void setCountryUpdatedByUserId(Object countryUpdatedByUserId) {
            this.countryUpdatedByUserId = countryUpdatedByUserId;
        }

        public Object getStateCreatedByUserId() {
            return stateCreatedByUserId;
        }

        public void setStateCreatedByUserId(Object stateCreatedByUserId) {
            this.stateCreatedByUserId = stateCreatedByUserId;
        }

        public Object getStateUpdatedByUserId() {
            return stateUpdatedByUserId;
        }

        public void setStateUpdatedByUserId(Object stateUpdatedByUserId) {
            this.stateUpdatedByUserId = stateUpdatedByUserId;
        }

        public Object getDistrictCreatedByUserId() {
            return districtCreatedByUserId;
        }

        public void setDistrictCreatedByUserId(Object districtCreatedByUserId) {
            this.districtCreatedByUserId = districtCreatedByUserId;
        }

        public Object getDistrictUpdatedByUserId() {
            return districtUpdatedByUserId;
        }

        public void setDistrictUpdatedByUserId(Object districtUpdatedByUserId) {
            this.districtUpdatedByUserId = districtUpdatedByUserId;
        }

        public Object getMandalCreatedByUserId() {
            return mandalCreatedByUserId;
        }

        public void setMandalCreatedByUserId(Object mandalCreatedByUserId) {
            this.mandalCreatedByUserId = mandalCreatedByUserId;
        }

        public Object getMandalUpdatedByUserId() {
            return mandalUpdatedByUserId;
        }

        public void setMandalUpdatedByUserId(Object mandalUpdatedByUserId) {
            this.mandalUpdatedByUserId = mandalUpdatedByUserId;
        }

        public Object getVillageCreatedByUserId() {
            return villageCreatedByUserId;
        }

        public void setVillageCreatedByUserId(Object villageCreatedByUserId) {
            this.villageCreatedByUserId = villageCreatedByUserId;
        }

        public Object getVillageUpdatedByUserId() {
            return villageUpdatedByUserId;
        }

        public void setVillageUpdatedByUserId(Object villageUpdatedByUserId) {
            this.villageUpdatedByUserId = villageUpdatedByUserId;
        }

        public Object getClassTypeCreatedByUserId() {
            return classTypeCreatedByUserId;
        }

        public void setClassTypeCreatedByUserId(Object classTypeCreatedByUserId) {
            this.classTypeCreatedByUserId = classTypeCreatedByUserId;
        }

        public Object getClassTypeUpdatedByUserId() {
            return classTypeUpdatedByUserId;
        }

        public void setClassTypeUpdatedByUserId(Object classTypeUpdatedByUserId) {
            this.classTypeUpdatedByUserId = classTypeUpdatedByUserId;
        }

        public Object getTypeCdDmtCreatedByUserId() {
            return typeCdDmtCreatedByUserId;
        }

        public void setTypeCdDmtCreatedByUserId(Object typeCdDmtCreatedByUserId) {
            this.typeCdDmtCreatedByUserId = typeCdDmtCreatedByUserId;
        }

        public Object getTypeCdDmtUpdatedByUserId() {
            return typeCdDmtUpdatedByUserId;
        }

        public void setTypeCdDmtUpdatedByUserId(Object typeCdDmtUpdatedByUserId) {
            this.typeCdDmtUpdatedByUserId = typeCdDmtUpdatedByUserId;
        }

        public Object getBankCreatedByUserId() {
            return bankCreatedByUserId;
        }

        public void setBankCreatedByUserId(Object bankCreatedByUserId) {
            this.bankCreatedByUserId = bankCreatedByUserId;
        }

        public Object getBankUpdatedByUserId() {
            return bankUpdatedByUserId;
        }

        public void setBankUpdatedByUserId(Object bankUpdatedByUserId) {
            this.bankUpdatedByUserId = bankUpdatedByUserId;
        }

        public Object getRegionCreatedByUserId() {
            return regionCreatedByUserId;
        }

        public void setRegionCreatedByUserId(Object regionCreatedByUserId) {
            this.regionCreatedByUserId = regionCreatedByUserId;
        }

        public Object getRegionUpdatedByUserId() {
            return regionUpdatedByUserId;
        }

        public void setRegionUpdatedByUserId(Object regionUpdatedByUserId) {
            this.regionUpdatedByUserId = regionUpdatedByUserId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNormalizedUserName() {
            return normalizedUserName;
        }

        public void setNormalizedUserName(String normalizedUserName) {
            this.normalizedUserName = normalizedUserName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNormalizedEmail() {
            return normalizedEmail;
        }

        public void setNormalizedEmail(String normalizedEmail) {
            this.normalizedEmail = normalizedEmail;
        }

        public Boolean getEmailConfirmed() {
            return emailConfirmed;
        }

        public void setEmailConfirmed(Boolean emailConfirmed) {
            this.emailConfirmed = emailConfirmed;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        public String getSecurityStamp() {
            return securityStamp;
        }

        public void setSecurityStamp(String securityStamp) {
            this.securityStamp = securityStamp;
        }

        public String getConcurrencyStamp() {
            return concurrencyStamp;
        }

        public void setConcurrencyStamp(String concurrencyStamp) {
            this.concurrencyStamp = concurrencyStamp;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Boolean getPhoneNumberConfirmed() {
            return phoneNumberConfirmed;
        }

        public void setPhoneNumberConfirmed(Boolean phoneNumberConfirmed) {
            this.phoneNumberConfirmed = phoneNumberConfirmed;
        }

        public Boolean getTwoFactorEnabled() {
            return twoFactorEnabled;
        }

        public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
            this.twoFactorEnabled = twoFactorEnabled;
        }

        public Object getLockoutEnd() {
            return lockoutEnd;
        }

        public void setLockoutEnd(Object lockoutEnd) {
            this.lockoutEnd = lockoutEnd;
        }

        public Boolean getLockoutEnabled() {
            return lockoutEnabled;
        }

        public void setLockoutEnabled(Boolean lockoutEnabled) {
            this.lockoutEnabled = lockoutEnabled;
        }

        public Integer getAccessFailedCount() {
            return accessFailedCount;
        }

        public void setAccessFailedCount(Integer accessFailedCount) {
            this.accessFailedCount = accessFailedCount;
        }

    }

}