package admin1.example.com.orderdistsapp.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import org.greenrobot.eventbus.Subscribe;

import admin1.example.com.orderdistsapp.R;
import admin1.example.com.orderdistsapp.base.BaseActivity;
import admin1.example.com.orderdistsapp.eventbus.EventBusUtils;
import admin1.example.com.orderdistsapp.eventbus.EventContants;
import admin1.example.com.orderdistsapp.eventbus.IntentToNextFragmentEvent;
import admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment.FullyInfoFragment;
import admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment.RegistContainerFragment;
import me.ele.uetool.UETool;

/**
 * @Created by admin
 * @Created on 2018/9/12.
 **/
public class RegistActivity extends BaseActivity {

    private RegistContainerFragment registContainerFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBusUtils.register(this);

        UETool.showUETMenu(1000);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_regist);
        registContainerFragment = new RegistContainerFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rl_content, registContainerFragment)
                .commit();
    }

    public void setNextFragment() {
        registContainerFragment.getFragmentList();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.rl_content, new FullyInfoFragment());
        ft.commit();
    }


    @Subscribe
    public void onEventNext(IntentToNextFragmentEvent intentToNextFragmentEvent) {
        if (intentToNextFragmentEvent.eventType.equals(EventContants.NEXTFRAGMENT)) {
            setNextFragment();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }
}

