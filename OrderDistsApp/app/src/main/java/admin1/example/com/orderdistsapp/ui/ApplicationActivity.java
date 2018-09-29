package admin1.example.com.orderdistsapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import admin1.example.com.orderdistsapp.base.BaseActivity;
import admin1.example.com.orderdistsapp.constant.UIConstant;
import admin1.example.com.orderdistsapp.util.PreUtil;

public class ApplicationActivity extends BaseActivity{

    private boolean isFirst = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initAction();
    }


    private void initData() {

        isFirst = PreUtil.getBoolean(UIConstant.isFirst, false);
    }

    private void initAction() {

        if (isFirst) {

            readyGoThenKill(SplashActivity.class);
            PreUtil.putBoolean(UIConstant.isFirst, false);
        } else {
            readyGoThenKill(LoginActivity.class);
        }
    }
}
