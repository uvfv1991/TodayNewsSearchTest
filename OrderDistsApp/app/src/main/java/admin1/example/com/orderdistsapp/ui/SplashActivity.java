package admin1.example.com.orderdistsapp.ui;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import admin1.example.com.orderdistsapp.R;
import admin1.example.com.orderdistsapp.base.BaseActivity;

public final class SplashActivity extends BaseActivity {
    private Handler mHandler;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);
        this.initData();
        this.IntentToHome();
    }

    private final void initData () {
        this.mHandler = new Handler ();
    }

    private final void IntentToHome () {
    }

}