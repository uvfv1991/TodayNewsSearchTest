package admin1.example.com.orderdistsapp.NetEvents.exception;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class ErrorTransformer<T> implements ObservableTransformer<HttpResult<T>, T>{


    public static <T> ErrorTransformer<T> create() {
        return new ErrorTransformer<>();
    }

    private static ErrorTransformer instance = null;

    private ErrorTransformer(){
    }
    /**
     * 双重校验锁单例(线程安全)
     */
    public static <T>ErrorTransformer<T> getInstance() {
        if (instance == null) {
            synchronized (ErrorTransformer.class) {
                if (instance == null) {
                    instance = new ErrorTransformer<>();
                }
            }
        }
        return instance;
    }

    @Override
    public ObservableSource<T> apply(Observable<HttpResult<T>> upstream) {

        return upstream.map(new Function<HttpResult<T>, T>() {
            @Override
            public T apply(HttpResult<T> tHttpResult) throws Exception {
                // 通过对返回状态值进行业务判断决定是返回错误还是正常取数据
                if (!tHttpResult.getState().equals("success"))
                    throw new ServerException(tHttpResult.getState(), -1);
                return tHttpResult.data;
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
            @Override
            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
                //ExceptionEngine为处理异常的驱动器
                throwable.printStackTrace();
                return Observable.error(ExceptionEngine.handleException(throwable));
            }
        });
    }
}