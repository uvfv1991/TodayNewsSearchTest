package admin1.example.com.orderdistsapp.present.regist;

import android.text.Editable;

import java.util.ArrayList;
import java.util.List;

import admin1.example.com.orderdistsapp.Interfact.CommonRegistView;
import admin1.example.com.orderdistsapp.interactor.regist.RegistInteractor;
import admin1.example.com.orderdistsapp.interactor.regist.RegistInteractorImpl;
import admin1.example.com.orderdistsapp.present.login.BasePresent;
import admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment.BaseFragment;

/**
 * @Created by admin
 * @Created on 2018/9/13.
 **/
public class RegistPresentImpl extends RegistPresent{

    private RegistInteractor registInteractor;
    private CommonRegistView commonRegistView;
    List<BaseFragment> registFragment;


    public RegistPresentImpl(CommonRegistView commonRegistView) {
        this.commonRegistView = commonRegistView;
    }

    @Override
    public void initViews() {

        registInteractor= new RegistInteractorImpl();
        registFragment= new ArrayList<BaseFragment>();
        registInteractor.getRegistFragment();
        commonRegistView.initPagerView(registInteractor.getRegistFragment());

    }


}
