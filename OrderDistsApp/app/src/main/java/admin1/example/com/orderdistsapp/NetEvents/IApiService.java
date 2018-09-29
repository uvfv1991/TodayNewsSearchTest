package admin1.example.com.orderdistsapp.NetEvents;

import java.util.List;

import admin1.example.com.orderdistsapp.NetEvents.exception.HttpResult;
import admin1.example.com.orderdistsapp.bean.LoginResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Created by admin
 * @Created on 2018/9/7.
 **/
public interface IApiService {


    /**
     * 获取登陆返回值
     *
     * @return
     */
    @GET("com.share.jack.servlet/LoginServlet")
    Observable<HttpResult<LoginResponse>> getLoginResponse(@Query("phone") String phone, @Query("username") String username, @Query("password") String password);

}
