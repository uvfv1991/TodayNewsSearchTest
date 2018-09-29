package admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.squareup.leakcanary.RefWatcher;

import admin1.example.com.orderdistsapp.DiskApplication;
import butterknife.ButterKnife;

/**
 * @Created by admin
 * @Created on 2018/9/13.
 **/
public class BaseFragment extends Fragment {

    Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext= getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = DiskApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
