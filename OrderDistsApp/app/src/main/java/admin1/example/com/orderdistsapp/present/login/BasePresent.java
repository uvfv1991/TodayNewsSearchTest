package admin1.example.com.orderdistsapp.present.login;

import android.text.Editable;

import admin1.example.com.orderdistsapp.NetEvents.IApiService;
import admin1.example.com.orderdistsapp.NetEvents.RetrofitManager;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Created by admin
 * @Created on 2018/9/7.
 **/
public abstract class BasePresent<V> {

    protected IApiService mRetrofitService = RetrofitManager.getInstance().getApiService();
    private CompositeDisposable mCompositeDisposable;


    private V mView;

    public BasePresent(V view) {
        attachView(view);
    }

    protected BasePresent() {
    }

    private void attachView(V view) {
        mView = view;
        
    }

    public void detachView() {
        mView = null;
        unDispose();

    }


    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    //在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
    public void unDispose() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public abstract void init(Editable text, Editable et_usernameText, Editable et_pwdText);

}
