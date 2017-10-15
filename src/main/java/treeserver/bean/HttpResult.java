package treeserver.bean;

/**
 * Created by kevinyin on 2017/10/15.
 */
public class HttpResult {
    private boolean ret;
    private String message;
    private int code;
    private Object data;

    public HttpResult(boolean ret, String message, int code) {
        this.ret = ret;
        this.message = message;
        this.code = code;
    }

    public HttpResult(boolean ret, String message, int code, Object data) {
        this.ret = ret;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static HttpResult success(Object data){
        return new HttpResult(true,"success",200,data);
    }

    public static HttpResult success(){
        return new HttpResult(true,"success",200);
    }

    public static HttpResult error(String message){
        return error(message,403);
    }

    public static HttpResult error(String message,int code) {
        return new HttpResult(false,message,code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
