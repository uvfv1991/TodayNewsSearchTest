package admin1.example.com.orderdistsapp.NetEvents.exception;

/**
 * 约定异常信息
 */

public class ErrorMessage {
    /**
     * 鉴权失败
     */
    static final String UNAUTHORIZED= "当前请求需要用户验证";
    /**
     * 鉴权失败
     */
    static final String FORBIDDEN= "服务器已经理解请求，但是拒绝执行它";

    /**
     * 鉴权失败
     */
    static final String NOT_FOUND= "服务器异常，请稍后再试";


    /**
     * 请求超时
     */
    static final String REQUEST_TIMEOUT= "请求超时";


    /**
     * 鉴权失败
     */
    static final String  GATEWAY_TIMEOUT= "作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS）收到响应";



    /**
     * 鉴权失败
     */
    static final String INTERNAL_SERVER_ERROR= "当前请求需要用户验证";



    /**
     * 鉴权失败
     */
    static final String BAD_GATEWAY= "作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应";



    /**
     * 鉴权失败
     */
    static final String SERVICE_UNAVAILABLE= "当前请求需要用户验证";



    /**
     * 鉴权失败
     */
    static final String Default= "网络错误";


    /**
     * 解析错误
     */
    static final String  PARSE_ERROR= "解析错误";


    /**
     * 连接错误
     */
    static final String  CONNECT_ERROR= "连接失败";



    /**
     * 未知错误
     */
    static final String  UNKNOWN_ERROR= "未知失败";





}
