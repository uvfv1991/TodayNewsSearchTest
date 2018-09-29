package admin1.example.com.orderdistsapp.NetEvents.exception;

import android.net.ParseException;
import com.google.gson.JsonParseException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import retrofit2.HttpException;

/**
 * @Created by admin
 * @Created on 2018/9/11.
 **/
public class ExceptionEngine {

    /*
    * HTTP常用返回的错误值
    * */
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e){
        ApiException ex;
        if (e instanceof HttpException){             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ErrorType.HTTP_ERROR);
            switch(httpException.code()){
                case UNAUTHORIZED:
                    ex.message = ErrorMessage.UNAUTHORIZED;
                    break;
                case FORBIDDEN:
                    ex.message = ErrorMessage.FORBIDDEN;
                    break;
                case NOT_FOUND:
                    ex.message = ErrorMessage.NOT_FOUND;
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = ErrorMessage.REQUEST_TIMEOUT;
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message = ErrorMessage.GATEWAY_TIMEOUT;
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = ErrorMessage.INTERNAL_SERVER_ERROR;
                    break;
                case BAD_GATEWAY:
                    ex.message = ErrorMessage.BAD_GATEWAY;
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = ErrorMessage.SERVICE_UNAVAILABLE;
                    break;
                default:
                    ex.message = ErrorMessage.Default;  //其它均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof ServerException){    //服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException){
            ex = new ApiException(e, ErrorType.PARSE_ERROR);
            ex.message =  ErrorMessage.PARSE_ERROR; //均视为解析错误
            return ex;
        }else if(e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof ConnectTimeoutException){
            ex = new ApiException(e, ErrorType.NETWORD_ERROR);
            ex.message = ErrorMessage.CONNECT_ERROR;  //均视为网络错误
            return ex;
        }
        else {
            ex = new ApiException(e, ErrorType.UNKNOWN);
            ex.message = ErrorMessage.UNKNOWN_ERROR;          //未知错误
            return ex;
        }
    }
}
