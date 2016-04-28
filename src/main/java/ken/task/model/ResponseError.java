package ken.task.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseError extends ModelBase {
    @SerializedName("error")
    private ErrorStructure error;

    public ErrorStructure getError() {
        return error;
    }

    public void setError(ErrorStructure error) {
        this.error = error;
    }

    public static class ErrorStructure {
        @SerializedName("errors")
        public List<ErrorItem> errors;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        @SerializedName("code")
        private int code;
        @SerializedName("message")
        private String message;

        public List<ErrorItem> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorItem> errors) {
            this.errors = errors;
        }
    }

    public static class ErrorItem {

        public ErrorItem(String reason, String message){
            this.reason = reason;
            this.message = message;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @SerializedName("reason")
        private String reason;
        @SerializedName("message")
        private String message;
    }

}
