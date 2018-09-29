package admin1.example.com.orderdistsapp.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import admin1.example.com.orderdistsapp.R;
import admin1.example.com.orderdistsapp.bean.User;
import admin1.example.com.orderdistsapp.databinding.ActivityMainBinding;

public class MainActivity extends Activity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //首先要在你要接受EventBus的界面注册，这一步很重要
        EventBus.getDefault().register(this);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User("Test", "User");
        activityMainBinding.setUser(user);
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在界面销毁的地方要解绑
        EventBus.getDefault().unregister(this);
    }
}
