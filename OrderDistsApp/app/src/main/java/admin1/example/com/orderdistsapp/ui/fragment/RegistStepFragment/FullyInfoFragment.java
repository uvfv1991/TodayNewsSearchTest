package admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mob.MobSDK;

import admin1.example.com.orderdistsapp.R;
import butterknife.ButterKnife;

/**
 * @Created by admin
 * @Created on 2018/9/12.
 **/
public class FullyInfoFragment extends BaseFragment{

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_fully_info, container, false);
            ButterKnife.bind(this, rootView);
            //initListener();
            //initDataAndViews();

        }
        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
