package admin1.example.com.orderdistsapp.NetEvents;

import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import admin1.example.com.orderdistsapp.DiskApplication;
import admin1.example.com.orderdistsapp.util.NetUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Created by admin
 * @Created on 2018/9/5.
 **/
public class RetrofitManager {
    private static final String TAG="RetrofitManager";

    private static RetrofitManager mApiRetrofit;
    private Retrofit mRetrofit;
    private OkHttpClient mClient;
    private IApiService mApiService;


    //缓存配置
    private Interceptor mCacheInterceptor = (Interceptor.Chain chain) -> {

        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
        }
        CacheControl cacheControl = cacheBuilder.build();

        Request request = chain.request();
        if (!NetUtil.isNetworkAvailable(DiskApplication.getContext())) {
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetUtil.isNetworkAvailable(DiskApplication.getContext())) {
            int maxAge = 0; // read from cache
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };

    /**
     * 请求访问quest和response拦截器
     */
    private Interceptor mLogInterceptor = chain -> {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.e(TAG, "----------Request Start----------------");
        Log.e(TAG, "| " + request.toString());
        //Log.json("| Response:" + content);
        Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    };

    /**
     * 增加头部信息的拦截器
     */
    private Interceptor mHeaderInterceptor = chain -> {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.108 Safari/537.36 2345Explorer/8.0.0.13547");
        builder.addHeader("Cache-Control", "max-age=0");
        builder.addHeader("Upgrade-Insecure-Requests", "1");
        builder.addHeader("X-Requested-With", "XMLHttpRequest");
        builder.addHeader("Cookie", "uuid=\"w:f2e0e469165542f8a3960f67cb354026\"; __tasessionId=4p6q77g6q1479458262778; csrftoken=7de2dd812d513441f85cf8272f015ce5; tt_webid=36385357187");
        return chain.proceed(builder.build());
    };


    public RetrofitManager() {
        //cache url
        File httpCacheDirectory = new File(DiskApplication.getContext().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        mClient = new OkHttpClient.Builder()
                .addInterceptor(mHeaderInterceptor)//添加头部信息拦截器
                .addInterceptor(mLogInterceptor)//添加log拦截器
                .cache(cache)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Api.LOGIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .build();

        mApiService = mRetrofit.create(IApiService.class);
    }


    public static RetrofitManager getInstance() {
        if (mApiRetrofit == null) {
            synchronized (Object.class) {
                if (mApiRetrofit == null) {
                    mApiRetrofit = new RetrofitManager();
                }
            }
        }
        return mApiRetrofit;
    }

    public IApiService getApiService() {
        return mApiService;
    }


}
