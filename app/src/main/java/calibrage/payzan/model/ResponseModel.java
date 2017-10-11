package calibrage.payzan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Calibrage11 on 10/3/2017.
 */



    public class ResponseModel {

        @SerializedName("statusCode")
        @Expose
        private Integer statusCode;
        @SerializedName("statusMessage")
        @Expose
        private String statusMessage;

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusMessage() {
            return statusMessage;
        }

        public void setStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
        }

    }

