package admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import admin1.example.com.orderdistsapp.Interfact.CommonRegistView;
import admin1.example.com.orderdistsapp.R;
import admin1.example.com.orderdistsapp.adapter.RegistPagerAdapter;
import admin1.example.com.orderdistsapp.present.regist.RegistPresent;
import admin1.example.com.orderdistsapp.present.regist.RegistPresentImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by admin
 * @Created on 2018/9/13.
 **/
public class RegistContainerFragment extends BaseFragment  implements CommonRegistView {


    @BindView(R.id.vp_input)
    ViewPager viewPager;

    private RegistPresent registPresent;
    List<BaseFragment> registFragmentList;

    private View rootView;
    private RegistPagerAdapter registPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_regist_container, container, false);
            ButterKnife.bind(this, rootView);
            initDataAndViews();

        }
        return rootView;

    }

    private void initDataAndViews() {

        registFragmentList = new ArrayList<>();
        registPresent = new RegistPresentImpl(this);
        registPresent.initViews();

    }


    @Override
    public void initPagerView(List<BaseFragment> fragmentList) {
        /*
         *将fragment与ViewPager进行绑定
         * */

        registFragmentList=fragmentList;

            try {
                if (fragmentList.size()!=0){
                    }else {
                    throw new Exception("无可初始化fragments");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        registPagerAdapter= new RegistPagerAdapter(getChildFragmentManager(),fragmentList);
        viewPager.setAdapter(registPagerAdapter);



        //viewPager.setCurrentItem(1);

    }

    public List<BaseFragment> getFragmentList() {

    return registFragmentList;
    }
}
