package cc.zenking.cloud.subojetstudy.core.response;

/**
 * @author chengjunchao
 * 返回参数
 */
public class JsonData<T> {

    public static final int CODE_SUCCESS = 0;

    private int status;
    private String reason;
    private T data;

    public String getReason() {
        return reason;
    }

    public void setReason(String msg) {
        this.reason = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int code) {
        this.status = code;
    }
}
