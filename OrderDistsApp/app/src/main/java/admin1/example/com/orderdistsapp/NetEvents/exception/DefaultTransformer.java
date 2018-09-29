package admin1.example.com.orderdistsapp.NetEvents.exception;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;


//抽出公用设置

public class DefaultTransformer<T>
        implements ObservableTransformer<HttpResult<T>, T> {
    @Override
    public ObservableSource<T> apply(Observable<HttpResult<T>> upstream) {

        return upstream
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.schedulers.Schedulers.newThread())
                //统一做不同类型异常处理
                .compose(ErrorTransformer.<T>getInstance())
                .observeOn(AndroidSchedulers.mainThread());
    }
}