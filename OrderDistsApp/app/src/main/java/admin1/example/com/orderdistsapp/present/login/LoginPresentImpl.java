package admin1.example.com.orderdistsapp.present.login;

import android.text.Editable;
import android.util.Log;

import java.util.ArrayList;
import admin1.example.com.orderdistsapp.NetEvents.exception.DefaultTransformer;
import admin1.example.com.orderdistsapp.bean.LoginResponse;
import admin1.example.com.orderdistsapp.view.ILoginview;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Created by admin
 * @Created on 2018/9/7.
 **/
public class LoginPresentImpl extends BasePresent {

    private ILoginview iLoginview;
    private ArrayList<LoginResponse> loginResponses;

    public LoginPresentImpl(ILoginview view) {
        super(view);

        this.iLoginview = view;
        loginResponses = new ArrayList<>();
    }


    public void init(Editable phone, Editable username, Editable pwd) {

        mRetrofitService.getLoginResponse(phone.toString(),username.toString(), pwd.toString())
                //增加异常控制
                .compose(new DefaultTransformer<LoginResponse>())
                .subscribe(new Observer<LoginResponse>() {// 第二步：初始化Observer
                    @Override
                    public void onSubscribe(Disposable d) {

                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LoginResponse loginResponses) {
                        Log.i("--onNext", "loginResponse" + loginResponses.toString());

                        iLoginview.sucessLogin();
                        //unDispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("--onError", e.toString());
                        iLoginview.failLogin();
                    }

                    @Override
                    public void onComplete() {
                        Log.i("--onComplete","");

                    }
                });

    }
}
