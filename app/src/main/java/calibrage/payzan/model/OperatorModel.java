package calibrage.payzan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Calibrage11 on 10/16/2017.
 */

public class OperatorModel {

    @SerializedName("ListResult")
    @Expose
    private List<ListResult> listResult = null;
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

    public List<ListResult> getListResult() {
        return listResult;
    }

    public void setListResult(List<ListResult> listResult) {
        this.listResult = listResult;
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

    public class ListResult {

        @SerializedName("ServiceProviderName")
        @Expose
        private String serviceProviderName;
        @SerializedName("ServiceProviderId")
        @Expose
        private Integer serviceProviderId;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("ClassTypeId")
        @Expose
        private Integer classTypeId;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("TableName")
        @Expose
        private Object tableName;
        @SerializedName("ColumnName")
        @Expose
        private Object columnName;
        @SerializedName("SortOrder")
        @Expose
        private Object sortOrder;
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;
        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;
        @SerializedName("Created")
        @Expose
        private String created;
        @SerializedName("ModifiedBy")
        @Expose
        private String modifiedBy;
        @SerializedName("Modified")
        @Expose
        private String modified;

        public String getServiceProviderName() {
            return serviceProviderName;
        }

        public void setServiceProviderName(String serviceProviderName) {
            this.serviceProviderName = serviceProviderName;
        }

        public Integer getServiceProviderId() {
            return serviceProviderId;
        }

        public void setServiceProviderId(Integer serviceProviderId) {
            this.serviceProviderId = serviceProviderId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getClassTypeId() {
            return classTypeId;
        }

        public void setClassTypeId(Integer classTypeId) {
            this.classTypeId = classTypeId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getTableName() {
            return tableName;
        }

        public void setTableName(Object tableName) {
            this.tableName = tableName;
        }

        public Object getColumnName() {
            return columnName;
        }

        public void setColumnName(Object columnName) {
            this.columnName = columnName;
        }

        public Object getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(Object sortOrder) {
            this.sortOrder = sortOrder;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

    }
}
