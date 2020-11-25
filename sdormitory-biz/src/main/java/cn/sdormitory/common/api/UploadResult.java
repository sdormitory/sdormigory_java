package cn.sdormitory.common.api;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/14 12:06
 * @version：V1.0
 */
public class UploadResult implements  java.io.Serializable{
    private boolean success;
    private String message;

    public UploadResult(){

    }

    public UploadResult(boolean success,String message){
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
