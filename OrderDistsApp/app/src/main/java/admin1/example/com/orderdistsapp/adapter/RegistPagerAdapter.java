package admin1.example.com.orderdistsapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment.BaseFragment;

/**
 * @Created by admin
 * @Created on 2018/9/13.
 **/
public class RegistPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> mfragmentList;

    public RegistPagerAdapter(FragmentManager fm,List<BaseFragment> fragmentList) {
        super(fm);
        if (fragmentList != null){
            mfragmentList= fragmentList;
        }else {
            mfragmentList = new ArrayList<BaseFragment>();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.size()!=0?mfragmentList.get(position):null;
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }
}
