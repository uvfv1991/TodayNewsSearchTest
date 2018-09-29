package admin1.example.com.orderdistsapp.interactor.regist;

import java.util.List;

import admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment.BaseFragment;

/**
 * @Created by admin
 * @Created on 2018/9/13.
 **/
public interface RegistInteractor {
    /*
    * 注册界面的三个fragments
    *
    * Interactor 和 interface区别
    *
    * 同样是接口，Interactor用于处理界面所需要的数据，Fragment,tab数据等等
    *
    * interfact 无返回值  但有参数，对其填入需要的参数来完成操作
    * */
     List<BaseFragment> getRegistFragment();
}
