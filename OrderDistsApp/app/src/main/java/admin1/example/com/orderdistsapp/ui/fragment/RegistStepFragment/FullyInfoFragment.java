package admin1.example.com.orderdistsapp.ui.fragment.RegistStepFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import admin1.example.com.orderdistsapp.R;
import admin1.example.com.orderdistsapp.dialog.registe.SelectDialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by admin
 * @Created on 2018/9/12.
 **/
public class FullyInfoFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.et_sel_indu)
    EditText et_sel_indu;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_fully_info, container, false);
            ButterKnife.bind(this, rootView);
            initListener();
            initViews();

        }
        return rootView;
    }


    private void initListener() {

        et_sel_indu.setOnClickListener(this);

    }
    private void initViews() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.et_sel_indu:
                showSelDiaLog();
                break;

        }
    }

    private void showSelDiaLog() {

        FragmentManager fragmentManager = getFragmentManager();
        SelectDialogFragment selectDialogFragment = new SelectDialogFragment();
        selectDialogFragment.show(fragmentManager,"selectIndu");
    }
}
