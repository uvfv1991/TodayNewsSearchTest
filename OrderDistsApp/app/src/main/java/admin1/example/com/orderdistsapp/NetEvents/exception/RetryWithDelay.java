package admin1.example.com.orderdistsapp.NetEvents.exception;

import android.util.Log;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/*
* 发生错误重试
* */

public class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>>{

    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }


    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        return observable
                .flatMap(new Function<Throwable, Observable<Long>>() {
                    @Override
                    public Observable<Long> apply(Throwable throwable) throws Exception {

                        if (throwable instanceof UnknownHostException) {
                            //若没打开网络则停止重试
                            return Observable.error(throwable);
                        } else if (throwable instanceof NullPointerException)
                            Log.i("v", "call: Time:" + new Date(System.currentTimeMillis()) + " thread:" + Thread.currentThread().getName());

                        //重试三次
                        if (++retryCount < 3)
                            return Observable.timer(5, TimeUnit.SECONDS);
                        else
                            return Observable.error(new IllegalArgumentException("超过最大次数"));//超过最大次数终止


                    }});
    }
}


