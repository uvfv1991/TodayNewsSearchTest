package admin1.example.com.orderdistsapp.NetEvents.exception;

/**
 * @Created by admin
 * @Created on 2018/9/11.
 **/
public class ApiException extends Exception {
    // 异常处理，为速度，不必要设置getter和setter
    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}