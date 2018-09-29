package admin1.example.com.orderdistsapp.interactor.regist;

import java.util.ArrayList;
import java.util.List;

import admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment.BaseFragment;
import admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment.FullyInfoFragment;
import admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment.RegistResultFragment;
import admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment.VertifyPhoneFragment;

/**
 * @Created by admin
 * @Created on 2018/9/13.
 **/
public class RegistInteractorImpl implements RegistInteractor{

   private ArrayList<BaseFragment> regFragMentsList = new ArrayList<>();

    @Override
    public List<BaseFragment> getRegistFragment() {

        regFragMentsList.add(new VertifyPhoneFragment());
        regFragMentsList.add(new FullyInfoFragment());
        regFragMentsList.add(new RegistResultFragment());

        return regFragMentsList;
    }
}
